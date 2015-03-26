package org.nem.console.utils;

import org.apache.commons.cli.Options;

public class OptionsUtils {

	public static void addWriteOptions(final Options options) {
		options.addOption("output", true, "The output cold wallet file");
		addEncryptionDecryptionOptions(options);
	}

	public static void addReadOptions(final Options options) {
		options.addOption("input", true, "The input cold wallet file");
		addEncryptionDecryptionOptions(options);
	}

	private static void addEncryptionDecryptionOptions(final Options options) {
		options.addOption("pass", true, "The cold wallet password");
		options.addOption("numHashes", false, "The number of password hashes");
	}
}
