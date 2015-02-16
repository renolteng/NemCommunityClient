package org.nem.monitor.launcher;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Helper class for launching external java processes.
 */
public class JavaProcessLauncher {
	private static final Logger LOGGER = Logger.getLogger(JavaProcessLauncher.class.getName());

	/**
	 * Launches a new java process.
	 *
	 * @param arguments The process arguments.
	 * @param workingDirectory The process working directory.
	 */
	public void launch(final List<String> arguments, final File workingDirectory) throws IOException {
		final List<String> argumentsWithExe = new ArrayList<>();
		argumentsWithExe.add("java");
		argumentsWithExe.addAll(arguments);

		LOGGER.info(String.format(
				"Launching '%s' in '%s'",
				argumentsWithExe.stream().collect(Collectors.joining(" ")),
				workingDirectory));

		final ProcessBuilder builder = new ProcessBuilder()
				.command(argumentsWithExe)
				.inheritIO()
				.directory(workingDirectory);
		builder.start();
	}
}
