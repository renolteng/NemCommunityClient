package org.nem.ncc.addressbook;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storable.entity.StorableEntityPassword;

/**
 * Represents an address book password.
 */
public class AddressBookPassword extends StorableEntityPassword<AddressBookPassword> {

	/**
	 * Creates a new address book password.
	 *
	 * @param password The password.
	 */
	public AddressBookPassword(final String password) {
		super(password, AddressBookPassword.class);
	}

	/**
	 * Creates a new address book password.
	 *
	 * @param deserializer The deserializer.
	 */
	public AddressBookPassword(final Deserializer deserializer) {
		super(deserializer, "password", AddressBookPassword.class);
	}

	//region inline serialization

	/**
	 * Reads an address book password object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static AddressBookPassword readFrom(final Deserializer deserializer, final String label) {
		return new AddressBookPassword(deserializer.readString(label));
	}

	/**
	 * Reads an address book password object that can be null.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static AddressBookPassword readFromOptional(final Deserializer deserializer, final String label) {
		final String password = deserializer.readOptionalString(label);
		return null == password ? null : new AddressBookPassword(password);
	}

	//endregion
}
