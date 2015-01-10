package org.nem.ncc.addressbook.storage;

import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.wallet.storage.SecureWalletDescriptorFactory;

import java.io.File;

public class SecureAddressBookDescriptorFactoryTest extends SecureStorableEntityDescriptorFactoryTest {

	@Override
	protected SecureStorableEntityDescriptorFactory createFactory(final File file) {
		return new SecureAddressBookDescriptorFactory(file);
	}
}
