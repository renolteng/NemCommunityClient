package org.nem.ncc.services;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.*;
import org.nem.core.serialization.*;
import org.nem.ncc.connector.SimpleNisConnector;
import org.nem.ncc.model.*;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ChainServicesTest {

	//region getLastBlockHeightAsync

	@Test
	public void getLastBlockHeightAsyncReturnsNodeOnSuccess() {
		// Assert:
		final TestContext context = new TestContext();
		ServicesUtils.assertSuccessfulDelegationToConnector(
				endpoint -> context.services.getLastBlockHeightAsync(endpoint).join(),
				context.connector,
				NisApiId.NIS_REST_CHAIN_LAST_BLOCK,
				new BlockHeight(17));
	}

	@Test
	public void getLastBlockHeightAsyncThrowsExceptionOnError() {
		// Assert:
		final TestContext context = new TestContext();
		ServicesUtils.assertFailureDelegationToConnector(
				endpoint -> context.services.getLastBlockHeightAsync(endpoint).join(),
				context.connector,
				NisApiId.NIS_REST_CHAIN_LAST_BLOCK);
	}

	//endregion

	//region getMaxBlockHeightAsync

	@Test
	public void getMaxBlockHeightAsyncReturnsMaxHeightOnSuccess() {
		// Arrange:
		final TestContext context = new TestContext();

		final List<Node> originalNodes = createThreeTestNodes();
		context.setLastBlockForNode(originalNodes.get(0), createBlockHeightFuture(7));
		context.setLastBlockForNode(originalNodes.get(1), createBlockHeightFuture(12));
		context.setLastBlockForNode(originalNodes.get(2), createBlockHeightFuture(3));

		// Act:
		final BlockHeight maxBlockHeight =
				context.services.getMaxBlockHeightAsync(nodesToEndpoints(originalNodes)).join();

		// Assert:
		Assert.assertThat(maxBlockHeight, IsEqual.equalTo(new BlockHeight(12)));

		context.verifyNumLastBlockRequests(3);
		for (final Node node : originalNodes) {
			context.verifySingleLastBlockRequest(node.getEndpoint());
		}
	}

	@Test
	public void getMaxBlockHeightAsyncIgnoresFailedNodes() {
		// Arrange:
		final TestContext context = new TestContext();

		final List<Node> originalNodes = createThreeTestNodes();
		context.setLastBlockForNode(originalNodes.get(0), createExceptionalFuture());
		context.setLastBlockForNode(originalNodes.get(1), createBlockHeightFuture(12));
		context.setLastBlockForNode(originalNodes.get(2), createExceptionalFuture());

		// Act:
		final BlockHeight maxBlockHeight =
				context.services.getMaxBlockHeightAsync(nodesToEndpoints(originalNodes)).join();

		// Assert:
		Assert.assertThat(maxBlockHeight, IsEqual.equalTo(new BlockHeight(12)));

		context.verifyNumLastBlockRequests(3);
		for (final Node node : originalNodes) {
			context.verifySingleLastBlockRequest(node.getEndpoint());
		}
	}

	@Test
	public void getMaxBlockHeightAsyncReturnsBlockHeightOneIfAllNodesFail() {
		// Arrange:
		final TestContext context = new TestContext();

		final List<Node> originalNodes = createThreeTestNodes();
		context.setLastBlockForNode(originalNodes.get(0), createExceptionalFuture());
		context.setLastBlockForNode(originalNodes.get(1), createExceptionalFuture());
		context.setLastBlockForNode(originalNodes.get(2), createExceptionalFuture());

		// Act:
		final BlockHeight maxBlockHeight =
				context.services.getMaxBlockHeightAsync(nodesToEndpoints(originalNodes)).join();

		// Assert:
		Assert.assertThat(maxBlockHeight, IsEqual.equalTo(BlockHeight.ONE));

		context.verifyNumLastBlockRequests(3);
		for (final Node node : originalNodes) {
			context.verifySingleLastBlockRequest(node.getEndpoint());
		}
	}

	//endregion

	//region getNodeMetaDataAsync

	@Test
	public void getNodeMetaDataAsyncReturnsAppropriateMetaData() {
		// Arrange: set up the target node
		final TestContext context = new TestContext();
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		context.setLastBlockForNode(endpoint, createBlockHeightFuture(5));

		// Arrange: set up the peer nodes
		final List<Node> originalNodes = createThreeTestNodes();
		final NodeCollection collection = new NodeCollection();
		originalNodes.forEach(n -> collection.update(n, NodeStatus.ACTIVE));
		context.setLastBlockForNode(originalNodes.get(0), createBlockHeightFuture(7));
		context.setLastBlockForNode(originalNodes.get(1), createBlockHeightFuture(12));
		context.setLastBlockForNode(originalNodes.get(2), createBlockHeightFuture(3));
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
	public void getNodeMetaDataAsyncFailsIfMaxBlockHeightCannotBeRetrieved() {
		// Arrange: set up the target node
		final TestContext context = new TestContext();
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		context.setLastBlockForNode(endpoint, createExceptionalFuture());

		// Arrange: set up the peer nodes
		Mockito.when(context.networkServices.getNodePeerListAsync(endpoint))
				.thenReturn(CompletableFuture.completedFuture(new NodeCollection()));

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.services.getNodeMetaDataAsync(endpoint).join(),
				FatalPeerException.class);
	}

	@Test
	public void getNodeMetaDataAsyncFailsIfNodeBlockHeightCannotBeRetrieved() {
		// Arrange: set up the target node
		final TestContext context = new TestContext();
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		context.setLastBlockForNode(endpoint, createBlockHeightFuture(5));

		// Arrange: set up the peer nodes
		Mockito.when(context.networkServices.getNodePeerListAsync(endpoint))
				.thenReturn(createExceptionalFuture().thenApply(NodeCollection::new));

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> context.services.getNodeMetaDataAsync(endpoint).join(),
				FatalPeerException.class);
	}

	//endregion

	private static Collection<NodeEndpoint> nodesToEndpoints(final Collection<Node> nodes) {
		return nodes.stream().map(Node::getEndpoint).collect(Collectors.toList());
	}

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
		private final SimpleNisConnector connector = Mockito.mock(SimpleNisConnector.class);
		private final NetworkServices networkServices = Mockito.mock(NetworkServices.class);
		private final ChainServices services = new ChainServices(this.connector, this.networkServices);

		private void setLastBlockForNode(final Node node, final CompletableFuture<Deserializer> future) {
			this.setLastBlockForNode(node.getEndpoint(), future);
		}

		private void setLastBlockForNode(final NodeEndpoint endpoint, final CompletableFuture<Deserializer> future) {
			Mockito.when(this.connector.getAsync(endpoint, NisApiId.NIS_REST_CHAIN_LAST_BLOCK, null))
					.thenReturn(future);
		}

		private void verifyNumLastBlockRequests(final int numExpectedRequests) {
			Mockito.verify(this.connector, Mockito.times(numExpectedRequests)).getAsync(
					Mockito.any(),
					Mockito.eq(NisApiId.NIS_REST_CHAIN_LAST_BLOCK),
					Mockito.eq(null));
		}

		private void verifySingleLastBlockRequest(final NodeEndpoint expectedEndpoint) {
			Mockito.verify(this.connector, Mockito.times(1)).getAsync(
					expectedEndpoint,
					NisApiId.NIS_REST_CHAIN_LAST_BLOCK,
					null);
		}
	}
}