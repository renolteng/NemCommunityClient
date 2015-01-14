package org.nem.ncc.addressbook.storage;

import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class SecureAddressBookDescriptorFactoryTest extends SecureStorableEntityDescriptorFactoryTest {

	@Override
	protected SecureStorableEntityDescriptorFactory createFactory(final File file) {
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
