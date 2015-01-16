package org.nem.ncc.wallet.storage;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.function.QuadFunction;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.storable.entity.storage.StorableEntityFileDescriptorFactory;
import org.nem.ncc.wallet.*;

import java.io.File;
import java.util.function.Function;

/**
 * Factory that creates file-backed storable wallet descriptors.
 */
public class WalletFileDescriptorFactory
		extends StorableEntityFileDescriptorFactory<
		StorableWallet,
		WalletName,
		WalletPassword,
		WalletFileExtension,
		WalletNamePasswordPair,
		WalletDescriptor>
		implements WalletDescriptorFactory {

	/**
	 * Creates a new wallet descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public WalletFileDescriptorFactory(final File directory) {
		super(directory, MemoryWallet::new, WalletName::new, WalletFileExtension::new, WalletFileDescriptor::new);
	}

	/**
	 * Creates a new wallet descriptor factory.
	 *
	 * @param directory The search directory.
	 * @param deserializer The deserializer.
	 * @param nameActivator The name activator.
	 * @param fileExtensionActivator The file extension activator.
	 * @param descriptorActivator The descriptor activator.
	 */
	public WalletFileDescriptorFactory(
			final File directory,
			final ObjectDeserializer<StorableWallet> deserializer,
			final Function<String, WalletName> nameActivator,
			final Function<String, WalletFileExtension> fileExtensionActivator,
			final QuadFunction<
					File,
					ObjectDeserializer<StorableWallet>,
					Function<String, WalletName>,
					Function<String, WalletFileExtension>,
					WalletDescriptor> descriptorActivator) {
		super(directory, deserializer, nameActivator, fileExtensionActivator, descriptorActivator);
	}

	@Override
	protected StorableEntityStorageException getException(final int value) {
		return new WalletStorageException(value);
	}
}
