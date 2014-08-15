package org.nem.deploy;

import javax.jnlp.*;
import java.io.File;
import java.net.*;
import java.security.CodeSource;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class WebStartHandler {
	private static final Logger LOGGER = Logger.getLogger(WebStartHandler.class.getName());

	protected WebStartHandler() {
		//
	}

	public static void logSupportedServices() {
		String result[] = null;
		result = ServiceManager.getServiceNames();

		if (result == null) {
			return;
		}

		for (final String string : result) {
			LOGGER.info(String.format("JNLP Client supports <%s>.", string));
		}
	}

	public static String getLocalInstallation(final Class clazz) {
		final CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
		final URL locationURL = codeSource.getLocation();
		String result = null;

		DownloadService2 jnlpDownloadService2 = null;
		try {
			jnlpDownloadService2 = (DownloadService2)ServiceManager.lookup("javax.jnlp.DownloadService2");
			final DownloadService2.ResourceSpec jnlpResourceSpec = new DownloadService2.ResourceSpec(locationURL.toExternalForm(), ".*",
					DownloadService2.ALL);
			final DownloadService2.ResourceSpec[] resources = jnlpDownloadService2.getCachedResources(jnlpResourceSpec);

			LOGGER.info(String.format("%d resources found.", resources.length));

			final DateFormat df = DateFormat.getDateTimeInstance();
			for (final DownloadService2.ResourceSpec resourceSpec : resources) {
				LOGGER.info(String.format("Resource <%s> <%s> <%s>", resourceSpec.getUrl(), resourceSpec.getVersion(),
						df.format(new Date(resourceSpec.getLastModified()))));
			}
			if (resources.length != 0) {
				final File file = new File(resources[0].getUrl());
				result = file.getAbsolutePath();
			}
		} catch (final UnavailableServiceException e) {
			//
			LOGGER.warning(String.format("WebStart not available <%s>.", e.getMessage()));
		}

		return result;
	}

	public static boolean openWebBrowser(final String homeUrl) {
		boolean result = false;

		BasicService jnlpBasicService = null;

		try {
			jnlpBasicService = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
			if (jnlpBasicService.isWebBrowserSupported()) {
				final URL homeURL = new URL(homeUrl);
				jnlpBasicService.showDocument(homeURL);
				result = true;
			}
		} catch (final UnavailableServiceException e) {
			LOGGER.warning(String.format("WebStart not available <%s>.", e.getMessage()));
		} catch (final MalformedURLException e) {
			LOGGER.warning(String.format("URL <%s> malformed.", homeUrl));
		}

		return result;
	}
}
