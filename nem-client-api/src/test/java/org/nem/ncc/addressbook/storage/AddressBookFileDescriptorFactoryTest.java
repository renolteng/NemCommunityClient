package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class AddressBookFileDescriptorFactoryTest extends StorableEntityFileDescriptorFactoryTest<
		AddressBookName,
		AddressBookPassword,
		AddressBookFileExtension,
		AddressBookNamePasswordPair,
		AddressBookFileDescriptorFactory> {

	@Override
	protected AddressBookNamePasswordPair createEntityNamePasswordPair(final String name) {
		return new AddressBookNamePasswordPair(name, "xyz");
	}

	@Override
	protected AddressBookFileExtension createFileExtension(final String extension) {
		return new AddressBookFileExtension(extension);
	}

	@Override
	protected AddressBookFileDescriptorFactory createFactory(final File file) {
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
