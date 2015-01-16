package org.nem.ncc.services;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.viewmodels.AccountViewModel;
import org.nem.ncc.test.Utils;
import org.nem.ncc.wallet.WalletAccount;

public class AccountMapperTest {

	@Test
	public void addressCanBeMappedToViewModel() {
		// Arrange:
		final Address address = Address.fromEncoded("sigma");
		final AccountLabels accountLabels = Mockito.mock(AccountLabels.class);
		Mockito.when(accountLabels.getLabel(address))
				.thenReturn(new AccountLabel(address, "my pub label", "my pri label"));

		final AccountMetaDataPairLookup accountLookup = Mockito.mock(AccountMetaDataPairLookup.class);
		Mockito.when(accountLookup.findPairByAddress(address))
				.thenReturn(createMetaDataPair(address));

		// Act:
		final AccountMapper mapper = new AccountMapper(accountLabels, accountLookup);
		final AccountViewModel viewModel = mapper.toViewModel(address);

		// Assert:
		Assert.assertThat(viewModel.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(viewModel.getStatus(), IsEqual.equalTo(AccountStatus.LOCKED));
		Assert.assertThat(viewModel.getLabel(), IsEqual.equalTo("my pri label"));
	}

	@Test
	public void walletAccountCanBeMappedToViewModel() {
		// Arrange:
		final WalletAccount account = new WalletAccount();
		final AccountLabels accountLabels = Mockito.mock(AccountLabels.class);
		Mockito.when(accountLabels.getLabel(account.getAddress()))
				.thenReturn(new AccountLabel(account.getAddress(), "my pub label", "my pri label"));

		final AccountMetaDataPairLookup accountLookup = Mockito.mock(AccountMetaDataPairLookup.class);
		Mockito.when(accountLookup.findPairByAddress(account.getAddress()))
				.thenReturn(createMetaDataPair(account.getAddress()));

		// Act:
		final AccountMapper mapper = new AccountMapper(accountLabels, accountLookup);
		final AccountViewModel viewModel = mapper.toViewModel(account);

		// Assert:
		Assert.assertThat(viewModel.getAddress(), IsEqual.equalTo(account.getAddress()));
		Assert.assertThat(viewModel.getStatus(), IsEqual.equalTo(AccountStatus.LOCKED));
		Assert.assertThat(viewModel.getLabel(), IsEqual.equalTo("my pri label"));
	}

	private static AccountMetaDataPair createMetaDataPair(final Address address) {
		return new AccountMetaDataPair(
				Utils.createAccountInfoFromAddress(address),
				new AccountMetaData(AccountStatus.LOCKED, AccountRemoteStatus.INACTIVE));
	}
}