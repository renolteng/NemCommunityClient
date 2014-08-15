package org.nem.ncc.connector;

import org.nem.core.connect.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.NisException;
import org.nem.ncc.model.NisApiId;

import java.net.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * The default NIS connector.
 */
public class DefaultNisConnector implements SimpleNisConnector {
	final private static Logger LOGGER = Logger.getLogger(DefaultNisConnector.class.getName());
	private final NodeEndpoint defaultEndpoint;
	private final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient;
	private HttpErrorResponseDeserializerUnionStrategy httpDeserializerResponseStrategy;

	/**
	 * Creates a new default NIS connector.
	 *
	 * @param defaultEndpoint The default node endpoint.
	 * @param httpClient The HTTP client.
	 */
	public DefaultNisConnector(
			final NodeEndpoint defaultEndpoint,
			final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient) {
		this.defaultEndpoint = defaultEndpoint;
		this.httpClient = httpClient;
	}

	/**
	 * Sets the account lookup associated with this connector.
	 * This allows address to automatically be deserialized into accounts.
	 *
	 * @param accountLookup The account lookup to use.
	 */
	public void setAccountLookup(final AccountLookup accountLookup) {
		this.httpDeserializerResponseStrategy = new HttpErrorResponseDeserializerUnionStrategy(
				new DeserializationContext(accountLookup));
	}

	/**
	 * Gets the response strategy in use.
	 *
	 * @return The response strategy.
	 */
	public HttpErrorResponseDeserializerUnionStrategy getResponseStrategy() {
		return this.httpDeserializerResponseStrategy;
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
		return ExceptionUtils.propagate(() -> this.getAsync(this.defaultEndpoint, apiId, query).get());
	}

	@Override
	public CompletableFuture<Deserializer> getAsync(
			final NodeEndpoint endpoint,
			final NisApiId apiId,
			final String query) {
		final String path = apiId + (null == query ? "" : "?" + query);
		return ExceptionUtils.propagate(() ->
				this.httpClient.get(
						this.createNisUrl(endpoint, path),
						this.httpDeserializerResponseStrategy)
						.getFuture()
						.thenApply(response -> {
							if (response.hasError()) {
								throw new NisException(response.getError());
							}

							return response.getDeserializer();
						}));
	}

	@Override
	public Deserializer post(final NisApiId apiId, final HttpPostRequest postRequest) {
		return this.postSync(apiId, postRequest).getDeserializer();
	}

	@Override
	public void voidPost(final NisApiId apiId, final HttpPostRequest postRequest) {
		this.postSync(apiId, postRequest);
	}

	/**
	 * Posts a request a wait for the result.
	 *
	 * @param apiId The api to call.
	 * @param postRequest The post request.
	 * @return A union containing the deserializer in case of success and an error response in case of failure.
	 */
	private ErrorResponseDeserializerUnion postSync(final NisApiId apiId, final HttpPostRequest postRequest) {
		return ExceptionUtils.propagate(() -> {
			final ErrorResponseDeserializerUnion response = this.httpClient.post(
					this.createNisUrl(this.defaultEndpoint, apiId.toString()),
					postRequest,
					this.httpDeserializerResponseStrategy)
					.getFuture()
					.get();
			if (response.hasError()) {
				throw new NisException(response.getError());
			}

			return response;
		});
	}

	/**
	 * Creates an url from a NIS node endpoint that has the specified path.
	 *
	 * @param endpoint The NIS node endpoint.
	 * @param nisPath The path.
	 * @return The url.
	 * @throws MalformedURLException
	 */
	private URL createNisUrl(final NodeEndpoint endpoint, final String nisPath) throws MalformedURLException {
		return new URL(endpoint.getBaseUrl(), nisPath);
	}
}
