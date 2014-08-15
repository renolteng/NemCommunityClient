package org.nem.ncc.connector;

import org.nem.core.connect.HttpPostRequest;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.model.NisApiId;

import java.util.concurrent.CompletableFuture;

/**
 * A simple NIS connector.
 */
public interface SimpleNisConnector {

	/**
	 * Gets a value indicating whether or not this a NIS instance is accessible.
	 *
	 * @return true if a NIS instance is accessible
	 */
	public boolean isConnected();

	/**
	 * Gets a response from the specified NIS relative url path.
	 *
	 * @param apiId The api to call.
	 * @param query The get query string or null.
	 * @return The result.
	 */
	public Deserializer get(final NisApiId apiId, final String query);

	/**
	 * Gets a response from the specified NIS relative url path (asynchronously).
	 *
	 * @param endpoint The NIS endpoint.
	 * @param apiId The api to call.
	 * @param query The get query string or null.
	 * @return The result.
	 */
	public CompletableFuture<Deserializer> getAsync(
			final NodeEndpoint endpoint,
			final NisApiId apiId,
			final String query);

	/**
	 * Posts a request to the specified NIS relative url path.
	 *
	 * @param apiId The api to call.
	 * @param postRequest The request data.
	 * @return The result.
	 */
	public Deserializer post(final NisApiId apiId, final HttpPostRequest postRequest);

	/**
	 * Posts a request to the specified NIS relative url path.
	 *
	 * @param apiId The api to call.
	 * @param postRequest The request data.
	 */
	public void voidPost(final NisApiId apiId, final HttpPostRequest postRequest);
}
