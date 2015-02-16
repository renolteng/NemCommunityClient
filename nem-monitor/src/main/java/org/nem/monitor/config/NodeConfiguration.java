package org.nem.monitor.config;

/**
 * Configuration for a node.
 */
public class NodeConfiguration {
	private final String uri;
	private final String vmOptions;
	private final String jnlpUrl;
	private final boolean isMonitored;
	private final boolean shouldAutoBoot;

	/**
	 * Creates a new node configuration.
	 *
	 * @param uri The uri for launching the (local) node.
	 * @param vmOptions The vm options for launching the (local) node.
	 * @param jnlpUrl The JNLP url.
	 * @param isMonitored true if the node should be monitored.
	 * @param shouldAutoBoot true if the node should be auto-booted.
	 */
	public NodeConfiguration(
			final String uri,
			final String vmOptions,
			final String jnlpUrl,
			final boolean isMonitored,
			final boolean shouldAutoBoot) {
		this.uri = uri;
		this.vmOptions = vmOptions;
		this.jnlpUrl = jnlpUrl;
		this.isMonitored = isMonitored;
		this.shouldAutoBoot = shouldAutoBoot;
	}

	/**
	 * Gets the uri for launching the (local) node.
	 *
	 * @return The uri for launching the (local) node.
	 */
	public String getUri() {
		return this.uri;
	}

	/**
	 * Gets the vm options for launching the (local) node.
	 *
	 * @return The vm options for launching the (local) node.
	 */
	public String getVmOptions() {
		return this.vmOptions;
	}

	/**
	 * Gets the JNLP url.
	 *
	 * @return The JNLP url.
	 */
	public String getJnlpUrl() {
		return this.jnlpUrl;
	}

	/**
	 * Gets a value indicating whether or not the node should be monitored.
	 *
	 * @return true if the node should be monitored.
	 */
	public boolean isMonitored() {
		return this.isMonitored;
	}

	/**
	 * Gets a value indicating whether or not the node should be auto-booted.
	 *
	 * @return true if the node should be auto-booted.
	 */
	public boolean shouldAutoBoot() {
		return this.shouldAutoBoot;
	}
}
