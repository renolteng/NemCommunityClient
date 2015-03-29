package org.nem.console.commands;

public interface Command {

	public String getName();

	public void handle(final CommandLine commandLine);

	public Options getOptions();
}
