package org.nem.ncc.cache;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.*;
import org.nem.core.serialization.AccountLookup;
import org.nem.ncc.services.WalletServices;
import org.nem.ncc.wallet.WalletAccount;
import org.springframework.context.annotation.Primary;

/**
 * An AccountLookup decorator that is aware of wallets and adds the private keys
 * of open wallet accounts to accounts returned by this implementation.
 */
@Primary
public class WalletAwareAccountLookup implements AccountLookup {
	private final AccountLookup accountLookup;
	private final WalletServices walletServices;

	/**
	 * Creates a new wallet aware account lookup.
	 *
	 * @param accountLookup The account lookup.
	 * @param walletServices The wallet services.
	 */
	public WalletAwareAccountLookup(
			final AccountLookup accountLookup,
			final WalletServices walletServices) {
		this.accountLookup = accountLookup;
		this.walletServices = walletServices;
	}

	@Override
	public Account findByAddress(final Address address) {
		// don't cache private keys so that private keys will never be provided for closed wallet accounts
		final Account account = this.accountLookup.findByAddress(address);
		if (null == account) {
			return null;
		}

		final WalletAccount walletAccount = this.walletServices.tryFindOpenAccount(address);
		return null == walletAccount
				? account
				: account.shallowCopyWithKeyPair(new KeyPair(walletAccount.getPrivateKey()));
	}

	@Override
	public boolean isKnownAddress(final Address address) {
		return this.accountLookup.isKnownAddress(address);
	}
}
