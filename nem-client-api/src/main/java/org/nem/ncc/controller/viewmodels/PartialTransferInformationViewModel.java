package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;

/**
 * Simple view model for a validated transfer which contains a fee that wraps an Amount. and the verification result whether a message can be sent encrypted.
 * TODO 20150131 J-G: why is multisig fee here?
 */
public class PartialTransferInformationViewModel implements SerializableEntity {
	private final Amount fee;
	private final Amount multisigFee;
	private final boolean isEncryptionSupported;

	/**
	 * Creates a new partial transfer information view model.
	 *
	 * @param fee The fee.
	 * @param multisigFee The multisig fee.
	 * @param isEncryptionSupported true if encryption is supported.
	 */
	public PartialTransferInformationViewModel(final Amount fee, final Amount multisigFee, final boolean isEncryptionSupported) {
		this.fee = fee;
		this.multisigFee = multisigFee;
		this.isEncryptionSupported = isEncryptionSupported;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Amount.writeTo(serializer, "fee", this.fee);
		Amount.writeTo(serializer, "multisigFee", this.multisigFee);
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
	 * Gets the multisig fee.
	 *
	 * @return The multisig fee (0 if not a multisig transaction).
	 */
	public Amount getMultisigFee() {
		return this.multisigFee;
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
