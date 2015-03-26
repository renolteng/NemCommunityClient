package org.nem.console.commands;

import org.apache.commons.cli.*;
import org.apache.commons.cli.CommandLine;
import org.nem.core.model.*;
import org.nem.core.model.primitive.*;
import org.nem.core.time.TimeInstant;

import java.util.function.Function;

public class TransferCommand extends TransactionCommand {

	public TransferCommand() {
		super("transfer");
	}

	@Override
	protected Transaction createTransaction(
			final TimeInstant timeStamp,
			final Account sender,
			final Function<String, Account> accountLookup,
			final CommandLine commandLine) {
		final Account recipient = accountLookup.apply(commandLine.getOptionValue("recipient"));
		final long amount = Long.parseLong(commandLine.getOptionValue("amount"));
		System.out.println(String.format("transfer (%s) '%s' -> '%s'", amount, sender, recipient));
		return new TransferTransaction(
				timeStamp,
				sender,
				recipient,
				Amount.fromMicroNem(amount),
				null);
	}

	@Override
	protected void addCustomOptions(final Options options) {
		options.addOption("recipient", true, "The recipient alias");
		options.addOption("amount", true, "The amount in micronem");
	}
}
