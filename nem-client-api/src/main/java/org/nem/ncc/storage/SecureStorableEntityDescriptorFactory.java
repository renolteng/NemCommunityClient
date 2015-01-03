package org.nem.ncc.storage;

import org.nem.core.serialization.ObjectDeserializer;

import java.io.File;
import java.util.function.*;

/**
 * Factory that creates secure, file-backed storable entity descriptors.
 */
public class SecureStorableEntityDescriptorFactory<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityPassword extends StorableEntityPassword,
		TEntityNamePasswordPair extends StorableEntityNamePasswordPair<TEntityName, TEntityPassword, TEntityNamePasswordPair>,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityDescriptor extends StorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension>,
		TEntityDescriptorFactory extends StorableEntityFileDescriptorFactory<TEntity, TEntityName, TEntityPassword, TEntityFileExtension, TEntityNamePasswordPair, TEntityDescriptor>,
		TSecureDescriptor extends SecureStorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension, TEntityPassword, TEntityDescriptor>>
		implements StorableEntityDescriptorFactory<TEntityNamePasswordPair, TEntityFileExtension, TSecureDescriptor> {
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
	private final PentaFunction<
			File,
			ObjectDeserializer<TEntity>,
			Function<String, TEntityName>,
			Function<String, TEntityFileExtension>,
			QuadFunction<
					File,
					ObjectDeserializer<TEntity>,
					Function<String, TEntityName>,
					Function<String, TEntityFileExtension>,
					TEntityDescriptor>,
			TEntityDescriptorFactory> descriptorFactoryActivator;
	private final BiFunction<TEntityDescriptor, TEntityPassword, TSecureDescriptor> secureDescriptorActivator;

	/**
	 * Creates a new secure storable entity descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureStorableEntityDescriptorFactory(
			final File directory,
			final ObjectDeserializer<TEntity> deserializer,
			final Function<String, TEntityName> nameActivator,
			final Function<String, TEntityFileExtension> fileExtensionActivator,
			final QuadFunction<
					File,
					ObjectDeserializer<TEntity>,
					Function<String, TEntityName>,
					Function<String, TEntityFileExtension>,
					TEntityDescriptor> descriptorActivator,
			final BiFunction<TEntityDescriptor, TEntityPassword, TSecureDescriptor> secureDescriptorActivator,
			final PentaFunction<
					File,
					ObjectDeserializer<TEntity>,
					Function<String, TEntityName>,
					Function<String, TEntityFileExtension>,
					QuadFunction<
							File,
							ObjectDeserializer<TEntity>,
							Function<String, TEntityName>,
							Function<String, TEntityFileExtension>,
							TEntityDescriptor>,
					TEntityDescriptorFactory> descriptorFactoryActivator) {
		this.directory = directory;
		this.deserializer = deserializer;
		this.nameActivator = nameActivator;
		this.fileExtensionActivator = fileExtensionActivator;
		this.descriptorActivator = descriptorActivator;
		this.secureDescriptorActivator = secureDescriptorActivator;
		this.descriptorFactoryActivator = descriptorFactoryActivator;
	}

	@Override
	public TSecureDescriptor createNew(final TEntityNamePasswordPair pair, final TEntityFileExtension fileExtension) {
		return secureDescriptorActivator.apply(this.getFileDescriptorFactory().createNew(pair, fileExtension), pair.getPassword());
	}

	@Override
	public TSecureDescriptor openExisting(final TEntityNamePasswordPair pair, final TEntityFileExtension fileExtension) {
		return secureDescriptorActivator.apply(this.getFileDescriptorFactory().openExisting(pair, fileExtension), pair.getPassword());
	}

	private TEntityDescriptorFactory getFileDescriptorFactory() {
		return descriptorFactoryActivator.apply(
				this.directory,
				this.deserializer,
				this.nameActivator,
				this.fileExtensionActivator,
				this.descriptorActivator);
	}
}
