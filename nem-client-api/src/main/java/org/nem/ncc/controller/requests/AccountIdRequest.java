package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

/**
 * A request representing an account identifier.
 */
public class AccountIdRequest implements SerializableEntity {
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

	@Override
	public void serialize(final Serializer serializer) {
		Address.writeTo(serializer, "account", this.address);
	}

	@Override
	public int hashCode() {
		return this.address.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof AccountIdRequest)) {
			return false;
		}

		final AccountIdRequest rhs = (AccountIdRequest)obj;
		return this.address.equals(rhs.address);
	}
}