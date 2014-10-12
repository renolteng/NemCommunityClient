package org.nem.ncc.model.graph;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

/**
 * Represents an edge between two graph nodes.
 */
public class GraphEdge implements SerializableEntity {
	private final String id;
	private final GraphNode source;
	private final GraphNode target;

	/**
	 * Creates a graph edge between two nodes.
	 *
	 * @param source The source node.
	 * @param target The target node.
	 */
	public GraphEdge(final GraphNode source, final GraphNode target) {
		this.id = String.format("%s-%s", source.getId(), target.getId());
		this.source = source;
		this.target = target;
	}

	/**
	 * Deserializes a graph edge.
	 *
	 * @param deserializer The deserializer.
	 */
	public GraphEdge(final Deserializer deserializer) {
		this.id = deserializer.readString("id");
		this.source = new GraphNode(Address.readFrom(deserializer, "source"));
		this.target = new GraphNode(Address.readFrom(deserializer, "target"));
	}

	/**
	 * Gets the unique id for the graph edge.
	 *
	 * @return The id.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets the source node of the edge.
	 *
	 * @return The source node.
	 */
	public GraphNode getSource() {
		return this.source;
	}

	/**
	 * Gets the target node of the edge.
	 *
	 * @return The target node.
	 */
	public GraphNode getTarget() {
		return this.target;
	}

	@Override
	public void serialize(final Serializer serializer) {
		//
		serializer.writeString("id", this.id);
		Address.writeTo(serializer, "source", this.source.getId());
		Address.writeTo(serializer, "target", this.target.getId());
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof GraphEdge)) {
			return false;
		}

		final GraphEdge rhs = (GraphEdge)obj;
		return this.id.equals(rhs.id);
	}

	@Override
	public String toString() {
		return String.format("edge: %s", this.id);
	}
}
