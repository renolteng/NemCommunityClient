package org.nem.ncc.addressbook;

import org.nem.ncc.storable.entity.*;

public class BinaryAddressBookRepositoryTest extends BinaryStorableEntityRepositoryTest {

	@Override
	protected BinaryAddressBookRepository createRepository() {
		return new BinaryAddressBookRepository();
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
