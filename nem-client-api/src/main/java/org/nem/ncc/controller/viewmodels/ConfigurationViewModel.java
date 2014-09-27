package org.nem.ncc.controller.viewmodels;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.model.NisBootInfo;

/**
 * A view model containing configuration information.
 */
public class ConfigurationViewModel implements SerializableEntity {
	private final String language;
	private final NodeEndpoint nisEndpoint;
	private final NisBootInfo nisBootInfo;

	/**
	 * Creates a configuration view model.
	 *
	 * @param language The language.
	 * @param nisEndpoint The NIS server's endpoint.
	 * @param nisBootInfo The NIS boot info.
	 */
	public ConfigurationViewModel(
			final String language,
			final NodeEndpoint nisEndpoint,
			final NisBootInfo nisBootInfo) {
		this.language = language;
		this.nisEndpoint = nisEndpoint;
		this.nisBootInfo = nisBootInfo;
	}

	/**
	 * Deserializes a configuration view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public ConfigurationViewModel(final Deserializer deserializer) {
		this.language = deserializer.readString("language");
		this.nisEndpoint = deserializer.readObject("remoteServer", NodeEndpoint::new);
		this.nisBootInfo = deserializer.readObject("nisBootInfo", NisBootInfo::new);
	}

	/**
	 * Gets the language.
	 *
	 * @return The language.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Gets the NIS server's endpoint.
	 *
	 * @return The NIS server's endpoint.
	 */
	public NodeEndpoint getNisEndpoint() {
		return this.nisEndpoint;
	}

	/**
	 * Gets the NIS boot info.
	 *
	 * @return The NIS boot info.
	 */
	public NisBootInfo getNisBootInfo() {
		return this.nisBootInfo;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("language", this.language);
		serializer.writeObject("remoteServer", this.nisEndpoint);
		serializer.writeObject("nisBootInfo", this.nisBootInfo);
	}
}
