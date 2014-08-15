package org.nem.ncc.services;

/**
 * Represents a transaction direction filter.
 */
public enum TransactionDirection {
	/**
	 * Both incoming and outgoing transactions match.
	 */
	ALL,

	/**
	 * Only incoming transactions match.
	 */
	INCOMING,

	/**
	 * Only outgoing transactions match.
	 */
	OUTGOING
}
