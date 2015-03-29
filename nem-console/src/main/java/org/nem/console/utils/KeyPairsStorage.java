package org.nem.console.utils;

import org.apache.commons.cli.CommandLine;
import org.nem.console.models.AliasedKeyPair;

import java.util.List;

public class KeyPairsStorage {

	public static void save(final List<AliasedKeyPair> keyPairs, final CommandLine commandLine) {
		save(
				keyPairs,
				commandLine.getOptionValue("output"),
				commandLine.getOptionValue("pass"),
				Integer.parseInt(commandLine.getOptionValue("numHashes", "1")));
	}

	public static void save(final List<AliasedKeyPair> keyPairs, final String outputFileName, final String password, final int numHashes) {
		final byte[] buffer = BinarySerializer.serializeToBytes(serializer -> serializer.writeObjectArray("tuples", keyPairs));
		final EncryptedInputOutput io = new EncryptedInputOutput(password, numHashes);

		System.out.println("Writing encrypted data to: " + outputFileName);
		io.writeTo(outputFileName, buffer);
	}

	public static List<AliasedKeyPair> load(final CommandLine commandLine) {
		return load(
				commandLine.getOptionValue("input"),
				commandLine.getOptionValue("pass"),
				Integer.parseInt(commandLine.getOptionValue("numHashes", "1")));
	}

	public static List<AliasedKeyPair> load(final String inputFileName, final String password, final int numHashes) {
		System.out.println("Reading encrypted data from: " + inputFileName);

		final EncryptedInputOutput io = new EncryptedInputOutput(password, numHashes);
		final byte[] buffer = io.readFrom(inputFileName);
		final Deserializer deserializer = new BinaryDeserializer(buffer, new DeserializationContext(null));
		return deserializer.readObjectArray("tuples", AliasedKeyPair::new);
	}
}
