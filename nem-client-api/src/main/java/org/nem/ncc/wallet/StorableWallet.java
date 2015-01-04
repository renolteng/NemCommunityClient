package org.nem.ncc.wallet;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.storable.entity.StorableEntity;

/**
 * Interface that represents a wallet that can be stored and loaded.
 */
public interface StorableWallet extends
		Wallet,
		StorableEntity<WalletName, WalletFileExtension>,
		ObjectDeserializer<StorableWallet> {
}
