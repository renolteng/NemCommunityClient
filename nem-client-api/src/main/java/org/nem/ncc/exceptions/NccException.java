package org.nem.ncc.exceptions;

/**
 * Base class for all NCC exceptions.
 */
public class NccException extends RuntimeException {
	/**
	 * General NIS error codes.
	 */
	public static enum Code implements ValueBasedEnum {
		/**
		 * The specified wallet is not open.
		 */
		WALLET_IS_NOT_OPEN(106),

		/**
		 * The specified address book is not open.
		 */
		ADDRESS_BOOK_IS_NOT_OPEN(126),

		/**
		 * The recipient public key is unknown.
		 */
		NO_PUBLIC_KEY(202),

		/**
		 * The recipient public key is unknown.
		 */
		COSIGNATORY_NO_PUBLIC_KEY(203),

		/**
		 * There was a problem with the account cache.
		 */
		ACCOUNT_CACHE_ERROR(901),

		/**
		 * The public key derived from the private key does not match the given public key.
		 */
		PRIVATE_KEY_PUBLIC_KEY_MISMATCH(1000),

		/**
		 * The address derived from the public key does not match the given address.
		 */
		PUBLIC_KEY_ADDRESS_MISMATCH(1001),

		/**
		 * The address does not belong to the main network.
		 */
		NOT_MAIN_NETWORK_ADDRESS(1002),

		/**
		 * NCC is disconnected from the network.
		 */
		NIS_NOT_AVAILABLE(305);

		private final int value;

		private Code(final int value) {
			this.value = value;
		}

		@Override
		public int value() {
			return this.value;
		}
	}

	private final ValueBasedEnum code;

	/**
	 * Creates a new NCC exception.
	 *
	 * @param code The error code.
	 */
	public NccException(final Code code) {
		this((ValueBasedEnum)code);
	}

	/**
	 * Creates a new NCC exception.
	 *
	 * @param code The error code.
	 */
	protected NccException(final ValueBasedEnum code) {
		super(code.toString());
		this.code = code;
	}

	/**
	 * Creates a new NCC exception.
	 *
	 * @param code The exception code.
	 * @param cause The exception cause.
	 */
	protected NccException(final ValueBasedEnum code, final Throwable cause) {
		super(code.toString(), cause);
		this.code = code;
	}

	/**
	 * Gets the underlying code.
	 *
	 * @return The underlying code.
	 */
	public ValueBasedEnum getCode() {
		return this.code;
	}
}
