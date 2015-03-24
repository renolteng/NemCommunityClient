package org.nem.console.utils;

import org.nem.core.model.*;
import org.nem.core.model.primitive.BlockHeight;

public class TransactionUtils {

	public static void prepareAndSign(final Transaction transaction) {
		transaction.setFee(TransactionFeeCalculator.calculateMinimumFee(transaction, BlockHeight.MAX));
		transaction.setDeadline(transaction.getTimeStamp().addHours(12));
		transaction.sign();
	}
}
