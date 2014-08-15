package org.nem.ncc.controller.viewmodels;

import org.nem.core.crypto.Hash;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.*;
import org.nem.core.time.*;

/**
 * A view model representing harvest information.
 */
public class HarvestInfoViewModel implements SerializableEntity {
	private final Hash blockHash;
	private final BlockHeight height;
	private final TimeInstant timeStamp;
	private final Amount totalFee;

	/**
	 * Creates a new harvest info view model.
	 *
	 * @param harvestInfo The harvest info.
	 */
	public HarvestInfoViewModel(final HarvestInfo harvestInfo) {
		this.blockHash = harvestInfo.getHash();
		this.height = harvestInfo.getBlockHeight();
		this.timeStamp = harvestInfo.getTimeStamp();
		this.totalFee = harvestInfo.getTotalFee();
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeLong("id", this.blockHash.getShortId());
		serializer.writeString("hash", this.blockHash.toString());
		serializer.writeString("message", String.format("Block #%s", this.height));
		serializer.writeLong("timeStamp", UnixTime.fromTimeInstant(this.timeStamp).getMillis());
		Amount.writeTo(serializer, "fee", this.totalFee);
		BlockHeight.writeTo(serializer, "height", this.height);
	}

	/**
	 * Gets the block hash.
	 *
	 * @return The block hash.
	 */
	public Hash getHash() {
		return this.blockHash;
	}

	/**
	 * Gets the block height.
	 *
	 * @return The block height.
	 */
	public BlockHeight getBlockHeight() {
		return this.height;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return The time stamp.
	 */
	public TimeInstant getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Gets the total fee.
	 *
	 * @return the total fee.
	 */
	public Amount getTotalFee() {
		return this.totalFee;
	}
}
