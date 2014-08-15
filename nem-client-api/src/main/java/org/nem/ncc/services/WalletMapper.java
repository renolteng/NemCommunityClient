package org.nem.ncc.services;

import org.nem.core.time.TimeProvider;
import org.nem.ncc.controller.viewmodels.WalletViewModel;
import org.nem.ncc.wallet.Wallet;

import java.util.stream.Collectors;

/**
 * Helper class that is able to map a Wallet to a WalletViewModel.
 */
public class WalletMapper {
	private final AccountMapper accountMapper;
	private final TimeProvider timeProvider;

	/**
	 * Creates a wallet mapper.
	 *
	 * @param accountMapper The account mapper.
	 * @param timeProvider The time provider.
	 */
	public WalletMapper(
			final AccountMapper accountMapper,
			final TimeProvider timeProvider) {
		this.accountMapper = accountMapper;
		this.timeProvider = timeProvider;
	}

	/**
	 * Converts the specified model to a view model.
	 *
	 * @param model The model.
	 * @return The view model.
	 */
	public WalletViewModel toViewModel(final Wallet model) {
		return new WalletViewModel(
				model.getName(),
				this.accountMapper.toViewModel(model.getPrimaryAccount()),
				model.getOtherAccounts().stream()
						.map(this.accountMapper::toViewModel)
						.collect(Collectors.toList()),
				this.timeProvider.getCurrentTime());
	}
}