package org.nem.ncc.model;

/**
 * Exception that is used when a configuration operation fails.
 */
public class ConfigurationException extends RuntimeException {

	/**
	 * Creates a new configuration exception.
	 *
	 * @param message The exception message.
	 */
	public ConfigurationException(final String message) {
		super(message);
	}

	/**
	 * Creates a new configuration exception.
	 *
	 * @param cause The exception cause.
	 */
	public ConfigurationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new configuration exception.
	 *
	 * @param message The exception message.
	 * @param cause The exception cause.
	 */
	public ConfigurationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
