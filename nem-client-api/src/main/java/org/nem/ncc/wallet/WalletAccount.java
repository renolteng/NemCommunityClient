package org.nem.ncc.wallet;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;

import java.math.BigInteger;

/**
 * Represents an account that is stored in a wallet.
 */
public class WalletAccount implements SerializableEntity {
	private final Address address;
	private final PrivateKey privateKey;

	// TODO 20141005 J-G: i'm not sure i really like this change ... i view a wallet as a collection of accounts
	// > the accounts may or may not be related, but i don't see why an account needs to know about its remote account
	// > the link is in the block chain and can change over time
	// > i'd really prefer to keep the wallet format as simple as possible
	// > if we really want to treat accounts heterogenously, i would suggest adding a bit-flags field


	// Will only be filled if the account is used for remote harvesting.
	private PrivateKey remoteHarvestingPrivateKey;
	private NodeEndpoint remoteHarvestingEndpoint;

	/**
	 * Creates a new wallet account around a new account.
	 */
	public WalletAccount() {
		this(new KeyPair().getPrivateKey(), new KeyPair().getPrivateKey());
	}

	/**
	 * Creates a new wallet account.
	 *
	 * @param privateKey The private key.
	 */
	public WalletAccount(final PrivateKey privateKey) {
		this(privateKey, new KeyPair().getPrivateKey());
	}

	/**
	 * Creates a new wallet account based on the provided key-pair for the account itself and also for remote harvesting if key is not null.
	 *
	 * @param privateKey The private key.
	 * @param remoteHarvestingPrivateKey The raw private key.
	 */
	public WalletAccount(final PrivateKey privateKey, final PrivateKey remoteHarvestingPrivateKey) {
		if (null == privateKey || null == remoteHarvestingPrivateKey) {
			throw new IllegalArgumentException("wallet account requires private key");
		}

		this.address = Address.fromPublicKey(new KeyPair(privateKey).getPublicKey());
		this.privateKey = privateKey;
		this.remoteHarvestingPrivateKey = remoteHarvestingPrivateKey;
		this.remoteHarvestingEndpoint = null;
	}

	/**
	 * Deserializes a wallet account.
	 *
	 * @param deserializer The deserializer.
	 */
	public WalletAccount(final Deserializer deserializer) {
		// TODO 20141005 - so we're now requiring every account to have a remote harvesting private key?
		// TODO 20141006 G-J: yeah it probably would be nicer to have accounts and link them somehow,
		//   BUT think how this should be done from user perspective. How would you like to do "account linking"
		//   inside UI (that given acc will be remote for other account)? Also I always like "your mom" argument,
		//   so, how would you explain "your mom", she need to link two accounts? also, why would you want
		//   users to create empty account, that they actually can't do anything with besides linking it to main account?
		// TODO 20141005 and what is the point of storing the remote endpoint here?
		// TODO 20141006 G-J: I'm not sure what I've told Thies, but the reason for storing it here is that:
		// a) frontend must be able to do /status on proper endpoint
		// b) /unlock must be made on proper endpoint
		// I'm not sure it there's sense to have separate endpoint for harvesting, I haven't actually mentioned it
		// anywhere on trello, but please also check my comment in RemoteHarvestRequest
		this(new PrivateKey(deserializer.readBigInteger("privateKey")),
				new PrivateKey(deserializer.readBigInteger("remoteHarvestingPrivateKey")));
		final NodeEndpoint endpoint = deserializer.readOptionalObject("remoteHarvestingEndpoint", NodeEndpoint::new);
		this.remoteHarvestingEndpoint = endpoint;
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
		if (remoteHarvestingPrivateKey == null) {
			remoteHarvestingPrivateKey = new KeyPair().getPrivateKey();
		}

		return remoteHarvestingPrivateKey;
	}

	/**
	 * Gets the node endpoint of the remote harvesting nis node.
	 *
	 * @return The endpoint.
	 */
	public NodeEndpoint getRemoteHarvestingEndpoint() {
		return remoteHarvestingEndpoint;
	}

	/**
	 * Sets the node endpoint of the remote harvesting nis node.
	 *
	 * @param remoteHarvestingEndpoint The endpoint.
	 */
	public void setRemoteHarvestingEndpoint(final NodeEndpoint remoteHarvestingEndpoint) {
		this.remoteHarvestingEndpoint = remoteHarvestingEndpoint;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeBigInteger("privateKey", this.privateKey.getRaw());
		serializer.writeBigInteger("remoteHarvestingPrivateKey", this.remoteHarvestingPrivateKey.getRaw());
		serializer.writeObject("remoteHarvestingEndpoint", this.remoteHarvestingEndpoint);
	}

	@Override
	public int hashCode() {
		return this.address.hashCode();
	}

	@Override
	// TODO 20141002 G-?: should this also compare remote priv key? I guess not
	public boolean equals(final Object obj) {
		if (!(obj instanceof WalletAccount)) {
			return false;
		}

		final WalletAccount rhs = (WalletAccount) obj;
		return this.address.equals(rhs.address);
	}

	@Override
	public String toString() {
		return this.address.toString();
	}
}
