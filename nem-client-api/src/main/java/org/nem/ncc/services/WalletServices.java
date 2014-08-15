package org.nem.ncc.services;

import org.nem.core.model.Address;
import org.nem.ncc.wallet.*;

/**
 * A set of services for interacting with wallets.
 */
public interface WalletServices {

	/**
	 * Retrieves the specified wallet.
	 *
	 * @param name The name of the wallet.
	 * @return The wallet.
	 */
	public Wallet get(final WalletName name);

	/**
	 * Searches all open wallets for a wallet account with the specified address.
	 *
	 * @param address The address.
	 * @return The wallet account or null if no matches were found.
	 */
	public WalletAccount tryFindOpenAccount(final Address address);

	/**
	 * Opens and returns the specified wallet.
	 *
	 * @param pair The wallet name and password pair.
	 * @return The wallet.
	 */
	public Wallet open(final WalletNamePasswordPair pair);

	/**
	 * Creates and returns the specified wallet.
	 *
	 * @param pair The wallet name and password pair.
	 * @return The wallet.
	 */
	public Wallet create(final WalletNamePasswordPair pair);

	/**
	 * Closes the specified wallet.
	 *
	 * @param name The name of the wallet.
	 */
	public void close(final WalletName name);

	/**
	 * Renames the specified wallet.
	 *
	 * @param originalPair The original wallet name and password pair.
	 * @param desiredPair The desired wallet name and password pair.
	 */
	public void move(final WalletNamePasswordPair originalPair, final WalletNamePasswordPair desiredPair);
}
