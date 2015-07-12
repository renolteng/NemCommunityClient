package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.MosaicCreationTransaction;
import org.nem.core.model.mosaic.MosaicProperties;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Serializer;

public class MosaicCreationTransactionViewModel extends TransactionViewModel {
	private final String description;
	private final String mosaicName;
	private final MosaicProperties mosaicProperties;

	public MosaicCreationTransactionViewModel(final TransactionMetaDataPair metaDataPair, final BlockHeight lastBlockHeight) {
		super(Type.Mosaic_Creation, metaDataPair, lastBlockHeight);

		final MosaicCreationTransaction transaction = (MosaicCreationTransaction)metaDataPair.getTransaction();
		this.description = transaction.getMosaic().getDescriptor().toString();
		this.mosaicName = transaction.getMosaic().getId().toString();
		this.mosaicProperties = transaction.getMosaic().getProperties();
	}

	@Override
	protected void serializeImpl(final Serializer serializer) {
		super.serializeImpl(serializer);

		serializer.writeString("description", this.description);
		serializer.writeString("mosaicName", this.mosaicName);
		serializer.writeObjectArray("properties", this.mosaicProperties.asCollection());
	}

	/**
	 * Gets the description.
	 *
	 * @return The description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the mosaic expressed as a string.
	 *
	 * @return The mosaic name.
	 */
	public String getMosaicName() {
		return mosaicName;
	}

	/**
	 * Gets the mosaic properties.
	 *
	 * @return The mosaic properties.
	 */
	public MosaicProperties getMosaicProperties() {
		return mosaicProperties;
	}
}
