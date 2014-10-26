package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.Account;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;

/**
 * Simple view model for a validated transfer which contains a fee that wraps an Amount. and the verification result whether a message can be sent encrypted.
 */
public class PartialTransferInformationViewModel implements SerializableEntity {
	private final Amount fee;
	private final boolean isEncryptionSupported;

	/**
	 * Creates a new partial transfer information view model.
	 *
	 * @param fee The fee.
	 * @param isEncryptionSupported true if encryption is supported.
	 */
	public PartialTransferInformationViewModel(final Amount fee, final boolean isEncryptionSupported) {
		this.fee = fee;
		this.isEncryptionSupported = isEncryptionSupported;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Amount.writeTo(serializer, "fee", this.fee);
		serializer.writeInt("encryptionSupported", this.isEncryptionSupported ? 1 : 0);
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
	public Boolean isEncryptionSupported() {
		return this.isEncryptionSupported;
	}
}
