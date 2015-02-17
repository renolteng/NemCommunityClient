package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.wallet.*;

import java.io.File;

public class WalletFileDescriptorTest extends StorableEntityFileDescriptorTest<
		WalletName,
		WalletFileExtension,
		WalletFileDescriptor> {

	@Override
	protected WalletFileDescriptor createDescriptor(final File file) {
		return new WalletFileDescriptor(file);
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