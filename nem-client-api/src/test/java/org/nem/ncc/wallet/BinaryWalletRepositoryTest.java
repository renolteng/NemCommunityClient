package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.BinaryStorableEntityRepositoryTest;

public class BinaryWalletRepositoryTest extends BinaryStorableEntityRepositoryTest {

	@Override
	protected BinaryWalletRepository createRepository() {
		return new BinaryWalletRepository();
	}
}