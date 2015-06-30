package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.Address;
import org.nem.core.model.ImportanceTransferTransaction;
import org.nem.core.model.ProvisionNamespaceTransaction;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Serializer;

public class ProvisionNamespaceTransactionViewModel extends TransactionViewModel {
	private final Address lessor;
	private final Amount rentalFee;
	private final String namespaceName;

	public ProvisionNamespaceTransactionViewModel(final TransactionMetaDataPair metaDataPair, final BlockHeight lastBlockHeight) {
		super(Type.Provision_Namespace, metaDataPair, lastBlockHeight);

		final ProvisionNamespaceTransaction transaction = (ProvisionNamespaceTransaction)metaDataPair.getTransaction();
		this.lessor = transaction.getLessor().getAddress();
		this.rentalFee = transaction.getRentalFee();
		this.namespaceName = transaction.getResultingNamespaceId().toString();
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		Address.writeTo(serializer, "lessor", this.lessor);
		Amount.writeTo(serializer, "amount", this.rentalFee);
		serializer.writeString("namespace", this.namespaceName);
	}
}
