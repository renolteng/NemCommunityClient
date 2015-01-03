package org.nem.ncc.storage;

import org.eclipse.jetty.util.UrlEncoded;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * A StorableEntityDescriptor implementation that references files stored on disk.
 */
public class StorableEntityFileDescriptor<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityFileExtension extends StorableEntityFileExtension>
		implements StorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension> {
	private final static Logger LOGGER = Logger.getLogger(StorableEntityFileDescriptor.class.getName());

	private final File file;
	private final TEntityName name;
	private final TEntityFileExtension fileExtension;
	private final ObjectDeserializer<TEntity> deserializer;

	/**
	 * Creates a new storable entity file descriptor around a file.
	 *
	 * @param file The storable entity location.
	 */
	public StorableEntityFileDescriptor(
			final File file,
			final ObjectDeserializer<TEntity> deserializer,
			final Function<String, TEntityName> nameActivator,
			final Function<String, TEntityFileExtension> fileExtensionActivator) {
		this.file = file;
		this.deserializer = deserializer;
		if (file.isDirectory()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_CANNOT_BE_DIRECTORY);
		}

		final String fileName = this.file.getName();
		if (!StorableEntityFileExtension.hasValidExtension(fileName)) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_HAS_INVALID_EXTENSION);
		}

		final String entityName = fileName.substring(0, fileName.length() - StorableEntityFileExtension.EXTENSION_LENGTH);
		this.name = nameActivator.apply(UrlEncoded.decodeString(entityName, 0, entityName.length(), UrlEncoded.ENCODING));
		final String entityFileExtension = fileName.substring(fileName.length() - StorableEntityFileExtension.EXTENSION_LENGTH, fileName.length());
		this.fileExtension = fileExtensionActivator.apply(entityFileExtension);
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
	public TEntityName getName() {
		return this.name;
	}

	@Override
	public TEntityFileExtension getFileExtension() {
		return this.fileExtension;
	}

	@Override
	public ObjectDeserializer<TEntity> getDeserializer() {
		return this.deserializer;
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
		LOGGER.info(String.format("Deleting storable entity <%s> located at <%s>.", this.name, this.getStorableEntityLocation()));
		if (!this.file.delete()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_DELETED);
		}
	}

	@Override
	public void serialize(final Serializer serializer) {
		StorableEntityName.writeTo(serializer, "name", this.name);
		serializer.writeString("location", this.getStorableEntityLocation());
	}
}
