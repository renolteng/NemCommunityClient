package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.storage.SecureStorableEntityDescriptorFactory;

import java.io.File;

/**
 * Factory that creates secure, file-backed address book descriptors.
 */
public class SecureAddressBookDescriptorFactory
		extends SecureStorableEntityDescriptorFactory<
		StorableAddressBook,
		AddressBookName,
		AddressBookPassword,
		AddressBookNamePasswordPair,
		AddressBookFileExtension,
		AddressBookDescriptor,
		AddressBookFileDescriptorFactory,
		SecureAddressBookDescriptor>
		implements AddressBookDescriptorFactory {

	/**
	 * Creates a new secure address book descriptor factory.
	 *
	 * @param directory The search directory.
	 */
	public SecureAddressBookDescriptorFactory(final File directory) {
		super(
				directory,
				MemoryAddressBook::new,
				AddressBookName::new,
				AddressBookFileExtension::new,
				AddressBookFileDescriptor::new,
				SecureAddressBookDescriptor::new,
				AddressBookFileDescriptorFactory::new);
	}
}
