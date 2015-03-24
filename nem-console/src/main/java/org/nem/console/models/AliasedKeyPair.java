package org.nem.console.models;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

public class AliasedKeyPair implements SerializableEntity {
	private final String alias;
	private final Address address;
	private final KeyPair keyPair;

	public AliasedKeyPair(final String alias, final KeyPair keyPair) {
		this.alias = alias;
		this.keyPair = keyPair;
		this.address = Address.fromPublicKey(this.keyPair.getPublicKey());
	}

	public AliasedKeyPair(final Deserializer deserializer) {
		this.alias = deserializer.readString("alias");
		final PrivateKey sk = new PrivateKey(deserializer.readBigInteger("sk"));
		this.keyPair = new KeyPair(sk);
		this.address = Address.fromPublicKey(this.keyPair.getPublicKey());
	}

	public String alias() {
		return this.alias;
	}

	public Address address() {
		return this.address;
	}

	public KeyPair keyPair() {
		return this.keyPair;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("alias", this.alias);
		serializer.writeBigInteger("sk", this.keyPair.getPrivateKey().getRaw());
	}
}
