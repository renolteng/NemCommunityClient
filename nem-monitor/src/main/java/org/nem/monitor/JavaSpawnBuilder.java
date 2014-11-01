package org.nem.monitor;

import java.io.*;

public interface JavaSpawnBuilder {

	/**
	 * Sets the log file.
	 *
	 * @param logFile The log file.
	 */
	public abstract void setLogFile(File logFile);

	/**
	 * Starts the process.
	 */
	public abstract void start() throws IOException;

}