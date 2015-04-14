package org.nem.console.utils;

/**
 * An observer that is notified of address generation status.
 */
public interface MultiAddressGeneratorObserver {

	/**
	 * Notifies the observer that an iteration has started.
	 *
	 * @param source The source generator.
	 * @param iteration The iteration.
	 */
	void notifyIteration(final MultiAddressGenerator source, final int iteration);

	/**
	 * Notifies the observer that an alias has been found.
	 *
	 * @param source The source generator.
	 * @param alias The found alias.
	 */
	void notifyAddressFound(final MultiAddressGenerator source, final String alias);
}
