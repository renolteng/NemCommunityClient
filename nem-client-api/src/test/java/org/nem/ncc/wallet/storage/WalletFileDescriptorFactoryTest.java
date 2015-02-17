package org.nem.ncc.wallet.storage;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.test.StorableEntity.DefaultStorableEntity;
import org.nem.ncc.wallet.*;

import java.io.File;

public class WalletFileDescriptorFactoryTest extends StorableEntityFileDescriptorFactoryTest {

	@Override
	protected StorableEntityNamePasswordPair createEntityNamePasswordPair(final String name) {
		return new WalletNamePasswordPair(name, "xyz");
	}

	@Override
	protected StorableEntityFileExtension getDefaultExtension() {
		return new WalletFileExtension(DefaultStorableEntity.DEFAULT_FILE_EXTENSION);
	}

	@Override
	protected StorableEntityFileDescriptorFactory createFactory(final File file) {
		return new WalletFileDescriptorFactory(file);
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}
}
