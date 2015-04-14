package org.nem.console.utils;

import org.apache.commons.cli.CommandLine;
import org.nem.core.crypto.Signer;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.*;
import org.nem.core.serialization.BinarySerializer;

/**
 * Helper class for loading and saving transactions.
 */
public class TransactionStorage {

	/**
	 * Saves the specified transaction using the output settings from the command line.
	 *
	 * @param commandLine The command line.
	 */
	public static void save(final Transaction transaction, final CommandLine commandLine) {
		save(transaction, commandLine.getOptionValue("output"));
	}

	/**
	 * Saves the specified transaction using the specified settings.
	 *
	 * @param outputFileName The output file name.
	 */
	public static void save(final Transaction transaction, final String outputFileName) {
		final byte[] transferBytes = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());
		final RequestPrepare preparedTransaction = new RequestPrepare(transferBytes);

		final Signer signer = transaction.getSigner().createSigner();
		final RequestAnnounce announce = new RequestAnnounce(
				preparedTransaction.getData(),
				signer.sign(preparedTransaction.getData()).getBytes());
		final byte[] buffer = BinarySerializer.serializeToBytes(announce);
		final PlainTextInputOutput io = new PlainTextInputOutput();

		System.out.println("Writing transaction data to: " + outputFileName);
		io.writeTo(outputFileName, buffer);
	}
}
