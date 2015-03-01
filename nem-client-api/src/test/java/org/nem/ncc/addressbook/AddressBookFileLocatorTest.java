package org.nem.ncc.addressbook;

import org.junit.BeforeClass;
import org.nem.ncc.addressbook.storage.AddressBookDescriptor;
import org.nem.ncc.storable.entity.StorableEntityFileLocatorTest;

import java.io.*;

public class AddressBookFileLocatorTest extends StorableEntityFileLocatorTest<
		AddressBookDescriptor,
		AddressBookFileLocator> {

	@BeforeClass
	public static void createTestFiles() throws IOException {
		createTestFiles(AddressBookFileExtension.getDefaultFileExtension().toString());
	}

	@Override
	protected AddressBookFileLocator createFileLocator(final File file) {
		return new AddressBookFileLocator(file);
	}
}
