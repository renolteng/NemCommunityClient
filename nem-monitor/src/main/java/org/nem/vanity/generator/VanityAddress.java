package org.nem.vanity.generator;

import org.nem.core.crypto.*;

/**
 * A view model of a vanity address.
 *
 */
public class VanityAddress {
	final private String address;
	final private String privateKey;
	
	/**
	 * Create a new view model for the provided NEM address and private key
	 * @param address
	 * @param privateKey
	 */
	public VanityAddress(final String address, final PrivateKey privateKey) {
		this.address = address;
		this.privateKey = privateKey.toString();
	}

	/**
	 * Get the address of the vanity address
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	public String getPrivateKey() {
		return privateKey;
	}
	
	public String getClipboardFormat() {
		String result = String.format("%s / %s", getAddress(), getPrivateKey());
		return result;
	}

	public String toString() {
		return getAddress();
	}
}
