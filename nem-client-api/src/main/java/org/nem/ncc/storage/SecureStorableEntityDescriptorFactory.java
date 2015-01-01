package org.nem.ncc.storage;

import java.io.File;
import java.util.function.*;

/**
 * Factory that creates secure, file-backed storable entity descriptors.
 */
public class SecureStorableEntityDescriptorFactory<TEntity extends StorableEntity> implements StorableEntityDescriptorFactory {
	private final File directory;
	private final Function<StorableEntityName, TEntity> entityActivator;

	/**
	 * Creates a new secure storable entity descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureStorableEntityDescriptorFactory(
			final File directory,
			final Function<StorableEntityName, TEntity> entityActivator) {
		this.directory = directory;
		this.entityActivator = entityActivator;
	}

	@Override
	public StorableEntityDescriptor createNew(final StorableEntityNamePasswordPair pair) {
		return new SecureStorableEntityDescriptor(this.getFileDescriptorFactory(entityActivator).createNew(pair), pair.getPassword());
	}

	@Override
	public StorableEntityDescriptor openExisting(final StorableEntityNamePasswordPair pair) {
		return new SecureStorableEntityDescriptor(this.getFileDescriptorFactory(entityActivator).openExisting(pair), pair.getPassword());
	}

	private StorableEntityFileDescriptorFactory<TEntity> getFileDescriptorFactory(final Function<StorableEntityName, TEntity> entityActivator) {
		return new StorableEntityFileDescriptorFactory<>(this.directory, entityActivator);
	}
}
