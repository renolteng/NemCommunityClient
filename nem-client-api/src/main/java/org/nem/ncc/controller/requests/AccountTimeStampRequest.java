package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.core.time.*;

/**
 * A view model representing an account and an optional timestamp.
 */
public class AccountTimeStampRequest extends AccountIdRequest {
	private final Long timeStamp;

	/**
	 * Creates a new account / timestamp view model.
	 *
	 * @param address The account address.
	 * @param timeStamp The (optional) timestamp (unix time).
	 */
	public AccountTimeStampRequest(
			final Address address,
			final Long timeStamp) {
		super(address);
		this.timeStamp = timeStamp;
	}

	/**
	 * Deserializes an account / timestamp view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountTimeStampRequest(final Deserializer deserializer) {
		super(deserializer);
		final Long timeStamp = deserializer.readOptionalLong("timeStamp");
		this.timeStamp = (null == timeStamp || -1 == timeStamp) ? null : timeStamp;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return The timestamp.
	 */
	public TimeInstant getTimeStamp() {
		return null == this.timeStamp ? null : UnixTime.fromUnixTimeInMillis(this.timeStamp).getTimeInstant();
	}
}