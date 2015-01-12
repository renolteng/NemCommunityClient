package org.nem.ncc.controller.viewmodels;

import org.nem.core.crypto.Hash;
import org.nem.core.model.*;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.*;
import org.nem.core.time.UnixTime;
import org.nem.core.utils.StringEncoder;

/**
 * A view model representing a transfer transaction.
 */
public class TransactionViewModel implements SerializableEntity {
	enum Type {
		Unknown(0),
		Transfer(1),
		Importance_Transfer(2),
		Multisig_Modification(3),
		Multisig_Transfer(4);

		private final int value;

		private Type(final int value) {
			this.value = value;
		}

		/**
		 * Gets the underlying integer representation of the status.
		 *
		 * @return The underlying value.
		 */
		public int getValue() {
			return this.value;
		}
	}

	private Type transactionViewModelType;
	private long transactionId;
	private final Hash transactionHash;

	private final Address signer;
	private final long timeStamp;
	private final Amount fee;

	private boolean isConfirmed;
	private long confirmations;
	private long blockHeight;

	/**
	 * Creates a new transaction view model around a transaction (confirmed or unconfirmed).
	 *
	 * @param transactionViewModelType
	 * @param metaDataPair
	 * @param lastBlockHeight
	 */
	public TransactionViewModel(
			final Type transactionViewModelType,
			final TransactionMetaDataPair metaDataPair,
			final BlockHeight lastBlockHeight) {
		final Transaction transaction = metaDataPair.getTransaction();
		if (TransactionTypes.TRANSFER != transaction.getType() &&
				TransactionTypes.IMPORTANCE_TRANSFER != transaction.getType() &&
				TransactionTypes.MULTISIG != transaction.getType()) {
			throw new IllegalArgumentException("TransferViewModel can only be created around TRANSFER or IMPORTANCE_TRANSFER");
		}

		this.transactionViewModelType = transactionViewModelType;
		this.transactionHash = HashUtils.calculateHash(transaction);
		this.signer = transaction.getSigner().getAddress();
		this.timeStamp = UnixTime.fromTimeInstant(transaction.getTimeStamp()).getMillis();
		this.fee = transaction.getFee();

		if (metaDataPair.getMetaData() == null) {
			this.confirmations = 0;
			this.blockHeight = 0;
			this.transactionId = this.transactionHash.getShortId();
			this.isConfirmed = false;

		} else {
			this.confirmations = lastBlockHeight.subtract(metaDataPair.getMetaData().getHeight()) + 1;
			this.blockHeight = metaDataPair.getMetaData().getHeight().getRaw();
			// TODO 20141201 J-B: is it confusing that both Transaction and TransactionMetaData have ids now?
			// we might want to rename one
			this.transactionId = metaDataPair.getMetaData().getId();
			this.isConfirmed = true;
		}
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return The transaction id.
	 */
	public long getId() {
		return this.transactionId;
	}

	/**
	 * Gets the transaction hash.
	 *
	 * @return The transaction hash.
	 */
	public Hash getHash() {
		return this.transactionHash;
	}

	/**
	 * Gets the signer address.
	 *
	 * @return The signer address.
	 */
	public Address getSigner() {
		return this.signer;
	}

	/**
	 * Gets the timestamp (unix time).
	 *
	 * @return The timestamp.
	 */
	public long getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Gets the fee.
	 *
	 * @return The fee.
	 */
	public Amount getFee() {
		return this.fee;
	}

	/**
	 * Gets a value indicating whether or not the transaction is confirmed.
	 *
	 * @return true if the message is confirmed.
	 */
	public boolean isConfirmed() {
		return this.isConfirmed;
	}

	/**
	 * Gets the number of confirmations.
	 *
	 * @return The number of confirmations.
	 */
	public long getConfirmations() {
		return this.confirmations;
	}

	/**
	 * Gets the height of the block that includes the transaction
	 * or zero if the transaction is unconfirmed.
	 *
	 * @return The height of the block that includes the transaction.
	 */
	public long getBlockHeight() {
		return this.blockHeight;
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.serializeImpl(serializer);
	}

	protected void serializeImpl(final Serializer serializer) {
		serializer.writeInt("type", this.transactionViewModelType.getValue());
		serializer.writeLong("id", this.transactionId);
		serializer.writeString("hash", this.transactionHash.toString());

		Address.writeTo(serializer, "sender", this.signer);
		serializer.writeLong("timeStamp", this.timeStamp);
		Amount.writeTo(serializer, "fee", this.fee);

		serializer.writeInt("confirmed", this.isConfirmed ? 1 : 0);
		serializer.writeLong("confirmations", this.confirmations);
		serializer.writeLong("blockHeight", this.blockHeight);
	}
}
