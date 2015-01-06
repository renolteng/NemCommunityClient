package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;

/**
 * Factory for creating address book descriptors.
 */
public interface AddressBookDescriptorFactory {

	/**
	 * Creates an address book descriptor corresponding to a new address book.
	 *
	 * @param pair The address book name and password.
	 * @param fileExtension The address book file extension.
	 * @return The descriptor.
	 */
	public AddressBookDescriptor createNew(final AddressBookNamePasswordPair pair, final AddressBookFileExtension fileExtension);

	/**
	 * Opens an address book descriptor corresponding to en existing address book.
	 *
	 * @param pair The address book name and password.
	 * @param fileExtension The address book file extension.
	 * @return The descriptor.
	 */
	public AddressBookDescriptor openExisting(final AddressBookNamePasswordPair pair, final AddressBookFileExtension fileExtension);
}
