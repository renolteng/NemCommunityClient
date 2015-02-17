package org.nem.ncc.addressbook;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storable.entity.StorableEntityNamePasswordPairTest;

public class AddressBookNamePasswordPairTest extends StorableEntityNamePasswordPairTest<
		AddressBookName,
		AddressBookPassword,
		AddressBookNamePasswordPair> {

	@Override
	protected AddressBookNamePasswordPair createEntityNamePasswordPair(final AddressBookName name, final AddressBookPassword password) {
		return new AddressBookNamePasswordPair(name, password);
	}

	@Override
	protected AddressBookNamePasswordPair createEntityNamePasswordPair(final String name, final String password) {
		return new AddressBookNamePasswordPair(name, password);
	}

	@Override
	protected AddressBookNamePasswordPair createEntityNamePasswordPair(final Deserializer deserializer) {
		return new AddressBookNamePasswordPair(deserializer);
	}
}
