package org.nem.monitor;

import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.nio.file.*;
import java.util.function.*;
import java.util.logging.*;

/**
 * Class that provides functionality for downloading and launching a web start process.
 */
public class WebStartLauncher {
	private static final Logger LOGGER = Logger.getLogger(WebStartLauncher.class.getName());

	private final String nemFolder;
	private final Function<String, WebStartProcessBuilder> processBuilderFactory;

	/**
	 * Creates a new launcher.
	 *
	 * @param nemFolder The base NEM folder.
	 */
	public WebStartLauncher(final String nemFolder) {
		this(nemFolder, WebStartProcessBuilder::new);
	}

	/**
	 * Creates a new launcher.
	 *
	 * @param nemFolder The base NEM folder.
	 * @param processBuilderFactory The process builder factory.
	 */
	public WebStartLauncher(final String nemFolder, final Function<String, WebStartProcessBuilder> processBuilderFactory) {
		this.nemFolder = nemFolder;
		this.processBuilderFactory = processBuilderFactory;
	}

	/**
	 * Launches the web start application located at the specified url.
	 *
	 * @param jnlpUrl The url to the java network launching protocol.
	*/
	public void launch(final String jnlpUrl) {
		LOGGER.info(String.format("Launching JNLP from URL: %s", jnlpUrl));

		ExceptionUtils.propagateVoid(() -> {
			final WebStartProcessBuilder pb = this.processBuilderFactory.apply(jnlpUrl);
			pb.setLogFile(this.getLogFile(jnlpUrl));
			pb.start();
		});
	}

	private File getLogFile(final String jnlpUrl) throws IOException {
		final String fileNameWithExtension = Paths.get(jnlpUrl).toFile().getName();
		final String fileNameWithoutExtension = fileNameWithExtension.substring(0, fileNameWithExtension.indexOf('.'));
		final Path file = Paths.get(this.nemFolder, fileNameWithoutExtension + ".log");
		final File logFile = file.toFile().getCanonicalFile();
		LOGGER.info(String.format("Launching %s with logs in <%s>.", jnlpUrl, logFile.getAbsolutePath()));
		return logFile;
	}
}
