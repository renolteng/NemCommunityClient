package org.nem.ncc.controller.viewmodels;

import org.nem.core.crypto.Hash;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Serializer;

import java.util.List;
import java.util.stream.Collectors;

public class MultisigTransactionViewModel extends TransactionViewModel {
	private final TransactionViewModel otherTransactionViewModel;
	private final List<MultisigSignatureViewModel> signatureViewModel;
	private final int requiresSignature;
	private final Hash innerTransactionHash;
	private final Address issuer;

	public MultisigTransactionViewModel(final TransactionMetaDataPair metaDataPair, final Address relativeAccountAddress, final BlockHeight lastBlockHeight) {
		super(Type.Multisig_Transfer, metaDataPair, lastBlockHeight);
		final MultisigTransaction multisigTransaction = (MultisigTransaction)metaDataPair.getTransaction();
		final Transaction other = multisigTransaction.getOtherTransaction();
		final TransactionMetaData innerMetaData = metaDataPair.getMetaData() == null
				? null
				: new TransactionMetaData(metaDataPair.getMetaData().getHeight(), 0L);

		this.issuer = multisigTransaction.getSigner().getAddress();

		if (other.getType() == TransactionTypes.TRANSFER) {
			this.otherTransactionViewModel = new TransferTransactionViewModel(
					new TransactionMetaDataPair(other, innerMetaData), relativeAccountAddress, lastBlockHeight);
		} else {
			throw new IllegalArgumentException("MultisigTransactionViewModel can handle only Transfers at the moment");
		}

		this.innerTransactionHash = multisigTransaction.getOtherTransactionHash();
		this.signatureViewModel = multisigTransaction.getCosignerSignatures().stream()
				.map(t -> new MultisigSignatureViewModel(new TransactionMetaDataPair(t, innerMetaData), lastBlockHeight))
				.collect(Collectors.toList());

		this.requiresSignature = requiresSignature(metaDataPair, relativeAccountAddress);
	}

	private int requiresSignature(final TransactionMetaDataPair metaDataPair, final Address relativeAccountAddress) {
		final MultisigTransaction multisigTransaction = (MultisigTransaction)metaDataPair.getTransaction();
		if (metaDataPair.getMetaData() != null) {
			return 0;
		}
		return  multisigTransaction.getSigner().getAddress().equals(relativeAccountAddress)
				|| multisigTransaction.getSigners().stream()
				.anyMatch(t -> t.getAddress().equals(relativeAccountAddress))
				? 0 : 1;
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		Address.writeTo(serializer, "issuer", this.issuer);
		serializer.writeObject("inner", this.otherTransactionViewModel);
		serializer.writeObject("innerHash", this.innerTransactionHash);
		serializer.writeObjectArray("signatures", this.signatureViewModel);
		serializer.writeInt("requiresSignature", this.requiresSignature);
	}
}
