package org.nem.ncc.exceptions;

import org.nem.core.connect.ErrorResponse;
import org.nem.core.model.ncc.NemRequestResult;

import java.util.*;

/**
 * A NIS-originating exception.
 */
public class NisException extends NccException {
	/**
	 * General NIS error codes.
	 */
	public static enum Code implements ValueBasedEnum {
		/**
		 * An unexpected NIS error occurred.
		 */
		UNEXPECTED_ERROR(306),

		/**
		 * The NIS node is not booted.
		 */
		NODE_NOT_BOOTED(600),

		/**
		 * The NIS node is already booted.
		 */
		NODE_ALREADY_BOOTED(601),

		/**
		 * The account is ineligible for foraging.
		 */
		FORAGING_INELIGIBLE(700),

		/**
		 * The transaction was rejected because its deadline has passed.
		 */
		TRANSACTION_REJECTED_PAST_DEADLINE(701),

		/**
		 * The transaction was rejected because its deadline is too far in the future.
		 */
		TRANSACTION_REJECTED_FUTURE_DEADLINE(702),

		/**
		 * The transaction was rejected because the sender had an insufficient balance.
		 */
		TRANSACTION_REJECTED_INSUFFICIENT_BALANCE(703),

		/**
		 * The transaction was rejected because the associated message is too large.
		 */
		TRANSACTION_REJECTED_MESSAGE_TOO_LARGE(704),

		/**
		 * The transaction was rejected because it has already been submitted.
		 */
		TRANSACTION_REJECTED_HASH_EXISTS(705),

		/**
		 * The transaction was rejected because it is not verifiable.
		 */
		TRANSACTION_REJECTED_NOT_VERIFIABLE(706),

		/**
		 * The transaction was rejected because its timestamp is too far in the past.
		 */
		TRANSACTION_REJECTED_NOT_TIMESTAMP_TOO_FAR_IN_PAST(707),

		/**
		 * The transaction was rejected because its timestamp is too far in the future.
		 */
		TRANSACTION_REJECTED_NOT_TIMESTAMP_TOO_FAR_IN_FUTURE(708),

		/**
		 * The transaction was rejected because one of the accounts was unknown.
		 */
		TRANSACTION_REJECTED_UNKNOWN_ACCOUNT(709),

		/**
		 * The transaction was rejected because one of the accounts was unknown.
		 * note: shouldn't happen in UI
		 */
		TRANSACTION_REJECTED_CONFLICTING_IMPORTANCE_TRANSFER(730),

		/**
		 * Importance transfer transaction was rejected because destination account had non-zero balance.
		 */
		TRANSACTION_REJECTED_DESTINATION_ACCOUNT_NON_EMPTY(731),

		/**
		 * Importance transfer transaction was rejected cause there is already importance transfer in progress.
		 */
		TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_IN_PROGRESS(732),

		/**
		 * Importance transfer transaction was rejected cause importance transfer is active.
		 */
		TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_IS_ACTIVE(733),

		/**
		 * Importance transfer transaction was rejected cause importance transfer is NOT active.
		 */
		TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_NOT_ACTIVE(734),

		/**
		 * Transaction was rejected cause multisig account cannot perform such transactions.
		 */
		TRANSACTION_REJECTED_NOT_ALLOWED_FOR_MULTISIG(740),

		/**
		 * Multisig signature transaction rejected cause account is not a cosignatory of multisig account.
		 */
		MULTISIG_SIGNATURE_REJECTED_NOT_A_COSIGNER(741),

		/**
		 * Multisig signature rejected cause associated multisig transaction does not exist.
		 */
		MULTISIG_SIGNATURE_REJECTED_NO_MATCHING_MULTISIG_TRANSACTION(742),

		/**
		 * Multisig account modification rejected one of added accounts is already a cosignatory.
		 */
		MULTISIG_REJECTED_ALREADY_A_COSIGNER(743);

		private final int value;

		private Code(final int value) {
			this.value = value;
		}

		@Override
		public int value() {
			return this.value;
		}

