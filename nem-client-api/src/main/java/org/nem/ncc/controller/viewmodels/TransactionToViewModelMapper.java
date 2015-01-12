package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.Address;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionTypes;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;

public class TransactionToViewModelMapper {
	public static TransactionViewModel map(final Transaction transaction, final Address address) {
		return map(new TransactionMetaDataPair(transaction, null), address, null);
	}

	public static TransactionViewModel map(final TransactionMetaDataPair metaDataPair, final Address address, final BlockHeight blockHeight) {
		switch(metaDataPair.getTransaction().getType()) {
			case TransactionTypes.TRANSFER:
				return new TransferTransactionViewModel(metaDataPair, address, blockHeight);
			case TransactionTypes.IMPORTANCE_TRANSFER:
				return new ImportanceTransferTransactionViewModel(metaDataPair, blockHeight);
			case TransactionTypes.MULTISIG:
				return new MultisigTransactionViewModel(metaDataPair, address, blockHeight);
			default:
				throw new IllegalArgumentException("transaction type is not handled inside TransactionToViewModelMapper");
		}
	}
}
