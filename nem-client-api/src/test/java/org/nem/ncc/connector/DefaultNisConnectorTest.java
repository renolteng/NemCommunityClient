package org.nem.ncc.connector;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.*;
import org.nem.core.connect.client.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.*;
import org.nem.ncc.test.ExceptionAssert;

import java.util.concurrent.CompletableFuture;
import java.util.function.*;

public class DefaultNisConnectorTest {

	//region isConnected

	@Test
	public void isConnectedReturnsTrueWhenNoNisExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		context.setGetToken(NisApiId.NIS_REST_HEARTBEAT, null, createDeserializerFuture());

		// Act:
		final boolean result = context.connector.isConnected();

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(true));
		context.verifySingleGetRequest(NisApiId.NIS_REST_HEARTBEAT, null);
	}

	@Test
	public void isConnectedReturnsTrueWhenNisExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		context.setGetToken(NisApiId.NIS_REST_HEARTBEAT, null, createNisExceptionFuture());

		// Act:
		final boolean result = context.connector.isConnected();

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(true));
		context.verifySingleGetRequest(NisApiId.NIS_REST_HEARTBEAT, null);
	}

	@Test
	public void isConnectedReturnsFalseWhenOtherExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		context.setGetToken(NisApiId.NIS_REST_HEARTBEAT, null, createOtherExceptionFuture());

		// Act:
		final boolean result = context.connector.isConnected();

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(false));
		context.verifySingleGetRequest(NisApiId.NIS_REST_HEARTBEAT, null);
	}

	//endregion

	//region get

	@Test
	public void getThrowsNisExceptionOnError() {
		// Arrange:
		final TestContext context = new TestContext();
		context.setGetToken(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, null, createNisExceptionFuture());

		// Act:
		ExceptionAssert.assertThrows(
				v -> context.connector.get(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, null),
				NisException.class);

		// Assert:
		context.verifySingleGetRequest(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, null);
	}

	@Test
	public void getReturnsDeserializerOnSuccessWhenQueryStringIsNotProvided() {
		// Assert:
		assertGetReturnsDeserializerOnSuccess(null);
	}

	@Test
	public void getReturnsDeserializerOnSuccessWhenQueryStringIsProvided() {
		// Assert:
		assertGetReturnsDeserializerOnSuccess("foo=1&bar=7");
	}

	private static void assertGetReturnsDeserializerOnSuccess(final String queryString) {
		// Arrange:
		final TestContext context = new TestContext();
		final Deserializer expectedDeserializer = Mockito.mock(Deserializer.class);
		context.setGetToken(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, queryString, createDeserializerFuture(expectedDeserializer));

		// Act:
		final Deserializer deserializer = context.connector.get(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, queryString);

		// Assert:
		Assert.assertThat(deserializer, IsEqual.equalTo(expectedDeserializer));
		context.verifySingleGetRequest(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, queryString);
	}

	//endregion

	//region forward / forwardAsync

	@Test
	public void forwardThrowsNisExceptionOnError() {
		// Assert:
		assertForwardThrowsNisExceptionOnError((connector, func) -> connector.forward(func));
	}

	@Test
	public void forwardAsyncThrowsNisExceptionOnError() {
		// Assert:
		assertForwardThrowsNisExceptionOnError((connector, func) -> ExceptionUtils.propagate(() -> connector.forwardAsync(func).get()));
	}

	private static void assertForwardThrowsNisExceptionOnError(
			final BiFunction<PrimaryNisConnector, Function<NodeEndpoint, CompletableFuture<BlockHeight>>, BlockHeight> forward) {
		// Arrange:
		final TestContext context = new TestContext();
		final Function<NodeEndpoint, CompletableFuture<BlockHeight>> func = createMockFunction();
		Mockito.when(func.apply(context.defaultEndpoint))
				.thenReturn(CompletableFuture.supplyAsync(() -> { throw createNisException(); }));

		// Act:
		ExceptionAssert.assertThrows(
				v -> forward.apply(context.connector, func),
				NisException.class);

		// Assert:
		Mockito.verify(func, Mockito.times(1)).apply(context.defaultEndpoint);
	}

	@Test
	public void forwardReturnsObjectOnSuccess() {
		// Assert:
		assertForwardReturnsObjectOnSuccess((connector, func) -> connector.forward(func));
	}

	@Test
	public void forwardAsyncReturnsObjectOnSuccess() {
		// Assert:
		assertForwardReturnsObjectOnSuccess((connector, func) -> ExceptionUtils.propagate(() -> connector.forwardAsync(func).get()));
	}

	private static void assertForwardReturnsObjectOnSuccess(
			final BiFunction<PrimaryNisConnector, Function<NodeEndpoint, CompletableFuture<BlockHeight>>, BlockHeight> forward) {
		// Arrange:
		final TestContext context = new TestContext();
		final BlockHeight expectedHeight = new BlockHeight(12);
		final Function<NodeEndpoint, CompletableFuture<BlockHeight>> func = createMockFunction();
		Mockito.when(func.apply(context.defaultEndpoint))
				.thenReturn(CompletableFuture.completedFuture(expectedHeight));

		// Act:
		final BlockHeight height = forward.apply(context.connector, func);

		// Assert:
		Assert.assertThat(height, IsEqual.equalTo(expectedHeight));
		Mockito.verify(func, Mockito.times(1)).apply(context.defaultEndpoint);
	}

	@SuppressWarnings("unchecked")
	private static Function<NodeEndpoint, CompletableFuture<BlockHeight>> createMockFunction() {
		return (Function<NodeEndpoint, CompletableFuture<BlockHeight>>)Mockito.mock(Function.class);
	}

	//endregion

	//region post / postVoid

	@Test
	public void postThrowsNisExceptionOnError() {
		// Arrange:
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		context.setPostToken(NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, postRequest, createNisExceptionFuture());

		// Act:
		ExceptionAssert.assertThrows(
				v -> context.connector.post(NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, postRequest),
				NisException.class);

		// Assert:
		context.verifySinglePostRequest(NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, postRequest);
	}

	@Test
	public void postReturnsDeserializerOnSuccess() {
		// Arrange:
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		final Deserializer expectedDeserializer = Mockito.mock(Deserializer.class);
		context.setPostToken(NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, postRequest, createDeserializerFuture(expectedDeserializer));

		// Act:
		final Deserializer deserializer = context.connector.post(NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, postRequest);

		// Assert:
		Assert.assertThat(deserializer, IsEqual.equalTo(expectedDeserializer));
		context.verifySinglePostRequest(NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, postRequest);
	}

	@Test
	public void voidPostThrowsNisExceptionOnError() {
		// Arrange:
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		Mockito.when(context.asyncNisConnector.postVoidAsync(context.defaultEndpoint, NisApiId.NIS_REST_ACCOUNT_LOCK, postRequest))
				.thenReturn(CompletableFuture.runAsync(() -> { throw createNisException(); }));

		// Act:
		ExceptionAssert.assertThrows(
				v -> context.connector.voidPost(NisApiId.NIS_REST_ACCOUNT_LOCK, postRequest),
				NisException.class);

		// Assert:
		context.verifySinglePostVoidRequest(NisApiId.NIS_REST_ACCOUNT_LOCK, postRequest);
	}

	@Test
	public void voidPostReturnsNothingOnSuccess() {
		// Arrange:
		final HttpPostRequest postRequest = Mockito.mock(HttpPostRequest.class);
		final TestContext context = new TestContext();
		Mockito.when(context.asyncNisConnector.postVoidAsync(context.defaultEndpoint, NisApiId.NIS_REST_ACCOUNT_LOCK, postRequest))
				.thenReturn(CompletableFuture.completedFuture(null));

		// Act:
		context.connector.voidPost(NisApiId.NIS_REST_ACCOUNT_LOCK, postRequest);

		// Assert:
		context.verifySinglePostVoidRequest(NisApiId.NIS_REST_ACCOUNT_LOCK, postRequest);
	}

	//endregion

	private static NisException createNisException() {
		return new NisException(new ErrorResponse(TimeInstant.ZERO, "badness", 500));
	}

	private static CompletableFuture<Deserializer> createDeserializerFuture() {
		return createDeserializerFuture(Mockito.mock(Deserializer.class));
	}

	private static CompletableFuture<Deserializer> createDeserializerFuture(final Deserializer deserializer) {
		return CompletableFuture.completedFuture(deserializer);
	}

	private static CompletableFuture<Deserializer> createNisExceptionFuture() {
		return CompletableFuture.supplyAsync(() -> {
			throw createNisException();
		});
	}

	private static CompletableFuture<Deserializer> createOtherExceptionFuture() {
		return CompletableFuture.supplyAsync(() -> {
			throw new NccException(NccException.Code.NO_PUBLIC_KEY);
		});
	}

	private static class TestContext {
		private final NodeEndpoint defaultEndpoint = NodeEndpoint.fromHost("10.0.0.11");
		private final AsyncNisConnector asyncNisConnector = Mockito.mock(AsyncNisConnector.class);
		private final DefaultNisConnector connector;

		private TestContext() {
			// Arrange: when creating the DefaultNisConnector, have the supplier return a different endpoint
			// during its construction than after its construction; this validates against the constructor
			// caching the endpoint
			final int[] flags = new int[] { 0 };
			this.connector = new DefaultNisConnector(
					() -> 0 == flags[0] ? NodeEndpoint.fromHost("10.0.0.90") : this.defaultEndpoint,
					this.asyncNisConnector);
			flags[0] = 1;
		}

		private void setGetToken(final NisApiId apiId, final String query, final CompletableFuture<Deserializer> future) {
			Mockito.when(this.asyncNisConnector.getAsync(this.defaultEndpoint, apiId, query)).thenReturn(future);
		}

		private void setPostToken(final NisApiId apiId, final HttpPostRequest postRequest, final CompletableFuture<Deserializer> future) {
			Mockito.when(this.asyncNisConnector.postAsync(this.defaultEndpoint, apiId, postRequest)).thenReturn(future);
		}

		private void verifySingleGetRequest(final NisApiId apiId, final String query) {
			Mockito.verify(this.asyncNisConnector, Mockito.times(1)).getAsync(this.defaultEndpoint, apiId, query);
		}

		private void verifySinglePostRequest(final NisApiId apiId, final HttpPostRequest postRequest) {
			Mockito.verify(this.asyncNisConnector, Mockito.times(1)).postAsync(this.defaultEndpoint, apiId, postRequest);
		}

		private void verifySinglePostVoidRequest(final NisApiId apiId, final HttpPostRequest postRequest) {
			Mockito.verify(this.asyncNisConnector, Mockito.times(1)).postVoidAsync(this.defaultEndpoint, apiId, postRequest);
		}
	}
}