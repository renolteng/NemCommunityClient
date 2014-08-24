package org.nem.monitor.ux;

import org.nem.monitor.*;
import org.nem.monitor.node.NemNodeType;

import java.util.logging.Logger;

/**
 * Class that provides management functionality for a node.
 */
public class NodeManager {
	private static final Logger LOGGER = Logger.getLogger(WebStartLauncher.class.getName());

	private final NemNodeType nodeType;
	private final NemConnector connector;
	private final WebStartLauncher launcher;
	private final String jnlpUrl;

	/**
	 * Creates a new visitor.
	 *
	 * @param nodeType The node type being managed.
	 * @param connector The nem connector.
	 * @param launcher The web start launcher.
	 * @param jnlpUrl The url to the java network launching protocol.
	 */
	public NodeManager(
			final NemNodeType nodeType,
			final NemConnector connector,
			final WebStartLauncher launcher,
			final String jnlpUrl) {
		this.nodeType = nodeType;
		this.connector = connector;
		this.launcher = launcher;
		this.jnlpUrl = jnlpUrl;
	}

	/**
	 * Shuts down the node.
	 */
	public void shutdown() {
		LOGGER.info(String.format("Shutting down node %s", this.nodeType));
		this.connector.shutdown();
	}

	/**
	 * Launches the node.
	 */
	public void launch() {
		LOGGER.info(String.format("Launching node %s", this.nodeType));
		this.launcher.launch(this.jnlpUrl);
	}
}
