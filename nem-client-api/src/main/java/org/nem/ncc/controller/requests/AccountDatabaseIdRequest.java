package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.model.ncc.AccountId;
import org.nem.core.serialization.Deserializer;

/**
 * A request representing an account and an optional database id.
 */
public class AccountDatabaseIdRequest extends AccountId {
	private final Long databaseId;

	/**
	 * Creates a new account / database id request.
	 *
	 * @param address The account address.
	 * @param databaseId The (optional) database id.
	 */
	public AccountDatabaseIdRequest(
			final Address address,
			final Long databaseId) {
		super(address);
		this.databaseId = databaseId;
	}

	/**
	 * Deserializes an account / database id request.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountDatabaseIdRequest(final Deserializer deserializer) {
		super(deserializer);
		this.databaseId = deserializer.readOptionalLong("id");
	}

	/**
	 * Gets the database id.
	 *
	 * @return The database id.
	 */
	public Long getDatabaseId() {
		return this.databaseId;
	}
}
