package org.nem.ncc.addressbook;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.storable.entity.StorableEntity;

/**
 * Interface that represents an address book that can be stored and loaded.
 */
public interface StorableAddressBook extends
		AddressBook,
		StorableEntity<AddressBookName, AddressBookFileExtension>,
		ObjectDeserializer<StorableAddressBook> {
}
