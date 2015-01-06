package org.nem.ncc.addressbook.storage;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.storage.StorableEntityFileDescriptor;

import java.io.File;
import java.util.function.Function;

/**
 * An AddressBookDescriptor implementation that references files stored on disk.
 */
public class AddressBookFileDescriptor
		extends StorableEntityFileDescriptor<StorableAddressBook, AddressBookName, AddressBookFileExtension>
		implements AddressBookDescriptor {

	/**
	 * Creates a new address book file descriptor around a storable address book and a file.
	 *
	 * @param file The address book location.
	 */
	public AddressBookFileDescriptor(final File file) {
		// TODO 20150301 BR: any way to do it with an interface rather than having MemoryAddressBook here?
		this(file, MemoryAddressBook::new, AddressBookName::new, AddressBookFileExtension::new);
	}

	/**
	 * Creates a new address book file descriptor around a storable address book and a file.
	 *
	 * @param file The address book location.
	 */
	public AddressBookFileDescriptor(
			final File file,
			final ObjectDeserializer<StorableAddressBook> deserializer,
			final Function<String, AddressBookName> nameActivator,
			final Function<String, AddressBookFileExtension> fileExtensionActivator) {
		super(file, deserializer, nameActivator, fileExtensionActivator);
	}

	/**
	 * Gets the address book location.
	 *
	 * @return The address book location.
	 */
	public String getAddressBookLocation() {
		return super.getStorableEntityLocation();
	}

	@Override
	public AddressBookName getAddressBookName() {
		return super.getName();
	}
}
