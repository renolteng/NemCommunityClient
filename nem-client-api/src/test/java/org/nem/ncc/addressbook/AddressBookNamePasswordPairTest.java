package org.nem.ncc.addressbook;

import org.nem.ncc.storable.entity.StorableEntityNamePasswordPairTest;

public class AddressBookNamePasswordPairTest extends StorableEntityNamePasswordPairTest {

	@Override
	protected AddressBookNamePasswordPair createEntityNamePasswordPair(final String name, final String password) {
		return new AddressBookNamePasswordPair(name, password);
	}
}
