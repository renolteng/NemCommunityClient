package org.nem.ncc.wallet;

import org.nem.core.serialization.Deserializer;

/**
 * A pair of a wallet name and a wallet password.
 */
public class WalletNamePasswordPair {
	private final WalletName name;
	private final WalletPassword password;

	/**
	 * Creates a new wallet name / password pair.
	 *
	 * @param name The wallet name.
	 * @param password The wallet password.
	 */
	public WalletNamePasswordPair(final WalletName name, final WalletPassword password) {
		this.name = name;
		this.password = password;
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
		this.name = WalletName.readFrom(deserializer, "wallet");
		this.password = WalletPassword.readFrom(deserializer, "password");
	}

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getName() {
		return this.name;
	}

	/**
	 * Gets the wallet password.
	 *
	 * @return The wallet password.
	 */
	public WalletPassword getPassword() {
		return this.password;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() ^ this.password.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof WalletNamePasswordPair)) {
			return false;
		}

		final WalletNamePasswordPair rhs = (WalletNamePasswordPair)obj;
		return this.name.equals(rhs.name) && this.password.equals(rhs.password);
	}
}
