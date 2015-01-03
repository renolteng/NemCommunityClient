package org.nem.ncc.wallet;

import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Address;
import org.nem.core.serialization.Serializer;
import org.nem.ncc.wallet.storage.WalletDescriptor;

import java.util.List;

/**
 * Wallet implementation that automatically saves the wallet after changes are made.
 */
public class AutoSavingWallet implements Wallet {
	private final StorableWallet wallet;
	private final WalletDescriptor descriptor;
	private final WalletRepository repository;

	/**
	 * Creates a new auto-saving wallet.
	 *
	 * @param wallet The original wallet.
	 * @param descriptor The descriptor.
	 * @param repository The repository to use for saving.
	 */
	public AutoSavingWallet(
			final StorableWallet wallet,
			final WalletDescriptor descriptor,
			final WalletRepository repository) {
		this.wallet = wallet;
		this.descriptor = descriptor;
		this.repository = repository;
	}

	@Override
	public WalletName getName() {
		return this.wallet.getName();
	}

	@Override
	public WalletAccount getPrimaryAccount() {
		return this.wallet.getPrimaryAccount();
	}

	@Override
	public List<WalletAccount> getOtherAccounts() {
		return this.wallet.getOtherAccounts();
	}

	@Override
	public void addOtherAccount(final WalletAccount account) {
		this.wallet.addOtherAccount(account);
		this.save();
	}

	@Override
	public void setPrimaryAccount(final Address address) {
		this.wallet.setPrimaryAccount(address);
		this.save();
	}

	@Override
	public void removeAccount(final Address address) {
		this.wallet.removeAccount(address);
		this.save();
	}

	@Override
	public PrivateKey getAccountPrivateKey(final Address address) {
		return this.wallet.getAccountPrivateKey(address);
	}

	@Override
	public WalletAccount tryGetWalletAccount(final Address address) {
		return this.wallet.tryGetWalletAccount(address);
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.wallet.serialize(serializer);
	}

	/**
	 * Saves this wallet to the underlying repository.
	 */
	public void save() {
		if (null != this.repository) {
			this.repository.save(this.descriptor, this.wallet);
		}
	}
}