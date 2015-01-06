package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.storage.SecureStorableEntityDescriptorFactory;
import org.nem.ncc.wallet.*;

import java.io.File;

/**
 * Factory that creates secure, file-backed wallet descriptors.
 */
public class SecureWalletDescriptorFactory
		extends SecureStorableEntityDescriptorFactory<
				StorableWallet,
				WalletName,
				WalletPassword,
				WalletNamePasswordPair,
				WalletFileExtension,
				WalletDescriptor,
				WalletFileDescriptorFactory,
				SecureWalletDescriptor>
		implements WalletDescriptorFactory {

	/**
	 * Creates a new secure wallet descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureWalletDescriptorFactory(final File directory) {
		super(
				directory,
				MemoryWallet::new,
				WalletName::new,
				WalletFileExtension::new,
				WalletFileDescriptor::new,
				SecureWalletDescriptor::new,
				WalletFileDescriptorFactory::new);
	}
}
