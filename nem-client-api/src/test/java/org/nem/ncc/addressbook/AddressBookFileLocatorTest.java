package org.nem.ncc.addressbook;

import org.junit.BeforeClass;
import org.nem.ncc.storable.entity.*;

import java.io.*;

public class AddressBookFileLocatorTest extends StorableEntityFileLocatorTest {

	@BeforeClass
	public static void createTestFiles() throws IOException {
		createTestFiles(getDefaultFileExtension());
	}

	protected static String getDefaultFileExtension() {
		return AddressBookFileExtension.getDefaultFileExtension().toString();
	}

	@Override
	protected StorableEntityFileLocator createFileLocator(final File file) {
		return new AddressBookFileLocator(file);
	}
}
