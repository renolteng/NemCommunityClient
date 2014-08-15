package org.nem.ncc.exceptions;

/**
 * An enum that has explicit values.
 */
public interface ValueBasedEnum {

	/**
	 * Gets the underlying integer representation of the enum.
	 *
	 * @return The underlying value.
	 */
	int value();
}
