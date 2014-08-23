package org.nem.monitor;

import java.util.logging.Logger;

/**
 * Exception that is thrown when there is an issue interacting with the system tray.
 */
public class SystemTrayException extends RuntimeException {
	private static final Logger LOGGER = Logger.getLogger(SystemTrayException.class.getName());

	/**
	 * Creates a system tray exception.
	 *
	 * @param message The message.
	 */
	public SystemTrayException(final String message) {
		super(message);
		LOGGER.severe(message);
	}

	/**
	 * Creates a system tray exception.
	 *
	 * @param message The message.
	 * @param cause The cause.
	 */
	public SystemTrayException(final String message, final Throwable cause) {
		super(message, cause);
		LOGGER.severe(String.format("%s: %s", message, cause));
	}
}
