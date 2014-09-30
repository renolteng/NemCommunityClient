package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.*;

/**
 * A request containing all information necessary to create a transfer.
 */
public class TransferSendRequest extends TransferValidateRequest {
	private final WalletPassword password;
	private final Amount fee;

	/**
	 * Creates a new transfer send request.
	 */
	public TransferSendRequest(
			final WalletName walletName,
			final Address senderAddress,
			final Address recipientAddress,
			final Amount amount,
			final String message,
			final boolean shouldEncrypt,
			final int hoursDue,
			final WalletPassword password,
			final Amount fee) {
		super(walletName, senderAddress, recipientAddress, amount, message, shouldEncrypt, hoursDue);
		this.password = password;
		this.fee = fee;
	}

	/**
	 * Deserializes a transfer send request.
	 *
	 * @param deserializer The deserializer.
	 */
	public TransferSendRequest(final Deserializer deserializer) {
		super(deserializer);
		this.password = WalletPassword.readFrom(deserializer, "password");
		this.fee = Amount.readFrom(deserializer, "fee");
	}

	/**
	 * Gets the password.
	 *
	 * @return The password.
	 */
	public WalletPassword getPassword() {
		return this.password;
	}

	/**
	 * Gets the fee.
	 *
	 * @return The fee.
	 */
	public Amount getFee() {
		return this.fee;
	}
}
