package org.nem.console.utils;

/**
 * An exception that is thrown when there is a storage error.
 */
public class StorageException extends RuntimeException {

	/**
	 * Creates a new storage exception.
	 *
	 * @param message The exception message.
	 * @param cause The exception cause.
	 */
	protected StorageException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
