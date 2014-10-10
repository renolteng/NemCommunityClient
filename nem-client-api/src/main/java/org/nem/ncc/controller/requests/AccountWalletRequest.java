package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.WalletName;

/**
 * A request representing an account and a wallet.
 */
public class AccountWalletRequest extends AccountIdRequest {
	private final WalletName walletName;

	/**
	 * Creates a account / wallet view model.
	 *
	 * @param address The account address.
	 * @param walletName The wallet name.
	 */
	public AccountWalletRequest(
			final Address address,
			final WalletName walletName) {
		super(address);
		this.walletName = walletName;
	}

	/**
	 * Deserializes an account / wallet view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountWalletRequest(final Deserializer deserializer) {
		super(deserializer);
		this.walletName = WalletName.readFrom(deserializer, "wallet");
	}

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getWalletName() {
		return this.walletName;
	}
}
