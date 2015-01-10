package org.nem.ncc.storable.entity;

import org.nem.ncc.exceptions.*;

/**
 * Exception that is used when a storable entity storage operation fails.
 */
public class StorableEntityStorageException extends NccException {

	/**
	 * Storable entity storage exception codes.
	 */
	public static enum Code implements ValueBasedEnum {
		/**
		 * The specified storable entity file is actually a directory.
		 */
		STORABLE_ENTITY_CANNOT_BE_DIRECTORY(103),

		/**
		 * The specified storable entity file has an invalid extension.
		 */
		STORABLE_ENTITY_HAS_INVALID_EXTENSION(103),

		/**
		 * The specified storable entity file does not exist.
		 */
		STORABLE_ENTITY_DOES_NOT_EXIST(101),

		/**
		 * The storable entity could not be deleted.
		 */
		STORABLE_ENTITY_COULD_NOT_BE_DELETED(103),

		/**
		 * The storable entity could not be saved.
		 */
		STORABLE_ENTITY_COULD_NOT_BE_SAVED(102),

		/**
		 * The storable entity could not be read.
		 */
		STORABLE_ENTITY_COULD_NOT_BE_READ(103),

		/**
		 * The storable entity password was incorrect.
		 */
		STORABLE_ENTITY_PASSWORD_INCORRECT(104),

		/**
		 * The storable entity password cannot be a null sting.
		 */
		STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL(105),

		/**
		 * The storable entity creation failed because a storable entity of the same name already exists.
		 */
		STORABLE_ENTITY_ALREADY_EXISTS(109),

		/**
		 * The name of the provided file doesn't not match the storable entity name.
		 */
		STORABLE_ENTITY_FILE_NAME_DOES_NOT_MATCH_ENTITY_NAME(110),

		/**
		 * The file extension of the provided file doesn't not match the storable entity file extension.
		 */
		STORABLE_ENTITY_FILE_EXTENSION_DOES_NOT_MATCH_ENTITY_FILE_EXTENSION(111);

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
	 * Creates a new storable entity storage exception.
	 *
	 * @param code The exception code.
	 */
	public StorableEntityStorageException(final Code code) {
		super(code);
	}

	/**
	 * Creates a new storable entity storage exception.
	 *
	 * @param code The exception code.
	 * @param cause The exception cause.
	 */
	public StorableEntityStorageException(final Code code, final Throwable cause) {
		super(code, cause);
	}
}
