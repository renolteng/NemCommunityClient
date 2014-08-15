package org.nem.ncc.model;

import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.*;

/**
 * Meta data for a NIS node.
 */
public class NisNodeMetaData implements SerializableEntity {
	private final int activePeers;
	private final BlockHeight maxBlockChainHeight;
	private final BlockHeight nodeBlockChainHeight;

	/**
	 * Creates a NIS node meta data.
	 *
	 * @param activePeers The number of active peers.
	 * @param maxBlockChainHeight The maximum block chain height reported by the peers.
	 * @param nodeBlockChainHeight The block chain height of the NIS node.
	 */
	public NisNodeMetaData(
			final int activePeers,
			final BlockHeight maxBlockChainHeight,
			final BlockHeight nodeBlockChainHeight) {
		this.activePeers = activePeers;
		this.maxBlockChainHeight = maxBlockChainHeight;
		this.nodeBlockChainHeight = nodeBlockChainHeight;
	}

	/**
	 * Deserializes a NIS node meta data.
	 *
	 * @param deserializer The deserializer.
	 */
	public NisNodeMetaData(final Deserializer deserializer) {
		this.activePeers = deserializer.readInt("activePeers");
		this.maxBlockChainHeight = BlockHeight.readFrom(deserializer, "maxBlockChainHeight");
		this.nodeBlockChainHeight = BlockHeight.readFrom(deserializer, "nodeBlockChainHeight");
	}

	/**
	 * Gets the number of active peers.
	 *
	 * @return The number of active peers.
	 */
	public int getActivePeers() {
		return this.activePeers;
	}

	/**
	 * Gets the maximum block chain height.
	 *
	 * @return The maximum block chain height.
	 */
	public BlockHeight getMaxBlockHeight() {
		return this.maxBlockChainHeight;
	}

	/**
	 * Gets the node's block chain height.
	 *
	 * @return The node's block chain height.
	 */
	public BlockHeight getNodeBlockHeight() {
		return this.nodeBlockChainHeight;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeInt("activePeers", this.activePeers);
		BlockHeight.writeTo(serializer, "maxBlockChainHeight", this.maxBlockChainHeight);
		BlockHeight.writeTo(serializer, "nodeBlockChainHeight", this.nodeBlockChainHeight);
	}
}
