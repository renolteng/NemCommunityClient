package org.nem.ncc.cache;

import org.nem.core.model.ncc.AccountInfo;

import java.util.Collection;

/**
 * A repository for loading and saving accounts.
 */
public interface AccountsRepository {
	/**
	 * Saves the specified accounts.
	 *
	 * @param accounts The accounts to save.
	 */
	public void save(final Collection<AccountInfo> accounts);

	/**
	 * Loads all accounts.
	 *
	 * @return The accounts.
	 */
	public Collection<AccountInfo> load();
}
