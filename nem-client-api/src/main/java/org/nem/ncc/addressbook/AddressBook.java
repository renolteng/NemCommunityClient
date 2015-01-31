package org.nem.ncc.addressbook;

import org.nem.core.serialization.SerializableEntity;

import java.util.Collection;

/**
 * Represents a NEM address book that can contain zero or more entries.
 */
public interface AddressBook extends SerializableEntity, AccountLabels {
	/**
	 * Gets the size of the address book.
	 *
	 * @return The size of the address book.
	 */
	public int size();

	/**
	 * Gets the name of the address book.
	 *
	 * @return The name of the address book.
	 */
	public AddressBookName getName();

	/**
	 * Gets the account label collection.
	 *
	 * @return The account label collection.
	 */
	public Collection<AccountLabel> getAccountLabels();
}
