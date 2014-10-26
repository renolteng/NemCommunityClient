package org.nem.vanity.generator;

import org.nem.core.crypto.*;

public class VanityAddress {
	final private String address;
	final private String privateKey;
	
	public VanityAddress(final String address, final PrivateKey privateKey) {
		this.address = address;
		this.privateKey = privateKey.toString();
	}

	public String getAddress() {
		return address;
	}

	public String getPrivateKey() {
		return privateKey;
	}

}
