package org.nem.ncc.services;

import org.nem.core.crypto.Hash;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.connector.PrimaryNisConnector;
import org.nem.ncc.model.NisApiId;

import java.util.List;

/**
 * This class provides higher-level functions around accessing accounts.
 */
public class AccountServices {
	private final PrimaryNisConnector nisConnector;

	/**
	 * Creates new account services.
	 *
	 * @param nisConnector The NIS connector.
	 */
	public AccountServices(final PrimaryNisConnector nisConnector) {
		this.nisConnector = nisConnector;
	}

	/**
	 * Gets account information for the specified account.
	 *
	 * @param address The account address.
	 * @return The account information.
	 */
	public AccountMetaDataPair getAccountMetaDataPair(final Address address) {
		final Deserializer deserializer = this.nisConnector.get(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, formatQueryString(address, null));
		return new AccountMetaDataPair(deserializer);
	}

	private NisApiId typeOfTransactionToQueryId(final TransactionDirection direction) {
		switch (direction) {
			case INCOMING:
				return NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING;
			case OUTGOING:
				return NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING;
			default:
				return NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL;
		}
	}

	/**
	 * Gets confirmed transactions for the specified account.
	 *
	 * @param direction Type of transactions.
	 * @param address The account address.
	 * @param endHash The hash of top-most transaction.
	 * @return The account information.
	 */
	public List<TransactionMetaDataPair> getTransactions(final TransactionDirection direction, final Address address, final Hash endHash) {
		final String queryString = formatQueryString(address, endHash);
		final Deserializer deserializer = this.nisConnector.get(this.typeOfTransactionToQueryId(direction), queryString);
		return deserializer.readObjectArray("data", TransactionMetaDataPair::new);
	}

	/**
	 * Gets unconfirmed transactions for the specified account.
	 *
	 * @param address The account address.
	 * @return The account information.
	 */
	public List<Transaction> getUnconfirmedTransactions(final Address address) {
		final String queryString = formatQueryString(address, null);
		final Deserializer deserializer = this.nisConnector.get(NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, queryString);
		return deserializer.readObjectArray("data", TransactionFactory.VERIFIABLE);
	}

	/**
	 * Formats a string containing address and hash information.
	 *
	 * @param address The address.
	 * @param hash The hash.
	 * @return The formatted string.
	 */
	private static String formatQueryString(final Address address, final Hash hash) {
		final StringBuilder builder = new StringBuilder();
		builder.append("address=");
		builder.append(address.getEncoded());

		if (null != hash) {
			builder.append("&hash=");
			builder.append(hash);
		}

		return builder.toString();
	}
}
