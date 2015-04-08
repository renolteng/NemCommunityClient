package org.nem.console.models;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

/**
 * A pair comprised of an alias and a keypair.
 */
public class AliasedKeyPair implements SerializableEntity {
	private final String alias;
	private final Address address;
	private final KeyPair keyPair;

	/**
	 * Creates a new pair.
	 *
	 * @param alias The alias.
	 * @param keyPair The key pair.
	 */
	public AliasedKeyPair(final String alias, final KeyPair keyPair) {
		this.alias = alias;
		this.keyPair = keyPair;
		this.address = Address.fromPublicKey(this.keyPair.getPublicKey());
	}

	/**
	 * Deserializes an aliased key pair.
	 *
	 * @param deserializer The deserializer.
	 */
	public AliasedKeyPair(final Deserializer deserializer) {
		this.alias = deserializer.readString("alias");
		final PrivateKey sk = new PrivateKey(deserializer.readBigInteger("sk"));
		this.keyPair = new KeyPair(sk);
		this.address = Address.fromPublicKey(this.keyPair.getPublicKey());
	}

	/**
	 * Gets the alias.
	 *
	 * @return The alias.
	 */
	public String alias() {
		return this.alias;
	}

	/**
	 * Gets the address.
	 *
	 * @return The address.
	 */
	public Address address() {
		return this.address;
	}

	/**
	 * Gets the key pair.
	 *
	 * @return The key pair.
	 */
	public KeyPair keyPair() {
		return this.keyPair;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("alias", this.alias);
		serializer.writeBigInteger("sk", this.keyPair.getPrivateKey().getRaw());
	}
}
