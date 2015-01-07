package org.nem.ncc.controller.viewmodels;

import org.nem.core.serialization.*;
import org.nem.ncc.addressbook.*;

import java.util.List;

/**
 * A view model representing an address book.
 */
public class AddressBookViewModel implements SerializableEntity {
	private final AddressBookName name;
	private final List<AccountLabel> accountLabels;

	/**
	 * Creates a new address book view model.
	 *
	 * @param name The address book name.
	 * @param accountLabels The account labels.
	 */
	public AddressBookViewModel(
			final AddressBookName name,
			final List<AccountLabel> accountLabels) {
		this.name = name;
		this.accountLabels = accountLabels;
	}

	/**
	 * Gets the address book name.
	 *
	 * @return The address book name.
	 */
	public AddressBookName getName() {
		return this.name;
	}

	/**
	 * Gets a copy of the account labels.
	 *
	 * @return The account labels.
	 */
	public List<AccountLabel> getAccountLabels() {
		return this.accountLabels;
	}

	@Override
	public void serialize(final Serializer serializer) {
		AddressBookName.writeTo(serializer, "addressBook", this.name);
		serializer.writeObjectArray("accountLabels", this.accountLabels);
	}
}
