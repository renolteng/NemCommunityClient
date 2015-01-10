package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.BinaryStorableEntityRepository;

/**
 * A binary address book repository.
 */
public class BinaryAddressBookRepository
		extends BinaryStorableEntityRepository<StorableAddressBook, AddressBookName, AddressBookFileExtension, AddressBookDescriptor>
		implements AddressBookRepository {
}
