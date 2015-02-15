package org.nem.monitor.config;

import org.apache.commons.cli.Option;
import org.nem.core.deploy.NemCommandLine;

import java.util.Arrays;

/**
 * Helper class for parsing the NEM monitor command line.
 */
public class MonitorCommandLine {
	private static final String NCC_URI = "nccUri";
	private static final String NCC_URI_DESCRIPTION = "URI for starting NCC.";
	private static final String NIS_URI = "nisUri";
	private static final String NIS_URI_DESCRIPTION = "URI for starting NIS.";

	private final String nccUri;
	private final String nisUri;

	private MonitorCommandLine(final String nccUri, final String nisUri) {
		this.nccUri = nccUri;
		this.nisUri = nisUri;
	}

	/**
	 * Gets the NCC URI as a string.
	 *
	 * @return The NCC URI as a string.
	 */
	public String getNccUri() {
		return this.nccUri;
	}

	/**
	 * Gets the NIS URI as a string.
	 *
	 * @return The NIS URI as a string.
	 */
	public String getNisUri() {
		return this.nisUri;
	}

	/**
	 * Parses an array of commandline arguments into a MonitorCommandLine instance.
	 *
	 * @param args The arguments.
	 * @return The configuration.
	 */
	public static MonitorCommandLine parse(final String[] args) {
		final NemCommandLine commandLine = new NemCommandLine(Arrays.asList(
				new Option(NCC_URI, true, NCC_URI_DESCRIPTION),
				new Option(NIS_URI, true, NIS_URI_DESCRIPTION)));

		// TODO 20150215 J-J: shouldn't default to jnlp as that will not work
		String nccJnlpUrl = "http://bob.nem.ninja/webstart/nem-client.jnlp";
		String nisJnlpUrl = "http://bob.nem.ninja/webstart/nem-server.jnlp";
		if (commandLine.parse(args)) {
			nccJnlpUrl = getParameterOrDefault(commandLine, NCC_URI, nccJnlpUrl);
			nisJnlpUrl = getParameterOrDefault(commandLine, NIS_URI, nisJnlpUrl);
		}

		return new MonitorCommandLine(nccJnlpUrl, nisJnlpUrl);
	}

	private static String getParameterOrDefault(final NemCommandLine commandLine, final String key, final String defaultValue) {
		return commandLine.hasParameter(key) ? commandLine.getParameter(key) : defaultValue;
	}
}
