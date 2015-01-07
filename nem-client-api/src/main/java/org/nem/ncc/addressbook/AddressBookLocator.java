package org.nem.ncc.addressbook;

import org.nem.ncc.addressbook.storage.AddressBookDescriptor;

import java.util.List;

/**
 * Interface for locating address books.
 */
public interface AddressBookLocator {

	/**
	 * Gets all address book descriptors.
	 *
	 * @return A list of all address book descriptors.
	 */
	public List<AddressBookDescriptor> getAllAddressBooks();
}
