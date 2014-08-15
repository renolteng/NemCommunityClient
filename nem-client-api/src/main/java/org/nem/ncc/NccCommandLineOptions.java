package org.nem.ncc;

import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.util.logging.*;

/**
 * Encapsulates NCC command line options.
 */
public class NccCommandLineOptions {
	private static final Logger LOGGER = Logger.getLogger(NccCommandLineOptions.class.getName());

	private static final String OPTION_NIS_JNLP = "nis_jnlp";
	private static final String OPTION_NEM_FOLDER = "nem_folder";
	private static final String OPTION_NIS_WS = "nis_ws";

	private final String nisJnlpUrl;
	private final String nemFolder;
	private final boolean nisWebStart;

	private NccCommandLineOptions(final String nisJnlpUrl, final String storagePath, final boolean nisWebStart) {
		this.nisJnlpUrl = nisJnlpUrl;
		this.nemFolder = storagePath;
		this.nisWebStart = nisWebStart;
	}

	/**
	 * Gets the NIS JNLP URL.
	 *
	 * @return The NIS JNLP URL.
	 */
	public String getNisJnlpUrl() {
		return this.nisJnlpUrl;
	}

	/**
	 * Gets the NEM folder path.
	 *
	 * @return The the NEM folder path.
	 */
	public String getNemFolder() {
		return this.nemFolder;
	}

	/**
	 * Gets a value indicating whether or not NIS should be started via web start.
	 *
	 * @return true if NIS should be started via web start.
	 */
	public boolean isNisWebStart() {
		return this.nisWebStart;
	}

	/**
	 * Creates a new options instance by parsing the specified command line arguments.
	 *
	 * @param args The command line arguments.
	 * @return The parsed options.
	 */
	public static NccCommandLineOptions parse(final String[] args) {
		final Options options = initializeOptions();
		final CommandLineParser parser = new BasicParser();

		String nisJnlpUrl = "http://bob.nem.ninja/webstart/nem-server.jnlp";
		String nemFolder = getDefaultLocation();
		boolean nisWebStart = true;

		try {
			final CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption(OPTION_NIS_JNLP)) {
				nisJnlpUrl = cmd.getOptionValue(OPTION_NIS_JNLP);
			}

			if (cmd.hasOption(OPTION_NEM_FOLDER)) {
				nemFolder = cmd.getOptionValue(OPTION_NEM_FOLDER);
			}

			if (cmd.hasOption(OPTION_NIS_WS)) {
				nisWebStart = "1".equals(cmd.getOptionValue(OPTION_NIS_WS));
			}
		} catch (final ParseException ex) {
			LOGGER.log(Level.WARNING, "arguments could not be parsed", ex);
		}

		return new NccCommandLineOptions(nisJnlpUrl, nemFolder, nisWebStart);
	}

	private static String getDefaultLocation() {
		return Paths.get(System.getProperty("user.home"), "ncc").toString();
	}

	private static Options initializeOptions() {
		final Options result = new Options();
		result.addOption(OPTION_NIS_JNLP, true, "JNLP URL for starting NIS via WebStart.");
		result.addOption(OPTION_NEM_FOLDER, true, "Path to the location holding the wallets and sub directories.");
		result.addOption(OPTION_NIS_WS, true, "Define whether NIS should be started via WebStart (default, 1) or not (0).");
		return result;
	}
}
