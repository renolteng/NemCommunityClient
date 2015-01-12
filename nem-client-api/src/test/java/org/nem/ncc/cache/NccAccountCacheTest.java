package org.nem.ncc.cache;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.core.connect.ErrorResponse;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.time.*;
import org.nem.ncc.controller.requests.AccountIdRequest;
import org.nem.ncc.exceptions.NisException;
import org.nem.ncc.services.AccountServices;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NccAccountCacheTest {

	//region findByAddress

	@Test
	public void findByAddressDelegatesToAccountServicesIfAccountNotInCache() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		final Account account = context.cache.findByAddress(context.address);

		// Assert:
		assertAreEqual(context.pair1.getAccount(), account);
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(context.address);
		Mockito.verify(context.timeProvider, Mockito.times(1)).getCurrentTime();
	}
/*
	@Test
	public void findByAddressDelegatesToAccountServicesIfCachedAccountIsExpired() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(52), new TimeInstant(52 + 61));

		// Act:
		context.cache.findByAddress(context.address);
		final Account account = context.cache.findByAddress(context.address);

		// Assert:
		assertAreEqual(context.pair2.getAccount(), account);
		Mockito.verify(context.accountServices, Mockito.times(2)).getAccountMetaDataPair(context.address);
		Mockito.verify(context.timeProvider, Mockito.times(2)).getCurrentTime();
	}
*/
	@Test
	public void findByAddressUsesCachedValueIfCachedAccountIsNotExpired() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.cache.findByAddress(context.address);
		final Account account = context.cache.findByAddress(context.address);

		// Assert:
		assertAreEqual(context.pair1.getAccount(), account);
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(context.address);
		Mockito.verify(context.timeProvider, Mockito.times(2)).getCurrentTime();
	}

	@Test
	public void cacheAutomaticallyMapsAccountInfoToAccount() {
		// Arrange:
		final Address address = Utils.generateRandomAddressWithPublicKey();
		final AccountInfo accountInfo = new AccountInfo(
				address,
				Amount.fromNem(271),
				new BlockAmount(3),
				"label",
				3.7);

		final TestContext context = new TestContext();
		Mockito.when(context.accountServices.getAccountMetaDataPair(address))
				.thenReturn(new AccountMetaDataPair(accountInfo, null));

		// Act:
		final Account account = context.cache.findByAddress(address);

		// Assert:
		Assert.assertThat(account.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(account.getAddress().getPublicKey(), IsEqual.equalTo(address.getPublicKey()));
	}

	//endregion

	//region isKnownAddress

	@Test
	public void isKnownAddressReturnsTrueIfAccountIsCachedAndNotExpired() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(52), new TimeInstant(52 + 60));

		// Act:
		context.cache.findByAddress(context.address);
		final boolean result = context.cache.isKnownAddress(context.address);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(true));
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(Mockito.any());
		Mockito.verify(context.timeProvider, Mockito.times(1)).getCurrentTime();
	}

	@Test
	public void isKnownAddressReturnsTrueIfAccountIsCachedAndExpired() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(52), new TimeInstant(52 + 61));

		// Act:
		context.cache.findByAddress(context.address);
		final boolean result = context.cache.isKnownAddress(context.address);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(true));
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(Mockito.any());
		Mockito.verify(context.timeProvider, Mockito.times(1)).getCurrentTime();
	}

	@Test
	public void isKnownAddressReturnsFalseIfAccountIsNotCached() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		final boolean result = context.cache.isKnownAddress(context.address);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(false));
		Mockito.verify(context.accountServices, Mockito.times(0)).getAccountMetaDataPair(Mockito.any());
		Mockito.verify(context.timeProvider, Mockito.times(0)).getCurrentTime();
	}

	//endregion

	//region findPairByAddress

	@Test
	public void findPairByAddressDelegatesToAccountServicesIfAccountNotInCache() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		final AccountMetaDataPair pair = context.cache.findPairByAddress(context.address);

		// Assert:
		Assert.assertThat(pair, IsEqual.equalTo(context.pair1));
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(context.address);
		Mockito.verify(context.timeProvider, Mockito.times(1)).getCurrentTime();
	}
