package org.nem.ncc.model.graph;

import org.nem.core.serialization.*;

/**
 * A view of a NIS graph.
 */
public class GraphViewModel implements SerializableEntity {
	private final Graph graph;
	private final GraphMetaData metaData;

	/**
	 * Creates a new view.
	 *
	 * @param graph The graph.
	 * @param metaData The meta data.
	 */
	public GraphViewModel(final Graph graph, final GraphMetaData metaData) {
		this.graph = graph;
		this.metaData = metaData;
	}

	/**
	 * Deserializes a pair.
	 *
	 * @param deserializer The deserializer
	 */
	public GraphViewModel(final Deserializer deserializer) {
		this.graph = deserializer.readObject("graph", Graph::new);
		this.metaData = deserializer.readObject("meta", GraphMetaData::new);
	}

	/**
	 * Gets the graph.
	 *
	 * @return The graph.
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Gets the graph's meta data.
	 *
	 * @return The graph's meta data.
	 */
	public GraphMetaData getMetaData() {
		return this.metaData;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeObject("graph", this.graph);
		serializer.writeObject("meta", this.metaData);
	}

	@Override
	public int hashCode() {
		return this.graph.hashCode() ^ this.metaData.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof GraphViewModel)) {
			return false;
		}

		final GraphViewModel rhs = (GraphViewModel)obj;
		return this.graph.equals(rhs.graph) && this.metaData.equals(rhs.metaData);
	}

	@Override
	public String toString() {
		return String.format(
				"[graph]%s%s%s[meta]%s%s",
				System.lineSeparator(),
				this.graph,
				System.lineSeparator(),
				System.lineSeparator(),
				this.metaData);
	}
}
