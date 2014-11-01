package org.nem.monitor;

import org.nem.core.utils.ExceptionUtils;
import org.nem.monitor.node.NemNodeType;

import org.apache.commons.io.FilenameUtils;

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
	private final Function<NemNodeType, JavaSpawnBuilder> processBuilderFactory;

	/**
	 * Creates a new launcher.
	 *
	 * @param nemFolder The base NEM folder.
	 */
	public JavaLauncher(final String nemFolder, final boolean isWebStartLaunched) {
		this.nemFolder = nemFolder;
		this.processBuilderFactory = isWebStartLaunched ? JavaThreadBuilder::new : JavaProcessBuilder::new;
	}

	/**
	 * Launches the Java application via CommonStarter and specified config file.
	 *
	 * @param commandLine The config file name.
	 */
	public void launch(final NemNodeType nodeType, final String configFile) {
		ExceptionUtils.propagateVoid(() -> {
			final JavaSpawnBuilder pb = this.processBuilderFactory.apply(nodeType);
			pb.setLogFile(this.getLogFile(configFile));
			pb.start();
		});
	}

	private File getLogFile(final String configFile) throws IOException {
		final String fileNameWithoutExtension = FilenameUtils.getBaseName(configFile);
		final Path file = Paths.get(this.nemFolder, fileNameWithoutExtension + ".log");
		final File logFile = file.toFile().getCanonicalFile();
		LOGGER.info(String.format("Launching %s with logs in <%s>.", configFile, logFile.getAbsolutePath()));
		return logFile;
	}
}
