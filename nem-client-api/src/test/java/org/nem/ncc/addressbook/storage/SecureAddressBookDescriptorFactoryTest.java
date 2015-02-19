package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class SecureAddressBookDescriptorFactoryTest extends SecureStorableEntityDescriptorFactoryTest<
		AddressBookName,
		AddressBookPassword,
		AddressBookFileExtension,
		AddressBookNamePasswordPair,
		SecureAddressBookDescriptorFactory> {

	@Override
	protected AddressBookName createEntityName(final String name) {
		return new AddressBookName(name);
	}

	@Override
	protected AddressBookNamePasswordPair createEntityNamePasswordPair(final AddressBookName name, final AddressBookPassword password) {
		return new AddressBookNamePasswordPair(name, password);
	}

	@Override
	protected AddressBookFileExtension createFileExtension(final String extension) {
		return new AddressBookFileExtension(extension);
	}

	@Override
	protected SecureAddressBookDescriptorFactory createFactory(final File file) {
		return new SecureAddressBookDescriptorFactory(file);
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
