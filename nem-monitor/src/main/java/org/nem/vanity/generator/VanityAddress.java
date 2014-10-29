package org.nem.vanity.generator;

import org.nem.core.crypto.*;

/**
 * A view model of a vanity address.
 *
 */
public class VanityAddress {
	final private String address;
	final private String privateKey;
	final private String vanityText;
	final byte version;
	
	/**
	 * Create a new view model for the provided NEM address and private key
	 * @param address
	 * @param privateKey
	 * @param vanityText
	 * @param version
	 */
	public VanityAddress(final String address, final PrivateKey privateKey, final String vanityText, final byte version) {
		this.address = address;
		this.privateKey = privateKey.toString();
		this.vanityText = vanityText;
		this.version = version;
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
	
	public String getVanityText() {
		return vanityText;
	}

	public byte getVersion() {
		return version;
	}

	public String getClipboardFormat() {
		String result = String.format("%s / %s", getAddress(), getPrivateKey());
		return result;
	}

	public String toString() {
		return getAddress();
	}
}
