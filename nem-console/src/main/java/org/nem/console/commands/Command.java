package org.nem.console.commands;

import org.apache.commons.cli.*;

public interface Command {

	public String getName();

	public void handle(final CommandLine commandLine);

	public Options getOptions();
}
