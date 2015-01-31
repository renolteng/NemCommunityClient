package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.StorableEntityNamePasswordPairTest;

public class WalletNamePasswordPairTest extends StorableEntityNamePasswordPairTest {

	@Override
	protected WalletNamePasswordPair createEntityNamePasswordPair(final String name, final String password) {
		return new WalletNamePasswordPair(name, password);
	}
}