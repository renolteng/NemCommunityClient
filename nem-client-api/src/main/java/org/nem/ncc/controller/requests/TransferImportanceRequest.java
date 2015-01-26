package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.*;

/**
 * A request containing all information necessary to create importance transfer.
 */
public class TransferImportanceRequest extends AccountWalletPasswordRequest {
	private final int hoursDue;

	/**
	 * Creates a transfer importance request.
	 *
	 * @param address The account address.
	 * @param walletName The wallet name.
	 * @param password The password.
	 */
	public TransferImportanceRequest(
			final Address address,
			final WalletName walletName,
			final WalletPassword password,
			final int hoursDue) {
		super(address, walletName, password);
		this.hoursDue = hoursDue;
	}

	/**
	 * Deserializes a transfer importance request.
	 *
	 * @param deserializer The deserializer.
	 */
	public TransferImportanceRequest(final Deserializer deserializer) {
		super(deserializer);
		this.hoursDue = deserializer.readInt("hoursDue");
	}

	/**
	 * Gets the wallet password.
	 *
	 * @return The wallet password.
	 */
	public WalletPassword getPassword() {
		return this.getWalletPassword();
	}

	/**
	 * Gets the hours due.
	 *
	 * @return The hours due.
	 */
	public int getHoursDue() {
		return this.hoursDue;
	}
}
