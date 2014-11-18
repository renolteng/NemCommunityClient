package org.nem.monitor.config;

import org.nem.core.i18n.UTF8ResourceBundleControl;

import java.util.*;

/**
 * Language support.
 */
public class LanguageSupport {
	private static final ResourceBundle languageBundle = loadBundle();

	/**
	 * Gets a message from the resource bundle.
	 *
	 * @param key The message key.
	 * @return The message.
	 */
	public static String message(final String key) {
		return languageBundle.getString(key);
	}

	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle("languages.language", new UTF8ResourceBundleControl());
	}
}
