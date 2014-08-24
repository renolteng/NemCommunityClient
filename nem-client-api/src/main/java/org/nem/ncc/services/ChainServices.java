package org.nem.ncc.services;

import org.nem.core.connect.client.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.*;
import org.nem.ncc.model.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
		final CompletableFuture<BlockHeight> heightFuture = this.getLastBlockHeightAsync(endpoint);
		final CompletableFuture<BlockHeight> maxHeightFuture = this.getMaxBlockHeightAsync(endpoint, numPeers);
		return CompletableFuture.allOf(heightFuture, maxHeightFuture)
				.thenApply(v -> new NisNodeMetaData(numPeers[0], maxHeightFuture.join(), heightFuture.join()));
	}

	private CompletableFuture<BlockHeight> getMaxBlockHeightAsync(final NodeEndpoint endpoint, final int[] numPeers) {
		return this.networkServices.getNodePeerListAsync(endpoint)
				.thenCompose(nodes -> {
					final Collection<Node> activeNodes = nodes.getActiveNodes();
					numPeers[0] = activeNodes.size();
					final List<NodeEndpoint> endpoints = activeNodes.stream().map(Node::getEndpoint).collect(Collectors.toList());
					return this.getMaxBlockHeightAsync(endpoints);
				});
	}

	/**
	 * Gets the height of the last block for the specified NIS node.
	 *
	 * @param endpoint The endpoint.
	 * @return The block height.
	 */
	public CompletableFuture<BlockHeight> getLastBlockHeightAsync(final NodeEndpoint endpoint) {
		// note this is assuming that the deserializer supports random access
		return this.connector.getAsync(endpoint, NisApiId.NIS_REST_CHAIN_LAST_BLOCK, null)
				.thenApply(d -> BlockHeight.readFrom(d, "height"));
	}

	/**
	 * Gets the maximum height of the last block for the specified NIS nodes.
	 *
	 * @param endpoints The endpoints.
	 * @return The maximum block height.
	 */
	public CompletableFuture<BlockHeight> getMaxBlockHeightAsync(final Collection<NodeEndpoint> endpoints) {
		// note this is assuming that the deserializer supports random access
		final List<CompletableFuture<BlockHeight>> heightFutures = endpoints.stream()
				.map(ne -> this.getLastBlockHeightAsync(ne).exceptionally(e -> null))
				.collect(Collectors.toList());

		return CompletableFuture.allOf(heightFutures.toArray(new CompletableFuture[heightFutures.size()]))
				.thenApply(v -> {
					final OptionalLong maxHeight = heightFutures.stream()
							.map(CompletableFuture::join)
							.filter(h -> null != h)
							.mapToLong(BlockHeight::getRaw)
							.max();

					return maxHeight.isPresent() ? new BlockHeight(maxHeight.getAsLong()) : BlockHeight.ONE;
				});
	}
}
