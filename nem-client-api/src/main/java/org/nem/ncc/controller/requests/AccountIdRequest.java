package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;

/**
 * A view model representing an account identifier.
 */
public class AccountIdRequest {
	private final Address address;

	/**
	 * Creates a new account id view model.
	 *
	 * @param address The account address.
	 */
	public AccountIdRequest(final Address address) {
		this.address = address;
		this.checkValidity();
	}

	/**
	 * Deserializes an account id view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountIdRequest(final Deserializer deserializer) {
		this.address = Address.readFrom(deserializer, "account");
		this.checkValidity();
	}

	private void checkValidity() {
		if (null == this.address || !this.address.isValid()) {
			throw new IllegalArgumentException("specified address is not valid");
		}
	}

	/**
	 * Gets the account id.
	 *
	 * @return The account id.
	 */
	public Address getAccountId() {
		return this.address;
	}
}