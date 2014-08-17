package org.nem.ncc.controller.interceptors;

/**
 * Exception that is thrown when an unauthorized request is made.
 */
public class UnauthorizedAccessException extends RuntimeException {

	/**
	 * Creates a new unauthorized access exception.
	 *
	 * @param message The exception message.
	 */
	public UnauthorizedAccessException(final String message) {
		super(message);
	}
}
