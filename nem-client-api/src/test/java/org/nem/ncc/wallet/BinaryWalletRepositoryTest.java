package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.wallet.storage.WalletStorageException;

public class BinaryWalletRepositoryTest extends BinaryStorableEntityRepositoryTest {

	@Override
	protected BinaryWalletRepository createRepository() {
		return new BinaryWalletRepository();
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