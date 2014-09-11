package org.nem.ncc.wallet;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

import java.math.BigInteger;

/**
 * Represents an account that is stored in a wallet.
 */
public class WalletAccount implements SerializableEntity {
	private final Address address;
	private final PrivateKey privateKey;
	//Will only be filled if the account is used for remote harvesting.
	private PrivateKey remoteHarvestingPrivateKey; 

	/**
	 * Creates a new wallet account around a new account.
	 */
	public WalletAccount() {
		final KeyPair keyPair = new KeyPair();
		this.address = Address.fromPublicKey(keyPair.getPublicKey());
		this.privateKey = keyPair.getPrivateKey();
		this.remoteHarvestingPrivateKey = null;
	}

	/**
	 * Creates a new wallet account.
	 *
	 * @param privateKey The private key.
	 */
	public WalletAccount(final PrivateKey privateKey) {
		if (null == privateKey) {
			throw new IllegalArgumentException("wallet account requires private key");
		}

		this.address = Address.fromPublicKey(new KeyPair(privateKey).getPublicKey());
		this.privateKey = privateKey;
		this.remoteHarvestingPrivateKey = null;
	}

	/**
	 * Creates a new wallet account based on the provided key-pair for the account itself 
	 * and also for remote harvesting if key is not null.
	 *
	 * @param privateKey The private key.
	 * @param remoteHarvestingPK The raw private key.
	 */
	public WalletAccount(final PrivateKey privateKey, final BigInteger remoteHarvestingPK) {
		if (null == privateKey) {
			throw new IllegalArgumentException("wallet account requires private key");
		}

		this.address = Address.fromPublicKey(new KeyPair(privateKey).getPublicKey());
		this.privateKey = privateKey;
		//Be fault tolerant
		if(remoteHarvestingPK != null) {
			this.remoteHarvestingPrivateKey = new PrivateKey(remoteHarvestingPK);
		}
	}

	/**
	 * Deserializes a wallet account.
	 *
	 * @param deserializer The deserializer.
	 */
	public WalletAccount(final Deserializer deserializer) {
		this(new PrivateKey(deserializer.readBigInteger("privateKey")), deserializer.readOptionalBigInteger("remoteHarvestPrivateKey"));
	}

	/**
	 * Gets the address.
	 *
	 * @return The address.
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Gets the private key.
	 *
	 * @return The private key.
	 */
	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}
	
	/**
	 * Gets the private key for remote harvesting.
	 *
	 * @return The private key.
	 */
	public PrivateKey getRemoteHarvestingPrivateKey() {
		if(remoteHarvestingPrivateKey == null) {
			remoteHarvestingPrivateKey = new KeyPair().getPrivateKey();
		}
		
		return remoteHarvestingPrivateKey;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeBigInteger("privateKey", this.privateKey.getRaw());
		if(remoteHarvestingPrivateKey != null) {
			serializer.writeBigInteger("remoteHarvestPrivateKey", this.remoteHarvestingPrivateKey.getRaw());
		}
	}

	@Override
	public int hashCode() {
		return this.address.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof WalletAccount)) {
			return false;
		}

		final WalletAccount rhs = (WalletAccount)obj;
		return this.address.equals(rhs.address);
	}

	@Override
	public String toString() {
		return this.address.toString();
	}
}
