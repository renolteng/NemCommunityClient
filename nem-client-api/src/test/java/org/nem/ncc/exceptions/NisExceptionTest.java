package org.nem.ncc.exceptions;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.connect.ErrorResponse;
import org.nem.core.time.TimeInstant;

import java.util.*;

public class NisExceptionTest {

	@Test
	public void nisErrorResponseMessageIsCorrectlyMappedToNisExceptionCode() {
		// Arrange:
		final Map<String, NisException.Code> nisErrorMap = new HashMap<String, NisException.Code>() {
			{
				this.put("FAILURE_SERVER_LIMIT", NisException.Code.HARVESTING_LIMIT_HIT);
				this.put("FAILURE_HARVESTING_INELIGIBLE", NisException.Code.HARVESTING_INELIGIBLE);
				this.put("FAILURE_PAST_DEADLINE", NisException.Code.TRANSACTION_REJECTED_PAST_DEADLINE);
				this.put("FAILURE_FUTURE_DEADLINE", NisException.Code.TRANSACTION_REJECTED_FUTURE_DEADLINE);
				this.put("FAILURE_INSUFFICIENT_BALANCE", NisException.Code.TRANSACTION_REJECTED_INSUFFICIENT_BALANCE);
				this.put("FAILURE_MESSAGE_TOO_LARGE", NisException.Code.TRANSACTION_REJECTED_MESSAGE_TOO_LARGE);
				this.put("network boot was already attempted", NisException.Code.NODE_ALREADY_BOOTED);
				this.put("FAILURE_HASH_EXISTS", NisException.Code.TRANSACTION_REJECTED_HASH_EXISTS);
				this.put("FAILURE_SIGNATURE_NOT_VERIFIABLE", NisException.Code.TRANSACTION_REJECTED_NOT_VERIFIABLE);
				this.put("FAILURE_TIMESTAMP_TOO_FAR_IN_PAST", NisException.Code.TRANSACTION_REJECTED_NOT_TIMESTAMP_TOO_FAR_IN_PAST);
				this.put("FAILURE_TIMESTAMP_TOO_FAR_IN_FUTURE", NisException.Code.TRANSACTION_REJECTED_NOT_TIMESTAMP_TOO_FAR_IN_FUTURE);
				this.put("FAILURE_UNKNOWN_ACCOUNT", NisException.Code.TRANSACTION_REJECTED_UNKNOWN_ACCOUNT);
				this.put("FAILURE_TRANSACTION_CACHE_TOO_FULL", NisException.Code.TRANSACTION_REJECTED_CACHE_TOO_FULL);
				this.put("network has not been booted yet", NisException.Code.NODE_NOT_BOOTED);

				// IMPORTANCE TRANSFERS
				this.put("FAILURE_CONFLICTING_IMPORTANCE_TRANSFER", NisException.Code.TRANSACTION_REJECTED_CONFLICTING_IMPORTANCE_TRANSFER);
				this.put("FAILURE_DESTINATION_ACCOUNT_HAS_NONZERO_BALANCE", NisException.Code.TRANSACTION_REJECTED_DESTINATION_ACCOUNT_NON_EMPTY);
				this.put("FAILURE_IMPORTANCE_TRANSFER_IN_PROGRESS", NisException.Code.TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_IN_PROGRESS);
				this.put("FAILURE_IMPORTANCE_TRANSFER_NEEDS_TO_BE_DEACTIVATED", NisException.Code.TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_IS_ACTIVE);
				this.put("FAILURE_IMPORTANCE_TRANSFER_IS_NOT_ACTIVE", NisException.Code.TRANSACTION_REJECTED_IMPORTANCE_TRANSFER_NOT_ACTIVE);

				// MULTISIG RELATED
				this.put("FAILURE_TRANSACTION_NOT_ALLOWED_FOR_MULTISIG", NisException.Code.TRANSACTION_REJECTED_NOT_ALLOWED_FOR_MULTISIG);
				this.put("FAILURE_MULTISIG_NOT_A_COSIGNER", NisException.Code.MULTISIG_SIGNATURE_REJECTED_NOT_A_COSIGNER);
				this.put("FAILURE_MULTISIG_NO_MATCHING_MULTISIG", NisException.Code.MULTISIG_SIGNATURE_REJECTED_NO_MATCHING_MULTISIG_TRANSACTION);
				this.put("FAILURE_MULTISIG_ALREADY_A_COSIGNER", NisException.Code.MULTISIG_REJECTED_ALREADY_A_COSIGNER);
			}
		};

		for (final Map.Entry<String, NisException.Code> pair : nisErrorMap.entrySet()) {
			// Act:
			final ErrorResponse response = new ErrorResponse(TimeInstant.ZERO, pair.getKey(), 0);
			final NisException exception = new NisException(response);

			// Assert:
			Assert.assertThat(exception.getCode(), IsEqual.equalTo(pair.getValue()));
		}
	}
}