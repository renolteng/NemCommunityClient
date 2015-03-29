package org.nem.console.commands;

import org.apache.commons.cli.*;

public interface Command {

	String getName();

	void handle(final CommandLine commandLine);

	Options getOptions();
}
