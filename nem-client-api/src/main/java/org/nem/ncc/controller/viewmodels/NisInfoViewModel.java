package org.nem.ncc.controller.viewmodels;

import org.nem.core.node.NisNodeInfo;
import org.nem.core.serialization.*;
import org.nem.ncc.model.NisNodeMetaData;

/**
 * A view of a NIS node.
 */
public class NisInfoViewModel implements SerializableEntity {
	private final NisNodeInfo nodeInfo;
	private final NisNodeMetaData nodeMetaData;

	/**
	 * Creates a new NIS info view model.
	 *
	 * @param nodeInfo The NIS node data.
	 * @param nodeMetaData The NIS node meta data.
	 */
	public NisInfoViewModel(final NisNodeInfo nodeInfo, final NisNodeMetaData nodeMetaData) {
		this.nodeInfo = nodeInfo;
		this.nodeMetaData = nodeMetaData;
	}

	/**
	 * Deserializes a NIS info view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public NisInfoViewModel(final Deserializer deserializer) {
		this.nodeInfo = deserializer.readObject("nodeInfo", NisNodeInfo::new);
		this.nodeMetaData = deserializer.readObject("nodeMetaData", NisNodeMetaData::new);
	}

	/**
	 * Gets the NIS node info.
	 *
	 * @return The NIS node info.
	 */
	public NisNodeInfo getNodeInfo() {
		return this.nodeInfo;
	}

	/**
	 * Gets the NIS node meta data.
	 *
	 * @return The NIS node meta data.
	 */
	public NisNodeMetaData getNodeMetaData() {
		return this.nodeMetaData;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeObject("nodeInfo", this.nodeInfo);
		serializer.writeObject("nodeMetaData", this.nodeMetaData);
	}
}
