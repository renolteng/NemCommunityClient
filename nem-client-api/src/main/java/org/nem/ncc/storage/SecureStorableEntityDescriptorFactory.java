package org.nem.ncc.storage;

import org.nem.core.serialization.ObjectDeserializer;

import java.io.File;
import java.util.function.*;

/**
 * Factory that creates secure, file-backed storable entity descriptors.
 */
public class SecureStorableEntityDescriptorFactory<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TDescriptor extends StorableEntityDescriptor<TEntity>,
		TDescriptorFactory extends StorableEntityFileDescriptorFactory<TEntity, TDescriptor>,
		TSecureDescriptor extends SecureStorableEntityDescriptor<TEntity, TDescriptor>>
		implements StorableEntityDescriptorFactory<TSecureDescriptor> {
	private final File directory;
	private final Function<StorableEntityName, TEntity> entityActivator;
	private final BiFunction<TEntity, File, TDescriptor> descriptorActivator;
	private final TripleFunction<
			File,
			Function<StorableEntityName, TEntity>,
			BiFunction<TEntity, File, TDescriptor>,
			TDescriptorFactory> descriptorFactoryActivator;
	private final BiFunction<TDescriptor, StorableEntityPassword, TSecureDescriptor> secureDescriptorActivator;

	/**
	 * Creates a new secure storable entity descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureStorableEntityDescriptorFactory(
			final File directory,
			final Function<StorableEntityName, TEntity> entityActivator,
			final BiFunction<TEntity, File, TDescriptor> descriptorActivator,
			final BiFunction<TDescriptor, StorableEntityPassword, TSecureDescriptor> secureDescriptorActivator,
			final TripleFunction<
					File,
					Function<StorableEntityName, TEntity>,
					BiFunction<TEntity, File, TDescriptor>,
					TDescriptorFactory> descriptorFactoryActivator) {
		this.directory = directory;
		this.entityActivator = entityActivator;
		this.descriptorActivator = descriptorActivator;
		this.secureDescriptorActivator = secureDescriptorActivator;
		this.descriptorFactoryActivator = descriptorFactoryActivator;
	}

	@Override
	public TSecureDescriptor createNew(final StorableEntityNamePasswordPair pair) {
		return secureDescriptorActivator.apply(this.getFileDescriptorFactory().createNew(pair), pair.getPassword());
	}

	@Override
	public TSecureDescriptor openExisting(final StorableEntityNamePasswordPair pair) {
		return secureDescriptorActivator.apply(this.getFileDescriptorFactory().openExisting(pair), pair.getPassword());
	}

	private TDescriptorFactory getFileDescriptorFactory() {
		return descriptorFactoryActivator.apply(this.directory, this.entityActivator, this.descriptorActivator);
	}
}
