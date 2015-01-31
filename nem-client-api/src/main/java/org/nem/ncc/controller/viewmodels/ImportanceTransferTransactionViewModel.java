package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.*;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Serializer;

/**
 * TODO 20150131 J-G: fix empty comments
 * TODO 20150131 J-G: some basic tests
 */
public class ImportanceTransferTransactionViewModel extends TransactionViewModel {
	private final Address remote;

	public ImportanceTransferTransactionViewModel(final TransactionMetaDataPair metaDataPair, final BlockHeight lastBlockHeight) {
		super(Type.Importance_Transfer, metaDataPair, lastBlockHeight);

		final ImportanceTransferTransaction importanceTransfer = (ImportanceTransferTransaction)metaDataPair.getTransaction();
		this.remote = importanceTransfer.getRemote().getAddress();
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		Address.writeTo(serializer, "remote", this.remote);
	}
}
