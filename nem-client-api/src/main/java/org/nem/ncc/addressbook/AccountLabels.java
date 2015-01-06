package org.nem.ncc.addressbook;

import org.nem.core.model.Address;

/**
 * A collection of account labels that are mapped to accounts.
 */
public interface AccountLabels {

	/**
	 * Gets the number of account labels.
	 *
	 * @return The number of account labels.
	 */
	public int getNumLabels();

	/**
	 * Gets the label associated with the specified address.
	 *
	 * @param address The address.
	 * @return The label.
	 */
	public AccountLabel getLabel(final Address address);

	/**
	 * Sets the labels associated with the specified address.
	 *
	 * @param address The address.
	 * @param label The public label.
	 * @param privateLabel The private label.
	 */
	public void setLabel(final Address address, final String label, final String privateLabel);

	/**
	 * Removes the label associate with the specified address.
	 *
	 * @param address The address.
	 */
	public void removeLabel(final Address address);
}
