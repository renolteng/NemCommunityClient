package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.AccountStatus;
import org.nem.core.serialization.*;

public class AccountStatusViewModel implements SerializableEntity {
	private final AccountStatus status;

	/**
	 * Creates a new account status view model around an account status.
	 *
	 * @param accountStatus The account metadata pair.
	 */
	public AccountStatusViewModel(final AccountStatus accountStatus) {
		this.status = accountStatus;
	}

	public AccountStatusViewModel(final Deserializer deserializer) {
		this.status = AccountStatus.readFrom(deserializer, "status");
	}

	/**
	 * Gets the status of the account.
	 *
	 * @return The status.
	 */
	public AccountStatus getStatus() {
		return this.status;
	}

	@Override
	public void serialize(final Serializer serializer) {
		AccountStatus.writeTo(serializer, "status", this.status);
	}
}
