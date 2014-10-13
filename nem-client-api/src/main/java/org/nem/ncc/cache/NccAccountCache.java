package org.nem.ncc.cache;

import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.time.*;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.services.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * An account cache used by NCC.
 */
public class NccAccountCache implements AccountMetaDataPairLookup {
	private final AccountServices accountServices;
	private final TimeProvider timeProvider;
	private final int refreshInSeconds;
	private final ConcurrentMap<Address, FreshnessPair> cache;

	/**
	 * Creates a new account cache.
	 *
	 * @param accountServices The account services.
	 * @param timeProvider The time provider.
	 * @param refreshInSeconds The number of seconds that a cached entry is valid.
	 */
	public NccAccountCache(
			final AccountServices accountServices,
			final TimeProvider timeProvider,
			final int refreshInSeconds) {
		this.accountServices = accountServices;
		this.timeProvider = timeProvider;
		this.refreshInSeconds = refreshInSeconds;
		this.cache = new ConcurrentHashMap<>();
	}

	/**
	 * Gets all accounts in this cache.
	 *
	 * @return All accounts in this cache.
	 */
	public List<AccountInfo> getAccounts() {
		return this.cache.entrySet().stream()
				.map(e -> e.getValue().accountMetaDataPair.getAccount())
				.collect(Collectors.toList());
	}

	/**
	 * Seeds this cache with the specified accounts.
	 *
	 * @param seedAccounts The initial seed accounts.
	 */
	public void seedAccounts(final Collection<AccountInfo> seedAccounts) {
		seedAccounts.forEach(info -> this.cache.put(info.getAddress(), this.createFreshnessPairFromSeedAccount(info)));
	}

	private FreshnessPair createFreshnessPairFromSeedAccount(final AccountInfo info) {
		return new FreshnessPair(
				new AccountMetaDataPair(info, new AccountMetaData(AccountStatus.UNKNOWN, AccountRemoteStatus.INACTIVE)),
				null);
	}

	@Override
	public Account findByAddress(final Address id) {
		return this.findFreshnessPairByAddress(id).account;
	}

	@Override
	public boolean isKnownAddress(final Address id) {
		return this.cache.containsKey(id);
	}

	@Override
	public AccountMetaDataPair findPairByAddress(final Address id) {
		return this.findFreshnessPairByAddress(id).accountMetaDataPair;
	}

	private FreshnessPair findFreshnessPairByAddress(final Address id) {
		final TimeInstant currentTime = this.timeProvider.getCurrentTime();
		FreshnessPair freshnessPair = this.cache.getOrDefault(id, null);
		if (this.shouldUpdate(freshnessPair, currentTime)) {
			freshnessPair = new FreshnessPair(
					this.update(id, null == freshnessPair ? null : freshnessPair.accountMetaDataPair),
					currentTime);
			this.cache.put(id, freshnessPair);
		}

		return freshnessPair;
	}

	private AccountMetaDataPair update(
			final Address id,
			final AccountMetaDataPair originalPair) {
		try {
			return this.accountServices.getAccountMetaDataPair(id);
		} catch (final NccException ex) {
			if (null == originalPair) {
				throw ex;
			}

			return originalPair;
		}
	}

	private boolean shouldUpdate(final FreshnessPair freshnessPair, final TimeInstant currentTime) {
		return null == freshnessPair ||
				null == freshnessPair.refreshTime ||
				currentTime.subtract(freshnessPair.refreshTime) > this.refreshInSeconds;
	}

	private static class FreshnessPair {
		public final AccountMetaDataPair accountMetaDataPair;
		public final Account account;
		public final TimeInstant refreshTime;

		public FreshnessPair(
				final AccountMetaDataPair accountMetaDataPair,
				final TimeInstant refreshTime) {
			this.accountMetaDataPair = accountMetaDataPair;
			this.account = mapAccountInfoToAccount(this.accountMetaDataPair.getAccount());
			this.refreshTime = refreshTime;
		}

		private static Account mapAccountInfoToAccount(final AccountInfo info) {
			return new Account(
					info.getAddress(),
					info.getBalance(),
					info.getNumForagedBlocks(),
					info.getLabel());
		}
	}
}
