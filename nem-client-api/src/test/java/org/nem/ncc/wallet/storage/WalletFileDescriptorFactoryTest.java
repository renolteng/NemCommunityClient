package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.wallet.*;

import java.io.File;

public class WalletFileDescriptorFactoryTest extends StorableEntityFileDescriptorFactoryTest<
		WalletName,
		WalletPassword,
		WalletFileExtension,
		WalletNamePasswordPair,
		WalletFileDescriptorFactory> {

	@Override
	protected WalletNamePasswordPair createEntityNamePasswordPair(final String name) {
		return new WalletNamePasswordPair(name, "xyz");
	}

	@Override
	protected WalletFileExtension createFileExtension(final String extension) {
		return new WalletFileExtension(extension);
	}

	@Override
	protected WalletFileDescriptorFactory createFactory(final File file) {
		return new WalletFileDescriptorFactory(file);
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
