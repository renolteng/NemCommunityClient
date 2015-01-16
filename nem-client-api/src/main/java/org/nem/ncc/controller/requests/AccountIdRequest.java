package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

// TODO 20150115 J-B: maybe we should share the NIS one (in org.nem.nis.controller.requests)
// TODO 20150116 BR -> J: yea, pretty much the same as AccountId in NIS.
// TODO 20150115 J-B: why does this need to be serializable?
// TODO 20150116 BR -> J: AccountServices.getAccountMetaDataPairs() sends a list of AccountIdRequests to NIS

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