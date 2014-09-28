package org.nem.ncc.services;

import org.nem.core.connect.client.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.synchronization.CommunicationTimeStamps;

import java.util.concurrent.CompletableFuture;

/**
 * This class provides a higher-level function to synchronize time with the NIS.
 */
public class TimeSynchronizationServices {
	private final AsyncNisConnector nisConnector;

	/**
	 * Creates new time synchronization services.
	 *
	 * @param nisConnector The NIS connector.
	 */
	public TimeSynchronizationServices(final AsyncNisConnector nisConnector) {
		this.nisConnector = nisConnector;
	}

	/**
	 * Asynchronously retrieves communication time stamps from a node running on a given endpoint.
	 *
	 * @param endpoint The endpoint.
	 * @return The communication time stamps.
	 */
	public CompletableFuture<CommunicationTimeStamps> getCommunicationTimeStampsAsync(final NodeEndpoint endpoint) {
		return this.nisConnector.getAsync(endpoint, NisApiId.NIS_REST_TIME_SYNC_NETWORK_TIME, null).thenApply(CommunicationTimeStamps::new);
	}
}
