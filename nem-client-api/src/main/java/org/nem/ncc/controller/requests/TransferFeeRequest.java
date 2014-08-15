package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.WalletName;

/**
 * A request containing all information necessary to determine a transfer fee.
 */
public class TransferFeeRequest {
	private final WalletName walletName;
	private final Address senderAddress;
	private final Address recipientAddress;
	private final Amount amount;
	private final String message;
	private final boolean shouldEncrypt;
	private final int hoursDue;

	/**
	 * Creates a new transfer fee view model.
	 */
	public TransferFeeRequest(
			final WalletName walletName,
			final Address senderAddress,
			final Address recipientAddress,
			final Amount amount,
			final String message,
			final boolean shouldEncrypt,
			final int hoursDue) {
		this.walletName = walletName;
		this.senderAddress = senderAddress;
		this.recipientAddress = recipientAddress;
		this.amount = amount;
		this.message = message;
		this.shouldEncrypt = shouldEncrypt;
		this.hoursDue = hoursDue;
	}

	/**
	 * Deserializes a transfer fee view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public TransferFeeRequest(final Deserializer deserializer) {
		this.walletName = WalletName.readFrom(deserializer, "wallet");
		this.senderAddress = Address.readFrom(deserializer, "account");
		this.recipientAddress = Address.readFrom(deserializer, "recipient");
		this.amount = Amount.readFrom(deserializer, "amount");
		this.message = deserializer.readOptionalString("message");
		this.shouldEncrypt = 0 != deserializer.readInt("encrypt");
		this.hoursDue = deserializer.readInt("hours_due");
	}

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getWalletName() {
		return this.walletName;
	}

	/**
	 * Gets the sender account id.
	 *
	 * @return The sender account id.
	 */
	public Address getSenderAddress() {
		return this.senderAddress;
	}

	/**
	 * Gets the recipient id.
	 *
	 * @return The recipient id.
	 */
	public Address getRecipientAddress() {
		return this.recipientAddress;
	}

	/**
	 * Gets the (optional) message.
	 *
	 * @return The message.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets the amount.
	 *
	 * @return The amount.
	 */
	public Amount getAmount() {
		return this.amount;
	}

	/**
	 * Gets a value indicating whether or not the payload should be encrypted.
	 *
	 * @return true if the payload should be encrypted.
	 */
	public boolean shouldEncrypt() {
		return this.shouldEncrypt;
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
