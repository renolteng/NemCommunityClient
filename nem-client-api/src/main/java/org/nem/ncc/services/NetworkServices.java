package org.nem.ncc.services;

import org.nem.core.node.*;
import org.nem.ncc.connector.SimpleNisConnector;
import org.nem.ncc.model.NisApiId;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * This class provides higher-level functions around accessing the NIS network.
 */
public class NetworkServices {
	private final SimpleNisConnector nisConnector;

	/**
	 * Creates new network services.
	 *
	 * @param nisConnector The NIS connector.
	 */
	public NetworkServices(final SimpleNisConnector nisConnector) {
		this.nisConnector = nisConnector;
	}

	/**
	 * Asynchronously retrieves the peers known to a node running on a given endpoint.
	 *
	 * @param endpoint The endpoint.
	 * @return The peers.
	 */
	public CompletableFuture<NodeCollection> getNodePeerListAsync(final NodeEndpoint endpoint) {
		return this.nisConnector.getAsync(endpoint, NisApiId.NIS_REST_PEER_LIST, null).thenApply(NodeCollection::new);
	}

	/**
	 * Asynchronously retrieves the peers known to the specified nodes.
	 * On an error, the returned peers will be null.
	 *
	 * @param nodes The nodes.
	 * @return The node to peers map.
	 */
	public CompletableFuture<Map<Node, NodeCollection>> getNodePeerListsAsync(final Collection<Node> nodes) {
		final Map<Node, CompletableFuture<NodeCollection>> nodeToPeersAsyncMap = new HashMap<>();
		nodes.stream()
				.forEach(n -> {
					final CompletableFuture<NodeCollection> peerListFuture =
							this.getNodePeerListAsync(n.getEndpoint()).exceptionally(e -> null);
					nodeToPeersAsyncMap.put(n, peerListFuture);
				});

		final Collection<CompletableFuture<NodeCollection>> peerListFutures = nodeToPeersAsyncMap.values();
		//noinspection MismatchedQueryAndUpdateOfCollection
		final Map<Node, NodeCollection> nodeToPeersMap = new HashMap<>();
		return CompletableFuture.allOf(peerListFutures.toArray(new CompletableFuture[peerListFutures.size()]))
				.whenComplete((o, e) -> {
					for (final Map.Entry<Node, CompletableFuture<NodeCollection>> entry : nodeToPeersAsyncMap.entrySet()) {
						nodeToPeersMap.put(entry.getKey(), entry.getValue().join());
					}
				})
				.thenApply(v -> nodeToPeersMap);
	}
}