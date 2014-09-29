package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.Account;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;

/**
 * Simple view model for a validated transfer which contains a fee that wraps an Amount. and the verification result whether a message can be sent encrypted.
 */
public class ValidatedTransferViewModel implements SerializableEntity {
	private final Amount fee;
	private final boolean encryptionPossible;

	/**
	 * Creates a new validated transfer view model.
	 *
	 * @param fee The fee.
	 * @param recipient The recipient.
	 */
	public ValidatedTransferViewModel(final Amount fee, final Account recipient) {
		this.fee = fee;
		this.encryptionPossible = (recipient == null) ? true : (recipient.getKeyPair() != null ? (recipient.getKeyPair().hasPublicKey() ? true : false) : false);
	}

	@Override
	public void serialize(final Serializer serializer) {
		Amount.writeTo(serializer, "fee", this.fee);
		serializer.writeInt("encryptionPossible", this.encryptionPossible ? 1 : 0);
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
	 * Gets the possibility of sending encrypted messages to the recipient.
	 * 
	 * @return The indicator for possibility of encryption
	 */
	public boolean isEncryptionPossible() {
		return encryptionPossible;
	}
}
