package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;

/**
 * Simple view model for a fee that wraps an Amount.
 */
public class FeeViewModel implements SerializableEntity {
	private final Amount fee;

	/**
	 * Creates a new fee view model.
	 *
	 * @param fee The fee.
	 */
	public FeeViewModel(final Amount fee) {
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
