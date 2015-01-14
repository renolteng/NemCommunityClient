package org.nem.ncc.addressbook.storage;

import org.nem.ncc.exceptions.*;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;

import java.util.HashMap;

/**
 * Exception that is used when an address book operation fails.
 */
public class AddressBookStorageException extends StorableEntityStorageException {

	public static final int OFFSET = 20;

	/**
	 * AddressBook storage exception codes.
	 */
	public static enum Code implements ValueBasedEnum {
		/**
		 * The specified address book file does not exist.
		 */
		ADDRESS_BOOK_DOES_NOT_EXIST(101 + OFFSET),

		/**
		 * The address book could not be saved.
		 */
		ADDRESS_BOOK_COULD_NOT_BE_SAVED(102 + OFFSET),

		/**
		 * The address book could not be read.
		 */
		ADDRESS_BOOK_COULD_NOT_BE_READ(103 + OFFSET),

		/**
		 * The address book password was incorrect.
		 */
		ADDRESS_BOOK_PASSWORD_INCORRECT(104 + OFFSET),

		/**
		 * The address book password cannot be a null sting.
		 */
		ADDRESS_BOOK_PASSWORD_CANNOT_BE_NULL(105 + OFFSET),

		/**
		 * The address book creation failed because an address book of the same name already exists.
		 */
		ADDRESS_BOOK_ALREADY_EXISTS(109 + OFFSET),

		/**
		 * The specified address book file is actually a directory.
		 */
		ADDRESS_BOOK_CANNOT_BE_DIRECTORY(111 + OFFSET),

		/**
		 * The specified address book file has an invalid extension.
		 */
		ADDRESS_BOOK_HAS_INVALID_EXTENSION(112 + OFFSET),

		/**
		 * The address book could not be deleted.
		 */
		ADDRESS_BOOK_COULD_NOT_BE_DELETED(113 + OFFSET);

		private static final HashMap<Integer, Code> lookup = new HashMap<>();

		static {
			for (Code c : Code.values()) {
				lookup.put(c.value(), c);
			}
		}

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
	 * Creates a new address book storage exception.
	 *
	 * @param code The exception code.
	 */
	public AddressBookStorageException(final ValueBasedEnum code) {
		super(code);
	}

	/**
	 * Creates a new address book storage exception.
	 *
	 * @param code The exception code.
	 * @param cause The exception cause.
	 */
	public AddressBookStorageException(final ValueBasedEnum code, final Throwable cause) {
		super(code, cause);
	}

	/**
	 * Creates a new storable entity storage exception.
	 *
	 * @param value The exception code value.
	 */
	public AddressBookStorageException(final int value) {
		super(Code.lookup.get(value));
	}

	/**
	 * Creates a new storable entity storage exception.
	 *
	 * @param value The exception code value.
	 * @param cause The exception cause.
	 */
	public AddressBookStorageException(final int value, final Throwable cause) {
		super(Code.lookup.get(value), cause);
	}
}
