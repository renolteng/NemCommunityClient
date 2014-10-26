package org.nem.monitor;

import org.nem.core.connect.*;
import org.nem.core.deploy.LoggingBootstrapper;
import org.nem.core.utils.LockFile;
import org.nem.monitor.config.*;
import org.nem.monitor.ux.NemClientWindow;

import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Logger;
import javax.jnlp.*;
import javax.swing.SwingUtilities;

/**
 * The nem monitor program.
 */
public class NemMonitor {
	private static final Logger LOGGER = Logger.getLogger(NemMonitor.class.getName());
	private static Closeable fileLockHandle;

	static {
		// initialize logging before anything is logged; otherwise not all
		// settings will take effect
		LoggingBootstrapper.bootstrap(new MonitorConfiguration().getNemFolder());
	}

	/**
	 * The main entry point.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(final String[] args) {
		final MonitorCommandLine commandLine = MonitorCommandLine.parse(args);
		LOGGER.info(String.format("NCC JNLP configured as: %s", commandLine.getNccJnlpUrl()));
		LOGGER.info(String.format("NIS JNLP configured as: %s", commandLine.getNisJnlpUrl()));

		final String nemFolder = new MonitorConfiguration().getNemFolder();
		if (!canStart(nemFolder)) {
			return;
		}

		SwingUtilities.invokeLater(() -> {
			NemClientWindow window = new NemClientWindow();
			window.setupWindow(nemFolder, createHttpMethodClient(), isStartedViaWebStart(), commandLine.getNisJnlpUrl(), commandLine.getNccJnlpUrl());
		});
	}

	private static HttpMethodClient<ErrorResponseDeserializerUnion> createHttpMethodClient() {
		final int CONNECTION_TIMEOUT = 2000;
		final int SOCKET_TIMEOUT = 2000;
		final int REQUEST_TIMEOUT = 4000;
		return new HttpMethodClient<>(CONNECTION_TIMEOUT, SOCKET_TIMEOUT, REQUEST_TIMEOUT);
	}

	private static boolean canStart(final String nemFolder) {
		final File lockFile = Paths.get(nemFolder, "mon.lock").toFile();
		LOGGER.info(String.format("Acquiring exclusive lock to lock file: %s", lockFile));
		fileLockHandle = LockFile.tryAcquireLock(lockFile);
		if (null == fileLockHandle) {
			LOGGER.severe(String.format("Could not acquire exclusive lock to lock file"));
			return false;
		}

		return true;
	}

	public static boolean isStartedViaWebStart() {
		try {
			ServiceManager.lookup("javax.jnlp.DownloadService2");
			LOGGER.info("NEM monitor was started via web start.");
			return true;
		} catch (final UnavailableServiceException e) {
			return false;
		}
	}
}