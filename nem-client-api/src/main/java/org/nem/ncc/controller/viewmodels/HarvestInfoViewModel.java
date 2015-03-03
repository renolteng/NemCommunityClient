package org.nem.ncc.controller.viewmodels;

import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.*;
import org.nem.core.time.*;

/**
 * A view model representing harvest information.
 */
public class HarvestInfoViewModel implements SerializableEntity {
	private final Long blockId;
	private final BlockHeight height;
	private final TimeInstant timeStamp;
	private final Amount totalFee;
	private final Long difficulty;

	/**
	 * Creates a new harvest info view model.
	 *
	 * @param harvestInfo The harvest info.
	 */
	public HarvestInfoViewModel(final HarvestInfo harvestInfo) {
		this.blockId = harvestInfo.getId();
		this.height = harvestInfo.getBlockHeight();
		this.timeStamp = harvestInfo.getTimeStamp();
		this.totalFee = harvestInfo.getTotalFee();
		this.difficulty = harvestInfo.getDifficulty();
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeLong("id", this.blockId);
		serializer.writeString("message", String.format("Block #%s", this.height));
		serializer.writeLong("timeStamp", UnixTime.fromTimeInstant(this.timeStamp).getMillis());
		Amount.writeTo(serializer, "fee", this.totalFee);
		BlockHeight.writeTo(serializer, "height", this.height);
		serializer.writeLong("difficulty", this.difficulty);
	}

	/**
	 * Gets the block id.
	 *
	 * @return The block id.
	 */
	public Long getId() {
		return this.blockId;
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

	/**
	 * Gets the block difficulty.
	 *
	 * @return The block difficulty.
	 */
	public Long getDifficulty() {
		return this.difficulty;
	}
}
