package org.nem.ncc.model;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

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

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("privateKey", this.keyPair.getPrivateKey().toString());
		serializer.writeString("publicKey", this.keyPair.getPublicKey().toString());
		serializer.writeString("address", Address.fromPublicKey(networkVersion, keyPair.getPublicKey()).getEncoded());
	}
}
