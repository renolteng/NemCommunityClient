package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.*;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.StringEncoder;

/**
 * TODO 20150131 J-G: fix empty comments
 * TODO 20150131 J-G: i guess the test coverage is ok for this as you mostly just split up the original transferviewmodel into two?
 * > i mean i don't think you added anything to this class's functionality?
 */
public class TransferTransactionViewModel extends TransactionViewModel {
	private static final int INCOMING_FLAG = 1;
	private static final int OUTGOING_FLAG = 2;
	private static final int SELF_FLAG = 3;

	private final Address recipient;
	private final Amount amount;
	private final String message;
	private final boolean isEncrypted;
	private final int direction; // 1 - incoming, 2 - outgoing, 3 - self

	public TransferTransactionViewModel(final TransactionMetaDataPair metaDataPair, final Address relativeAccountAddress, final BlockHeight lastBlockHeight) {
		super(Type.Transfer, metaDataPair, lastBlockHeight);

		final TransferTransaction transfer = (TransferTransaction)metaDataPair.getTransaction();
		this.recipient = transfer.getRecipient().getAddress();
		this.amount = transfer.getAmount();

		final Message message = transfer.getMessage();
		this.message = getMessageText(message);
		this.isEncrypted = isEncrypted(message);

		this.direction = this.calculateDirection(relativeAccountAddress);
	}

	private int calculateDirection(final Address relativeAccountAddress) {
		if (this.recipient.equals(relativeAccountAddress)) {
			return this.getSigner().equals(relativeAccountAddress) ? SELF_FLAG : INCOMING_FLAG;
		}
		return OUTGOING_FLAG;
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		Address.writeTo(serializer, "recipient", this.recipient);
		Amount.writeTo(serializer, "amount", this.amount);
		if (this.message != null) {
			serializer.writeString("message", this.message);
			serializer.writeInt("encrypted", this.isEncrypted ? 1 : 0);
		}

		serializer.writeInt("direction", this.direction);
	}

	/**
	 * Gets the recipient address.
	 *
	 * @return The recipient address.
	 */
	public Address getRecipient() {
		return this.recipient;
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
	 * Gets the message.
	 *
	 * @return The message.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets a value indicating whether or not the message is encrypted.
	 *
	 * @return true if the message is encrypted.
	 */
	public boolean isEncrypted() {
		return this.isEncrypted;
	}

	/**
	 * Gets the transaction direction relative to the reference account.
	 *
	 * @return The transaction direction.
	 */
	public int getDirection() {
		return this.direction;
	}

	//region helper funcions
	private static boolean isEncrypted(final Message message) {
		return null != message && MessageTypes.SECURE == message.getType();
	}

	private static String getMessageText(final Message message) {
		if (null == message) {
			return null;
		}

		return message.canDecode()
				? StringEncoder.getString(message.getDecodedPayload())
				: "Warning: message cannot be decoded!";
	}
	//endregion
}