		// note: commented errors will never be shown in NCC UI
		private static final Map<String, Code> NIS_ERROR_MAP = new HashMap<String, Code>() {
			{ this.put("FAILURE_FORAGING_INELIGIBLE", FORAGING_INELIGIBLE); }
			{ this.put("FAILURE_PAST_DEADLINE", TRANSACTION_REJECTED_PAST_DEADLINE); }
			{ this.put("FAILURE_FUTURE_DEADLINE", TRANSACTION_REJECTED_FUTURE_DEADLINE); }
			{ this.put("FAILURE_INSUFFICIENT_BALANCE", TRANSACTION_REJECTED_INSUFFICIENT_BALANCE); }
			{ this.put("FAILURE_MESSAGE_TOO_LARGE", TRANSACTION_REJECTED_MESSAGE_TOO_LARGE); }
			{ this.put("network boot was already attempted", NODE_ALREADY_BOOTED); }
			{ this.put("FAILURE_HASH_EXISTS", TRANSACTION_REJECTED_HASH_EXISTS); }
			{ this.put("FAILURE_SIGNATURE_NOT_VERIFIABLE", TRANSACTION_REJECTED_NOT_VERIFIABLE); }
			{ this.put("FAILURE_TIMESTAMP_TOO_FAR_IN_PAST", TRANSACTION_REJECTED_NOT_TIMESTAMP_TOO_FAR_IN_PAST); }
			{ this.put("FAILURE_TIMESTAMP_TOO_FAR_IN_FUTURE", TRANSACTION_REJECTED_NOT_TIMESTAMP_TOO_FAR_IN_FUTURE); }
			{ this.put("FAILURE_UNKNOWN_ACCOUNT", TRANSACTION_REJECTED_UNKNOWN_ACCOUNT); }
			{ this.put("network has not been booted yet", NODE_NOT_BOOTED); }
			//{ this.put("FAILURE_ENTITY_UNUSABLE", ); }
			//{ this.put("FAILURE_CHAIN_SCORE_INFERIOR", ); }
			//{ this.put("FAILURE_CHAIN_INVALID", ); }
			// IMPORTANCE TRANSFERS
			{ this.put("FAILURE_CONFLICTING_IMPORTANCE_TRANSFER", TRANSACTION_REJECTED_CONFLICTING_IMPORTANCE_TRANSFER); }
			//{ this.put("FAILURE_TOO_MANY_TRANSACTIONS", ); }
			//{ this.put("FAILURE_SELF_SIGNED_TRANSACTION", ); }
			{ this.put("FAILURE_DESTINATION_ACCOUNT_HAS_NONZERO_BALANCE", TRANSACTION_REJECTED_DESTINATION_ACCOUNT_NON_EMPTY); }
			{ this.put("FAILURE_IMPORTANCE_TRANSFER_IN_PROGRESS", TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_IN_PROGRESS); }
			{ this.put("FAILURE_IMPORTANCE_TRANSFER_NEEDS_TO_BE_DEACTIVATED", TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_IS_ACTIVE); }
			{ this.put("FAILURE_IMPORTANCE_TRANSFER_IS_NOT_ACTIVE", TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_NOT_ACTIVE); }
			// MULTISIG RELATED
			{ this.put("FAILURE_TRANSACTION_NOT_ALLOWED_FOR_MULTISIG", TRANSACTION_REJECTED_NOT_ALLOWED_FOR_MULTISIG); }
			{ this.put("FAILURE_MULTISIG_NOT_A_COSIGNER", MULTISIG_SIGNATURE_REJECTED_NOT_A_COSIGNER); }
			//{ this.put("FAILURE_MULTISIG_INVALID_COSIGNERS", ); }
			{ this.put("FAILURE_MULTISIG_NO_MATCHING_MULTISIG", MULTISIG_SIGNATURE_REJECTED_NO_MATCHING_MULTISIG_TRANSACTION); }
			{ this.put("FAILURE_MULTISIG_ALREADY_A_COSIGNER", MULTISIG_REJECTED_ALREADY_A_COSIGNER); }
			//{ this.put("FAILURE_MULTISIG_MISMATCHED_SIGNATURE", ); }
		};

		/**
		 * Gets the NIS code corresponding to a NIS message.
		 */
		public static Code fromMessage(final String message) {
			return NIS_ERROR_MAP.getOrDefault(message, UNEXPECTED_ERROR);
		}
	}

	/**
	 * Creates a new NIS exception.
	 *
	 * @param errorResponse The NIS error response.
	 */
	public NisException(final ErrorResponse errorResponse) {
		super(Code.fromMessage(errorResponse.getMessage()));
	}

	/**
	 * Creates a new NIS exception.
	 *
	 * @param result The result of a request to the NIS server.
	 */
	public NisException(final NemRequestResult result) {
		super(Code.fromMessage(result.getMessage()));
	}
}
