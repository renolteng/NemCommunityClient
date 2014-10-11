package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.*;

/**
 * A request representing an account and a wallet + password.
 */
public class AccountWalletPasswordRequest extends AccountIdRequest {
	private final WalletNamePasswordPair walletNamePasswordPair;

	/**
	 * Creates a account / wallet + password view model.
	 *
	 * @param address The account address.
	 * @param walletName The wallet name.
	 * @param walletPassword The wallet password.
	 */
	public AccountWalletPasswordRequest(
			final Address address,
			final WalletName walletName,
			final WalletPassword walletPassword) {
		super(address);
		this.walletNamePasswordPair = new WalletNamePasswordPair(walletName, walletPassword);
	}

	/**
	 * Deserializes an account / wallet + password view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountWalletPasswordRequest(final Deserializer deserializer) {
		super(deserializer);
		this.walletNamePasswordPair = new WalletNamePasswordPair(deserializer);
	}

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getWalletName() {
		return this.walletNamePasswordPair.getName();
	}

	/**
	 * Gets the wallet password.
	 *
	 * @return The wallet password.
	 */
	public WalletPassword getWalletPassword() {
		return this.walletNamePasswordPair.getPassword();
	}
}
