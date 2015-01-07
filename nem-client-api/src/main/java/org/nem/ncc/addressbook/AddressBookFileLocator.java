package org.nem.ncc.addressbook;

import org.nem.ncc.addressbook.storage.*;
import org.nem.ncc.storable.entity.StorableEntityFileLocator;

import java.io.File;
import java.util.List;

/**
 * A file-based AddressBookLocator implementation.
 */
public class AddressBookFileLocator
	extends StorableEntityFileLocator<StorableAddressBook, AddressBookName, AddressBookFileExtension, AddressBookDescriptor>
	implements AddressBookLocator {

	/**
	 * Creates a new address book file locator.
	 *
	 * @param directory The search directory.
	 */
	public AddressBookFileLocator(final File directory) {
		super(
				directory,
				(dir, name) -> AddressBookFileExtension.isValidAndHasDefaultExtension(name),
				MemoryAddressBook::new,
				AddressBookName::new,
				AddressBookFileExtension::new,
				AddressBookFileDescriptor::new);
	}

	@Override
	public List<AddressBookDescriptor> getAllAddressBooks() {
		return super.getAll();
	}
}
