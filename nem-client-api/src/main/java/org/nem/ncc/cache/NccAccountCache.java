package org.nem.ncc.cache;

import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.time.*;
import org.nem.ncc.controller.requests.AccountIdRequest;
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
		// TODO 20150112 BR -> J: you had initially null as timestamp probably so every account would be updated the first
		// > time it was searched for. Since I changed to poll expired accounts on a regular interval it should be ok to use current time stamp.
		return new FreshnessPair(
				new AccountMetaDataPair(info, new AccountMetaData(AccountStatus.UNKNOWN, AccountRemoteStatus.INACTIVE)),
				this.timeProvider.getCurrentTime());
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
		if (null == freshnessPair) {
			freshnessPair = new FreshnessPair(this.update(id, null), currentTime);
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

	/**
	 * Updates all expired accounts. This method is called by a timer.
	 */
	public java.util.concurrent.CompletableFuture<java.lang.Void> updateCache() {
		final TimeInstant currentTime = this.timeProvider.getCurrentTime();
		final List<AccountIdRequest> requests = new ArrayList<>();
		this.cache.values().stream()
				.forEach(f -> { if (this.shouldUpdate(f, currentTime)) { requests.add(new AccountIdRequest(f.account.getAddress())); }});
		if (requests.isEmpty()) {
			return CompletableFuture.completedFuture(null);
		}
		try {
			final Collection<AccountMetaDataPair> pairs = this.accountServices.getAccountMetaDataPairs(requests);
			pairs.stream().forEach(p -> this.cache.put(p.getAccount().getAddress(), new FreshnessPair(p, currentTime)));
		} catch (final Exception ignored) {
		}

		return CompletableFuture.completedFuture(null);
	}

	private boolean shouldUpdate(final FreshnessPair freshnessPair, final TimeInstant currentTime) {
		return null == freshnessPair ||	currentTime.subtract(freshnessPair.refreshTime) > this.refreshInSeconds;
	}

	private static class FreshnessPair {
		public final AccountMetaDataPair accountMetaDataPair;
		public final Account account;
		public final TimeInstant refreshTime;

		public FreshnessPair(
				final AccountMetaDataPair accountMetaDataPair,
				final TimeInstant refreshTime) {
			this.accountMetaDataPair = accountMetaDataPair;
			this.account = new Account(this.accountMetaDataPair.getAccount().getAddress());
			this.refreshTime = refreshTime;
		}
	}
}
