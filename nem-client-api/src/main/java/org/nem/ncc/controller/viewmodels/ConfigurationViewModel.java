package org.nem.ncc.controller.viewmodels;

import org.nem.core.serialization.*;
import org.nem.ncc.model.*;

/**
 * A view model containing configuration information.
 * TODO 20150329 J-G,J: we should probably just replace this with ConfigurationPatch
 * TODO 20150329 J-G,J: update tests for setFirstStart
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
		this.patch.setFirstStart(patch.getFirstStart());
	}

	/**
	 * Deserializes a configuration view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public ConfigurationViewModel(final Deserializer deserializer) {
		// do NOT call readObject
		this.patch.deserialize(deserializer, false);
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
		// do NOT call writeObject
		this.patch.serialize(serializer);
	}
}
