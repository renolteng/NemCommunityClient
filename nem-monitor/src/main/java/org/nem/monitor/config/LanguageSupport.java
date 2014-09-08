package org.nem.monitor.config;

import java.util.*;

/**
 * Language support.
 */
public class LanguageSupport {

	private static final ResourceBundle languageBundle = loadBundle();

	public static String message(final String key) {
		return languageBundle.getString(key);
	}

	public static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle("languages.language", new Locale(Locale.getDefault().getLanguage()));
	}
}
