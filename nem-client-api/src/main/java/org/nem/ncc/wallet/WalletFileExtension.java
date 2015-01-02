package org.nem.ncc.wallet;

import org.nem.ncc.storage.StorableEntityFileExtension;

/**
 * Represents a file extension for a wallet.
 */
public class WalletFileExtension extends StorableEntityFileExtension {

	/**
	 * Creates a new wallet file extension.
	 *
	 */
	public WalletFileExtension() {
		super(".wlt", WalletFileExtension.class);
	}
}
