package org.nem.ncc.wallet.storage;

import org.nem.ncc.exceptions.*;

/**
 * Exception that is used when a wallet storage operation fails.
 */
public class WalletStorageException extends NccException {

	/**
	 * Wallet storage exception codes.
	 */
	public static enum Code implements ValueBasedEnum {
		/**
		 * The specified wallet file is actually a directory.
		 */
		WALLET_CANNOT_BE_DIRECTORY(103),

		/**
		 * The specified wallet file has an invalid extension.
		 */
		WALLET_HAS_INVALID_EXTENSION(103),

		/**
		 * The specified wallet file does not exist.
		 */
		WALLET_DOES_NOT_EXIST(101),

		/**
		 * The wallet could not be deleted.
		 */
		WALLET_COULD_NOT_BE_DELETED(103),

		/**
		 * The wallet could not be saved.
		 */
		WALLET_COULD_NOT_BE_SAVED(102),

		/**
		 * The wallet could not be read.
		 */
		WALLET_COULD_NOT_BE_READ(103),

		/**
		 * The wallet password was incorrect.
		 */
		WALLET_PASSWORD_INCORRECT(104),

		/**
		 * The wallet creation failed because a wallet of the same name already exists.
		 */
		WALLET_ALREADY_EXISTS(109);

		private final int value;

		private Code(final int value) {
			this.value = value;
		}

		@Override
		public int value() {
			return this.value;
		}
	}

	/**
	 * Creates a new wallet storage exception.
	 *
	 * @param code The exception code.
	 */
	public WalletStorageException(final Code code) {
		super(code);
	}

	/**
	 * Creates a new wallet storage exception.
	 *
	 * @param code The exception code.
	 * @param cause The exception cause.
	 */
	public WalletStorageException(final Code code, final Throwable cause) {
		super(code, cause);
	}
}