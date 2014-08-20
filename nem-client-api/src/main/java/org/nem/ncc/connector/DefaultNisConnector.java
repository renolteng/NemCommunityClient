package org.nem.ncc.connector;

import org.nem.core.connect.HttpPostRequest;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.NisException;
import org.nem.ncc.model.NisApiId;

import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.logging.Logger;

/**
 * The default NIS connector.
 */
public class DefaultNisConnector implements PrimaryNisConnector {
	final private static Logger LOGGER = Logger.getLogger(DefaultNisConnector.class.getName());
	private final Supplier<NodeEndpoint> defaultEndpointSupplier;
	private final AsyncNisConnector nisConnector;

	/**
	 * Creates a new default NIS connector.
	 *
	 * @param defaultEndpointSupplier The default node endpoint supplier.
	 * @param nisConnector The async NIS connector.
	 */
	public DefaultNisConnector(
			final Supplier<NodeEndpoint> defaultEndpointSupplier,
			final AsyncNisConnector nisConnector) {
		this.defaultEndpointSupplier = defaultEndpointSupplier;
		this.nisConnector = nisConnector;
	}

	@Override
	public boolean isConnected() {
		// Let's see whether the NIS is answering
		try {
			this.get(NisApiId.NIS_REST_HEARTBEAT, null);
			return true;
		} catch (final NisException e) {
			// if we receive a NisException then it is because NIS is there
			return true;
		} catch (final Exception e) {
			LOGGER.fine(String.format("isConnected is false. Exception received <%s> : <%s>", e.toString(), e.getMessage()));
		}

		return false;
	}

	@Override
	public Deserializer get(final NisApiId apiId, final String query) {
		return ExceptionUtils.propagate(() -> this.nisConnector.getAsync(this.getDefaultEndpoint(), apiId, query).get());
	}

	@Override
	public <T> T forward(final Function<NodeEndpoint, CompletableFuture<T>> request) {
		return ExceptionUtils.propagate(() -> request.apply(this.getDefaultEndpoint()).get());
	}

	@Override
	public Deserializer post(final NisApiId apiId, final HttpPostRequest postRequest) {
		return ExceptionUtils.propagate(() -> this.nisConnector.postAsync(this.getDefaultEndpoint(), apiId, postRequest).get());
	}

	@Override
	public void voidPost(final NisApiId apiId, final HttpPostRequest postRequest) {
		ExceptionUtils.propagateVoid(() -> this.nisConnector.postVoidAsync(this.getDefaultEndpoint(), apiId, postRequest).get());
	}

	private NodeEndpoint getDefaultEndpoint() {
		return this.defaultEndpointSupplier.get();
	}
}
