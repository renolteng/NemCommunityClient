package org.nem.ncc.connector;

import net.minidev.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.methods.HttpRequestBase;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.*;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.core.time.TimeInstant;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.NisException;
import org.nem.ncc.test.*;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class DefaultAsyncNisConnectorTest {

	//region getAsync

	@Test
	public void getAsyncThrowsNisExceptionOnError() {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.88");
		final URL url = ExceptionUtils.propagate(() -> new URL("http://10.0.0.88:7890/node/info"));
		final TestContext context = new TestContext();
		context.setGetToken(url, createErrorToken());

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.connector.getAsync(endpoint, NisApiId.NIS_REST_NODE_INFO, null).join(),
				NisException.class);

		// Assert:
		context.verifySingleGetRequest(url);
	}

	@Test
	public void getAsyncReturnsDeserializerOnSuccessWhenQueryStringIsNotProvided() {
		// Assert:
		assertGetAsyncReturnsDeserializerOnSuccess(
				NodeEndpoint.fromHost("10.0.0.88"),
				"http://10.0.0.88:7890/node/info",
				null);
	}

	@Test
	public void getAsyncReturnsDeserializerOnSuccessWhenQueryStringIsProvided() {
		// Assert:
		assertGetAsyncReturnsDeserializerOnSuccess(
				NodeEndpoint.fromHost("10.0.0.88"),
				"http://10.0.0.88:7890/node/info?foo=1&bar=7",
				"foo=1&bar=7");
	}

	private static void assertGetAsyncReturnsDeserializerOnSuccess(final NodeEndpoint endpoint, final String urlString, final String queryString) {
		// Arrange:
		final URL url = ExceptionUtils.propagate(() -> new URL(urlString));
		final TestContext context = new TestContext();
		context.setGetToken(url, createSuccessToken());

		// Act:
		final Deserializer deserializer = context.connector.getAsync(endpoint, NisApiId.NIS_REST_NODE_INFO, queryString).join();

		// Assert:
		Assert.assertThat(deserializer, IsNull.notNullValue());
		context.verifySingleGetRequest(url);
	}

	//endregion

	//region postAsync / postVoidAsync

	@Test
	public void postThrowsNisExceptionOnError() {
		// Arrange:
		final URL url = ExceptionUtils.propagate(() -> new URL("http://10.0.0.88:7890/node/info"));
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		context.setPostToken(url, postRequest, createErrorToken());

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.connector.postAsync(NodeEndpoint.fromHost("10.0.0.88"), NisApiId.NIS_REST_NODE_INFO, postRequest).join(),
				NisException.class);

		// Assert:
		context.verifySinglePostRequest(url, postRequest);
	}

	@Test
	public void postReturnsDeserializerOnSuccess() {
		// Arrange:
		final URL url = ExceptionUtils.propagate(() -> new URL("http://10.0.0.88:7890/node/info"));
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		context.setPostToken(url, postRequest, createSuccessToken());

		// Act:
		final Deserializer deserializer = context.connector.postAsync(NodeEndpoint.fromHost("10.0.0.88"), NisApiId.NIS_REST_NODE_INFO, postRequest).join();

		// Assert:
		Assert.assertThat(deserializer, IsNull.notNullValue());
		context.verifySinglePostRequest(url, postRequest);
	}

	@Test
	public void postVoidAsyncThrowsNisExceptionOnError() {
		// Arrange:
		final URL url = ExceptionUtils.propagate(() -> new URL("http://10.0.0.88:7890/node/info"));
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		context.setPostToken(url, postRequest, createErrorToken());

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.connector.postVoidAsync(NodeEndpoint.fromHost("10.0.0.88"), NisApiId.NIS_REST_NODE_INFO, postRequest).join(),
				NisException.class);

		// Assert:
		context.verifySinglePostRequest(url, postRequest);
	}

	@Test
	public void postVoidAsyncReturnsNothingOnSuccess() {
		// Arrange:
		final URL url = ExceptionUtils.propagate(() -> new URL("http://10.0.0.88:7890/node/info"));
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		context.setPostToken(url, postRequest, createSuccessToken());

		// Act:
		context.connector.postVoidAsync(NodeEndpoint.fromHost("10.0.0.88"), NisApiId.NIS_REST_NODE_INFO, postRequest).join();

		// Assert:
		context.verifySinglePostRequest(url, postRequest);
	}

	//endregion

	//region getResponseStrategy

	@Test
	public void getResponseStrategyCreatesDeserializerUsingSpecifiedAccountLookup() throws Exception {
		// Arrange:
		final TestContext context = new TestContext();
		final HttpErrorResponseDeserializerUnionStrategy strategy = context.connector.getResponseStrategy();

		final HttpResponse response = Mockito.mock(HttpResponse.class);
		final StatusLine statusLine = Mockito.mock(StatusLine.class);
		Mockito.when(response.getStatusLine()).thenReturn(statusLine);
		Mockito.when(statusLine.getStatusCode()).thenReturn(200);

		final HttpEntity entity = Mockito.mock(HttpEntity.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(JsonSerializer.serializeToBytes(new MockSerializableEntity()));
		Mockito.when(response.getEntity()).thenReturn(entity);
		Mockito.when(entity.getContent()).thenReturn(inputStream);

		final ErrorResponseDeserializerUnion union = strategy.coerce(Mockito.mock(HttpRequestBase.class), response);

		// Act:
		final DeserializationContext deserializationContext = union.getDeserializer().getContext();

		// Assert:
		final int initialCount = context.accountLookup.getNumFindByIdCalls();
		deserializationContext.findAccountByAddress(Utils.generateRandomAddress());
		Assert.assertThat(context.accountLookup.getNumFindByIdCalls(), IsEqual.equalTo(initialCount + 1));
	}

	//endregion

	@SuppressWarnings("unchecked")
	private static HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> createErrorToken() {
		final HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> token = Mockito.mock(HttpMethodClient.AsyncToken.class);
		final ErrorResponse response = new ErrorResponse(TimeInstant.ZERO, "badness", 500);
		final ErrorResponseDeserializerUnion union = new ErrorResponseDeserializerUnion(
				500,
				JsonSerializer.serializeToJson(response),
				null);
		Mockito.when(token.getFuture()).thenReturn(CompletableFuture.completedFuture(union));
		return token;
	}

	@SuppressWarnings("unchecked")
	private static HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> createSuccessToken() {
		return createSuccessToken(new JSONObject());
	}

	@SuppressWarnings("unchecked")
	private static HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> createSuccessToken(final JSONObject jsonObject) {
		final HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> token = Mockito.mock(HttpMethodClient.AsyncToken.class);
		final ErrorResponseDeserializerUnion union = new ErrorResponseDeserializerUnion(
				200,
				jsonObject,
				null);
		Mockito.when(token.getFuture()).thenReturn(CompletableFuture.completedFuture(union));
		return token;
	}

	private static class TestContext {
		private final HttpMethodClient<ErrorResponseDeserializerUnion> httpClient;
		private final MockAccountLookup accountLookup = new MockAccountLookup();
		private final DefaultAsyncNisConnector connector;

		@SuppressWarnings("unchecked")
		public TestContext() {
			this.httpClient = Mockito.mock(HttpMethodClient.class);
			this.connector = new DefaultAsyncNisConnector(this.httpClient);
			this.connector.setAccountLookup(this.accountLookup);
		}

		private void setGetToken(final URL url, final HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> token) {
			Mockito.when(this.httpClient.get(url, this.connector.getResponseStrategy())).thenReturn(token);
		}

		private void setPostToken(final URL url, final HttpPostRequest postRequest, final HttpMethodClient.AsyncToken<ErrorResponseDeserializerUnion> token) {
			Mockito.when(this.httpClient.post(url, postRequest, this.connector.getResponseStrategy())).thenReturn(token);
		}

		private void verifySingleGetRequest(final URL url) {
			Mockito.verify(this.httpClient, Mockito.times(1)).get(url, this.connector.getResponseStrategy());
		}

		private void verifySinglePostRequest(final URL url, final HttpPostRequest postRequest) {
			Mockito.verify(this.httpClient, Mockito.times(1)).post(url, postRequest, this.connector.getResponseStrategy());
		}
	}

	private static class MockSerializableEntity implements SerializableEntity {
		@Override
		public void serialize(final Serializer serializer) {
		}
	}
}