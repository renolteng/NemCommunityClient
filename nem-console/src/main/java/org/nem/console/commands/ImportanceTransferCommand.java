package org.nem.console.commands;

import org.apache.commons.cli.*;
import org.nem.core.model.*;
import org.nem.core.time.TimeInstant;

import java.util.function.Function;

public class ImportanceTransferCommand extends TransactionCommand {

	public ImportanceTransferCommand() {
		super("importance");
	}

	@Override
	protected Transaction createTransaction(
			final TimeInstant timeStamp,
			final Account sender,
			final Function<String, Account> accountLookup,
			final CommandLine commandLine) {
		final String remoteAlias = commandLine.getOptionValue("remote");
		System.out.println(String.format("importance '%s' -> '%s'", sender, remoteAlias));
		return new ImportanceTransferTransaction(
				timeStamp,
				sender,
				ImportanceTransferMode.Activate,
				accountLookup.apply(remoteAlias));
	}

	@Override
	protected void addCustomOptions(final Options options) {
		options.addOption("remote", true, "The remote alias");
	}
}
