package org.nem.ncc.wallet.storage;

import org.nem.ncc.wallet.WalletNamePasswordPair;

/**
 * Factory for creating wallet descriptors.
 */
public interface WalletDescriptorFactory {

	/**
	 * Creates a wallet descriptor corresponding to a new wallet.
	 *
	 * @param pair The wallet name and password.
	 * @return The descriptor.
	 */
	public WalletDescriptor createNew(final WalletNamePasswordPair pair);

	/**
	 * Opens a wallet descriptor corresponding to en existing wallet.
	 *
	 * @param pair The wallet name and password.
	 * @return The descriptor.
	 */
	public WalletDescriptor openExisting(final WalletNamePasswordPair pair);
}
