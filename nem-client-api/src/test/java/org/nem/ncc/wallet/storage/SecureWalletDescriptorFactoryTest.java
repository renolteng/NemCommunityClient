package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.wallet.*;

import java.io.File;

public class SecureWalletDescriptorFactoryTest extends SecureStorableEntityDescriptorFactoryTest<
		WalletName,
		WalletPassword,
		WalletFileExtension,
		WalletNamePasswordPair,
		SecureWalletDescriptorFactory> {

	@Override
	protected WalletNamePasswordPair createEntityNamePasswordPair(final String name) {
		return new WalletNamePasswordPair(new WalletName(name), null);
	}

	@Override
	protected WalletFileExtension createFileExtension(final String extension) {
		return new WalletFileExtension(extension);
	}

	@Override
	protected SecureWalletDescriptorFactory createFactory(final File file) {
		return new SecureWalletDescriptorFactory(file);
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}

	@Override
	protected Integer getExceptionValue(final Integer originalValue) {
		return originalValue;
	}
}