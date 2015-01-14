package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.StorableEntityStorageException;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class SecureWalletDescriptorFactoryTest extends SecureStorableEntityDescriptorFactoryTest {

	@Override
	protected SecureStorableEntityDescriptorFactory createFactory(final File file) {
		return new SecureWalletDescriptorFactory(file);
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}
}