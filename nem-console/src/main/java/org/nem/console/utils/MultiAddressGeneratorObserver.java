package org.nem.console.utils;

public interface MultiAddressGeneratorObserver {

	void notifyIteration(final MultiAddressGenerator source, final int iteration);

	void notifyAddressFound(final MultiAddressGenerator source, final String alias);
}
