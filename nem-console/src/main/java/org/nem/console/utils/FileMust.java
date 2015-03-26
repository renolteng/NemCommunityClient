package org.nem.console.utils;

import java.io.File;

public class FileMust {

	public static void exist(final String fileName) {
		if (!exists(fileName)) {
			throw new IllegalArgumentException(String.format("file '%s' does not exist", fileName));
		}
	}

	public static void notExist(final String fileName) {
		if (exists(fileName)) {
			throw new IllegalArgumentException(String.format("file '%s' already exists", fileName));
		}
	}

	private static boolean exists(final String fileName) {
		return new File(fileName).exists();
	}
}
