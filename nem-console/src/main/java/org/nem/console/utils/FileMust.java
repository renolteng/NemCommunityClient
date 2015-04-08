package org.nem.console.utils;

import java.io.File;

/**
 * Helper class for checking file preconditions.
 */
public class FileMust {

	/**
	 * Verifies that a file must exist.
	 *
	 * @param fileName The name of the file.
	 */
	public static void exist(final String fileName) {
		if (!exists(fileName)) {
			throw new IllegalArgumentException(String.format("file '%s' does not exist", fileName));
		}
	}

	/**
	 * Verifies that a file must not exist.
	 *
	 * @param fileName The name of the file.
	 */
	public static void notExist(final String fileName) {
		if (exists(fileName)) {
			throw new IllegalArgumentException(String.format("file '%s' already exists", fileName));
		}
	}

	private static boolean exists(final String fileName) {
		return new File(fileName).exists();
	}
}
