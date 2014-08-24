package org.nem.monitor;

import org.nem.core.connect.*;
import org.nem.core.deploy.LoggingBootstrapper;
import org.nem.monitor.config.*;
import org.nem.monitor.node.*;
import org.nem.monitor.ux.TrayIconBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * The nem monitor program.
 */
public class NemMonitor {
	private static final Logger LOGGER = Logger.getLogger(NemMonitor.class.getName());

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

		SwingUtilities.invokeLater(() -> {
			LOGGER.info("setting up system tray");

			// fail if the system tray is not supported
			if (!SystemTray.isSupported()) {
				throw new SystemTrayException("SystemTray is not supported");
			}

			final SystemTray tray = SystemTray.getSystemTray();
			final TrayIconBuilder builder = new TrayIconBuilder(
					createHttpMethodClient(),
					new WebStartLauncher(new MonitorConfiguration().getNemFolder()),
					new WebBrowser());
			builder.addStatusMenuItems(new NisNodePolicy(), commandLine.getNisJnlpUrl());
			builder.addSeparator();
			builder.addStatusMenuItems(new NccNodePolicy(), commandLine.getNccJnlpUrl());
			builder.addSeparator();
			builder.addExitMenuItem(tray);

			try {
				tray.add(builder.create());
			} catch (final AWTException e) {
				throw new SystemTrayException("Unable to add icon to system tray", e);
			}
		});
	}

	private static HttpMethodClient<ErrorResponseDeserializerUnion> createHttpMethodClient() {
		final int CONNECTION_TIMEOUT = 1000;
		final int SOCKET_TIMEOUT = 1000;
		final int REQUEST_TIMEOUT = 2000;
		return new HttpMethodClient<>(CONNECTION_TIMEOUT, SOCKET_TIMEOUT, REQUEST_TIMEOUT);
	}
}