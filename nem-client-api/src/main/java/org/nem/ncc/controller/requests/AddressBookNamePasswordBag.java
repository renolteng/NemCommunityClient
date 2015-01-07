package org.nem.ncc.controller.requests;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.addressbook.*;

/**
 * Request object that that requires an address book name and an address book
 * but can contain any number of optional properties too.
 */
public class AddressBookNamePasswordBag extends AddressBookNamePasswordPair {
	private final Deserializer deserializer;

	/**
	 * Deserializes a address book name / password bag.
	 *
	 * @param deserializer The deserializer.
	 */
	public AddressBookNamePasswordBag(final Deserializer deserializer) {
		super(deserializer);
		this.deserializer = deserializer;
	}

	/**
	 * Gets the new name if it was specified.
	 *
	 * @return The new name.
	 */
	public AddressBookName getNewName() {
		return AddressBookName.readFrom(this.deserializer, "new_name");
	}

	/**
	 * Gets the new password if it was specified.
	 *
	 * @return The new password.
	 */
	public AddressBookPassword getNewPassword() {
		return AddressBookPassword.readFrom(this.deserializer, "new_password");
	}
}
