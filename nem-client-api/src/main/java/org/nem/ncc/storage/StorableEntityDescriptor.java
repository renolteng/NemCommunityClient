package org.nem.ncc.storage;

import org.nem.core.serialization.*;

import java.io.*;

/**
 * An interface that describes a storable entity.
 */
public interface StorableEntityDescriptor<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityFileExtension extends StorableEntityFileExtension>
		extends SerializableEntity {

	/**
	 * Gets the entity's name.
	 *
	 * @return The entity's name.
	 */
	public TEntityName getName();

	/**
	 * Gets the entity's file extension.
	 *
	 * @return The entity's file extension.
	 */
	public TEntityFileExtension getFileExtension();

	/**
	 * Gets the entity deserializer.
	 *
	 * @return The entity deserializer.
	 */
	public ObjectDeserializer<TEntity> getDeserializer();

	/**
	 * Opens a read stream that can be used to read the contents of the referenced storable entity.
	 *
	 * @return The input stream.
	 */
	public InputStream openRead();

	/**
	 * Opens a write stream that can be used to modify the contents of the referenced storable entity.
	 *
	 * @return The output stream.
	 */
	public OutputStream openWrite();

	/**
	 * Deletes the referenced storable entity.
	 */
	public void delete();
}
