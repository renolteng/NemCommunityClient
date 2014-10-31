package org.nem.monitor;

import org.nem.core.deploy.CommonStarter;

import java.io.*;

/**
 * An abstraction on top of ProcessBuilder for Java processes.
 */
public class JavaProcessBuilder {
	private final ProcessBuilder builder;

	/**
	 * Creates a new Java process builder.
	 *
	 * @param configFilePath
	 */
	public JavaProcessBuilder(final String configFilePath) {
		this.builder = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), CommonStarter.class.getCanonicalName(), "-config", configFilePath);
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
