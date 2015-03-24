package org.nem.console.commands;

import org.apache.commons.cli.*;
import org.nem.console.models.*;
import org.nem.console.utils.*;
import org.nem.core.model.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.time.TimeInstant;

public class ImportanceTransferCommand implements Command {
	@Override
	public String getName() {
		return "importance";
	}

	@Override
	public void handle(final CommandLine commandLine) {
		final AliasedKeyPairs keyPairs = new AliasedKeyPairs(KeyPairsStorage.load(commandLine));

		final TimeInstant timeStamp = new TimeInstant(Integer.parseInt(commandLine.getOptionValue("time")));
		final String senderAlias = commandLine.getOptionValue("sender");
		final String remoteAlias = commandLine.getOptionValue("remote");
		System.out.println(String.format("importance '%s' -> '%s'", senderAlias, remoteAlias));
		final Transaction transaction = new ImportanceTransferTransaction(
				timeStamp,
				keyPairs.findByAlias(senderAlias),
				ImportanceTransferMode.Activate,
				keyPairs.findByAlias(remoteAlias));

		TransactionUtils.prepareAndSign(transaction);
		TransactionStorage.save(transaction, commandLine.getOptionValue("output"));
	}

	@Override
	public Options getOptions() {
		final Options options = new Options();
		options.addOption("sender", true, "The sender alias");
		options.addOption("time", true, "The soft timestamp");

		options.addOption("remote", true, "The remote alias");
		options.addOption("output", true, "The output file");

		options.addOption("pass", true, "The password");
		options.addOption("input", true, "The input file");
		return options;
	}
}
