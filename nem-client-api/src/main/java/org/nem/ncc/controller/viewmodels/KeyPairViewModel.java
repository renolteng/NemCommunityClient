package org.nem.ncc.controller.viewmodels;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

/**
 * Simple view on a key pair.
 */
public class KeyPairViewModel implements SerializableEntity {
	private final KeyPair keyPair;
	private final byte networkVersion;

	/**
	 * Creates a key pair view model.
	 *
	 * @param keyPair The key pair.
	 * @param networkVersion The network version.
	 */
	public KeyPairViewModel(final KeyPair keyPair, final byte networkVersion) {
		this.keyPair = keyPair;
		this.networkVersion = networkVersion;
	}

	/**
	 * Gets the key pair.
	 *
	 * @return The key pair.
	 */
	public KeyPair getKeyPair() {
		return this.keyPair;
	}

	/**
	 * Gets the network version.
	 *
	 * @return The network version.
	 */
	public byte getNetworkVersion() {
		return this.networkVersion;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("privateKey", this.keyPair.getPrivateKey().toString());
		serializer.writeString("publicKey", this.keyPair.getPublicKey().toString());
		serializer.writeString("address", Address.fromPublicKey(this.networkVersion, this.keyPair.getPublicKey()).getEncoded());
	}
}
