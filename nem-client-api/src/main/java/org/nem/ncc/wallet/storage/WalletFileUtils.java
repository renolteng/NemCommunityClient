package org.nem.ncc.wallet.storage;

import org.eclipse.jetty.util.UrlEncoded;
import org.nem.ncc.wallet.WalletName;

/**
 * Utility class for dealing with wallet files.
 */
public class WalletFileUtils {

	/**
	 * Gets the file name corresponding to a wallet name.
	 *
	 * @param name The wallet name.
	 * @return The file name.
	 */
	public static String getWalletFileName(final WalletName name) {
		// the wallet name could contain invalid file name characters, so encode the name
		return UrlEncoded.encodeString(name.toString()) + WalletFileDescriptor.WALLET_EXTENSION;
	}

	/**
	 * Returns true if the specified name is a valid wallet file name.
	 *
	 * @param name The file name.
	 * @return true if the name is a valid wallet file name.
	 */
	public static boolean isValidWalletFileName(final String name) {
		return name.toLowerCase().endsWith(WalletFileDescriptor.WALLET_EXTENSION) && name.indexOf(".") > 0;
	}
}
