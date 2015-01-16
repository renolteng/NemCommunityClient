package org.nem.ncc.wallet;

import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.StorableEntityPassword;

/**
 * Represents a wallet password.
 */
public class WalletPassword extends StorableEntityPassword<WalletPassword> {

	/**
	 * Creates a new wallet password.
	 *
	 * @param password The password.
	 */
	public WalletPassword(final String password) {
		super(password, WalletPassword.class);
	}

	/**
	 * Deserializes a wallet password.
	 *
	 * @param deserializer The deserializer.
	 */
	public WalletPassword(final Deserializer deserializer) {
		super(deserializer, "password", WalletPassword.class);
	}

	//region inline serialization

	/**
	 * Reads a wallet password object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static WalletPassword readFrom(final Deserializer deserializer, final String label) {
		return new WalletPassword(deserializer.readString(label));
	}

	/**
	 * Reads a wallet password object that can be null.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static WalletPassword readFromOptional(final Deserializer deserializer, final String label) {
		final String password = deserializer.readOptionalString(label);
		return null == password ? null : new WalletPassword(password);
	}

	//endregion
}