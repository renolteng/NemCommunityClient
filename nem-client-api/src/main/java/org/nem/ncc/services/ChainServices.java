package org.nem.ncc.services;

import org.nem.core.connect.client.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.*;
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
		final CompletableFuture<BlockHeight> heightFuture = this.getChainHeightAsync(endpoint);
		final CompletableFuture<BlockHeight> maxHeightFuture = this.connector.getAsync(endpoint, NisApiId.NIS_REST_ACTIVE_PEERS_MAX_CHAIN_HEIGHT, null)
				.thenApply(BlockHeight::new);
		final CompletableFuture<Integer> numNodesFuture = this.networkServices.getNodePeerListAsync(endpoint)
				.thenApply(NodeCollection::size);
		return CompletableFuture.allOf(heightFuture, maxHeightFuture, numNodesFuture)
				.thenApply(v -> new NisNodeMetaData(numNodesFuture.join(), maxHeightFuture.join(), heightFuture.join()));
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
