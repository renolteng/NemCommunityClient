package org.nem.ncc.model.graph;

import org.nem.core.node.*;
import org.nem.ncc.services.*;

import java.util.*;

/**
 * Factory for creating GraphViewModel.
 */
public class GraphViewModelFactory {
	/**
	 * Creates a graph view model for the given endpoints.
	 *
	 * @param networkServices The network services.
	 * @param nodeServices The node services.
	 * @param endpoints The collection of endpoints.
	 * @return The graph view model.
	 */
	public GraphViewModel createViewModel(
			final NetworkServices networkServices,
			final NodeServices nodeServices,
			final Collection<NodeEndpoint> endpoints) {
		final Map<Node, NodeCollection> nodePeersMap = nodeServices.getNodesAsync(endpoints)
				.thenCompose(networkServices::getNodePeerListsAsync)
				.join();
		final GraphBuilder builder = new GraphBuilder();
		nodePeersMap.entrySet().forEach(e -> builder.addToGraph(e.getKey(), e.getValue()));

		return builder.create();
	}
}
