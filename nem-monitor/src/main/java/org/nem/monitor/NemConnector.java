package org.nem.monitor;

import org.nem.core.connect.*;
import org.nem.core.connect.client.*;
import org.nem.core.serialization.Deserializer;
import org.nem.monitor.node.NemNodePolicy;

import java.util.concurrent.*;

/**
 * This connector can interact with both NIS and NCC because the APIs it
 * uses are exposed by both.
 *
 * TODO-J: add some tests
 */
public class NemConnector {
	private final NemNodePolicy policy;
	private final DefaultAsyncNemConnector<String> connector;

	/**
	 * Creates a new nem connector.
	 *
	 * @param policy The nem node policy.
	 * @param httpClient The http client.
	 */
	public NemConnector(
			final NemNodePolicy policy,
			final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient) {
		this.policy = policy;
		this.connector = new DefaultAsyncNemConnector<>(httpClient, r -> { throw new NemConnectionException(); });
		this.connector.setAccountLookup(null);
	}

	/**
	 * Checks if the connected process is running.
	 */
	public CompletableFuture<Boolean> isRunning() {
		return this.getAsync(NisApiId.NIS_REST_HEARTBEAT)
				.handle((d, e) -> {
					return null == e || (e instanceof CompletionException && e.getCause() instanceof NemConnectionException);
				});
	}

	/**
	 * Shuts down the connected process.
	 */
	public CompletableFuture<Void> shutdown() {
		return this.getAsync(NisApiId.NIS_REST_SHUTDOWN).thenApply(d -> null);
	}

	private CompletableFuture<Deserializer> getAsync(final NisApiId apiId) {
		return this.connector.getAsync(this.policy.getEndpoint(), this.policy.mapToUrlPath(apiId), null);
	}

	private static class NemConnectionException extends RuntimeException {
	}
}
