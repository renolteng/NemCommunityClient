package org.nem.monitor;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.utils.ExceptionUtils;

import javax.jnlp.*;
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
		ExceptionUtils.propagateVoid(() -> {
			final BasicService service = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
			this.navigate(service, endpoint);
		});
	}

	/**
	 * Navigates to the desired endpoint using the specified service.
	 *
	 * @param service The service.
	 * @param endpoint The NodeEndpoint endpoint.
	 */
	public void navigate(final BasicService service, final NodeEndpoint endpoint) {
		final URL url = endpoint.getBaseUrl();
		if (!service.isWebBrowserSupported()) {
			LOGGER.warning("web browser is not supported");
			return;
		}

		LOGGER.info(String.format("navigating to %s", url));
		service.showDocument(url);
	}
}
