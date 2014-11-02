package org.nem.monitor;

import org.nem.core.deploy.*;
import org.nem.monitor.node.NemNodeType;

import java.io.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * An abstraction on top of ProcessBuilder for Java processes.
 */
public class JavaThreadBuilder implements JavaSpawnBuilder {
	private static final Logger LOGGER = Logger.getLogger(JavaThreadBuilder.class.getName());
	final public static ExecutorService service = Executors.newFixedThreadPool(2);

	private String[] arguments;

	/**
	 * Creates a new Java process builder.
	 *
	 * @param configFilePath
	 */
	public JavaThreadBuilder(final NemNodeType nodeType) {
		final String configFilePath = nodeType == NemNodeType.NCC ? "ncc-config.properties" : "nis-config.properties";
		final String nodeTypeText = nodeType == NemNodeType.NCC ? "-ncc" : "-nis";
		arguments = new String[] { "-config", configFilePath, nodeTypeText };
	}

	/**
	 * Sets the log file.
	 *
	 * @param logFile The log file.
	 */
	public void setLogFile(final File logFile) {
		// this.builder.redirectErrorStream(true);
		// this.builder.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile));
		// this.builder.directory(logFile.getParentFile());
	}

	/**
	 * Starts the process.
	 */
	public void start() throws IOException {
		LOGGER.info(String.format("Starting Java thread: CommonStarter.start(%s).", arguments.toString()));
		service.submit(() -> CommonStarter.start(arguments));
	}
}
