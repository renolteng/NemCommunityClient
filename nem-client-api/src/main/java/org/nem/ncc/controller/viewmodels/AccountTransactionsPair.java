package org.nem.ncc.controller.viewmodels;

import org.nem.core.serialization.*;

import java.util.Collection;

/**
 * A pair containing both an account and account transactions.
 */
public class AccountTransactionsPair implements SerializableEntity {
	private final AccountViewModel account;
	private final Collection<TransferViewModel> transactions;

	/**
	 * Creates a new pair.
	 *
	 * @param account The account.
	 * @param transactions The transactions.
	 */
	public AccountTransactionsPair(final AccountViewModel account, final Collection<TransferViewModel> transactions) {
		this.account = account;
		this.transactions = transactions;
	}

	/**
	 * Gets the account.
	 *
	 * @return The account.
	 */
	public AccountViewModel getAccount() {
		return this.account;
	}

	/**
	 * Gets the transactions.
	 *
	 * @return The transactions.
	 */
	public Collection<TransferViewModel> getTransactions() {
		return this.transactions;
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.account.serialize(serializer);
		serializer.writeObjectArray("transactions", this.transactions);
	}
}
