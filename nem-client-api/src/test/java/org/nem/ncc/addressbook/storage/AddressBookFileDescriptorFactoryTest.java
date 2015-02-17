package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.test.StorableEntity.DefaultStorableEntity;

import java.io.File;

public class AddressBookFileDescriptorFactoryTest extends StorableEntityFileDescriptorFactoryTest {

	@Override
	protected StorableEntityNamePasswordPair createEntityNamePasswordPair(final String name) {
		return new AddressBookNamePasswordPair(name, "xyz");
	}

	@Override
	protected StorableEntityFileExtension getDefaultExtension() {
		return new AddressBookFileExtension(DefaultStorableEntity.DEFAULT_FILE_EXTENSION);
	}

	@Override
	protected StorableEntityFileDescriptorFactory createFactory(final File file) {
		return new AddressBookFileDescriptorFactory(file);
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return AddressBookStorageException.class;
	}

	@Override
	protected Integer getExceptionValue(final Integer originalValue) {
		return originalValue + AddressBookStorageException.OFFSET;
	}
}
