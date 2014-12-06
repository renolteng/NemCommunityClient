package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;

/**
 * A request representing an account and an optional transaction id.
 * TODO 20141201 J-B: can we get rid of AccountHashRequest now?
 */
public class AccountTransactionIdRequest extends AccountIdRequest {
	private final Long transactionId;

	/**
	 * Creates a new account / transactionId request.
	 *
	 * @param address The account address.
	 * @param transactionId The (optional) transaction id.
	 */
	public AccountTransactionIdRequest(
			final Address address,
			final Long transactionId) {
		super(address);
		this.transactionId = transactionId;
	}

	/**
	 * Deserializes an account / hash request.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountTransactionIdRequest(final Deserializer deserializer) {
		super(deserializer);
		this.transactionId = deserializer.readOptionalLong("id");
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return The transaction id.
	 */
	public Long getTransactionId() {
		return this.transactionId;
	}
}
