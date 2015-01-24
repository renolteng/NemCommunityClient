package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.*;
import org.nem.core.model.ncc.AccountInfo;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockAmount;
import org.nem.core.model.primitive.BlockHeight;

public class TransactionToViewModelMapper {
	public static TransactionViewModel map(final Transaction transaction, final Address address) {
		return map(new TransactionMetaDataPair(transaction, null), address, null);
	}

	public static TransactionViewModel map(final Transaction transaction, final AccountMetaDataPair accountData) {
		return map(new TransactionMetaDataPair(transaction, null), accountData, null);
	}

	public static TransactionViewModel map(final TransactionMetaDataPair metaDataPair, final Address address, final BlockHeight blockHeight) {
		return map(metaDataPair, new AccountMetaDataPair(new AccountInfo(address, Amount.ZERO, BlockAmount.ZERO, "", 0.0), null), blockHeight);
	}

	private static TransactionViewModel map(final TransactionMetaDataPair metaDataPair, final AccountMetaDataPair accountData, final BlockHeight blockHeight) {
		switch (metaDataPair.getTransaction().getType()) {
			case TransactionTypes.TRANSFER:
				return new TransferTransactionViewModel(metaDataPair, accountData.getAccount().getAddress(), blockHeight);
			case TransactionTypes.IMPORTANCE_TRANSFER:
				return new ImportanceTransferTransactionViewModel(metaDataPair, blockHeight);
			case TransactionTypes.MULTISIG:
				return new MultisigTransactionViewModel(metaDataPair, accountData, blockHeight);
			default:
				throw new IllegalArgumentException("transaction type is not handled inside TransactionToViewModelMapper");
		}
	}
}
