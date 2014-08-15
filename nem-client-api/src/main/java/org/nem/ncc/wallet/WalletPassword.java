package org.nem.ncc.wallet;

import org.nem.core.serialization.*;
import org.nem.core.utils.StringUtils;

/**
 * Represents a wallet password.
 */
public class WalletPassword {
	private final String password;

	/**
	 * Creates a new wallet password.
	 *
	 * @param password The password.
	 */
	public WalletPassword(final String password) {
		if (StringUtils.isNullOrWhitespace(password)) {
			throw new IllegalArgumentException("password must be non-whitespace");
		}

		this.password = password;
	}

	@Override
	public int hashCode() {
		return this.password.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof WalletPassword)) {
			return false;
		}

		final WalletPassword rhs = (WalletPassword)obj;
		return this.password.equals(rhs.password);
	}

	@Override
	public String toString() {
		return this.password;
	}

	//region inline serialization

	/**
	 * Writes a wallet password object.
	 *
	 * @param serializer The serializer to use.
	 * @param label The optional label.
	 * @param password The object.
	 */
	public static void writeTo(final Serializer serializer, final String label, final WalletPassword password) {
		serializer.writeString(label, password.toString());
	}

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