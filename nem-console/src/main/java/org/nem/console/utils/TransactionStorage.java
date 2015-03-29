package org.nem.console.utils;

import org.apache.commons.cli.CommandLine;
import org.nem.core.crypto.Signer;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.*;
import org.nem.core.serialization.BinarySerializer;

public class TransactionStorage {

	public static void save(final Transaction transaction, final CommandLine commandLine) {
		save(transaction, commandLine.getOptionValue("output"));
	}

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
