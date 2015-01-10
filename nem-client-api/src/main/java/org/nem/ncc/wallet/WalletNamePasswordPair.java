package org.nem.ncc.wallet;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storable.entity.StorableEntityNamePasswordPair;

/**
 * A pair of a wallet name and a wallet password.
 */
public class WalletNamePasswordPair extends StorableEntityNamePasswordPair<WalletName, WalletPassword, WalletNamePasswordPair> {

	/**
	 * Creates a new wallet name / password pair.
	 *
	 * @param name The wallet name.
	 * @param password The wallet password.
	 */
	public WalletNamePasswordPair(final WalletName name, final WalletPassword password) {
		super(name, password, WalletNamePasswordPair.class);
	}

	/**
	 * Creates a new wallet name / password pair.
	 *
	 * @param name The wallet name.
	 * @param password The wallet password.
	 */
	public WalletNamePasswordPair(final String name, final String password) {
		this(new WalletName(name), new WalletPassword(password));
	}

	/**
	 * Deserializes a new wallet name / password pair.
	 *
	 * @param deserializer The deserializer.
	 */
	public WalletNamePasswordPair(final Deserializer deserializer) {
		super(new WalletName(deserializer), new WalletPassword(deserializer), WalletNamePasswordPair.class);
	}
}
