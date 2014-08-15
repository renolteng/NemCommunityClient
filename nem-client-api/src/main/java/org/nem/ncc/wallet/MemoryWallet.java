package org.nem.ncc.wallet;

import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A memory-backed wallet implementation.
 */
public class MemoryWallet implements Wallet {
	private final WalletName name;
	private final Map<Address, WalletAccount> otherAccounts;
	private WalletAccount primaryAccount;

	/**
	 * Creates a new wallet with the specified name.
	 *
	 * @param name The wallet name.
	 */
	public MemoryWallet(final WalletName name) {
		this(name, new WalletAccount());
	}

	/**
	 * Creates a new wallet with the specified name and primary account.
	 *
	 * @param name The wallet name.
	 * @param primaryAccount The primary account.
	 */
	public MemoryWallet(final WalletName name, final WalletAccount primaryAccount) {
		this(name, primaryAccount, new ArrayList<>());
	}

	/**
	 * Creates a new wallet with the specified name, primary account, and other accounts.
	 *
	 * @param name The wallet name.
	 * @param primaryAccount The primary account.
	 * @param otherAccounts The other accounts.
	 */
	public MemoryWallet(final WalletName name, final WalletAccount primaryAccount, final Collection<WalletAccount> otherAccounts) {
		if (null == name) {
			throw new IllegalArgumentException("name must be non-null");
		}

		if (null == primaryAccount) {
			throw new IllegalArgumentException("primaryAccount must be non-null");
		}

		if (null == otherAccounts) {
			throw new IllegalArgumentException("otherAccounts must be non-null");
		}

		this.name = name;
		this.primaryAccount = primaryAccount;
		this.otherAccounts = new ConcurrentHashMap<>();
		this.addOtherAccounts(otherAccounts);
	}

	/**
	 * Deserializes a wallet.
	 *
	 * @param deserializer The deserializer.
	 */
	public MemoryWallet(final Deserializer deserializer) {
		this.name = WalletName.readFrom(deserializer, "name");
		this.primaryAccount = deserializer.readObject("primaryAccount", WalletAccount::new);
		this.otherAccounts = new ConcurrentHashMap<>();
		this.addOtherAccounts(deserializer.readObjectArray("otherAccounts", WalletAccount::new));
	}

	private void addOtherAccounts(final Collection<WalletAccount> accounts) {
		accounts.forEach(this::addOtherAccount);
	}

	@Override
	public WalletName getName() {
		return this.name;
	}

	@Override
	public WalletAccount getPrimaryAccount() {
		return this.primaryAccount;
	}

	@Override
	public List<WalletAccount> getOtherAccounts() {
		return new ArrayList<>(this.otherAccounts.values());
	}

	@Override
	public void addOtherAccount(final WalletAccount account) {
		if (null == account) {
			throw new IllegalArgumentException("account must be non-null");
		}

		if (this.primaryAccount.equals(account) || this.otherAccounts.containsKey(account.getAddress())) {
			throw new WalletException(WalletException.Code.WALLET_ALREADY_CONTAINS_ACCOUNT);
		}

		this.otherAccounts.put(account.getAddress(), account);
	}

	@Override
	public void setPrimaryAccount(final Address address) {
		if (null == address) {
			throw new IllegalArgumentException("account must be non-null");
		}

		// don't do anything if the current primary account is desired
		if (this.primaryAccount.getAddress().equals(address)) {
			return;
		}

		final WalletAccount removedAccount = this.otherAccounts.remove(address);
		if (null == removedAccount) {
			throw new WalletException(WalletException.Code.WALLET_ACCOUNT_NOT_IN_WALLET);
		}

		this.otherAccounts.put(this.primaryAccount.getAddress(), this.primaryAccount);
		this.primaryAccount = removedAccount;
	}

	@Override
	public void removeAccount(final Address address) {
		if (this.primaryAccount.getAddress().equals(address)) {
			throw new WalletException(WalletException.Code.WALLET_PRIMARY_ACCOUNT_CANNOT_BE_REMOVED);
		}

		if (null == this.otherAccounts.remove(address)) {
			throw new WalletException(WalletException.Code.WALLET_ACCOUNT_NOT_IN_WALLET);
		}
	}

	@Override
	public PrivateKey getAccountPrivateKey(final Address address) {
		final WalletAccount account = this.tryGetWalletAccount(address);
		if (null == account) {
			throw new WalletException(WalletException.Code.WALLET_ACCOUNT_NOT_IN_WALLET);
		}

		return account.getPrivateKey();
	}

	@Override
	public WalletAccount tryGetWalletAccount(final Address address) {
		final List<WalletAccount> allAccounts = new ArrayList<>(this.otherAccounts.values());
		allAccounts.add(this.primaryAccount);

		for (final WalletAccount account : allAccounts) {
			if (account.getAddress().equals(address)) {
				return account;
			}
		}

		return null;
	}

	@Override
	public void serialize(final Serializer serializer) {
		WalletName.writeTo(serializer, "name", this.name);
		serializer.writeObject("primaryAccount", this.primaryAccount);
		serializer.writeObjectArray("otherAccounts", this.otherAccounts.values());
	}
}
