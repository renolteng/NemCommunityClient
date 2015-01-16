package org.nem.ncc.addressbook.storage;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.storable.entity.storage.SecureStorableEntityDescriptor;

/**
 * AddressBookDescriptor that automatically encrypts and decrypts data using a password.
 */
public class SecureAddressBookDescriptor
		extends SecureStorableEntityDescriptor<
		StorableAddressBook,
		AddressBookName,
		AddressBookFileExtension,
		AddressBookPassword,
		AddressBookDescriptor>
		implements AddressBookDescriptor {

	/**
	 * Creates a new secure address book descriptor.
	 *
	 * @param descriptor The underlying descriptor.
	 * @param password The password.
	 */
	public SecureAddressBookDescriptor(
			final AddressBookDescriptor descriptor,
			final AddressBookPassword password) {
		super(descriptor, password);
	}

	@Override
	public AddressBookName getAddressBookName() {
		return super.getDescriptor().getAddressBookName();
	}
}
