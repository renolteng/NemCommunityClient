package org.nem.ncc.addressbook;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storable.entity.StorableEntityNamePasswordPair;

/**
 * A pair of an address book name and an address book password.
 */
public class AddressBookNamePasswordPair extends StorableEntityNamePasswordPair<AddressBookName, AddressBookPassword, AddressBookNamePasswordPair> {

	/**
	 * Creates an new address book name / password pair.
	 *
	 * @param name The address book name.
	 * @param password The address book password.
	 */
	public AddressBookNamePasswordPair(final AddressBookName name, final AddressBookPassword password) {
		super(name, password, AddressBookNamePasswordPair.class);
	}

	/**
	 * Creates a new address book name / password pair.
	 *
	 * @param name The address book name.
	 * @param password The address book password.
	 */
	public AddressBookNamePasswordPair(final String name, final String password) {
		this(new AddressBookName(name), new AddressBookPassword(password));
	}

	/**
	 * Deserializes a new address book name / password pair.
	 *
	 * @param deserializer The deserializer.
	 */
	public AddressBookNamePasswordPair(final Deserializer deserializer) {
		super(new AddressBookName(deserializer), new AddressBookPassword(deserializer), AddressBookNamePasswordPair.class);
	}
}
