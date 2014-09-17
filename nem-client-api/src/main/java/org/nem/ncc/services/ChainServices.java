package org.nem.ncc.services;

import org.nem.core.connect.client.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.NodeEndpoint;
import org.nem.ncc.model.NisNodeMetaData;

import java.util.concurrent.CompletableFuture;

/**
 * This class provides higher-level functions around accessing information about the NIS block chain.
 */
public class ChainServices {
	private final AsyncNisConnector connector;
	private final NetworkServices networkServices;

	/**
	 * Creates a new chain services instance.
	 *
	 * @param networkServices The graph services
	 */
	public ChainServices(
			final AsyncNisConnector connector,
			final NetworkServices networkServices) {
		this.connector = connector;
		this.networkServices = networkServices;
	}

	/**
	 * Retrieves additional information about the specified NIS node.
	 *
	 * @param endpoint The endpoint.
	 * @return The NIS node meta data.
	 */
	public CompletableFuture<NisNodeMetaData> getNodeMetaDataAsync(final NodeEndpoint endpoint) {
		final int[] numPeers = new int[1]; // this is just a hack to allow getMaxBlockHeightAsync to return the number of nodes
		final CompletableFuture<BlockHeight> heightFuture = this.getChainHeightAsync(endpoint);
		final CompletableFuture<BlockHeight> maxHeightFuture = this.getMaxChainHeightAsync(endpoint, numPeers);
		return CompletableFuture.allOf(heightFuture, maxHeightFuture)
				.thenApply(v -> new NisNodeMetaData(numPeers[0], maxHeightFuture.join(), heightFuture.join()));
	}

	private CompletableFuture<BlockHeight> getMaxChainHeightAsync(final NodeEndpoint endpoint, final int[] numPeers) {
		return this.networkServices.getNodePeerListAsync(endpoint)
				.thenCompose(nodes -> {
					numPeers[0] = nodes.getActiveNodes().size();
					return this.connector.getAsync(endpoint, NisApiId.NIS_REST_ACTIVE_PEERS_MAX_CHAIN_HEIGHT, null)
							.thenApply(BlockHeight::new);
				});
	}

	/**
	 * Gets the height of the last block for the specified NIS node.
	 *
	 * @param endpoint The endpoint.
	 * @return The block height.
	 */
	public CompletableFuture<BlockHeight> getChainHeightAsync(final NodeEndpoint endpoint) {
		return this.connector.getAsync(endpoint, NisApiId.NIS_REST_CHAIN_HEIGHT, null)
				.thenApply(BlockHeight::new);
	}
}
