package org.nem.ncc.storage;

import org.nem.core.serialization.Serializer;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.util.logging.Logger;

/**
 * A StorableEntityDescriptor implementation that references files stored on disk.
 */
public class StorableEntityFileDescriptor<TEntity extends StorableEntity> implements StorableEntityDescriptor {
	private final static Logger LOGGER = Logger.getLogger(StorableEntityFileDescriptor.class.getName());

	private final File file;
	private final TEntity entity;

	/**
	 * Creates a new storable entity file descriptor around a file.
	 *
	 * @param file The storable entity location.
	 */
	public StorableEntityFileDescriptor(final TEntity entity, final File file) {
		this.file = file;
		this.entity = entity;
		if (file.isDirectory()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_CANNOT_BE_DIRECTORY);
		}

		final String fileName = this.file.getName();
		if (!StorableEntityFileExtension.hasValidExtension(fileName)) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_HAS_INVALID_EXTENSION);
		}
	}

	/**
	 * Gets the storable entity location.
	 *
	 * @return The storable entity location.
	 */
	public String getStorableEntityLocation() {
		return this.file.getAbsolutePath();
	}

	@Override
	public StorableEntity getEntity() {
		return this.entity;
	}

	@Override
	public InputStream openRead() {
		LOGGER.info(String.format("Opening storable entity for reading located at <%s>", this.getStorableEntityLocation()));
		if (!this.file.exists()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST);
		}

		return ExceptionUtils.propagate(
				() -> new FileInputStream(this.getStorableEntityLocation()),
				ex -> new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ, ex));
	}

	@Override
	public OutputStream openWrite() {
		LOGGER.info(String.format("Opening storable entity for writing located at <%s>", this.getStorableEntityLocation()));
		return ExceptionUtils.propagate(
				() -> new FileOutputStream(this.getStorableEntityLocation()),
				ex -> new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED, ex));
	}

	@Override
	public void delete() {
		LOGGER.info(String.format("Deleting storable entity <%s> located at <%s>.", this.entity.getName(), this.getStorableEntityLocation()));
		if (!this.file.delete()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_DELETED);
		}
	}

	@Override
	public void serialize(final Serializer serializer) {
		StorableEntityName.writeTo(serializer, this.entity.getLabelName(), this.entity.getName());
		serializer.writeString("location", this.getStorableEntityLocation());
	}
}
