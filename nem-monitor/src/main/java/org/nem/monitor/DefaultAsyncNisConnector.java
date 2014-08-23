//package org.nem.monitor;
//
//import org.nem.core.connect.*;
//import org.nem.core.node.NodeEndpoint;
//import org.nem.core.serialization.*;
//import org.nem.core.utils.ExceptionUtils;
//
//import java.net.*;
//import java.util.concurrent.CompletableFuture;
//
///**
// * A default AsyncNisConnector implementation
// */
//public class DefaultAsyncNisConnector implements AsyncNisConnector {
//	private final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient;
//	private HttpErrorResponseDeserializerUnionStrategy httpDeserializerResponseStrategy;
//
//	/**
//	 * Creates a new default NIS connector.
//	 *
//	 * @param httpClient The HTTP client.
//	 */
//	public DefaultAsyncNisConnector(final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient) {
//		this.httpClient = httpClient;
//	}
//
//	/**
//	 * Sets the account lookup associated with this connector.
//	 * This allows address to automatically be deserialized into accounts.
//	 *
//	 * @param accountLookup The account lookup to use.
//	 */
//	public void setAccountLookup(final AccountLookup accountLookup) {
//		this.httpDeserializerResponseStrategy = new HttpErrorResponseDeserializerUnionStrategy(
//				new DeserializationContext(accountLookup));
//	}
//
//	/**
//	 * Gets the response strategy in use.
//	 *
//	 * @return The response strategy.
//	 */
//	public HttpErrorResponseDeserializerUnionStrategy getResponseStrategy() {
//		return this.httpDeserializerResponseStrategy;
//	}
//
//	@Override
//	public CompletableFuture<Deserializer> getAsync(final NodeEndpoint endpoint, final NisApiId apiId, final String query) {
//		final String path = apiId + (null == query ? "" : "?" + query);
//		return ExceptionUtils.propagate(() ->
//				this.httpClient.get(
//						this.createNisUrl(endpoint, path),
//						this.httpDeserializerResponseStrategy)
//						.getFuture()
//						.thenApply(response -> {
//							if (response.hasError()) {
//								throw new RuntimeException();
//							}
//
//							return response.getDeserializer();
//						}));
//	}
//
//	@Override
//	public CompletableFuture<Deserializer> postAsync(final NodeEndpoint endpoint, final NisApiId apiId, final HttpPostRequest postRequest) {
//		return this.postAsyncImpl(endpoint, apiId, postRequest).thenApply(ErrorResponseDeserializerUnion::getDeserializer);
//	}
//
//	@Override
//	public CompletableFuture<Void> postVoidAsync(final NodeEndpoint endpoint, final NisApiId apiId, final HttpPostRequest postRequest) {
//		return this.postAsyncImpl(endpoint, apiId, postRequest).thenApply(response -> null);
//	}
//
//	private CompletableFuture<ErrorResponseDeserializerUnion> postAsyncImpl(final NodeEndpoint endpoint, final NisApiId apiId, final HttpPostRequest postRequest) {
//		return ExceptionUtils.propagate(() ->
//				this.httpClient.post(
//						this.createNisUrl(endpoint, apiId.toString()),
//						postRequest,
//						this.httpDeserializerResponseStrategy)
//						.getFuture()
//						.thenApply(response -> {
//							if (response.hasError()) {
//								throw new RuntimeException();
//							}
//
//							return response;
//						}));
//	}
//
//	private URL createNisUrl(final NodeEndpoint endpoint, final String nisPath) throws MalformedURLException {
//		return new URL(endpoint.getBaseUrl(), nisPath);
//	}
//}
