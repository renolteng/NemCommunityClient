package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.StorableEntityFileExtension;

/**
 * Represents a file extension for a wallet.
 */
public class WalletFileExtension extends StorableEntityFileExtension<WalletFileExtension> {
	public static final String DEFAULT_FILE_EXTENSION = ".wlt";

	/**
	 * Creates a new wallet file extension.
	 */
	public WalletFileExtension() {
		this(DEFAULT_FILE_EXTENSION);
	}

	/**
	 * Creates a new wallet file extension.
	 *
	 * @param fileExtension The file extension.
	 */
	public WalletFileExtension(final String fileExtension) {
		super(fileExtension, WalletFileExtension.class);
	}

	/**
	 * Gets a value indicating whether the extension is the default extension.
	 *
	 * @return true if the extension is the default extension, false otherwise.
	 */
	public boolean isDefaultExtension() {
		return this.toString().toLowerCase().equals(DEFAULT_FILE_EXTENSION);
	}

	/**
	 * Gets the default file extension.
	 *
	 * @return The file extension.
	 */
	public static WalletFileExtension getDefaultFileExtension() {
		return new WalletFileExtension(DEFAULT_FILE_EXTENSION);
	}

	/**
	 * Gets a value indicating whether the supplies file name is valid and has the default extension.
	 *
	 * @param fileName The file name.
	 * @return true if the file name is valid and has the default extension, false otherwise.
	 */
	public static boolean isValidAndHasDefaultExtension(final String fileName) {
		return fileName.toLowerCase().endsWith(DEFAULT_FILE_EXTENSION) && fileName.indexOf(".") > 0;
	}
}
