package org.nem.ncc.controller.viewmodels;

import org.nem.core.serialization.*;
import org.nem.core.time.*;
import org.nem.ncc.wallet.WalletName;

import java.util.List;

/**
 * A view model representing a wallet.
 */
public class WalletViewModel implements SerializableEntity {
	private final WalletName name;
	private final AccountViewModel primaryAccount;
	private final List<AccountViewModel> otherAccounts;
	private final TimeInstant updateTime;

	/**
	 * Creates a new wallet view model.
	 *
	 * @param name The wallet name.
	 * @param primaryAccount The wallet primary account.
	 * @param otherAccounts The other wallet accounts.
	 * @param updateTime The wallet update time.
	 */
	public WalletViewModel(
			final WalletName name,
			final AccountViewModel primaryAccount,
			final List<AccountViewModel> otherAccounts,
			final TimeInstant updateTime) {
		this.name = name;
		this.primaryAccount = primaryAccount;
		this.otherAccounts = otherAccounts;
		this.updateTime = updateTime;
	}

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getName() {
		return this.name;
	}

	/**
	 * Gets the primary account.
	 *
	 * @return The primary account.
	 */
	public AccountViewModel getPrimaryAccount() {
		return this.primaryAccount;
	}

	/**
	 * Gets a copy of the other accounts.
	 *
	 * @return The other accounts.
	 */
	public List<AccountViewModel> getOtherAccounts() {
		return this.otherAccounts;
	}

	/**
	 * Gets the wallet update time.
	 *
	 * @return The wallet update time.
	 */
	public TimeInstant getUpdateTime() {
		return this.updateTime;
	}

	@Override
	public void serialize(final Serializer serializer) {
		WalletName.writeTo(serializer, "wallet", this.name);
		serializer.writeLong("lastRefresh", UnixTime.fromTimeInstant(this.updateTime).getMillis());
		serializer.writeObject("primaryAccount", this.primaryAccount);
		serializer.writeObjectArray("otherAccounts", this.otherAccounts);
	}
}
