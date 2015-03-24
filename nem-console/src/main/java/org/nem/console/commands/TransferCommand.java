package org.nem.console.commands;

import org.apache.commons.cli.*;
import org.nem.console.models.AliasedKeyPairs;
import org.nem.console.utils.*;
import org.nem.core.model.*;
import org.nem.core.model.primitive.*;
import org.nem.core.time.TimeInstant;

public class TransferCommand implements Command {
	@Override
	public String getName() {
		return "transfer";
	}

	@Override
	public void handle(final CommandLine commandLine) {
		final AliasedKeyPairs keyPairs = new AliasedKeyPairs(KeyPairsStorage.load(commandLine));

		final TimeInstant timeStamp = new TimeInstant(Integer.parseInt(commandLine.getOptionValue("time")));
		final String senderAlias = commandLine.getOptionValue("sender");
		final String recipientAlias = commandLine.getOptionValue("recipient");
		final long amount = Long.parseLong(commandLine.getOptionValue("amount"));
		System.out.println(String.format("transfer (%s) '%s' -> '%s'", amount, senderAlias, recipientAlias));
		final Transaction transaction = new TransferTransaction(
				timeStamp,
				keyPairs.findByAlias(senderAlias),
				keyPairs.findByAlias(recipientAlias),
				Amount.fromMicroNem(amount),
				null);

		TransactionUtils.prepareAndSign(transaction);
		TransactionStorage.save(transaction, commandLine.getOptionValue("output"));
	}

	@Override
	public Options getOptions() {
		final Options options = new Options();
		options.addOption("sender", true, "The sender alias");
		options.addOption("time", true, "The soft timestamp");

		options.addOption("recipient", true, "The recipient alias");
		options.addOption("amount", true, "The amount in micronem");
		options.addOption("output", true, "The output file");

		options.addOption("pass", true, "The password");
		options.addOption("input", true, "The input file");
		return options;
	}
}
