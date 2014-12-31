package org.nem.ncc.storage;

import org.nem.core.serialization.SerializableEntity;

import java.io.*;

/**
 * An interface that describes a storable entity.
 */
public interface StorableEntityDescriptor<TEntity extends StorableEntity> extends SerializableEntity{

	/**
	 * Gets the underlying TEntity.
	 *
	 * @return The underlying entity.
	 */
	public TEntity getEntity();

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
