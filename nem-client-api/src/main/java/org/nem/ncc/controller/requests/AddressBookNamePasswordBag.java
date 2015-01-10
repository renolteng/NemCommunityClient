package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
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
		return AddressBookName.readFrom(this.deserializer, "newName");
	}

	/**
	 * Gets the new password if it was specified.
	 *
	 * @return The new password.
	 */
	public AddressBookPassword getNewPassword() {
		return AddressBookPassword.readFrom(this.deserializer, "newPassword");
	}

	/**
	 * Gets the address if it was specified.
	 *
	 * @return The address.
	 */
	public Address getAddress() {
		return Address.readFrom(this.deserializer, "address");
	}

	/**
	 * Gets the public label if it was specified.
	 *
	 * @return The public label.
	 */
	public String getPublicLabel() {
		return this.deserializer.readString("publicLabel");
	}

	/**
	 * Gets the private label if it was specified.
	 *
	 * @return The private label.
	 */
	public String getPrivateLabel() {
		return this.deserializer.readString("privateLabel");
	}
}
