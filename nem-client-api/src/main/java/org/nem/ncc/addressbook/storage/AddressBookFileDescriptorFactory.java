package org.nem.ncc.addressbook.storage;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.function.QuadFunction;
import org.nem.ncc.storable.entity.storage.*;

import java.io.File;
import java.util.function.Function;

/**
 * Factory that creates file-backed storable address book descriptors.
 */
public class AddressBookFileDescriptorFactory
		extends StorableEntityFileDescriptorFactory<
		StorableAddressBook,
		AddressBookName,
		AddressBookPassword,
		AddressBookFileExtension,
		AddressBookNamePasswordPair,
		AddressBookDescriptor>
		implements AddressBookDescriptorFactory {

	/**
	 * Creates a new address book descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public AddressBookFileDescriptorFactory(final File directory) {
		super(directory, MemoryAddressBook::new, AddressBookName::new, AddressBookFileExtension::new, AddressBookFileDescriptor::new);
	}

	/**
	 * Creates a new address book descriptor factory.
	 *
	 * @param directory The search directory.
	 * @param deserializer The deserializer.
	 * @param nameActivator The name activator.
	 * @param fileExtensionActivator The file extension activator.
	 * @param descriptorActivator The descriptor activator.
	 */
	public AddressBookFileDescriptorFactory(
			final File directory,
			final ObjectDeserializer<StorableAddressBook> deserializer,
			final Function<String, AddressBookName> nameActivator,
			final Function<String, AddressBookFileExtension> fileExtensionActivator,
			final QuadFunction<
					File,
					ObjectDeserializer<StorableAddressBook>,
					Function<String, AddressBookName>,
					Function<String, AddressBookFileExtension>,
					AddressBookDescriptor> descriptorActivator) {
		super(directory, deserializer, nameActivator, fileExtensionActivator, descriptorActivator);
	}

	@Override
	protected StorableEntityStorageException getException(final int value) {
		return new AddressBookStorageException(value + AddressBookStorageException.OFFSET);
	}
}
