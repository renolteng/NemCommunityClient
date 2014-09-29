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
		this.encryptionPossible = null != recipient && null != recipient.getKeyPair() && recipient.getKeyPair().hasPublicKey();
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
	 * Gets a value indicating whether or not encryption is supported.
	 *
	 * @return true if encryption is supported.
	 */
	public boolean isEncryptionPossible() {
		return this.encryptionPossible;
	}
}