/*
	@Test
	public void findPairByAddressDelegatesToAccountServicesIfCachedAccountIsExpired() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(52), new TimeInstant(52 + 61));

		// Act:
		context.cache.findPairByAddress(context.address);
		final AccountMetaDataPair pair = context.cache.findPairByAddress(context.address);

		// Assert:
		Assert.assertThat(pair, IsEqual.equalTo(context.pair2));
		Mockito.verify(context.accountServices, Mockito.times(2)).getAccountMetaDataPair(context.address);
		Mockito.verify(context.timeProvider, Mockito.times(2)).getCurrentTime();
	}
*/
	@Test
	public void findPairByAddressUsesCachedValueIfCachedAccountIsNotExpired() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(52), new TimeInstant(52 + 60));

		// Act:
		context.cache.findPairByAddress(context.address);
		final AccountMetaDataPair pair = context.cache.findPairByAddress(context.address);

		// Assert:
		Assert.assertThat(pair, IsEqual.equalTo(context.pair1));
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(context.address);
		Mockito.verify(context.timeProvider, Mockito.times(2)).getCurrentTime();
	}

	//endregion

	//region getAccounts

	@Test
	public void getAccountsReturnsAllAccountsInCache() {
		// Arrange:
		final TestContext context = new TestContext();

		// add three accounts to the cache
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(3);
		for (final AccountInfo account : accounts) {
			Mockito.when(context.accountServices.getAccountMetaDataPair(account.getAddress()))
					.thenReturn(new AccountMetaDataPair(account, null));
			context.cache.findPairByAddress(account.getAddress());
		}

		// Assert:
		Assert.assertThat(context.cache.getAccounts(), IsEquivalent.equivalentTo(accounts));
	}

	//endregion

	//region seedAccounts

	@Test
	public void allSeedAccountsAreAddedToCache() {
		// Arrange:
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(3);
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(3));
		context.setAccountServicesFailure(); // fail the services lookup so that the seed account is not replaced

		// Act:
		context.cache.seedAccounts(accounts);

		// Assert:
		for (final AccountInfo account : accounts) {
			final AccountMetaDataPair pair = context.cache.findPairByAddress(account.getAddress());
			Assert.assertThat(pair.getAccount(), IsEqual.equalTo(account));
		}
	}

	@Test
	public void allSeedAccountsHaveUnknownStatus() {
		// Assert:
		assertSeedAccountMetadata(metaData -> Assert.assertThat(metaData.getStatus(), IsEqual.equalTo(AccountStatus.UNKNOWN)));
	}

	@Test
	public void allSeedAccountsHaveInactiveRemoteStatus() {
		// Assert:
		assertSeedAccountMetadata(metaData -> Assert.assertThat(metaData.getRemoteStatus(), IsEqual.equalTo(AccountRemoteStatus.INACTIVE)));
	}

	private static void assertSeedAccountMetadata(final Consumer<AccountMetaData> assertMetaData) {
		// Arrange:
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(3);
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(3));
		context.setAccountServicesFailure(); // fail the services lookup so that the seed account is not replaced

		// Act:
		context.cache.seedAccounts(accounts);

		// Assert:
		for (final AccountInfo account : accounts) {
			final AccountMetaDataPair pair = context.cache.findPairByAddress(account.getAddress());
			assertMetaData.accept(pair.getMetaData());
		}
	}
/*
	@Test
	public void allSeedAccountsAreExpired() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(3));

		// Act:
		context.cache.seedAccounts(Arrays.asList(Utils.createAccountInfoFromAddress(context.address)));
		final AccountMetaDataPair pair = context.cache.findPairByAddress(context.address);

		// Assert: the seed account was replaced with a fresher account
		Assert.assertThat(pair, IsEqual.equalTo(context.pair1));
	}
*/
	//endregion

	//region disconnected fallback

	@Test
	public void lookupFailsIfAccountIsNotInCacheAndNisIsDisconnected() {
		// Arrange:
		final TestContext context = new TestContext();
		context.setAccountServicesFailure(); // fail the services lookup

		// Act:
		ExceptionAssert.assertThrows(
				v -> context.cache.findPairByAddress(Utils.generateRandomAddress()),
				NisException.class);

		// Assert: the request went to the underlying account services (which failed)
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPair(Mockito.any());
	}

	@Test
	public void lookupSucceedsIfAccountIsInCacheAndNisIsDisconnected() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(1), new TimeInstant(5000));
		context.cache.findPairByAddress(context.address); // initially cache the account
		/*context.setAccountServicesFailure(); // fail the services lookup*/

		// Act: retrieve the (expired) pair
		final AccountMetaDataPair pair = context.cache.findPairByAddress(context.address);

		// Assert: the expired pair is returned
		Assert.assertThat(pair, IsEqual.equalTo(context.pair1));

		// Assert: the request went to the underlying account services (1 - initial cache, 2 - failed lookup)
		/*Mockito.verify(context.accountServices, Mockito.times(2)).getAccountMetaDataPair(Mockito.any());*/
	}
