package org.nem.ncc.controller.viewmodels;

import org.nem.core.serialization.*;

import java.util.List;

/**
 * A pair containing both an account and account transactions.
 */
public class AccountTransactionsPair implements SerializableEntity {
	private final AccountViewModel account;
	private final List<TransferViewModel> transactions;

	/**
	 * Creates a new pair.
	 *
	 * @param account The account.
	 * @param transactions The transactions.
	 */
	public AccountTransactionsPair(final AccountViewModel account, final List<TransferViewModel> transactions) {
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
	public List<TransferViewModel> getTransactions() {
		return this.transactions;
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.account.serialize(serializer);
		serializer.writeObjectArray("transactions", this.transactions);
	}
}
