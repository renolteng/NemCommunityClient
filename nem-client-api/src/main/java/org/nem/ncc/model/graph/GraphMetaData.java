package org.nem.ncc.model.graph;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for holding additional information about a graph.
 */
public class GraphMetaData implements SerializableEntity {
	private final Map<Address, GraphNodeMetaData> metaData = new ConcurrentHashMap<>();

	/**
	 * Creates a graph meta data.
	 */
	public GraphMetaData() {
	}

	/**
	 * Deserializes a graph meta data.
	 *
	 * @param deserializer The deserializer.
	 */
	public GraphMetaData(final Deserializer deserializer) {
		this.addAll(deserializer.readObjectArray("meta", GraphNodeMetaData::new));
	}

	/**
	 * Gets the number of known addresses.
	 *
	 * @return The number of known addresses.
	 */
	public int size() {
		return this.metaData.size();
	}

	/**
	 * Adds a node's meta data.
	 *
	 * @param nodeMetaData The node's meta data.
	 */
	public void add(final GraphNodeMetaData nodeMetaData) {
		this.metaData.put(nodeMetaData.getAddress(), nodeMetaData);
	}

	/**
	 * Adds a collection of node meta data information.
	 *
	 * @param nodeMetaDataCollection The collection of node meta data information.
	 */
	public void addAll(final Collection<GraphNodeMetaData> nodeMetaDataCollection) {
		nodeMetaDataCollection.stream()
				.forEach(meta -> this.metaData.put(meta.getAddress(), meta));
	}

	/**
	 * Gets a node's meta data for a given address.
	 *
	 * @param address The node's address
	 * @return The meta data.
	 */
	public GraphNodeMetaData get(final Address address) {
		return this.metaData.get(address);
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeObjectArray("meta", new ArrayList<>(this.metaData.values()));
	}

	@Override
	public int hashCode() {
		return this.metaData.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof GraphMetaData)) {
			return false;
		}

		final GraphMetaData rhs = (GraphMetaData)obj;
		return this.metaData.equals(rhs.metaData);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(String.format("[%d node meta data]", this.metaData.size()));

		this.metaData.values().stream()
				.sorted((meta1, meta2) -> meta1.getAddress().getEncoded().compareTo(meta2.getAddress().getEncoded()))
				.forEach(meta -> builder.append(System.lineSeparator()).append(meta.toString()));
		return builder.toString();
	}
}
