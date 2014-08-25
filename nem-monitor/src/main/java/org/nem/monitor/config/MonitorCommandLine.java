package org.nem.monitor.config;

import org.apache.commons.cli.Option;
import org.nem.core.deploy.NemCommandLine;

import java.util.Arrays;

public class MonitorCommandLine {
	private static final String NCC_JNLP_URL = "nccJnlpUrl";
	private static final String NCC_JNLP_URL_DESCRIPTION = "JNLP URL for starting NCC via WebStart.";
	private static final String NIS_JNLP_URL = "nisJnlpUrl";
	private static final String NIS_JNLP_URL_DESCRIPTION = "JNLP URL for starting NIS via WebStart.";

	private final String nccJnlpUrl;
	private final String nisJnlpUrl;

	private MonitorCommandLine(final String nccJnlpUrl, final String nisJnlpUrl) {
		this.nccJnlpUrl = nccJnlpUrl;
		this.nisJnlpUrl = nisJnlpUrl;
	}

	/**
	 * Gets the NCC JNLP url as string.
	 *
	 * @return The NCC JNLP url as string.
	 */
	public String getNccJnlpUrl() {
		return this.nccJnlpUrl;
	}

	/**
	 * Gets the NIS JNLP url as string.
	 *
	 * @return The NIS JNLP url as string.
	 */
	public String getNisJnlpUrl() {
		return this.nisJnlpUrl;
	}

	/**
	 * Parses an array of commandline arguments into a MonitorCommandLine instance.
	 *
	 * @param args The arguments.
	 * @return The configuration.
	 */
	public static MonitorCommandLine parse(final String[] args) {
		final NemCommandLine commandLine = new NemCommandLine(Arrays.asList(
				new Option(NCC_JNLP_URL, true, NCC_JNLP_URL_DESCRIPTION),
				new Option(NIS_JNLP_URL, true, NIS_JNLP_URL_DESCRIPTION)));

		String nccJnlpUrl = "http://bob.nem.ninja/webstart/nem-client.jnlp";
		String nisJnlpUrl = "http://bob.nem.ninja/webstart/nem-server.jnlp";
		if (commandLine.parse(args)) {
			nccJnlpUrl = getParameterOrDefault(commandLine, NCC_JNLP_URL, nccJnlpUrl);
			nisJnlpUrl = getParameterOrDefault(commandLine, NIS_JNLP_URL, nisJnlpUrl);
		}

		return new MonitorCommandLine(nccJnlpUrl, nisJnlpUrl);
	}

	private static String getParameterOrDefault(final NemCommandLine commandLine, final String key, final String defaultValue) {
		return commandLine.hasParameter(key) ? commandLine.getParameter(key) : defaultValue;
	}
}
