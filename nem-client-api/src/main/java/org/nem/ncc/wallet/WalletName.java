package org.nem.ncc.wallet;

import org.nem.core.serialization.*;
import org.nem.core.utils.StringUtils;

/**
 * Represents a wallet name.
 */
public class WalletName {
	private final String name;

	/**
	 * Creates a new wallet name.
	 *
	 * @param name The name.
	 */
	public WalletName(final String name) {
		if (StringUtils.isNullOrWhitespace(name)) {
			throw new IllegalArgumentException("name must be non-whitespace");
		}

		this.name = name;
	}

	public WalletName(final Deserializer deserializer) {
		this.name = deserializer.readString("wallet");
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof WalletName)) {
			return false;
		}

		final WalletName rhs = (WalletName)obj;
		return this.name.equals(rhs.name);
	}

	@Override
	public String toString() {
		return this.name;
	}

	//region inline serialization

	/**
	 * Writes a wallet name object.
	 *
	 * @param serializer The serializer to use.
	 * @param label The optional label.
	 * @param name The object.
	 */
	public static void writeTo(final Serializer serializer, final String label, final WalletName name) {
		serializer.writeString(label, name.toString());
	}

	/**
	 * Reads a wallet name object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static WalletName readFrom(final Deserializer deserializer, final String label) {
		return new WalletName(deserializer.readString(label));
	}

	//endregion
}