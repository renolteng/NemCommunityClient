package org.nem.monitor;

import org.nem.core.connect.*;
import org.nem.core.connect.client.*;
import org.nem.core.node.NodeEndpoint;

import java.util.concurrent.CompletableFuture;
/**
 * This connector can interact with both NIS and NCC because the APIs it
 * uses are exposed by both.
 */
public class NemConnector {
	private final NodeEndpoint endpoint;
	private final DefaultAsyncNisConnector connector;

	/**
	 * Creates a new nem connector.
	 *
	 * @param endpoint The nem node endpoint.
	 * @param httpClient The http client.
	 */
	public NemConnector(
			final NodeEndpoint endpoint,
			final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient) {
		this.endpoint = endpoint;
		this.connector = new DefaultAsyncNisConnector(httpClient, r -> { throw new NemConnectionException(); });
		this.connector.setAccountLookup(null);
	}

	/**
	 * Checks if the connected process is running.
	 */
	public CompletableFuture<Boolean> isRunning() {
		return this.connector.getAsync(this.endpoint, NisApiId.NIS_REST_HEARTBEAT, null)
				.handle((d, e) -> null == e || e instanceof NemConnectionException);
	}

	/**
	 * Shuts down the connected process.
	 */
	public CompletableFuture<Void> shutdown() {
		return this.connector.getAsync(this.endpoint, NisApiId.NIS_REST_SHUTDOWN, null).thenApply(d -> null);
	}

	private static class NemConnectionException extends RuntimeException {
	}
}
