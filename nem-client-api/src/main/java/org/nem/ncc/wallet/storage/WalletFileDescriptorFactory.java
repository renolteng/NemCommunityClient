package org.nem.ncc.wallet.storage;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.storage.*;
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
		WalletFileDescriptor>
		implements WalletDescriptorFactory {

	/**
	 * Creates a new storable entity descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public WalletFileDescriptorFactory(final File directory) {
		super(directory, MemoryWallet::new, WalletName::new, WalletFileExtension::new, WalletFileDescriptor::new);
	}

	/**
	 * Creates a new storable entity descriptor factory.
	 *
	 * @param directory The search directory.
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
					WalletFileDescriptor> descriptorActivator) {
		super(directory, deserializer, nameActivator, fileExtensionActivator, descriptorActivator);
	}
}
