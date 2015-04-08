package org.nem.console.commands;

import org.apache.commons.cli.*;

/**
 * A command that is supported by the NEM console CLI.
 */
public interface Command {

	/**
	 * Gets the command name.
	 *
	 * @return The command name.
	 */
	String name();

	/**
	 * Executes the command.
	 *
	 * @param commandLine The user-supplied command line.
	 */
	void handle(final CommandLine commandLine);

	/**
	 * Gets the command options.
	 *
	 * @return The command options.
	 */
	Options options();
}
