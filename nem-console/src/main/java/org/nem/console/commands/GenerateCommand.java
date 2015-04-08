package org.nem.console.commands;

import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.nem.console.models.AliasedKeyPair;
import org.nem.console.utils.*;

import java.util.List;

/**
 * A command for generating vanity addresses.
 */
public class GenerateCommand implements Command {

	@Override
	public String name() {
		return "generate";
	}

	@Override
	public void handle(final CommandLine commandLine) {
		final String[] prefixes = commandLine.getOptionValue("prefixes").split(",");
		System.out.println("Generating addresses for prefixes: " + StringUtils.join(prefixes, ","));
		final List<AliasedKeyPair> keyPairs = MultiAddressGeneratorHost.generate(prefixes);
		KeyPairsStorage.save(keyPairs, commandLine);
	}

	@Override
	public Options options() {
		final Options options = new Options();
		OptionsUtils.addWriteOptions(options);
		options.addOption("prefixes", true, "Comma-separated list of address prefixes to generate");
		options.addOption("pass", true, "The password of the output file");
		return options;
	}
}
