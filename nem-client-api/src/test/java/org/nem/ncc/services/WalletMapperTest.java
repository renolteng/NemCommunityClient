package org.nem.ncc.services;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.time.*;
import org.nem.ncc.controller.viewmodels.*;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.*;

import java.util.stream.Collectors;

public class WalletMapperTest {

	@Test
	public void modelCanBeMappedToViewModel() {
		// Arrange:
		final WalletAccount account = new WalletAccount();
		final Wallet wallet = new MemoryWallet(new WalletName("bar"), account);
		wallet.addOtherAccount(new WalletAccount());
		wallet.addOtherAccount(new WalletAccount());
		wallet.addOtherAccount(new WalletAccount());

		final AccountMapper accountMapper = Mockito.mock(AccountMapper.class);
		Mockito.when(accountMapper.toViewModel(account)).thenReturn(createViewModel(account));
		for (final WalletAccount otherAccount : wallet.getOtherAccounts()) {
			Mockito.when(accountMapper.toViewModel(otherAccount)).thenReturn(createViewModel(otherAccount));
		}

		final TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
		Mockito.when(timeProvider.getCurrentTime()).thenReturn(new TimeInstant(87));

		// Act:
		final WalletMapper mapper = new WalletMapper(accountMapper, timeProvider);
		final WalletViewModel viewModel = mapper.toViewModel(wallet);

		// Assert:
		Mockito.verify(timeProvider, Mockito.times(1)).getCurrentTime();
		Mockito.verify(accountMapper, Mockito.times(4)).toViewModel(Mockito.any(WalletAccount.class));

		Assert.assertThat(viewModel.getName(), IsEqual.equalTo(new WalletName("bar")));
		Assert.assertThat(
				viewModel.getPrimaryAccount().getAddress(),
				IsEqual.equalTo(account.getAddress()));
		Assert.assertThat(
				viewModel.getOtherAccounts().stream().map(AccountViewModel::getAddress).collect(Collectors.toList()),
				IsEquivalent.equivalentTo(wallet.getOtherAccounts().stream().map(WalletAccount::getAddress).collect(Collectors.toList())));
		Assert.assertThat(viewModel.getUpdateTime(), IsEqual.equalTo(new TimeInstant(87)));
	}

	private static AccountViewModel createViewModel(final WalletAccount account) {
		return Utils.createAccountViewModelFromAddress(account.getAddress());
	}
}