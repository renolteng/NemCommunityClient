package org.nem.ncc.addressbook;

import org.mockito.Mockito;
import org.nem.ncc.addressbook.storage.*;
import org.nem.ncc.storable.entity.BinaryStorableEntityRepositoryTest;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.test.Utils;

public class BinaryAddressBookRepositoryTest extends BinaryStorableEntityRepositoryTest<
		StorableAddressBook,
		AddressBookDescriptor,
		BinaryAddressBookRepository> {

	@Override
	protected StorableAddressBook createEntity(final String name) {
		return new MemoryAddressBook(new AddressBookName(name));
	}

	@Override
	protected StorableAddressBook createEntityWithEntries(final String name, final int numEntries) {
		final StorableAddressBook addressBook = this.createEntity(name);
		for (int i = 0; i < numEntries; ++i) {
			addressBook.addLabel(new AccountLabel(Utils.generateRandomAddress(), "", ""));
		}

		return addressBook;
	}

	@Override
	protected boolean areEquivalent(final StorableAddressBook lhs, final StorableAddressBook rhs) {
		return lhs.getName().equals(rhs.getName())
				&& lhs.getAccountLabels().equals(rhs.getAccountLabels());
	}

	@Override
	protected AddressBookDescriptor createDescriptor() {
		return Mockito.mock(AddressBookDescriptor.class);
	}

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
