package org.nem.ncc.addressbook;

import org.nem.ncc.addressbook.storage.AddressBookDescriptor;

/**
 * Repository class for loading and saving address books.
 */
public interface AddressBookRepository {

	/**
	 * Saves the address book to the specified descriptor.
	 *
	 * @param descriptor The output descriptor.
	 * @param addressBook The address book.
	 */
	void save(final AddressBookDescriptor descriptor, final StorableAddressBook addressBook);

	/**
	 * Loads a address book from the specified descriptor.
	 *
	 * @param descriptor The input descriptor.
	 * @return The address book.
	 */
	StorableAddressBook load(final AddressBookDescriptor descriptor);
}
