package org.nem.ncc.services;

import org.nem.core.node.*;
import org.nem.ncc.connector.*;
import org.nem.ncc.model.NisApiId;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * This class provides higher-level functions around accessing NIS nodes.
 */
public class NodeServices {
	private final AsyncNisConnector nisConnector;

	/**
	 * Creates new node services.
	 *
	 * @param nisConnector The NIS connector.
	 */
	public NodeServices(final AsyncNisConnector nisConnector) {
		this.nisConnector = nisConnector;
	}

	/**
	 * Asynchronously retrieves information about a node running on a given endpoint.
	 * On an error, the returned node will be null.
	 *
	 * @param endpoint The endpoint.
	 * @return The node.
	 */
	public CompletableFuture<Node> getNodeAsync(final NodeEndpoint endpoint) {
		return this.nisConnector.getAsync(endpoint, NisApiId.NIS_REST_NODE_INFO, null)
				.thenApply(Node::new);
	}

	/**
	 * Asynchronously retrieves extended information about a node running on a given endpoint.
	 * On an error, the returned extended node info will be null.
	 *
	 * @param endpoint The endpoint.
	 * @return The extended node info.
	 */
	public CompletableFuture<NisNodeInfo> getNisNodeInfoAsync(final NodeEndpoint endpoint) {
		return this.nisConnector.getAsync(endpoint, NisApiId.NIS_REST_NODE_EXTENDED_INFO, null)
				.thenApply(NisNodeInfo::new);
	}

	/**
	 * Asynchronously retrieves information about all nodes running on the given endpoints.
	 *
	 * @param endpoints The endpoints.
	 * @return The nodes.
	 */
	public CompletableFuture<Collection<Node>> getNodesAsync(final Collection<NodeEndpoint> endpoints) {
		//noinspection MismatchedQueryAndUpdateOfCollection
		final Collection<Node> nodes = new ArrayList<>();
		final List<CompletableFuture<Node>> nodeFutures = endpoints.stream()
				.map(n -> this.getNodeAsync(n).exceptionally(e -> null))
				.collect(Collectors.toList());

		return CompletableFuture.allOf(nodeFutures.toArray(new CompletableFuture[nodeFutures.size()]))
				.whenComplete((o, e) -> {
					final Collection<Node> nonNullNodes = nodeFutures.stream()
							.map(CompletableFuture::join)
							.filter(n -> null != n)
							.collect(Collectors.toList());
					nodes.addAll(nonNullNodes);
				})
				.thenApply(v -> nodes);
	}
}
