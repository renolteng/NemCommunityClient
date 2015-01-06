package org.nem.ncc.addressbook.storage;

import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class AddressBookFileDescriptorFactoryTest extends StorableEntityFileDescriptorFactoryTest {

	@Override
	protected StorableEntityFileDescriptorFactory createFactory(final File file) {
		return new AddressBookFileDescriptorFactory(file);
	}
}
