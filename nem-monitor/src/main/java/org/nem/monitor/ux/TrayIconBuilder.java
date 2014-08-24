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
	private final Collection<NemNodePolicy> nodePolicies = new ArrayList<>();

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
	public void addStatusMenuItems(final NemNodePolicy nodePolicy, final String jnlpUrl) {
		final NemNodeType nodeType = nodePolicy.getNodeType();
		final NodeManager manager = new NodeManager(
				nodeType,
				this.createConnector(nodePolicy),
				this.webStartLauncher,
				jnlpUrl);
		final NodeStatusToManagementActionAdapter actionAdapter = new NodeStatusToManagementActionAdapter(nodeType, manager);
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(nodeType);

		this.popup.add(visitor.getStatusMenuItem());
		this.popup.add(visitor.getActionMenuItem());
		visitor.getActionMenuItem().addActionListener(actionAdapter);

		this.visitors.add(visitor);
		this.visitors.add(actionAdapter);
		this.nodePolicies.add(nodePolicy);
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

		for (final NemNodePolicy nodePolicy : this.nodePolicies) {
			final NemNodeType nodeType = nodePolicy.getNodeType();
			final NemConnector connector = this.createConnector(nodePolicy);
			new AsyncTimer(
					() -> connector.getStatus().thenAccept(status -> visitor.notifyStatus(nodeType, status)),
					250,
					new UniformDelayStrategy(1000),
					null);
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
}