package org.nem.monitor.ux;

import org.nem.core.async.*;
import org.nem.core.connect.*;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.monitor.*;
import org.nem.monitor.node.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;

/**
 * A builder that is used to construct the system tray icon.
 */
public class TrayIconBuilder {
	private final HttpMethodClient<ErrorResponseDeserializerUnion> client;
	private final WebStartLauncher webStartLauncher;
	private final TrayIcon trayIcon;
	private final PopupMenu popup;
	private final Collection<NodeStatusVisitor> visitors = new ArrayList<>();
	private final Collection<VisitorNodePolicyPair> visitorNodePolicyPairs = new ArrayList<>();

	/**
	 * Creates a new builder.
	 *
	 * @param client The http method client.
	 * @param webStartLauncher The web start launcher.
	 */
	public TrayIconBuilder(
			final HttpMethodClient<ErrorResponseDeserializerUnion> client,
			final WebStartLauncher webStartLauncher) {
		this.client = client;
		this.webStartLauncher = webStartLauncher;

		// initially create the tray icon around a 1x1 pixel image because it cannot be created around a null image
		this.trayIcon = new TrayIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
		this.popup = new PopupMenu();

		this.visitors.add(new NodeStatusToIconDescriptorAdapter(this::setImage));
	}

	private void setImage(final IconDescriptor descriptor) {
		final URL imageUrl = TrayIconBuilder.class.getClassLoader().getResource(descriptor.getImageFileName());
		final Image image = (new ImageIcon(imageUrl, descriptor.getDescription())).getImage();
		this.trayIcon.setImage(image);
	}

	/**
	 * Adds status menu items for the specified node policy.
	 *
	 * @param nodePolicy The node policy.
	 */
	public void addStatusMenuItems(final NemNodePolicy nodePolicy) {
		final NemNodeType nodeType = nodePolicy.getNodeType();
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(nodeType);

		this.popup.add(visitor.getStatusMenuItem());
		this.popup.add(visitor.getActionMenuItem());

		this.visitors.add(visitor);
		this.visitorNodePolicyPairs.add(new VisitorNodePolicyPair(visitor, nodePolicy));
	}

	public void addHackWebStartMenuItem(final String nemFolder, final String jnlpUrl) {
		// TODO-J: remove this hack!
		final MenuItem startMenuItem = new MenuItem("HACK: Start " + jnlpUrl);
		startMenuItem.addActionListener(e -> {
			this.webStartLauncher.launch(nemFolder, jnlpUrl);
		});

		this.popup.add(startMenuItem);
	}

	/**
	 * Adds a menu item separator.
	 */
	public void addSeparator() {
		this.popup.addSeparator();
	}

	/**
	 * Adds an exit menu item.
	 *
	 * @param tray The system tray.
	 */
	public void addExitMenuItem(final SystemTray tray) {
		final MenuItem exitItem = new MenuItem("Exit");
		this.popup.add(exitItem);
		exitItem.addActionListener(e -> {
			tray.remove(this.trayIcon);
			System.exit(0);
		});
	}

	/**
	 * Creates the tray icon and hooks up all event handlers.
	 *
	 * @return The tray icon.
	 */
	public TrayIcon create() {
		final AggregateNodeStatusVisitor visitor = new AggregateNodeStatusVisitor(this.visitors);

		for (final VisitorNodePolicyPair pair : this.visitorNodePolicyPairs) {
			final NemNodeType nodeType = pair.nodePolicy.getNodeType();
			final NemConnector connector = this.createConnector(pair.nodePolicy);
			new AsyncTimer(
					() -> connector.isRunning().thenAccept(b -> visitor.notifyStatus(nodeType, b ? NemNodeStatus.RUNNING : NemNodeStatus.STOPPED)),
					250,
					new UniformDelayStrategy(1000),
					null);

			pair.visitor.getActionMenuItem().addActionListener(e -> {
				// TODO-J: change the action based on the node state
				connector.shutdown();
				visitor.notifyStatus(nodeType, NemNodeStatus.STOPPED);
			});
		}

		this.trayIcon.setPopupMenu(this.popup);
		return this.trayIcon;
	}

	private NemConnector createConnector(final NemNodePolicy nodePolicy) {
		final DefaultAsyncNemConnector<String> connector = new DefaultAsyncNemConnector<>(
				this.client,
				r -> { throw new NemNodeExpectedException(); });
		connector.setAccountLookup(null);
		return new NemConnector(nodePolicy, connector);
	}

	private static class VisitorNodePolicyPair {
		private final NodeMenuItemNodeStatusVisitor visitor;
		private final NemNodePolicy nodePolicy;

		private VisitorNodePolicyPair(
				final NodeMenuItemNodeStatusVisitor visitor,
				final NemNodePolicy nodePolicy) {
			this.visitor = visitor;
			this.nodePolicy = nodePolicy;
		}
	}
}