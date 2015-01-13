package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.*;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Serializer;

public class MultisigTransactionViewModel extends TransactionViewModel {
	private final TransactionViewModel otherTransactionViewModel;

	public MultisigTransactionViewModel(final TransactionMetaDataPair metaDataPair, final Address relativeAccountAddress, final BlockHeight lastBlockHeight) {
		super(Type.Multisig_Transfer, metaDataPair, lastBlockHeight);
		final MultisigTransaction multisigTransaction = (MultisigTransaction)metaDataPair.getTransaction();
		final Transaction other = multisigTransaction.getOtherTransaction();

		final TransactionMetaData innerMetaData = metaDataPair.getMetaData() == null
				? null
				: new TransactionMetaData(metaDataPair.getMetaData().getHeight(), 0L);

		if (other.getType() == TransactionTypes.TRANSFER) {
			otherTransactionViewModel = new TransferTransactionViewModel(
					new TransactionMetaDataPair(other, innerMetaData), relativeAccountAddress, lastBlockHeight);
		} else {
			throw new IllegalArgumentException("MultisigTransactionViewModel can handle only Transfers at the moment");
		}
	}

	@Override
	protected void serializeImpl(Serializer serializer) {
		super.serializeImpl(serializer);

		serializer.writeObject("inner", otherTransactionViewModel);
	}
}
