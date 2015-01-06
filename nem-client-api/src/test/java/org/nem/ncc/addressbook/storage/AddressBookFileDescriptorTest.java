package org.nem.ncc.addressbook.storage;

import org.nem.ncc.storable.entity.storage.*;

import java.io.File;

public class AddressBookFileDescriptorTest extends StorableEntityFileDescriptorTest {

	@Override
	protected StorableEntityFileDescriptor createDesciptor(final File file) {
		return new AddressBookFileDescriptor(file);
	}
}
