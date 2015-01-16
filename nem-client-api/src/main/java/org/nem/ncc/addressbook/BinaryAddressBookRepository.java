package org.nem.ncc.addressbook;

import org.nem.ncc.addressbook.storage.*;
import org.nem.ncc.storable.entity.BinaryStorableEntityRepository;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;

/**
 * A binary address book repository.
 */
public class BinaryAddressBookRepository
		extends BinaryStorableEntityRepository<StorableAddressBook, AddressBookName, AddressBookFileExtension, AddressBookDescriptor>
		implements AddressBookRepository {

	@Override
	protected StorableEntityStorageException getException(final int value, final Exception ex) {
		return new AddressBookStorageException(value + AddressBookStorageException.OFFSET, ex);
	}
}
