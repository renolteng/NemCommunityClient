package org.nem.ncc.storage;

import org.nem.core.serialization.*;
import org.nem.core.serialization.ObjectDeserializer;

/**
 * An interface that describes an entity that can be stored.
 */
public interface StorableEntity extends ObjectDeserializer<StorableEntity>, SerializableEntity {

	/**
	 * Gets the label of the storable entity for serialization (e.g. 'wallet', 'accountLabel').
	 *
	 * @return The label.
	 */
	public String getLabel();

	/**
	 * Gets the name of the storable entity.
	 *
	 * @return The name.
	 */
	public StorableEntityName getName();

	/**
	 * Gets the file extension of the storable entity.
	 *
	 * @return The file extension.
	 */
	public StorableEntityFileExtension getFileExtension();
}
