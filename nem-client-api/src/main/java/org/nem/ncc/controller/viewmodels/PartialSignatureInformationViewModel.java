package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;

/**
 * Simple view model for a validated transfer which contains a fee that wraps an Amount. and the verification result whether a message can be sent encrypted.
 */
public class PartialSignatureInformationViewModel implements SerializableEntity {
	private final Amount fee;

	/**
	 * Creates a new partial transfer information view model.
	 *
	 * @param fee The fee.
	 */
	public PartialSignatureInformationViewModel(final Amount fee) {
		this.fee = fee;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Amount.writeTo(serializer, "fee", this.fee);
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
