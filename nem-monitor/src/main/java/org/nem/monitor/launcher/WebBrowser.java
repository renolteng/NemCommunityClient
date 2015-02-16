package org.nem.monitor.launcher;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.utils.ExceptionUtils;

import java.awt.*;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Exposes functions for interacting with web browsers.
 */
public class WebBrowser {
	private static final Logger LOGGER = Logger.getLogger(WebBrowser.class.getName());

	/**
	 * Navigates to the desired endpoint.
	 *
	 * @param endpoint The NodeEndpoint endpoint.
	 */
	public void navigate(final NodeEndpoint endpoint) {
		final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		this.navigate(desktop, endpoint);
	}

	/**
	 * Navigates to the desired endpoint using the specified service.
	 *
	 * @param desktop The service.
	 * @param endpoint The NodeEndpoint endpoint.
	 */
	public void navigate(final Desktop desktop, final NodeEndpoint endpoint) {
		final URL url = endpoint.getBaseUrl();
		if (null == desktop || !desktop.isSupported(Desktop.Action.BROWSE)) {
			LOGGER.warning("web browser is not supported");
			return;
		}

		LOGGER.info(String.format("navigating to %s", url));
		ExceptionUtils.propagateVoid(() -> desktop.browse(url.toURI()));
	}
}
