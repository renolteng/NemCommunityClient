package org.nem.ncc;

import org.apache.commons.io.*;
import org.nem.core.deploy.CommonConfiguration;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.model.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * The central NCC class.
 * <br/>
 * Did not find a better way of launching Jetty in combination with WebStart.
 * The physical location of the downloaded files is not pre-known, so passing a WAR
 * file to the Jetty runner does not work.
 * Servlet API 3.x with programmatic configuration was chosen.
 * The client starter can be launched from within the scope of WebStart which is the usual way for regular users.
 * In case WebStart is not available, it is assumed that both WAR files are available in the current directory.
 * - first command line argument is the name of the WAR file of API component
 * - second command line argument is the name of the WAR file of the WEB component.
 */
public class NccMain {
	private static final Logger LOGGER = Logger.getLogger(NccMain.class.getName());
	private static final String CONFIG_FILE_NAME = "ncc.cfg";
	private static final CommonConfiguration commonConfiguration = new CommonConfiguration();
	private final org.nem.ncc.model.Configuration configuration;

	public NccMain() {
		final String nccFolder = Paths.get(commonConfiguration.getNemFolder(), "ncc").toString();
		migrateDirectory(new File(nccFolder));
		verifyDirectory(new File(nccFolder));

		final String qualifiedConfigFileName = Paths.get(nccFolder, CONFIG_FILE_NAME).toString();
		try (final InputStream inputStream = new ByteArrayInputStream(loadConfigurationStream(nccFolder))) {
			this.configuration = new StreamBackedConfiguration(
					nccFolder,
					inputStream,
					() -> ExceptionUtils.propagate(() -> new FileOutputStream(qualifiedConfigFileName)));
		} catch (final IOException ex) {
			throw new ConfigurationException("unable to initialize NCC", ex);
		}
	}

	private static byte[] loadConfigurationStream(final String storagePath) {
		final String qualifiedConfigFileName = Paths.get(storagePath, CONFIG_FILE_NAME).toString();
		try (final InputStream inputStream = new FileInputStream(qualifiedConfigFileName)) {
			return IOUtils.toByteArray(inputStream);
		} catch (final IOException ex) {
			LOGGER.warning(String.format("unable to load NCC configuration: %s", ex));
			final Configuration defaultConfiguration = new Configuration("en", NisBootInfo.createLocal(), storagePath);
			return JsonSerializer.serializeToBytes(defaultConfiguration);
		}
	}

	private static void migrateDirectory(final File directory) {
		// in previous versions of nem, the ncc was peer to nem instead of a child of nem
		// so, we want to copy files from the old directory (e.g. ~/nem/ncc -> ~/ncc)
		final File oldDirectory = new File(Paths.get(new File(directory.getParent()).getParent(), "ncc").toString());
		if (!oldDirectory.exists()) {
			return;
		}

		try {
			// there are compatibility issues with the accounts_cache, so don't copy it when migrating
			FileUtils.copyDirectory(oldDirectory, directory, pathname -> !pathname.getName().equals("accounts_cache.json"));
			FileUtils.deleteDirectory(oldDirectory);
		} catch (final IOException ex) {
			final String message = String.format("Unable to migrate wallet files from <%s>.", oldDirectory.getAbsolutePath());
			LOGGER.severe(message);
		}
	}

	private static void verifyDirectory(final File directory) {
		if ((directory.exists() || directory.mkdirs()) && directory.isDirectory()) {
			return;
		}

		// the directory is either not a directory or could not be created
		final String message = String.format("Cannot use <%s> as a directory to store wallets.", directory.getAbsolutePath());
		LOGGER.severe(message);
		throw new ConfigurationException(message);
	}

	/**
	 * Gets the folder where all nem files are stored.
	 *
	 * @return The nem folder.
	 */
	public static String getNemFolder() {
		return commonConfiguration.getNemFolder();
	}

	/**
	 * Gets the web context string.
	 *
	 * @return The web context string.
	 */
	public static String getWebContext() {
		return commonConfiguration.getWebContext();
	}

	/**
	 * Gets the full url of the start page as string.
	 *
	 * @return The home url as string.
	 */
	public static String getHomeUrl() {
		return commonConfiguration.getHomeUrl();
	}

	/**
	 * Gets the configuration (for configuring of private stuff).
	 *
	 * @return The configuration.
	 */
	public org.nem.ncc.model.Configuration getConfiguration() {
		return this.configuration;
	}
}
