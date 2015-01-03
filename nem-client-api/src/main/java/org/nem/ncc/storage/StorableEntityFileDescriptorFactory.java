package org.nem.ncc.storage;

import org.eclipse.jetty.util.UrlEncoded;
import org.nem.core.serialization.ObjectDeserializer;

import java.io.File;
import java.util.function.Function;

/**
 * Factory that creates file-backed storable entity descriptors.
 */
public class StorableEntityFileDescriptorFactory<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityPassword extends StorableEntityPassword,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityNamePasswordPair extends StorableEntityNamePasswordPair<TEntityName, TEntityPassword, TEntityNamePasswordPair>,
		TEntityDescriptor extends StorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension>>
		implements StorableEntityDescriptorFactory<TEntityNamePasswordPair, TEntityFileExtension, TEntityDescriptor> {
	private final File directory;
	private final ObjectDeserializer<TEntity> deserializer;
	private final Function<String, TEntityName> nameActivator;
	private final Function<String, TEntityFileExtension> fileExtensionActivator;
	private final QuadFunction<
			File,
			ObjectDeserializer<TEntity>,
			Function<String, TEntityName>,
			Function<String, TEntityFileExtension>,
	TEntityDescriptor> descriptorActivator;

	/**
	 * Creates a new storable entity descriptor factory.
	 *
	 * @param directory The search directory.
	 * @param deserializer The entity deserializer.
	 * @param nameActivator The name activator.
	 * @param fileExtensionActivator The file extension activator.
	 * @param descriptorActivator The descriptor activator.
	 */
	public StorableEntityFileDescriptorFactory(
			final File directory,
			final ObjectDeserializer<TEntity> deserializer,
			final Function<String, TEntityName> nameActivator,
			final Function<String, TEntityFileExtension> fileExtensionActivator,
			final QuadFunction<
					File,
					ObjectDeserializer<TEntity>,
					Function<String, TEntityName>,
					Function<String, TEntityFileExtension>,
					TEntityDescriptor> descriptorActivator) {
		this.directory = directory;
		this.deserializer = deserializer;
		this.nameActivator = nameActivator;
		this.fileExtensionActivator = fileExtensionActivator;
		this.descriptorActivator = descriptorActivator;
	}

	@Override
	public TEntityDescriptor createNew(final TEntityNamePasswordPair pair, final TEntityFileExtension fileExtension) {
		final File file = this.createFile(pair.getName(), fileExtension);
		if (file.exists()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_ALREADY_EXISTS);
		}

		return this.descriptorActivator.apply(file, this.deserializer, this.nameActivator, this.fileExtensionActivator);
	}

	@Override
	public TEntityDescriptor openExisting(final TEntityNamePasswordPair pair, final TEntityFileExtension fileExtension) {
		final File file = this.createFile(pair.getName(), fileExtension);
		if (!file.exists()) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST);
		}

		return this.descriptorActivator.apply(file, this.deserializer, this.nameActivator, this.fileExtensionActivator);
	}

	private File createFile(final StorableEntityName name, final StorableEntityFileExtension fileExtension) {
		// this function should be modified if we want to add support for basic import / export
		// (i.e. opening storable entities from and saving them to different, non-default locations)
		// we can add logic here to check if StorableEntityName is really a path and do different things based on that
		return new File(this.directory, this.getStorableEntityFileName(name, fileExtension));
	}

	private String getStorableEntityFileName(final StorableEntityName name, final StorableEntityFileExtension fileExtension) {
		return UrlEncoded.encodeString(name.toString()) + fileExtension.toString();
	}
}
