package org.nem.deploy;

import java.lang.reflect.*;
import java.util.logging.*;

public class WebStartProxy {
	private static final Logger LOGGER = Logger.getLogger(WebStartProxy.class.getName());

	public static boolean isWebStartAvailable() {
		boolean result = false;
		// Let the show start without binding statically to JNLP
		// We first try to get the JNLP Service Manager

		try {
			Class.forName("javax.jnlp.ServiceManager");
			Class.forName("javax.jnlp.BasicService");

			result = true;
		} catch (ClassNotFoundException | NoClassDefFoundError ex) {
			// handle exception case
			LOGGER.info(String.format("JNLP not available, not started via WebStart. Assuming headless run. (%s)", ex.getMessage()));
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean openWebBrowser(final String homeUrl) {
		boolean result = false;

		try {
			final Class webStartHandler = Class.forName("org.nem.deploy.WebStartHandler");
			final Method openWebBrowser = webStartHandler.getMethod("openWebBrowser", new Class[] { String.class });
			final Object resultObject = openWebBrowser.invoke(webStartHandler, homeUrl);

			result = resultObject != null && (Boolean)resultObject;
		} catch (ClassNotFoundException | NoClassDefFoundError ex) {
			// handle exception case
			LOGGER.info("Class <org.nem.deploy.WebStartHandler> could not be loaded. Assuming WebStart not available.");
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchMethodException e) {
			LOGGER.log(Level.SEVERE, "Reflection failed.", e);
		} catch (final InvocationTargetException e) {
			LOGGER.log(Level.SEVERE, "Method call failed.", e);
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void logLocalWebStartCache() {
		try {
			final Class webStartHandler = Class.forName("org.nem.deploy.WebStartHandler");
			final Method getLocalInstallation = webStartHandler.getMethod("getLocalInstallation", new Class[] { Class.class });
			final Object resultObject = getLocalInstallation.invoke(webStartHandler, WebStartProxy.class);
			LOGGER.info(String.format("JavaWebStart cache: class <%s> located in <%s>.", WebStartProxy.class.getName(), resultObject));
		} catch (ClassNotFoundException | NoClassDefFoundError ex) {
			// handle exception case
			LOGGER.info("Class <org.nem.deploy.WebStartHandler> could not be loaded. Assuming WebStart not available.");
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchMethodException e) {
			LOGGER.log(Level.SEVERE, "Reflection failed.", e);
		} catch (final InvocationTargetException e) {
			LOGGER.log(Level.SEVERE, "Method call failed.", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void logSupportedServices() {
		try {
			final Class webStartHandler = Class.forName("org.nem.deploy.WebStartHandler");
			final Method logSupportedServices = webStartHandler.getMethod("logSupportedServices");
			logSupportedServices.invoke(webStartHandler);
		} catch (ClassNotFoundException | NoClassDefFoundError ex) {
			// handle exception case
			LOGGER.info("Class <org.nem.deploy.WebStartHandler> could not be loaded. Assuming WebStart not available.");
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchMethodException e) {
			LOGGER.log(Level.SEVERE, "Reflection failed.", e);
		} catch (final InvocationTargetException e) {
			LOGGER.log(Level.SEVERE, "Method call failed.", e);
		}
	}
}
