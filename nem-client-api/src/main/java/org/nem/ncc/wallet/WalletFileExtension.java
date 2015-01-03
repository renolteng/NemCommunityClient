package org.nem.ncc.wallet;

import org.nem.ncc.storage.StorableEntityFileExtension;

/**
 * Represents a file extension for a wallet.
 */
public class WalletFileExtension extends StorableEntityFileExtension {

	/**
	 * Creates a new wallet file extension.
	 */
	public WalletFileExtension() {
		this(".wlt");
	}

	/**
	 * Creates a new wallet file extension.
	 *
	 * @param fileExtension The file extension.
	 */
	public WalletFileExtension(final String fileExtension) {
		super(fileExtension, WalletFileExtension.class);
	}
}
