package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;

public class MultisigSignatureViewModel extends TransactionViewModel {

	public MultisigSignatureViewModel(final TransactionMetaDataPair metaDataPair, final BlockHeight lastBlockHeight) {
		super(Type.Multisig_Signature, metaDataPair, lastBlockHeight);
	}

	// no need to override serializeImpl, no additional data needed
}
