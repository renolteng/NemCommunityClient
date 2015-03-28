package org.nem.ncc.model;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

/**
 * A small class that holds updatable configuration.
 */
public class ConfigurationPatch {
	private String language;
	private NodeEndpoint nisEndpoint;
	private NisBootInfo nisBootInfo;


	public void update(final ConfigurationPatch patch) {
		this.language = patch.getLanguage();
		this.nisEndpoint = patch.getNisEndpoint();
		this.nisBootInfo = patch.getNisBootInfo();
	}

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

	/**
	 * Sets the fields based on deserializer.
	 *
	 * @param deserializer The deserializer.
	 */
	public void deserialize(final Deserializer deserializer, boolean remoteIsOptional) {
		this.setLanguage(deserializer.readString("language"));
		if (remoteIsOptional) {
			this.setNisEndpoint(deserializer.readOptionalObject("remoteServer", NodeEndpoint::new));
		} else {
			this.setNisEndpoint(deserializer.readObject("remoteServer", NodeEndpoint::new));
		}
		this.setNisBootInfo(deserializer.readObject("nisBootInfo", NisBootInfo::new));
	}

	public void serialize(final Serializer serializer) {
		serializer.writeString("language", this.getLanguage());
		serializer.writeObject("remoteServer", this.getNisEndpoint());
		serializer.writeObject("nisBootInfo", this.getNisBootInfo());
	}
}
