package org.nem.console.commands;

import org.apache.commons.cli.*;
import org.nem.console.models.AliasedKeyPair;
import org.nem.console.utils.*;

import java.util.List;

public class DumpContentsCommand implements Command {
	@Override
	public String getName() {
		return "dump";
	}

	@Override
	public void handle(final CommandLine commandLine) {
		final List<AliasedKeyPair> keyPairs = KeyPairsStorage.load(commandLine);

		for (final AliasedKeyPair keyPair : keyPairs) {
			System.out.println(String.format("'%s' -> '%s'", keyPair.alias(), keyPair.address()));
		}
	}

	@Override
	public Options getOptions() {
		final Options options = new Options();
		options.addOption("pass", true, "The password");
		options.addOption("input", true, "The input file");
		return options;
	}
}
