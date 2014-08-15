package org.nem.ncc.model.graph;

import org.nem.core.serialization.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a graph of nodes and edges.
 */
public class Graph implements SerializableEntity {
	private final Set<GraphNode> nodes = Collections.newSetFromMap(new ConcurrentHashMap<>());
	private final Set<GraphEdge> edges = Collections.newSetFromMap(new ConcurrentHashMap<>());

	/**
	 * Creates a graph.
	 */
	public Graph() {
	}

	/**
	 * Creates a graph from a given set of nodes and edges.
	 *
	 * @param nodes The nodes.
	 * @param edges The edges.
	 */
	public Graph(final Collection<GraphNode> nodes, final Collection<GraphEdge> edges) {
		this.nodes.addAll(nodes);
		this.edges.addAll(edges);
	}

	/**
	 * Deserializes a graph.
	 *
	 * @param deserializer The deserializer.
	 */
	public Graph(final Deserializer deserializer) {
		this.nodes.addAll(deserializer.readObjectArray("nodes", GraphNode::new));
		this.edges.addAll(deserializer.readObjectArray("edges", GraphEdge::new));
	}

	/**
	 * Gets the collection of graph nodes.
	 *
	 * @return The graph nodes.
	 */
	public Collection<GraphNode> getNodes() {
		return this.nodes;
	}

	/**
	 * Gets the collection of graph edges.
	 *
	 * @return The graph edges.
	 */
	public Collection<GraphEdge> getEdges() {
		return this.edges;
	}

	/**
	 * Adds a node to the graph.
	 *
	 * @param node The node.
	 */
	public void addNode(final GraphNode node) {
		this.nodes.add(node);
	}

	/**
	 * Adds an edge to the graph.
	 *
	 * @param edge The edge.
	 */
	public void addEdge(final GraphEdge edge) {
		this.edges.add(edge);
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeObjectArray("nodes", this.nodes);
		serializer.writeObjectArray("edges", this.edges);
	}

	@Override
	public int hashCode() {
		return this.nodes.hashCode() ^ this.edges.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Graph)) {
			return false;
		}

		final Graph rhs = (Graph)obj;
		return this.nodes.equals(rhs.nodes) && this.edges.equals(rhs.edges);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(String.format("[%d nodes, %d edges]", this.nodes.size(), this.edges.size()));

		this.nodes.stream()
				.sorted((n1, n2) -> n1.getId().getEncoded().compareTo(n2.getId().getEncoded()))
				.forEach(n -> builder.append(System.lineSeparator()).append(n.toString()));
		this.edges.stream()
				.sorted((e1, e2) -> e1.getId().compareTo(e2.getId()))
				.forEach(e -> builder.append(System.lineSeparator()).append(e.toString()));
		return builder.toString();
	}
}
