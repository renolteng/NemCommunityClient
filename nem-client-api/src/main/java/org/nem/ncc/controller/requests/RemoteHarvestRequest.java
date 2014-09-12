package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.*;

public class RemoteHarvestRequest extends AccountWalletRequest {
	private WalletPassword password;

	/**
	 * Creates a password / account / wallet request.
	 *
	 * @param address The account address.
	 * @param walletName The wallet name.
	 * @param password The password.
	 */
	public RemoteHarvestRequest(final Address address, 
			final WalletName walletName, 
			final WalletPassword password) {
		super(address, walletName);
		this.password = password;
	}

	/**
	 * Deserializes a password / account / wallet request.
	 *
	 * @param deserializer The deserializer.
	 */
	public RemoteHarvestRequest(final Deserializer deserializer) {
		super(deserializer);
		this.password = WalletPassword.readFrom(deserializer, "password");
	}

	/**
	 * Gets the wallet password.
	 *
	 * @return The wallet password.
	 */
	public WalletPassword getPassword() {
		return this.password;
	}

}
