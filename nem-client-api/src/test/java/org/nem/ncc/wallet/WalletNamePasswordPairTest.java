package org.nem.ncc.wallet;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storable.entity.StorableEntityNamePasswordPairTest;

public class WalletNamePasswordPairTest extends StorableEntityNamePasswordPairTest<
		WalletName,
		WalletPassword,
		WalletNamePasswordPair> {

	@Override
	protected String getDefaultNameLabel() {
		return "wallet";
	}

	@Override
	protected WalletNamePasswordPair createEntityNamePasswordPair(final WalletName name, final WalletPassword password) {
		return new WalletNamePasswordPair(name, password);
	}

	@Override
	protected WalletNamePasswordPair createEntityNamePasswordPair(final String name, final String password) {
		return new WalletNamePasswordPair(name, password);
	}

	@Override
	protected WalletNamePasswordPair createEntityNamePasswordPair(final Deserializer deserializer) {
		return new WalletNamePasswordPair(deserializer);
	}
}