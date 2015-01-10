package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class WalletFileDescriptorFactoryTest extends StorableEntityFileDescriptorFactoryTest {

	@Override
	protected StorableEntityFileDescriptorFactory createFactory(final File file) {
		return new WalletFileDescriptorFactory(file);
	}
}
