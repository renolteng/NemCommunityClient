package org.nem.monitor;

import org.nem.core.connect.client.*;
import org.nem.core.model.NemStatus;
import org.nem.core.model.ncc.NemRequestResult;
import org.nem.core.serialization.Deserializer;
import org.nem.core.utils.LockFile;
import org.nem.monitor.node.*;

import java.util.concurrent.*;

/**
 * This connector can interact with both NIS and NCC because the APIs it
 * uses are exposed by both.
 */
public class NemConnector {
	private final NemNodePolicy policy;
	private final DefaultAsyncNemConnector<String> connector;

	/**
	 * Creates a new nem connector.
	 *
	 * @param policy The nem node policy.
	 * @param connector The connector.
	 */
	public NemConnector(
			final NemNodePolicy policy,
			final DefaultAsyncNemConnector<String> connector) {
		this.policy = policy;
		this.connector = connector;
	}

	/**
	 * Checks if the connected process is running.
	 *
	 * @return The node status.
	 */
	public CompletableFuture<NemStatus> getStatus() {
		return this.getAsync(NisApiId.NIS_REST_STATUS).handle((d, e) -> {
			if (isRunning(e)) {
				return NemStatus.RUNNING;
			}

			if (null != e) {
				return this.isBooting() ? NemStatus.STARTING : NemStatus.STOPPED;
			}

			return nemStatusFromNemRequestResult(d);
		});
	}

	private boolean isBooting() {
		return LockFile.isLocked(this.policy.getLockFile());
	}

	private static boolean isRunning(final Throwable ex) {
		return (ex instanceof CompletionException && ex.getCause() instanceof NemNodeExpectedException);
	}

	private NemStatus nemStatusFromNemRequestResult(final Deserializer deserializer) {
		if (null == deserializer) {
			return NemStatus.RUNNING;
		}

		NemRequestResult result = new NemRequestResult(deserializer);
		return NemStatus.fromValue(result.getCode());
	}

	/**
	 * Shuts down the connected process.
	 *
	 * @return The future.
	 */
	public CompletableFuture<Void> shutdown() {
		return this.getAsync(NisApiId.NIS_REST_SHUTDOWN).thenApply(d -> null);
	}

	private CompletableFuture<Deserializer> getAsync(final NisApiId apiId) {
		return this.connector.getAsync(this.policy.getEndpoint(), this.policy.mapToUrlPath(apiId), null);
	}
}
