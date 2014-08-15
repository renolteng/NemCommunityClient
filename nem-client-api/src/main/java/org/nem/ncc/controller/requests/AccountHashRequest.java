package org.nem.ncc.controller.requests;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;

/**
 * A request representing an account and an optional hash.
 */
public class AccountHashRequest extends AccountIdRequest {
	private final Hash hash;

	/**
	 * Creates a new account / hash request.
	 *
	 * @param address The account address.
	 * @param hash The (optional) hash.
	 */
	public AccountHashRequest(
			final Address address,
			final Hash hash) {
		super(address);
		this.hash = hash;
	}

	/**
	 * Deserializes an account / hash request.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountHashRequest(final Deserializer deserializer) {
		super(deserializer);
		final String hashString = deserializer.readOptionalString("hash");
		this.hash = (null == hashString) ? null : Hash.fromHexString(hashString);
	}

	/**
	 * Gets the hash.
	 *
	 * @return The hash.
	 */
	public Hash getHash() {
		return this.hash;
	}
}