package org.nem.ncc.addressbook;

import org.nem.core.model.Address;

/**
 * A collection of account labels that are mapped to accounts.
 */
public interface AccountLabels {

	/**
	 * Gets the label associated with the specified address.
	 *
	 * @param address The address.
	 * @return The label.
	 */
	public AccountLabel getLabel(final Address address);

	/**
	 * Changes an existing label associated with the specified address.
	 * <br/>
	 * Having setLabel and addLabel allows better insight into the gui state and the ability
	 * to provide better error messages.
	 *
	 * @param address The address.
	 * @param label The public label.
	 * @param privateLabel The private label.
	 */
	public void setLabel(final Address address, final String label, final String privateLabel);

	/**
	 * Adds a new account label.
	 *
	 * @param accountLabel The account label.
	 */
	public void addLabel(final AccountLabel accountLabel);

	/**
	 * Removes the label associate with the specified address.
	 *
	 * @param address The address.
	 */
	public void removeLabel(final Address address);
}
