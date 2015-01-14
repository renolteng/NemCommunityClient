package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.wallet.storage.WalletStorageException;

public class WalletFileExtensionTest extends StorableEntityFileExtensionTest {

	@Override
	protected WalletFileExtension getDefaultFileExtension() {
		return WalletFileExtension.getDefaultFileExtension();
	}

	@Override
	protected WalletFileExtension createEntityFileExtension() {
		return new WalletFileExtension();
	}

	@Override
	protected WalletFileExtension createEntityFileExtension(final String fileExtension) {
		return new WalletFileExtension(fileExtension);
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}
}
