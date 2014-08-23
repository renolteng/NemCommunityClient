package org.nem.monitor;

import org.nem.core.utils.ExceptionUtils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class responsible for loading all monitor configuration settings.
 */
public class MonitorConfiguration {
	private final String nemFolder;

	/**
	 * Creates a new configuration object from the default properties.
	 */
	public MonitorConfiguration() {
		this(loadDefaultProperties());
	}

	protected static Properties loadDefaultProperties() {
		return ExceptionUtils.propagate(() -> {
			try (final InputStream inputStream = MonitorConfiguration.class.getClassLoader().getResourceAsStream("config.properties")) {
				final Properties properties = new Properties();
				properties.load(inputStream);
				return properties;
			}
		}, IllegalStateException::new);
	}

	/**
	 * Creates a new configuration object around the specified properties.
	 *
	 * @param properties The specified properties.
	 */
	public MonitorConfiguration(final Properties properties) {
		this.nemFolder = getOptionalString(properties, "nem.folder", this.getDefaultFolder()).replace("%h", this.getDefaultFolder());
	}

	protected static String getOptionalString(final Properties properties, final String name, final String defaultValue) {
		final String value = properties.getProperty(name);
		return null == value ? defaultValue : value;
	}

	/**
	 * Get the default folder for database and log files.
	 *
	 * @return path to the folder location.
	 */
	private String getDefaultFolder() {
		return System.getProperty("user.home");
	}

	/**
	 * Gets the path to the folder where database and log files should be located.
	 *
	 * @return The path to the folder.
	 */
	public String getNemFolder() {
		return this.nemFolder;
	}

}
