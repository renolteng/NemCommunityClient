package org.nem.ncc.wallet;

import org.mockito.Mockito;
import org.nem.ncc.storable.entity.BinaryStorableEntityRepositoryTest;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.wallet.storage.*;

public class BinaryWalletRepositoryTest extends BinaryStorableEntityRepositoryTest<
		StorableWallet,
		WalletDescriptor,
		BinaryWalletRepository> {

	@Override
	protected StorableWallet createEntity(final String name) {
		return new MemoryWallet(new WalletName(name));
	}

	@Override
	protected StorableWallet createEntityWithEntries(final String name, final int numEntries) {
		final StorableWallet wallet = this.createEntity(name);
		for (int i = 0; i < numEntries; ++i) {
			wallet.addOtherAccount(new WalletAccount());
		}

		return wallet;
	}

	@Override
	protected boolean areEquivalent(final StorableWallet lhs, final StorableWallet rhs) {
		return
				lhs.getPrimaryAccount().equals(rhs.getPrimaryAccount())
				&& lhs.getName().equals(rhs.getName())
				&& lhs.getOtherAccounts().equals(rhs.getOtherAccounts());
	}

	@Override
	protected WalletDescriptor createDescriptor() {
		return Mockito.mock(WalletDescriptor.class);
	}

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