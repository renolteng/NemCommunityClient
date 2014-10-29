package org.nem.vanity.generator;

import org.nem.core.crypto.PrivateKey;

import java.util.*;
import javax.swing.AbstractListModel;

/**
 * A swing model that holds the list of generated vanity addresses
 */
public class VanityAddressModel extends AbstractListModel<VanityAddress> {
	final private List<VanityAddress> vanityAddresses;

	/**
	 * Creates a new Model
	 *
	 */
	public VanityAddressModel() {
		this.vanityAddresses = new ArrayList<VanityAddress>();
	}

	/**
	 * A new vanity address was found. Save the address and notify listeners
	 * 
	 * @param address
	 * @param privateKey
	 */
	public void addressFound(final String address, final PrivateKey privateKey) {
		vanityAddresses.add(new VanityAddress(address, privateKey));
		int index = vanityAddresses.size() - 1;
		fireIntervalAdded(this, index, index);
	}

	/**
	 * Get a string that of the element at the provided index which is used for the clipboard.
	 * 
	 * @param index
	 * @return string containing the address and the private key.
	 */
	public String getClipboardFormatForElementAt(int index) {
		VanityAddress vanityAddress = vanityAddresses.get(index);
		return vanityAddress.getClipboardFormat();
	}

	@Override
	public int getSize() {
		return vanityAddresses.size();
	}

	@Override
	public VanityAddress getElementAt(int index) {
		return vanityAddresses.get(index);
	}
}
