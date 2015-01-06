package org.nem.ncc.addressbook;

import org.nem.ncc.storable.entity.StorableEntityFileExtensionTest;

public class AddressBookFileExtensionTest extends StorableEntityFileExtensionTest {

	@Override
	protected AddressBookFileExtension createEntityFileExtension(final String fileExtension) {
		return new AddressBookFileExtension(fileExtension);
	}
}
