package org.nem.ncc.model.graph;

import org.nem.core.node.*;
import org.nem.ncc.services.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

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
				.thenCompose(networkServices::getNodePeerListsAsync).join();
		final GraphBuilder builder = new GraphBuilder();
		nodePeersMap.entrySet().forEach(e -> builder.addToGraph(e.getKey(), e.getValue()));

		return builder.create();
	}

	/**
	 * Creates a graph view model for the local NIS server.
	 *
	 * @param networkServices The network services.
	 * @param nodeServices The node services.
	 * @return The graph view model.
	 */
	public GraphViewModel createViewModel(
			final NetworkServices networkServices,
			final NodeServices nodeServices) {
		// TODO 20141011 J-B: is the intent to really get the "local" network or the connected NIS network (when NIS is remote?)
		final NodeEndpoint localEndPoint = new NodeEndpoint("http", "127.0.0.1", 7890);
		final GraphBuilder builder = new GraphBuilder();
		try {
			final Node localNode = nodeServices.getNodeAsync(localEndPoint).get();

			// local end point should not be addressed through the external URL which is returned from getNode
			localNode.setEndpoint(localEndPoint);
			final Collection<Node> nodeSet = Collections.singleton(localNode);
			// TODO 20141011 J-B: can you call into createViewModel at this point?
			final Map<Node, NodeCollection> nodePeersMap = networkServices.getNodePeerListsAsync(
					nodeSet).join();
			nodePeersMap.entrySet().forEach(e -> builder.addToGraph(e.getKey(), e.getValue()));
		} catch (ExecutionException | InterruptedException ex) {
			// TODO 20141011 J-B: i would rather let an exception propagate out then bury it ... use ExceptionUtils.propagate
			// we do nothing just an empty result;
		}

		return builder.create();
	}
}