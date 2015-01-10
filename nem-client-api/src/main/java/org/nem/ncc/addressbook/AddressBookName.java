package org.nem.ncc.addressbook;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storable.entity.StorableEntityName;

/**
 * Represents an address book name.
 */
public class AddressBookName extends StorableEntityName<AddressBookName> {
	/**
	 * Creates a new address book name.
	 *
	 * @param name The name.
	 */
	public AddressBookName(final String name) {
		super(name, "addressBook", AddressBookName.class);
	}

	/**
	 * Creates a new address book name.
	 *
	 * @param deserializer The deserializer.
	 */
	public AddressBookName(final Deserializer deserializer) {
		super(deserializer, "addressBook", AddressBookName.class);
	}

	//region inline serialization

	/**
	 * Reads an address book name object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static AddressBookName readFrom(final Deserializer deserializer, final String label) {
		return new AddressBookName(deserializer.readString(label));
	}

	//endregion
}
