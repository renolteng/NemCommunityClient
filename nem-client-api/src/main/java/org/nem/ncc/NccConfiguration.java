package org.nem.ncc;

import org.apache.commons.io.IOUtils;
import org.nem.core.deploy.CommonConfiguration;
import org.nem.core.model.NetworkInfo;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.model.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * The central NCC configuration.
 */
public class NccConfiguration {
	private static final Logger LOGGER = Logger.getLogger(NccConfiguration.class.getName());
	private static final String CONFIG_FILE_NAME = "ncc.cfg";
	// TODO 20150325 J-J: there might be a reason for this but it seems like CommonConfiguration should be injected
	private static final CommonConfiguration COMMON_CONFIGURATION = new CommonConfiguration();
	private final org.nem.ncc.model.Configuration configuration;

	public NccConfiguration() {
		final String nccFolder = Paths.get(COMMON_CONFIGURATION.getNemFolder(), "ncc").toString();
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
			final Configuration defaultConfiguration = new Configuration(
					"en",
					NodeEndpoint.fromHost("localhost"),
					NisBootInfo.createLocal(),
					storagePath);
			return JsonSerializer.serializeToBytes(defaultConfiguration);
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
	 * Gets the web context string.
	 *
	 * @return The web context string.
	 */
	public static String getWebContext() {
		return COMMON_CONFIGURATION.getWebContext();
	}

	/**
	 * Gets the full url of the start page as string.
	 *
	 * @return The home url as string.
	 */
	public static String getHomeUrl() {
		return COMMON_CONFIGURATION.getHomeUrl();
	}

	/**
	 * Gets the configuration (for configuring of private stuff).
	 *
	 * @return The configuration.
	 */
	public org.nem.ncc.model.Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Gets the network information.
	 *
	 * @return The network information.
	 */
	public NetworkInfo getNetworkInfo() {
		return COMMON_CONFIGURATION.getNetworkInfo();
	}

	/**
	 * Gets the network name.
	 *
	 * @return The network name.
	 */
	public String getNetworkName() {
		return COMMON_CONFIGURATION.getNetworkName();
	}
}
