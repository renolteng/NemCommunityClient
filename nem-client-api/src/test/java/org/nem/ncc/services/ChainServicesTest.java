package org.nem.ncc.services;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.client.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.*;
import org.nem.core.serialization.*;
import org.nem.ncc.model.NisNodeMetaData;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ChainServicesTest {

	//region getLastBlockHeightAsync

	@Test
	public void getChainHeightAsyncReturnsHeightOnSuccess() {
		// Assert:
		final TestContext context = new TestContext();
		ServicesUtils.assertSuccessfulDelegationToConnector(
				endpoint -> context.services.getChainHeightAsync(endpoint).join(),
				context.connector,
				NisApiId.NIS_REST_CHAIN_HEIGHT,
				new BlockHeight(17));
	}

	@Test
	public void getChainHeightAsyncThrowsExceptionOnError() {
		// Assert:
		final TestContext context = new TestContext();
		ServicesUtils.assertFailureDelegationToConnector(
				endpoint -> context.services.getChainHeightAsync(endpoint).join(),
				context.connector,
				NisApiId.NIS_REST_CHAIN_HEIGHT);
	}

	//endregion

	//region getNodeMetaDataAsync

	@Test
	public void getNodeMetaDataAsyncReturnsAppropriateMetaData() {
		// Arrange: set up the target node
		final TestContext context = new TestContext();
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		context.setChainHeightForNode(endpoint, createBlockHeightFuture(5));

		// Arrange: set up the peer nodes
		final List<Node> originalNodes = createThreeTestNodes();
		final NodeCollection collection = new NodeCollection();
		originalNodes.forEach(n -> collection.update(n, NodeStatus.ACTIVE));
		context.setMaxChainHeightForNode(endpoint, createBlockHeightFuture(12));
		Mockito.when(context.networkServices.getNodePeerListAsync(endpoint))
				.thenReturn(CompletableFuture.completedFuture(collection));

		// Act:
		final NisNodeMetaData metaData = context.services.getNodeMetaDataAsync(endpoint).join();

		// Assert:
		Assert.assertThat(metaData.getActivePeers(), IsEqual.equalTo(3));
		Assert.assertThat(metaData.getMaxBlockHeight(), IsEqual.equalTo(new BlockHeight(12)));
		Assert.assertThat(metaData.getNodeBlockHeight(), IsEqual.equalTo(new BlockHeight(5)));
	}

	@Test
	public void getNodeMetaDataAsyncFailsIfChainHeightCannotBeRetrieved() {
		// Arrange: set up the target node
		final TestContext context = new TestContext();
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		context.setChainHeightForNode(endpoint, createExceptionalFuture());

		// Arrange: set up the peer nodes
		Mockito.when(context.networkServices.getNodePeerListAsync(endpoint))
				.thenReturn(CompletableFuture.completedFuture(new NodeCollection()));

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.services.getNodeMetaDataAsync(endpoint).join(),
				FatalPeerException.class);
	}

	@Test
	public void getNodeMetaDataAsyncFailsIfMaxChainHeightCannotBeRetrieved() {
		// Arrange: set up the target node
		final TestContext context = new TestContext();
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		context.setChainHeightForNode(endpoint, createBlockHeightFuture(5));
		context.setMaxChainHeightForNode(endpoint, createExceptionalFuture());

		// Arrange: set up the peer nodes
		Mockito.when(context.networkServices.getNodePeerListAsync(endpoint))
				.thenReturn(createExceptionalFuture().thenApply(NodeCollection::new));

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.services.getNodeMetaDataAsync(endpoint).join(),
				FatalPeerException.class);
	}

	//endregion

	private static List<Node> createThreeTestNodes() {
		return Arrays.asList(
				GraphUtils.createNodeFromHost("10.0.0.1"),
				GraphUtils.createNodeFromHost("10.0.0.2"),
				GraphUtils.createNodeFromHost("10.0.0.3"));
	}

	private static CompletableFuture<Deserializer> createExceptionalFuture() {
		return Utils.createExceptionalFuture(new FatalPeerException("badness"));
	}

	private static CompletableFuture<Deserializer> createBlockHeightFuture(final long height) {
		final JsonSerializer serializer = new JsonSerializer();
		BlockHeight.writeTo(serializer, "height", new BlockHeight(height));
		final Deserializer deserializer = Utils.createDeserializer(serializer.getObject());
		return CompletableFuture.completedFuture(deserializer);
	}

	private static class TestContext {
		private final AsyncNisConnector connector = Mockito.mock(AsyncNisConnector.class);
		private final NetworkServices networkServices = Mockito.mock(NetworkServices.class);
		private final ChainServices services = new ChainServices(this.connector, this.networkServices);

		private void setChainHeightForNode(final NodeEndpoint endpoint, final CompletableFuture<Deserializer> future) {
			Mockito.when(this.connector.getAsync(endpoint, NisApiId.NIS_REST_CHAIN_HEIGHT, null))
					.thenReturn(future);
		}

		private void setMaxChainHeightForNode(final NodeEndpoint endpoint, final CompletableFuture<Deserializer> future) {
			Mockito.when(this.connector.getAsync(endpoint, NisApiId.NIS_REST_ACTIVE_PEERS_MAX_CHAIN_HEIGHT, null))
					.thenReturn(future);
		}
	}
}