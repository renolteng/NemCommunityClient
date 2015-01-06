package org.nem.ncc.controller.viewmodels;

import org.nem.core.crypto.PublicKey;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.*;
import org.nem.ncc.addressbook.AccountLabel;

/**
 * Represents an account.
 */
public class AccountViewModel implements SerializableEntity {
	private final Address address;
	private final String label;
	private final AccountRemoteStatus remoteStatus;
	private final PublicKey publicKey;
	private final Amount balance;
	private final BlockAmount foragedBlocks;
	private final Double importance;
	private final AccountStatus status;

	/**
	 * Creates a new account view model around an account metadata pair.
	 *
	 * @param accountMetaDataPair The account metadata pair.
	 * @param label The account label.
	 */
	public AccountViewModel(
			final AccountMetaDataPair accountMetaDataPair,
			final AccountLabel label) {
		this(
				accountMetaDataPair.getAccount(),
				accountMetaDataPair.getMetaData().getStatus(),
				accountMetaDataPair.getMetaData().getRemoteStatus(),
				label);
	}

	/**
	 * Creates a new account view model.
	 *
	 * @param info The account info.
	 * @param status The account status.
	 * @param remoteStatus The remote account status.
	 * @param label The account label.
	 */
	public AccountViewModel(
			final AccountInfo info,
			final AccountStatus status,
			final AccountRemoteStatus remoteStatus,
			final AccountLabel label) {
		this.address = info.getAddress();
		this.label = null == label ? null : label.getLabel();
		this.remoteStatus = remoteStatus;

		this.publicKey = null == info.getKeyPair()
				? null
				: info.getKeyPair().getPublicKey();

		this.balance = info.getBalance();
		this.foragedBlocks = info.getNumForagedBlocks();
		this.importance = info.getImportance();
		this.status = status;
	}

	/**
	 * Gets the account address.
	 *
	 * @return The address.
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Gets the account remote status.
	 *
	 * @return The remote status.
	 */
	public AccountRemoteStatus getRemoteStatus() {
		return this.remoteStatus;
	}

	/**
	 * Gets the account label.
	 *
	 * @return The label.
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Gets the account public key.
	 *
	 * @return The public key.
	 */
	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	/**
	 * Gets the account balance.
	 *
	 * @return The balance.
	 */
	public Amount getBalance() {
		return this.balance;
	}

	/**
	 * Gets the number of blocks foraged by the account.
	 *
	 * @return The number of foraged blocks.
	 */
	public BlockAmount getForagedBlocks() {
		return this.foragedBlocks;
	}

	/**
	 * Gets the importance of the account.
	 *
	 * @return The importance.
	 */
	public Double getImportance() {
		return this.importance;
	}

	/**
	 * Gets the status of the account.
	 *
	 * @return The status.
	 */
	public AccountStatus getStatus() {
		return this.status;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Address.writeTo(serializer, "address", this.address);
		serializer.writeString("label", this.label);
		AccountRemoteStatus.writeTo(serializer, "remoteStatus", this.remoteStatus);
		serializer.writeBytes("publicKey", null == this.publicKey ? null : this.publicKey.getRaw());
		Amount.writeTo(serializer, "balance", this.balance);
		serializer.writeDouble("importance", this.importance);
		BlockAmount.writeTo(serializer, "foragedBlocks", this.foragedBlocks);
		AccountStatus.writeTo(serializer, "status", this.status);
	}
}
