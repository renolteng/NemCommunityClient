package org.nem.deploy;

import org.apache.commons.cli.Option;
import org.nem.core.deploy.*;

import java.util.*;

/**
 * Class responsible for holding all NCC configuration settings.
 * A NCC reboot is required for configuration changes to take effect.
 */
public class NccConfiguration extends CommonConfiguration {
	private static final String WEBSTART = "isWebStart";
	private static final String WEBSTART_DESCRIPTION = "Indicates whether NIS should be started via WebStart (1) or not (0).";
	private static final String NIS_JNLP_URL = "nisJnlpUrl";
	private static final String NIS_JNLP_URL_DESCRIPTION = "JNLP URL for starting NIS via WebStart.";

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

	/**
	 * Loads an NccConfiguration from an array of commandline arguments.
	 *
	 * @param args The arguments.
	 * @return The configuration.
	 */
	public static NccConfiguration loadConfig(final String[] args) {
		final NemCommandLine commandLine = new NemCommandLine(Arrays.asList(
				new Option(NccConfiguration.WEBSTART, true, NccConfiguration.WEBSTART_DESCRIPTION),
				new Option(NccConfiguration.NIS_JNLP_URL, true, NccConfiguration.NIS_JNLP_URL_DESCRIPTION)));
		if (commandLine.parse(args)) {
			return new NccConfiguration(
					"1".equals(commandLine.getParameter(NccConfiguration.WEBSTART)),
					commandLine.getParameter(NccConfiguration.NIS_JNLP_URL));
		}

		return new NccConfiguration();
	}
}
