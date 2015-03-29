package org.nem.console.commands;

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
		final Account remote = accountLookup.apply(commandLine.getOptionValue("remote"));
		System.out.println(String.format("importance '%s' -> '%s'", sender, remote));
		return new ImportanceTransferTransaction(
				timeStamp,
				sender,
				ImportanceTransferMode.Activate,
				remote);
	}

	@Override
	protected void addCustomOptions(final Options options) {
		options.addOption("remote", true, "The remote alias");
	}
}
