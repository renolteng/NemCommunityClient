package org.nem.ncc.model;

import org.nem.core.node.NodeEndpoint;

/**
 * A small class that holds updatable configuration.
 */
public class ConfigurationPatch {
	private String language;
	private NodeEndpoint nisEndpoint;
	private NisBootInfo nisBootInfo;

	/**
	 * Gets the configured language.
	 *
	 * @return The language.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Gets the configured NIS server's endpoint.
	 *
	 * @return The NIS server's endpoint.
	 */
	public NodeEndpoint getNisEndpoint() {
		return this.nisEndpoint;
	}

	/**
	 * Gets the configured NIS boot info.
	 *
	 * @return The NIS boot info.
	 */
	public NisBootInfo getNisBootInfo() {
		return this.nisBootInfo;
	}

	/**
	 * Sets the configured language.
	 *
	 * @param language The language.
	 */
	public void setLanguage(final String language) {
		this.language = language;
	}

	/**
	 * Sets the configured NIS server's endpoint.
	 *
	 * @param nisEndpoint The NIS server's endpoint.
	 */
	public void setNisEndpoint(final NodeEndpoint nisEndpoint) {
		this.nisEndpoint = nisEndpoint;
	}

	/**
	 * Sets the configured NIS boot info.
	 *
	 * @param nisBootInfo The NIS boot info.
	 */
	public void setNisBootInfo(final NisBootInfo nisBootInfo) {
		this.nisBootInfo = nisBootInfo;
	}
}
