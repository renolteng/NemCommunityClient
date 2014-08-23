package org.nem.monitor.ux;

import org.nem.core.async.*;
import org.nem.core.connect.*;
import org.nem.monitor.*;
import org.nem.monitor.node.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * A builder that is used to construct the system tray icon.
 */
public class TrayIconBuilder {
	private final HttpMethodClient<ErrorResponseDeserializerUnion> client;
	private final TrayIcon trayIcon;
	private final PopupMenu popup;
	private final Collection<NodeStatusVisitor> visitors = new ArrayList<>();
	private final Collection<VisitorNodePolicyPair> visitorNodePolicyPairs = new ArrayList<>();

	/**
	 * Creates a new builder.
	 *
	 * @param client The http method client.
	 */
	public TrayIconBuilder(final HttpMethodClient<ErrorResponseDeserializerUnion> client) {
		this.client = client;
		// initially create the tray icon around a 1x1 pixel image because it cannot be created around a null image
		this.trayIcon = new TrayIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
		this.popup = new PopupMenu();

		this.visitors.add(new IconNodeStatusVisitor(this.trayIcon));
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
			final NemConnector connector = new NemConnector(pair.nodePolicy, this.client);
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