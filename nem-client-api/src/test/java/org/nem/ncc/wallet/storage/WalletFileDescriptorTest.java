package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.StorableEntityStorageException;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class WalletFileDescriptorTest extends StorableEntityFileDescriptorTest {

	@Override
	protected StorableEntityFileDescriptor createDesciptor(final File file) {
		return new WalletFileDescriptor(file);
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}
}