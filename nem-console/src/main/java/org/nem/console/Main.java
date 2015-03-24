package org.nem.console;

import org.apache.commons.cli.*;
import org.nem.console.commands.*;

public class Main {
	private static final Command[] COMMANDS = new Command[] {
			//new GenerateCommand(),
			new DumpContentsCommand(),
			new ImportanceTransferCommand(),
			new TransferCommand()
	};

	public static void main(final String[] args) throws ParseException {
//		realMain(new String[] { "generate", "--pass=foo bar", "--prefixes=TA,TB,TC", "--output=sec.dat" });
		realMain(new String[] { "dump", "--pass=foo bar", "--input=sec.dat" });
		realMain(new String[] { "transfer", "--pass=foo bar", "--input=sec.dat", "--output=transfer.dat", "--time=4417373", "--sender=TA", "--recipient=TB", "--amount=1000000" });
		realMain(new String[] { "importance", "--pass=foo bar", "--input=sec.dat", "--output=importance.dat", "--time=4417373", "--sender=TA", "--remote=TC" });
	}

	public static void realMain(final String[] args) throws ParseException {
		if (0 == args.length) {
			OutputUsage();
			return;
		}

		final String mode = args[0].toLowerCase();
		for (final Command command : COMMANDS) {
			if (!command.getName().equals(mode)) {
				continue;
			}

			final CommandLineParser parser = new PosixParser();
			final CommandLine commandLine = parser.parse(command.getOptions(), args);
			command.handle(commandLine);
			return;
		}

		OutputUsage();
	}

	private static void OutputUsage(final Command command) {
		System.out.println(String.format("*** %s ***", command.getName()));
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(command.getName(), command.getOptions());
	}

	private static void OutputUsage() {
		for (final Command command : COMMANDS) {
			OutputUsage(command);
		}
	}
}