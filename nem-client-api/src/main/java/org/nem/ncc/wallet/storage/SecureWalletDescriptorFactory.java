package org.nem.ncc.wallet.storage;

import org.nem.ncc.wallet.*;

import java.io.File;

/**
 * Factory that creates secure, file-backed wallet descriptors.
 */
public class SecureWalletDescriptorFactory implements WalletDescriptorFactory {
	private final File directory;

	/**
	 * Creates a new secure wallet descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureWalletDescriptorFactory(final File directory) {
		this.directory = directory;
	}

	@Override
	public WalletDescriptor createNew(final WalletNamePasswordPair pair) {
		final File file = this.createFile(pair.getName());
		if (file.exists()) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_ALREADY_EXISTS);
		}

		return createDescriptor(file, pair.getPassword());
	}

	@Override
	public WalletDescriptor openExisting(final WalletNamePasswordPair pair) {
		final File file = this.createFile(pair.getName());
		if (!file.exists()) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_DOES_NOT_EXIST);
		}

		return createDescriptor(file, pair.getPassword());
	}

	private File createFile(final WalletName name) {
		// this function should be modified if we want to add support for basic import / export
		// (i.e. opening wallets from and saving them to different, non-default locations)
		// we can add logic here to check if WalletName is really a path and do different things based on that
		return new File(this.directory, WalletFileUtils.getWalletFileName(name));
	}

	private static WalletDescriptor createDescriptor(final File file, final WalletPassword password) {
		return new SecureWalletDescriptor(new WalletFileDescriptor(file), password);
	}
}
