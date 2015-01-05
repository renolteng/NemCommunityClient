package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.StorableEntityFileExtension;

/**
 * Represents a file extension for a wallet.
 */
public class WalletFileExtension extends StorableEntityFileExtension<WalletFileExtension> {

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
