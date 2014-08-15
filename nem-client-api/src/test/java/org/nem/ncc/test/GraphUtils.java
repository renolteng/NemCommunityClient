package org.nem.ncc.test;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;
import org.nem.core.node.*;
import org.nem.ncc.model.graph.*;

/**
 * Static class containing test utilities for graph-related classes.
 */
public class GraphUtils {

	/**
	 * Creates a graph node.
	 *
	 * @return The graph node.
	 */
	public static GraphNode createGraphNode() {
		return new GraphNode(createNode());
	}

	/**
	 * Creates a graph node from a given label.
	 *
	 * @param label The label.
	 * @return The graph node.
	 */
	public static GraphNode createGraphNode(final String label) {
		return new GraphNode(createNode(label));
	}

	/**
	 * Creates a graph edge from two graph nodes.
	 *
	 * @param source The source node.
	 * @param target The target node.
	 * @return The graph edge.
	 */
	public static GraphEdge createGraphEdge(final GraphNode source, final GraphNode target) {
		return new GraphEdge(source, target);
	}

	/**
	 * Creates a graph node meta data.
	 *
	 * @return The meta data.
	 */
	public static GraphNodeMetaData createGraphNodeMetaData(final boolean active) {
		final Address address = Address.fromEncoded("TBLOODZW6W4DUVL4NGAQXHZXFQJLNHPDXHULLHZW");
		final String platform = "Linux";
		final String version = "0.0.74";
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("79.220.196.143");

		return new GraphNodeMetaData(address, platform, version, endpoint, active);
	}

	/**
	 * Creates a graph meta dataPair.
	 *
	 * @return The meta data pair.
	 */
	public static GraphViewModel createGraphViewModel(final KeyPair keyPair1, final KeyPair keyPair2) {
		final Node node1 = createNode(keyPair1);
		final Node node2 = createNode(keyPair2);
		final GraphNode graphNode1 = new GraphNode(node1);
		final GraphNode graphNode2 = new GraphNode(node2);
		final GraphEdge graphEdge = new GraphEdge(graphNode1, graphNode2);
		final Graph graph = new Graph();
		graph.addNode(graphNode1);
		graph.addNode(graphNode2);
		graph.addEdge(graphEdge);

		final GraphNodeMetaData metaData1 = new GraphNodeMetaData(
				node1.getIdentity().getAddress(),
				node1.getMetaData().getPlatform(),
				node1.getMetaData().getVersion().toString(),
				node1.getEndpoint(),
				true);
		final GraphNodeMetaData metaData2 = new GraphNodeMetaData(
				node2.getIdentity().getAddress(),
				node2.getMetaData().getPlatform(),
				node2.getMetaData().getVersion().toString(),
				node2.getEndpoint(),
				true);
		final GraphMetaData graphMetaData = new GraphMetaData();
		graphMetaData.add(metaData1);
		graphMetaData.add(metaData2);

		return new GraphViewModel(graph, graphMetaData);
	}

	/**
	 * Creates a NIS node.
	 *
	 * @return The node.
	 */
	public static Node createNode() {
		final KeyPair keyPair = new KeyPair(Utils.generateRandomPublicKey());
		final NodeEndpoint endpoint = new NodeEndpoint("http", "localhost", 8080);
		final NodeMetaData metaData = new NodeMetaData("p", "a", new NodeVersion(1, 0, 0));
		final NodeIdentity identity = new NodeIdentity(keyPair, "Hi, I am Alice");
		return new Node(identity, endpoint, metaData);
	}

	/**
	 * Creates a NIS node from a given name.
	 *
	 * @param name The name.
	 * @return The node.
	 */
	public static Node createNode(final String name) {
		final KeyPair keyPair = new KeyPair(Utils.generateRandomPublicKey());
		final NodeEndpoint endpoint = new NodeEndpoint("http", "localhost", 8080);
		final NodeMetaData metaData = new NodeMetaData("p", "a", new NodeVersion(1, 0, 0));
		final NodeIdentity identity = new NodeIdentity(keyPair, name);
		return new Node(identity, endpoint, metaData);
	}

	/**
	 * Creates a NIS node from a given host.
	 *
	 * @param host The host.
	 * @return The node.
	 */
	public static Node createNodeFromHost(final String host) {
		final KeyPair keyPair = new KeyPair(Utils.generateRandomPublicKey());
		final NodeEndpoint endpoint = new NodeEndpoint("http", host, 8080);
		final NodeMetaData metaData = new NodeMetaData("p", "a", new NodeVersion(1, 0, 0));
		final NodeIdentity identity = new NodeIdentity(keyPair);
		return new Node(identity, endpoint, metaData);
	}

	/**
	 * Creates a NIS node from a given key pair.
	 *
	 * @param keyPair the key pair
	 * @return The node.
	 */
	public static Node createNode(KeyPair keyPair) {
		if (keyPair == null) {
			keyPair = new KeyPair(Utils.generateRandomPublicKey());
		}

		final NodeEndpoint endpoint = new NodeEndpoint("http", "localhost", 8080);
		final NodeMetaData metaData = new NodeMetaData("p", "a", new NodeVersion(1, 0, 0));
		final NodeIdentity identity = new NodeIdentity(keyPair, "Hi, I am Alice");
		return new Node(identity, endpoint, metaData);
	}

	/**
	 * Creates a GraphNode with the specified id.
	 *
	 * @param id The id.
	 * @return The graph node.
	 */
	public static GraphNode createGraphNodeWithId(final String id) {
		return new GraphNode(Address.fromEncoded(id));
	}
}
