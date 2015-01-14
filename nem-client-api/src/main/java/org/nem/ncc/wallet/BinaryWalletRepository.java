package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.wallet.storage.*;

/**
 * A binary wallet repository.
 */
public class BinaryWalletRepository
		extends BinaryStorableEntityRepository<StorableWallet, WalletName, WalletFileExtension, WalletDescriptor>
		implements WalletRepository {

	@Override
	protected StorableEntityStorageException getException(final int value, final Exception ex) {
		return new WalletStorageException(value, ex);
	}
}
