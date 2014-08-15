package org.nem.ncc.model.graph;

import org.nem.core.model.Address;
import org.nem.core.node.Node;
import org.nem.core.serialization.*;

/**
 * Represents a graph node.
 */
public class GraphNode implements SerializableEntity {
	private final Address id;
	private final String label;

	/**
	 * Creates a graph node from an id.
	 *
	 * @param id The unique id string.
	 */
	public GraphNode(final Address id) {
		this.id = id;
		this.label = "";
	}

	/**
	 * Creates a graph node from a network node.
	 *
	 * @param node The node.
	 */
	public GraphNode(final Node node) {
		this.id = node.getIdentity().getAddress();
		this.label = node.getIdentity().getName();
	}

	/**
	 * Deserializes a graph node.
	 *
	 * @param deserializer The deserializer.
	 */
	public GraphNode(final Deserializer deserializer) {
		this.id = Address.readFrom(deserializer, "id");
		this.label = deserializer.readString("label");
	}

	/**
	 * Gets the unique id for the graph node.
	 *
	 * @return The id.
	 */
	public Address getId() {
		return this.id;
	}

	/**
	 * Gets the label for the graph node.
	 *
	 * @return The label
	 */
	public String getLabel() {
		return this.label;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Address.writeTo(serializer, "id", this.id);
		serializer.writeString("label", this.label);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof GraphNode)) {
			return false;
		}

		final GraphNode rhs = (GraphNode)obj;
		return this.id.equals(rhs.id);
	}

	@Override
	public String toString() {
		return String.format("node: %s", this.id);
	}
}
