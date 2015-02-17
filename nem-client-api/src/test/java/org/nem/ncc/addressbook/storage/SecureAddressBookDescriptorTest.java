package org.nem.ncc.addressbook.storage;

import org.mockito.Mockito;
import org.nem.ncc.addressbook.AddressBookPassword;
import org.nem.ncc.storable.entity.storage.*;

public class SecureAddressBookDescriptorTest extends SecureStorableEntityDescriptorTest<
		AddressBookDescriptor,
		SecureAddressBookDescriptor> {

	@Override
	protected AddressBookDescriptor createDescriptor() {
		return Mockito.mock(AddressBookDescriptor.class);
	}

	@Override
	protected SecureAddressBookDescriptor createSecureDescriptor(final AddressBookDescriptor descriptor, final String password) {
		return new SecureAddressBookDescriptor(descriptor, new AddressBookPassword(password));
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return AddressBookStorageException.class;
	}

	@Override
	protected Integer getExceptionValue(final Integer originalValue) {
		return originalValue + AddressBookStorageException.OFFSET;
	}
}
