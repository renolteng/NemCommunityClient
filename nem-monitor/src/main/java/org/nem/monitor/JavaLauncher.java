package org.nem.monitor;

import org.apache.commons.io.FilenameUtils;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.nio.file.*;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Class that provides functionality for spawning a new Java process.
 */
public class JavaLauncher {
	private static final Logger LOGGER = Logger.getLogger(JavaLauncher.class.getName());

	private final String nemFolder;
	private final Function<String, JavaProcessBuilder> processBuilderFactory;

	/**
	 * Creates a new launcher.
	 *
	 * @param nemFolder The base NEM folder.
	 */
	public JavaLauncher(final String nemFolder) {
		this(nemFolder, JavaProcessBuilder::new);
	}

	/**
	 * Creates a new launcher.
	 *
	 * @param nemFolder The base NEM folder.
	 * @param processBuilderFactory The process builder factory.
	 */
	public JavaLauncher(final String nemFolder, final Function<String, JavaProcessBuilder> processBuilderFactory) {
		this.nemFolder = nemFolder;
		this.processBuilderFactory = processBuilderFactory;
	}

	/**
	 * Launches the Java application via CommonStarter and specified config file.
	 *
	 * @param configFileName The config file name.
	 */
	public void launch(final String configFileName) {
		LOGGER.info(String.format("Launching Java process with config file name: %s", configFileName));

		ExceptionUtils.propagateVoid(() -> {
			final JavaProcessBuilder pb = this.processBuilderFactory.apply(configFileName);
			pb.setLogFile(this.getLogFile(configFileName));
			pb.start();
		});
	}

	private File getLogFile(final String jnlpUrl) throws IOException {
		final String fileNameWithoutExtension = FilenameUtils.getBaseName(jnlpUrl);
		final Path file = Paths.get(this.nemFolder, fileNameWithoutExtension + ".log");
		final File logFile = file.toFile().getCanonicalFile();
		LOGGER.info(String.format("Launching %s with logs in <%s>.", jnlpUrl, logFile.getAbsolutePath()));
		return logFile;
	}
}
