package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.StorableEntityFileExtensionTest;

public class WalletFileExtensionTest extends StorableEntityFileExtensionTest {

	@Override
	protected WalletFileExtension createEntityFileExtension(final String fileExtension) {
		return new WalletFileExtension(fileExtension);
	}
}
