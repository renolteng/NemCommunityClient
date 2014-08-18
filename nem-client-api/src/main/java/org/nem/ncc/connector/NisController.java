package org.nem.ncc.connector;

import org.nem.ncc.NccMain;

import java.io.*;
import java.nio.file.*;
import java.util.logging.*;

/**
 * Class to start NIS server via web start if web start is available.
 */
public class NisController {
	private static final Logger LOGGER = Logger.getLogger(NisController.class.getName());

	/**
	 * If configured as web start, start NIS server.
	 *
	 * @param nisJnlpUrl The url to the java networl launching protocol.
	 */
	public void startNisViaWebStart(final String nisJnlpUrl) {
		// Will only be called if configured to do so.
		try {
			final ProcessBuilder pb = new ProcessBuilder("javaws", nisJnlpUrl);
			final Path file = Paths.get(NccMain.getNemFolder(), "ncc-nis.log");
			final File log = file.toFile().getCanonicalFile();
			LOGGER.info(String.format("Starting NIS via javaws, process logs into <%s>.", log.getAbsolutePath()));
			LOGGER.info(String.format("NIS JNLP URL is %s", nisJnlpUrl));
			pb.redirectErrorStream(true);
			pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
			pb.directory(log.getParentFile());
			pb.start();
		} catch (final IOException e) {
			//
			LOGGER.log(Level.SEVERE, String.format("Error in requesting the resources for NIS. (%s)", e));
		}
	}
}
