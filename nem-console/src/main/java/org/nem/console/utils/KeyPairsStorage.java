package org.nem.console.utils;

import org.apache.commons.cli.CommandLine;
import org.nem.console.models.AliasedKeyPair;
import org.nem.core.serialization.*;

import java.util.*;

/**
 * Helper class for loading and saving key pairs.
 */
public class KeyPairsStorage {

	/**
	 * Saves the specified key pairs using the output settings from the command line.
	 *
	 * @param keyPairs The key pairs.
	 * @param commandLine The command line.
	 */
	public static void save(final Collection<AliasedKeyPair> keyPairs, final CommandLine commandLine) {
		save(
				keyPairs,
				commandLine.getOptionValue("output"),
				commandLine.getOptionValue("pass"),
				Integer.parseInt(commandLine.getOptionValue("numHashes", "1")));
	}

	/**
	 * Saves the specified key pairs using the specified settings.
	 *
	 * @param keyPairs The key pairs.
	 * @param outputFileName The output file name.
	 * @param password The password.
	 * @param numHashes The number of hashes.
	 */
	public static void save(final Collection<AliasedKeyPair> keyPairs, final String outputFileName, final String password, final int numHashes) {
		final byte[] buffer = BinarySerializer.serializeToBytes(serializer -> serializer.writeObjectArray("tuples", keyPairs));
		final EncryptedInputOutput io = new EncryptedInputOutput(password, numHashes);

		System.out.println("Writing encrypted data to: " + outputFileName);
		io.writeTo(outputFileName, buffer);
	}

	/**
	 * Loads key pairs using the input settings from the command line.
	 *
	 * @param commandLine The command line.
	 */
	public static Collection<AliasedKeyPair> load(final CommandLine commandLine) {
		return load(
				commandLine.getOptionValue("input"),
				commandLine.getOptionValue("pass"),
				Integer.parseInt(commandLine.getOptionValue("numHashes", "1")));
	}

	/**
	 * Loads key pairs using the specified settings from the command line.
	 *
	 * @param inputFileName The input file name.
	 * @param password The password.
	 * @param numHashes The number of hashes.
	 * @return The key pairs.
	 */
	public static Collection<AliasedKeyPair> load(final String inputFileName, final String password, final int numHashes) {
		System.out.println("Reading encrypted data from: " + inputFileName);

		final EncryptedInputOutput io = new EncryptedInputOutput(password, numHashes);
		final byte[] buffer = io.readFrom(inputFileName);
		final Deserializer deserializer = new BinaryDeserializer(buffer, new DeserializationContext(null));
		return deserializer.readObjectArray("tuples", AliasedKeyPair::new);
	}
}
