package org.nem.ncc.wallet;

import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.StorableEntityName;

/**
 * Represents a wallet name.
 */
public class WalletName extends StorableEntityName {

	/**
	 * Creates a new wallet name.
	 *
	 * @param name The name.
	 */
	public WalletName(final String name) {
		super(name, "wallet", WalletName.class);
	}

	public WalletName(final Deserializer deserializer) {
		super(deserializer, "wallet", WalletName.class);
	}

	//region inline serialization

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