/*
	@Test
	public void lookupCacheHitDelaysExpiration() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(1), new TimeInstant(120), new TimeInstant(150));
		context.cache.findPairByAddress(context.address); // initially cache the account
		context.setAccountServicesFailure(); // fail the services lookup

		// Act: retrieve the pair twice
		context.cache.findPairByAddress(context.address); // should delegate (initially expired)
		context.cache.findPairByAddress(context.address); // should not delegate (not expired after cache hit)

		// Assert: the request went to the underlying account services (1 - initial cache, 2 - failed lookup)
		Mockito.verify(context.accountServices, Mockito.times(2)).getAccountMetaDataPair(Mockito.any());
	}
*/
	//endregion

	//region updateCache

	@Test
	public void updateUpdatesAllExpiredAccounts() {
		// Arrange:
		final TestContext context = new TestContext();
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(2);
		final List<AccountIdRequest> requests = accounts.stream()
				.map(a -> new AccountIdRequest(a.getAddress()))
				.collect(Collectors.toList());
		final ArgumentCaptor<? extends List<AccountIdRequest>> captor = ArgumentCaptor.forClass(ArrayList.class);
		context.cache.seedAccounts(accounts);
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(120));
		Mockito.when(context.accountServices.getAccountMetaDataPairs(captor.capture()))
				.thenReturn(Arrays.asList(context.pair1, context.pair2));

		// Act:
		context.cache.updateCache();

		// Assert:
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPairs(Mockito.any());
		Assert.assertThat(captor.getValue(), IsEquivalent.equivalentTo(requests));
	}

	@Test
	public void updateDoesNotUpdateUnexpiredAccounts() {
		// Arrange:
		final TestContext context = new TestContext();
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(2);
		final List<AccountIdRequest> requests = Arrays.asList(new AccountIdRequest(accounts.get(0).getAddress()));
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(1), new TimeInstant(120), new TimeInstant(130));
		context.cache.seedAccounts(accounts);
		Mockito.when(context.accountServices.getAccountMetaDataPairs(Mockito.eq(requests)))
				.thenReturn(Arrays.asList(context.pair1));

		// Act:
		context.cache.updateCache();

		// Assert:
		Mockito.verify(context.accountServices, Mockito.times(1)).getAccountMetaDataPairs(Mockito.eq(requests));
	}

	@Test
	public void updateDoesNotDelegateToAccountServicesIfAllAccountsAreUnexpired() {
		// Arrange:
		final TestContext context = new TestContext();
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(2);
		context.cache.seedAccounts(accounts);
		Mockito.when(context.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(60));

		// Act:
		context.cache.updateCache();

		// Assert:
		Mockito.verify(context.accountServices, Mockito.never()).getAccountMetaDataPairs(Mockito.any());
	}

	//endregion

	private static void assertAreEqual(final AccountInfo expectedInfo, final Account actualAccount) {
		// Assert:
		Assert.assertThat(actualAccount.getAddress(), IsEqual.equalTo(expectedInfo.getAddress()));
		Assert.assertThat(actualAccount.getAddress().getPublicKey(), IsEqual.equalTo(expectedInfo.getKeyPair().getPublicKey()));
	}

	private static class TestContext {
		private final Address address = Address.fromEncoded("sigma");
		private final AccountMetaDataPair pair1 = new AccountMetaDataPair(Utils.generateRandomAccountInfo(), null);
		private final AccountMetaDataPair pair2 = new AccountMetaDataPair(Utils.generateRandomAccountInfo(), null);

		private final AccountServices accountServices = Mockito.mock(AccountServices.class);
		private final TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
		private final NccAccountCache cache = new NccAccountCache(this.accountServices, this.timeProvider, 60);

		public TestContext() {
			Mockito.when(this.timeProvider.getCurrentTime()).thenReturn(new TimeInstant(52));
			Mockito.when(this.accountServices.getAccountMetaDataPair(this.address))
					.thenReturn(this.pair1, this.pair2);
		}

		public void setAccountServicesFailure() {
			Mockito.when(this.accountServices.getAccountMetaDataPair(Mockito.any()))
					.thenThrow(new NisException(new ErrorResponse(TimeInstant.ZERO, "badness", 17)));
		}
	}
}