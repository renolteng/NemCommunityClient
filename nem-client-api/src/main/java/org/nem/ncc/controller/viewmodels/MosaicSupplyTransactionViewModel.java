package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.SmartTileSupplyChangeTransaction;
import org.nem.core.model.SmartTileSupplyType;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.serialization.Serializer;

public class MosaicSupplyTransactionViewModel extends TransactionViewModel {
	private final String namespaceName;
	private final String mosaicName;
	private final SmartTileSupplyType supplyType;
	private final Quantity supplyQuantity;

	public MosaicSupplyTransactionViewModel(final TransactionMetaDataPair metaDataPair, final BlockHeight lastBlockHeight) {
		super(Type.Mosaic_Supply, metaDataPair, lastBlockHeight);

		final SmartTileSupplyChangeTransaction transaction = (SmartTileSupplyChangeTransaction)metaDataPair.getTransaction();
		this.namespaceName = transaction.getMosaicId().getNamespaceId().toString();
		this.mosaicName = transaction.getMosaicId().getName();
		this.supplyType = transaction.getSupplyType();
		this.supplyQuantity = transaction.getQuantity();
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		serializer.writeString("mosaicName", this.mosaicName);
		serializer.writeString("namespaceName", this.namespaceName);
		serializer.writeInt("supplyType", this.supplyType.value());
		Quantity.writeTo(serializer, "supplyQuantity", this.supplyQuantity);
	}

	/**
	 * Gets namespace in which mosaic resides.
	 *
	 * @return The namespace name.
	 */
	public String getNamespaceName() {
		return this.namespaceName;
	}
	/**
	 * Gets the mosaic expressed as a string.
	 *
	 * @return The mosaic name.
	 */
	public String getMosaicName() {
		return this.mosaicName;
	}

	/**
	 * Gets the mosaic supply type.
	 *
	 * @return The mosaic supply type.
	 */
	public SmartTileSupplyType getSupplyType() {
		return this.supplyType;
	}

	/**
	 * Gets the mosaic supply quantity.
	 *
	 * @return The mosaic supply quantity.
	 */
	public Quantity getSupplyQuantity() {
		return this.supplyQuantity;
	}
}
