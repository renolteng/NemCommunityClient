package org.nem.ncc.wallet;

import org.nem.core.serialization.Deserializer;
import org.nem.ncc.storage.StorableEntityNamePasswordPair;

/**
 * A pair of a wallet name and a wallet password.
 */
public class WalletNamePasswordPair extends StorableEntityNamePasswordPair {

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

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getName() {
		// TODO 20150201 BR -> ? any way to get around this cast?
		return (WalletName)super.getName();
	}

	/**
	 * Gets the wallet password.
	 *
	 * @return The wallet password.
	 */
	public WalletPassword getPassword() {
		// TODO 20150201 BR -> ? any way to get around this cast?
		return (WalletPassword)super.getPassword();
	}
}
