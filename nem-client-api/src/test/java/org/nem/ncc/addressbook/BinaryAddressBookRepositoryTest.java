package org.nem.ncc.addressbook;

import org.nem.ncc.addressbook.storage.BinaryAddressBookRepository;
import org.nem.ncc.storable.entity.BinaryStorableEntityRepositoryTest;

public class BinaryAddressBookRepositoryTest extends BinaryStorableEntityRepositoryTest {

	@Override
	protected BinaryAddressBookRepository createRepository() {
		return new BinaryAddressBookRepository();
	}
}
