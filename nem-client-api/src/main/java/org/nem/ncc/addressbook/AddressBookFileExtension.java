package org.nem.ncc.addressbook;

import org.nem.ncc.storable.entity.StorableEntityFileExtension;

/**
 * Represents a file extension for an address book.
 */
public class AddressBookFileExtension extends StorableEntityFileExtension<AddressBookFileExtension> {

	/**
	 * Creates a new address book file extension.
	 */
	public AddressBookFileExtension() {
		this(".adb");
	}

	/**
	 * Creates a new address book file extension.
	 *
	 * @param fileExtension The file extension.
	 */
	public AddressBookFileExtension(final String fileExtension) {
		super(fileExtension, AddressBookFileExtension.class);
	}
}
