package org.nem.console.utils;

import org.apache.commons.cli.Options;

/**
 * Helper class for manipulating CLI options.
 */
public class OptionsUtils {

	/**
	 * Adds default write options.
	 *
	 * @param options The options to augment.
	 */
	public static void addWriteOptions(final Options options) {
		options.addOption("output", true, "The output cold wallet file");
		addEncryptionDecryptionOptions(options);
	}

	/**
	 * Adds default read options.
	 *
	 * @param options The options to augment.
	 */
	public static void addReadOptions(final Options options) {
		options.addOption("input", true, "The input cold wallet file");
		addEncryptionDecryptionOptions(options);
	}

	private static void addEncryptionDecryptionOptions(final Options options) {
		options.addOption("pass", true, "The cold wallet password");
		options.addOption("numHashes", true, "The number of password hashes");
	}
}
