package org.nem.ncc.controller.viewmodels;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.model.*;

/**
 * A view model containing configuration information.
 */
public class ConfigurationViewModel implements SerializableEntity {
	private final ConfigurationPatch patch = new ConfigurationPatch();

	/**
	 * Creates a configuration view model.
	 *
	 * @param patch The configuration patch.
	 */
	public ConfigurationViewModel(final ConfigurationPatch patch) {
		this.patch.setLanguage(patch.getLanguage());
		this.patch.setNisEndpoint(patch.getNisEndpoint());
		this.patch.setNisBootInfo(patch.getNisBootInfo());
	}

	/**
	 * Deserializes a configuration view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public ConfigurationViewModel(final Deserializer deserializer) {
		this.patch.setLanguage(deserializer.readString("language"));
		this.patch.setNisEndpoint(deserializer.readObject("remoteServer", NodeEndpoint::new));
		this.patch.setNisBootInfo(deserializer.readObject("nisBootInfo", NisBootInfo::new));
	}

	/**
	 * Gets the configuration patch.
	 *
	 * @return The configuration patch.
	 */
	public ConfigurationPatch getPatch() {
		return this.patch;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("language", this.patch.getLanguage());
		serializer.writeObject("remoteServer", this.patch.getNisEndpoint());
		serializer.writeObject("nisBootInfo", this.patch.getNisBootInfo());
	}
}
