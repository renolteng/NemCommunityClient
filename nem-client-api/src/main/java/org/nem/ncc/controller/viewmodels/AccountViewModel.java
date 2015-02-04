package org.nem.ncc.controller.viewmodels;

import org.nem.core.crypto.PublicKey;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.*;

import java.util.List;

/**
 * Represents an account.
 */
public class AccountViewModel implements SerializableEntity {
	private final Address address;
	private final AccountRemoteStatus remoteStatus;
	private final PublicKey publicKey;
	private final Amount balance;
	private final BlockAmount harvestedBlocks;
	private final Double importance;
	private final AccountStatus status;
	private final List<AccountInfo> multisigAccounts;

	/**
	 * Creates a new account view model around an account metadata pair.
	 *
	 * @param accountMetaDataPair The account metadata pair.
	 */
	public AccountViewModel(final AccountMetaDataPair accountMetaDataPair) {
		this(
				accountMetaDataPair.getAccount(),
				accountMetaDataPair.getMetaData().getStatus(),
				accountMetaDataPair.getMetaData().getRemoteStatus(),
				accountMetaDataPair.getMetaData().getCosignatoryOf());
	}

	/**
	 * Creates a new account view model.
	 *
	 * @param info The account info.
	 * @param status The account status.
	 * @param remoteStatus The remote account status.
	 * @param multisigAccounts The list of account information.
	 */
	public AccountViewModel(
			final AccountInfo info,
			final AccountStatus status,
			final AccountRemoteStatus remoteStatus,
			final List<AccountInfo> multisigAccounts) {
		this.address = info.getAddress();
		this.remoteStatus = remoteStatus;

		this.publicKey = null == info.getKeyPair()
				? null
				: info.getKeyPair().getPublicKey();

		this.balance = info.getBalance();
		this.harvestedBlocks = info.getNumHarvestedBlocks();
		this.importance = info.getImportance();
		this.status = status;
		this.multisigAccounts = multisigAccounts;
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
	 * Gets the number of blocks harvested by the account.
	 *
	 * @return The number of harvested blocks.
	 */
	public BlockAmount getHarvestedBlocks() {
		return this.harvestedBlocks;
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

	/**
	 * Gets the list of accounts (infos) that this account is cosignatory of.
	 *
	 * @return The list of accounts (infos).
	 */
	public List<AccountInfo> getMultisigAccounts() {
		return this.multisigAccounts;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Address.writeTo(serializer, "address", this.address);
		AccountRemoteStatus.writeTo(serializer, "remoteStatus", this.remoteStatus);
		serializer.writeBytes("publicKey", null == this.publicKey ? null : this.publicKey.getRaw());
		Amount.writeTo(serializer, "balance", this.balance);
		serializer.writeDouble("importance", this.importance);
		BlockAmount.writeTo(serializer, "harvestedBlocks", this.harvestedBlocks);
		AccountStatus.writeTo(serializer, "status", this.status);
		serializer.writeObjectArray("multisigAccounts", this.multisigAccounts);
	}
}
