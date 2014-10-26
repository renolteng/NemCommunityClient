package org.nem.monitor;

import java.io.*;

/**
 * An abstraction on top of ProcessBuilder for nem start processes.
 */
public class NemProcessBuilder {
	private final ProcessBuilder builder;

	/**
	 * Creates a new builder.
	 *
	 * @param jnlpUrl The url to the java network launching protocol.
	 */
	public NemProcessBuilder(final String... parameter) {
		this.builder = new ProcessBuilder(parameter);
	}

	/**
	 * Sets the log file.
	 *
	 * @param logFile The log file.
	 */
	public void setLogFile(final File logFile) {
		this.builder.redirectErrorStream(true);
		this.builder.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile));
		this.builder.directory(logFile.getParentFile());
	}

	/**
	 * Starts the process.
	 */
	public void start() throws IOException {
		this.builder.start();
	}
}
