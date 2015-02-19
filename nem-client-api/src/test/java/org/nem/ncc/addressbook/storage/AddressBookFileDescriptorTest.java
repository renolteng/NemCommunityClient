package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class AddressBookFileDescriptorTest extends StorableEntityFileDescriptorTest<
		AddressBookName,
		AddressBookFileExtension,
		AddressBookFileDescriptor> {

	@Override
	protected AddressBookFileDescriptor createDescriptor(final File file) {
		return new AddressBookFileDescriptor(file);
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
