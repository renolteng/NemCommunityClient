package org.nem.monitor.visitors;

import org.nem.monitor.*;
import org.nem.monitor.node.NemNodePolicy;

import java.util.logging.Logger;

/**
 * Class that provides management functionality for a node.
 */
public class NodeManager {
	private static final Logger LOGGER = Logger.getLogger(NodeManager.class.getName());

	private final NemNodePolicy nodePolicy;
	private final String jnlpUrl;
	private final NemConnector connector;
	private final WebStartLauncher launcher;
	private final WebBrowser browser;

	/**
	 * Creates a new visitor.
	 *
	 * @param nodePolicy The policy of the node being managed.
	 * @param jnlpUrl The url to the java network launching protocol.
	 * @param connector The nem connector.
	 * @param launcher The web start launcher.
	 * @param browser The web browser.
	 */
	public NodeManager(
			final NemNodePolicy nodePolicy,
			final String jnlpUrl,
			final NemConnector connector,
			final WebStartLauncher launcher,
			final WebBrowser browser) {
		this.nodePolicy = nodePolicy;
		this.jnlpUrl = jnlpUrl;
		this.connector = connector;
		this.launcher = launcher;
		this.browser = browser;
	}

	/**
	 * Shuts down the node.
	 */
	public void shutdown() {
		LOGGER.info(String.format("Shutting down node %s", this.nodePolicy.getNodeType()));
		this.connector.shutdown();
	}

	/**
	 * Launches the node.
	 */
	public void launch() {
		LOGGER.info(String.format("Launching node %s", this.nodePolicy.getNodeType()));
		this.launcher.launch(this.jnlpUrl);
	}

	/**
	 * Navigates to the node's browser start page (if supported by the node).
	 */
	public void launchBrowser() {
		if (this.nodePolicy.hasBrowserGui()) {
			LOGGER.info(String.format("Launching browser for node %s", this.nodePolicy.getNodeType()));
			this.browser.navigate(this.nodePolicy.getEndpoint());
		}
	}
}
