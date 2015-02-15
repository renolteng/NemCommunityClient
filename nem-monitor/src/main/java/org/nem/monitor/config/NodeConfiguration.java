package org.nem.monitor.config;

/**
 * Configuration for a node.
 */
public class NodeConfiguration {
	private final String uri;
	private final String vmOptions;
	private final String jnlpUrl;

	/**
	 * Creates a new node configuration.
	 *
	 * @param uri The uri for launching the (local) node.
	 * @param vmOptions The vm options for launching the (local) node.
	 * @param jnlpUrl The JNLP url.
	 */
	public NodeConfiguration(
			final String uri,
			final String vmOptions,
			final String jnlpUrl) {
		this.uri = uri;
		this.vmOptions = vmOptions;
		this.jnlpUrl = jnlpUrl;
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
}
