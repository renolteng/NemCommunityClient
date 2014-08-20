package org.nem.deploy;

import org.nem.core.deploy.CommonConfiguration;

import java.util.Properties;

/**
 * Class responsible for holding all NCC configuration settings.
 * A NCC reboot is required for configuration changes to take effect.
 */
public class NccConfiguration extends CommonConfiguration {
	public static final String WEBSTART = "isWebStart";
	public static final String WEBSTART_DESCRIPTION = "Indicates whether NIS should be started via WebStart (1) or not (0).";
	public static final String NIS_JNLP_URL = "nisJnlpUrl";
	public static final String NIS_JNLP_URL_DESCRIPTION = "JNLP URL for starting NIS via WebStart.";

	final private Boolean isWebStart;
	final private String nisJnlpUrl;

	/**
	 * Creates a new configuration object from the default properties.
	 */
	public NccConfiguration() {
		this(loadDefaultProperties());
	}

	/**
	 * Creates a new configuration object around the default properties
	 * plus commandline parameters.
	 *
	 * @param isWebStart Value indicating whether or not web start is used.
	 * @param nisJnlpUrl The JNLP URL to use if starting via web start.
	 */
	public NccConfiguration(final Boolean isWebStart, final String nisJnlpUrl) {
		super(loadDefaultProperties());
		this.isWebStart = isWebStart;
		this.nisJnlpUrl = nisJnlpUrl;
	}

	/**
	 * Creates a new configuration object around the specified properties.
	 *
	 * @param properties The specified properties.
	 */
	public NccConfiguration(final Properties properties) {
		super(properties);
		this.isWebStart = getOptionalBoolean(properties, "ncc.isWebStart", false);
		this.nisJnlpUrl = getOptionalString(properties, "ncc.nisJnlpUrl", "http://bob.nem.ninja/webstart/nem-server.jnlp");
	}

	/**
	 * Get a value indicating whether or not web start is used.
	 *
	 * @return True if web start is used, false otherwise.
	 */
	public boolean isWebStart() {
		return this.isWebStart;
	}

	/**
	 * Gets JNLP url as string.
	 *
	 * @return The JNLP url as string.
	 */
	public String getNisJnlpUrl() {
		return this.nisJnlpUrl;
	}
}
