package org.nem.ncc.cache;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.ncc.services.*;
import org.nem.ncc.wallet.WalletAccount;
import org.springframework.context.annotation.Primary;

/**
 * An AccountLookup decorator that is aware of wallets and adds the private keys
 * of open wallet accounts to accounts returned by this implementation.
 */
@Primary
public class WalletAwareAccountLookup implements AccountMetaDataPairLookup {
	private final AccountMetaDataPairLookup accountLookup;
	private final WalletServices walletServices;

	/**
	 * Creates a new wallet aware account lookup.
	 *
	 * @param accountLookup The account lookup.
	 * @param walletServices The wallet services.
	 */
	public WalletAwareAccountLookup(
			final AccountMetaDataPairLookup accountLookup,
			final WalletServices walletServices) {
		this.accountLookup = accountLookup;
		this.walletServices = walletServices;
	}

	@Override
	public Account findByAddress(final Address id) {
		// don't cache private keys so that private keys will never be provided for closed wallet accounts
		final Account account = this.accountLookup.findByAddress(id);
		if (null == account) {
			return null;
		}

		final WalletAccount walletAccount = this.walletServices.tryFindOpenAccount(id);
		return null == walletAccount
				? account
				: account.shallowCopyWithKeyPair(new KeyPair(walletAccount.getPrivateKey()));
	}

	@Override
	public boolean isKnownAddress(final Address id) {
		return this.accountLookup.isKnownAddress(id);
	}

	@Override
	public AccountMetaDataPair findPairByAddress(final Address id) {
		final AccountMetaDataPair pair = this.accountLookup.findPairByAddress(id);
		if (null == pair || null != pair.getAccount().getKeyPair()) {
			return pair;
		}

		final WalletAccount walletAccount = this.walletServices.tryFindOpenAccount(id);
		if (null == walletAccount) {
			return pair;
		}

		final AccountInfo info = pair.getAccount();
		// TODO 20141004 G-?: is there a reson we're creating AccountInfo here (instead of using "info"?)
		return new AccountMetaDataPair(
				new AccountInfo(
						walletAccount.getAddress(),
						info.getBalance(),
						info.getNumForagedBlocks(),
						info.getRemoteStatus(),
						info.getLabel(),
						info.getImportance()),
				pair.getMetaData());
	}
}