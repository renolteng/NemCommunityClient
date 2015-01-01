package org.nem.ncc.storage;

import java.io.File;
import java.util.function.BiFunction;

/**
 * Factory that creates secure, file-backed storable entity descriptors.
 */
public class SecureStorableEntityDescriptorFactory<TEntity extends StorableEntity> implements StorableEntityDescriptorFactory {
	private final File directory;
	private final BiFunction<StorableEntityName, StorableEntityFileExtension, TEntity> entityActivator;

	/**
	 * Creates a new secure wallet descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureStorableEntityDescriptorFactory(
			final File directory,
			final BiFunction<StorableEntityName, StorableEntityFileExtension, TEntity> entityActivator) {
		this.directory = directory;
		this.entityActivator = entityActivator;
	}

	@Override
	public StorableEntityDescriptor createNew(final StorableEntityNamePasswordPair pair, final StorableEntityFileExtension fileExtension) {
		return new SecureStorableEntityDescriptor(this.getFileDescriptorFactory(entityActivator).createNew(pair, fileExtension), pair.getPassword());
	}

	@Override
	public StorableEntityDescriptor openExisting(final StorableEntityNamePasswordPair pair, final StorableEntityFileExtension fileExtension) {
		return new SecureStorableEntityDescriptor(this.getFileDescriptorFactory(entityActivator).openExisting(pair, fileExtension), pair.getPassword());
	}

	private StorableEntityFileDescriptorFactory<TEntity> getFileDescriptorFactory(
			final BiFunction<StorableEntityName, StorableEntityFileExtension, TEntity> entityActivator) {
		return new StorableEntityFileDescriptorFactory<>(this.directory, entityActivator);
	}
}
