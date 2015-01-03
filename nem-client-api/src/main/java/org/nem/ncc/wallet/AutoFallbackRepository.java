package org.nem.ncc.wallet;

import org.nem.ncc.wallet.storage.*;

import java.util.Collection;

/**
 * Aggregate repository that attempts to load a wallet as all known formats and saves wallets using
 * the latest format.
 */
public class AutoFallbackRepository implements WalletRepository {
	private final Collection<WalletRepository> repositories;

	/**
	 * Create a new auto fallback repository.
	 *
	 * @param repositories The repositories such that the most recent repository is first.
	 */
	public AutoFallbackRepository(final Collection<WalletRepository> repositories) {
		if (null == repositories || repositories.isEmpty()) {
			throw new IllegalArgumentException("at least one child repository is required");
		}

		this.repositories = repositories;
	}

	@Override
	public void save(final WalletDescriptor descriptor, final StorableWallet wallet) {
		this.repositories.iterator().next().save(descriptor, wallet);
	}

	@Override
	public StorableWallet load(final WalletDescriptor descriptor) {
		WalletStorageException firstException = null;
		for (final WalletRepository repository : this.repositories) {
			try {
				return repository.load(descriptor);
			} catch (final WalletStorageException e) {
				firstException = null == firstException ? e : firstException;
			}
		}

		throw firstException;
	}
}
