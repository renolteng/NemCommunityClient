package org.nem.ncc.controller.viewmodels;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.model.NisBootInfo;

/**
 * A view model containing configuration information.
 */
public class ConfigurationViewModel implements SerializableEntity {
	private final String language;
	private final NodeEndpoint remoteServer;
	private final NisBootInfo nisBootInfo;

	/**
	 * Creates a configuration view model.
	 *
	 * @param language The language.
	 * @param nisBootInfo The NIS boot info.
	 */
	public ConfigurationViewModel(
			final String language,
			final NodeEndpoint remoteServer,
			final NisBootInfo nisBootInfo) {
		this.language = language;
		this.remoteServer = remoteServer;
		this.nisBootInfo = nisBootInfo;
	}

	/**
	 * Deserializes a configuration view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public ConfigurationViewModel(final Deserializer deserializer) {
		this.language = deserializer.readString("language");
		this.remoteServer = deserializer.readObject("remoteServer", NodeEndpoint::new);
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
	 * Gets the remote server's endpoint.
	 *
	 * @return The remote server's endpoint.
	 */
	public NodeEndpoint getRemoteServer() {
		return this.remoteServer;
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
		serializer.writeObject("remoteServer", this.remoteServer);
		serializer.writeObject("nisBootInfo", this.nisBootInfo);
	}
}
