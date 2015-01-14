package org.nem.ncc.addressbook;

import org.nem.ncc.exceptions.*;

/**
 * Exception that is used when an address book operation fails.
 */
public class AddressBookException extends NccException {
	/**
	 * Address book storage exception codes.
	 */
	public static enum Code implements ValueBasedEnum {
		/**
		 * An address can not be added to the address book multiple times.
		 */
		ADDRESS_BOOK_ALREADY_CONTAINS_ADDRESS(130),

		/**
		 * An address can not be added to the address book multiple times.
		 */
		ADDRESS_NOT_IN_ADDRESS_BOOK(127);

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
	 * Creates a new wallet exception.
	 *
	 * @param code The exception code.
	 */
	public AddressBookException(final Code code) {
		super(code);
	}
}
