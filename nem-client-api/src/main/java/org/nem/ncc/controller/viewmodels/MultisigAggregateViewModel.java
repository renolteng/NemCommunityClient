package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.Address;
import org.nem.core.model.MultisigAggregateModificationTransaction;
import org.nem.core.model.MultisigModification;
import org.nem.core.model.MultisigModificationType;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

import java.util.List;
import java.util.stream.Collectors;

public class MultisigAggregateViewModel extends TransactionViewModel {
	private class ModificationWrapper implements SerializableEntity {
		final MultisigModificationType modificationType;
		final Address address;
		public ModificationWrapper(final MultisigModification multisigModification) {
			this.modificationType = multisigModification.getModificationType();
			this.address = multisigModification.getCosignatory().getAddress();
		}

		@Override
		public void serialize(final Serializer serializer) {
			Address.writeTo(serializer, "address", this.address);
			serializer.writeString("type", this.modificationType == MultisigModificationType.Add ? "add" : "del");
		}
	}
	final List<ModificationWrapper> modifications;

	public MultisigAggregateViewModel(final TransactionMetaDataPair metaDataPair, final AccountMetaDataPair accountData, final BlockHeight blockHeight) {
		super(Type.Multisig_Modification, metaDataPair, blockHeight);

		final MultisigAggregateModificationTransaction transaction = (MultisigAggregateModificationTransaction)metaDataPair.getTransaction();
		this.modifications = transaction.getModifications().stream()
				.map(o -> new ModificationWrapper(o))
				.collect(Collectors.toList());
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		serializer.writeObjectArray("modifications", this.modifications);
	}
}
