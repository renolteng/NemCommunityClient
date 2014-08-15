package org.nem.ncc.cache;

import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.*;
import org.nem.core.serialization.AccountLookup;
import org.nem.ncc.services.WalletServices;
import org.nem.ncc.test.Utils;
import org.nem.ncc.wallet.WalletAccount;

public class WalletAwareAccountLookupTest {

	//region findByAddress

	@Test
	public void findByAddressReturnsNullForUnknownAccount() {
		// Arrange:
		final TestContext context = new TestContext();
		final Address address = Utils.generateRandomAddress();

		// Act:
		final Account result = context.accountLookup.findByAddress(address);

		// Assert:
		Assert.assertThat(result, IsNull.nullValue());
		Mockito.verify(context.mockAccountLookup, Mockito.times(1)).findByAddress(address);
		Mockito.verify(context.walletServices, Mockito.times(0)).tryFindOpenAccount(address);
	}

	@Test
	public void findByAddressReturnsAccountWithUnknownPrivateKeyAsPassThrough() {
		// Arrange:
		final TestContext context = new TestContext();
		final Address address = Utils.generateRandomAddress();
		final Account account = Mockito.mock(Account.class);
		Mockito.when(context.mockAccountLookup.findByAddress(address)).thenReturn(account);

		// Act:
		final Account result = context.accountLookup.findByAddress(address);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(account));
		Mockito.verify(context.mockAccountLookup, Mockito.times(1)).findByAddress(address);
		Mockito.verify(context.walletServices, Mockito.times(1)).tryFindOpenAccount(address);
	}

	@Test
	public void findByAddressReturnsAccountWithKnownPrivateKeyAsShallowCopy() {
		// Arrange:
		final TestContext context = new TestContext();
		final Address address = Utils.generateRandomAddress();
		final WalletAccount walletAccount = new WalletAccount();
		final Account account = Mockito.mock(Account.class);
		final Account accountWithPrivateKey = Mockito.mock(Account.class);
		final ArgumentCaptor<KeyPair> keyPairCaptor = ArgumentCaptor.forClass(KeyPair.class);
		Mockito.when(context.mockAccountLookup.findByAddress(address)).thenReturn(account);
		Mockito.when(context.walletServices.tryFindOpenAccount(address)).thenReturn(walletAccount);
		Mockito.when(account.shallowCopyWithKeyPair(keyPairCaptor.capture())).thenReturn(accountWithPrivateKey);

		// Act:
		final Account result = context.accountLookup.findByAddress(address);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(accountWithPrivateKey));
		Assert.assertThat(keyPairCaptor.getValue().getPrivateKey(), IsEqual.equalTo(walletAccount.getPrivateKey()));
		Mockito.verify(context.mockAccountLookup, Mockito.times(1)).findByAddress(address);
		Mockito.verify(context.walletServices, Mockito.times(1)).tryFindOpenAccount(address);
		Mockito.verify(account, Mockito.times(1)).shallowCopyWithKeyPair(Mockito.any());
	}

	//endregion

	//region isKnownAddress

	@Test
	public void isKnownAddressDelegatesToInnerAccountLookup() {
		// Arrange:
		final TestContext context = new TestContext();
		final Address address = Utils.generateRandomAddress();
		Mockito.when(context.mockAccountLookup.isKnownAddress(address)).thenReturn(true);

		// Act:
		final boolean result = context.accountLookup.isKnownAddress(address);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(true));
		Mockito.verify(context.mockAccountLookup, Mockito.times(1)).isKnownAddress(address);
	}

	//endregion

	private static class TestContext {
		private final AccountLookup mockAccountLookup = Mockito.mock(AccountLookup.class);
		private final WalletServices walletServices = Mockito.mock(WalletServices.class);
		private final AccountLookup accountLookup = new WalletAwareAccountLookup(this.mockAccountLookup, this.walletServices);
	}
}