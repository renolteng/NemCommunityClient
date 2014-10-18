package org.nem.ncc.model;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.core.utils.Base32Encoder;

/**
 * Simple view on a key pair.
 */
public class KeyPairView implements SerializableEntity {

	final KeyPair keyPair;
	final byte networkVersion;

	public KeyPairView(final KeyPair keyPair, final byte networkVersion) {
		this.keyPair = keyPair;
		this.networkVersion = networkVersion;
	}

	/**
	 * Deserializes a key pair view.
	 *
	 * @param deserializer The deserializer.
	 */
	public KeyPairView(final Deserializer deserializer) {
		final PrivateKey privKey = PrivateKey.fromHexString(deserializer.readString("privateKey"));
		final PublicKey pubKey = PublicKey.fromHexString(deserializer.readString("publicKey"));
		this.keyPair = new KeyPair(privKey, pubKey);
		final String encodedAddress = deserializer.readString("address");
		final byte[] encodedBytes;
		try {
			encodedBytes = Base32Encoder.getBytes(encodedAddress);
		} catch (final IllegalArgumentException e) {
			throw new IllegalArgumentException("invalid deserializer passed to key pair view ctor.", e);
		}
		this.networkVersion = encodedBytes[0];
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
