package org.nem.ncc.model.graph;

import org.nem.core.node.*;
import org.nem.ncc.services.*;

import java.util.*;
import java.util.stream.Collectors;

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
		// TODO 20150116 BR -> J: see comment from Thies1965 below, there was a problem with using local nodes internet ip
		// > for people that had their port 7890 afaik.
		// TODO 20150118 J-B: right, i remember the issue, but if someone is using a remote NIS, shouldn't this request go to the remote NIS
		// > (as a NIS wouldn't be running locally)?
		// TODO 20150123 BR -> J: yea, in this case it should go to the remote NIS.

		// local end point should not be addressed through the external URL which is returned from getNode
		final NodeEndpoint localEndPoint = new NodeEndpoint("http", "127.0.0.1", 7890);
		final GraphViewModelFactory t = this;
		return nodeServices.getNodeAsync(localEndPoint).thenApply(localNode -> {
			final Collection<Node> nodeSet = Collections.singleton(localNode);
			return t.createViewModel(
					networkServices,
					nodeServices,
					nodeSet.stream().map(Node::getEndpoint).collect(Collectors.toList()));
		}).join();
	}
}