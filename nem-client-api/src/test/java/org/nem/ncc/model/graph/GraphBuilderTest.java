package org.nem.ncc.model.graph;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.node.*;
import org.nem.ncc.test.GraphUtils;

public class GraphBuilderTest {

	@Test
	public void canAddInactiveNodeToGraph() {
		// Arrange:
		final Node node = GraphUtils.createNodeFromHost("10.0.0.1");

		// Act:
		final GraphBuilder builder = new GraphBuilder();
		builder.addToGraph(node, null);
		final GraphViewModel viewModel = builder.create();
		final ViewModelVerifier verifier = new ViewModelVerifier(viewModel);

		// Assert:
		verifier.assertNumNodes(1);
		verifier.assertNumEdges(0);
		verifier.assertNumMetaData(1);
		verifier.assertNodeExistence(node, false);
	}

	@Test
	public void canAddActiveNodeToGraphWithoutNeighbors() {
		// Arrange:
		final Node node = GraphUtils.createNodeFromHost("10.0.0.1");

		// Act:
		final GraphBuilder builder = new GraphBuilder();
		builder.addToGraph(node, new NodeCollection());
		final GraphViewModel viewModel = builder.create();
		final ViewModelVerifier verifier = new ViewModelVerifier(viewModel);

		// Assert:
		verifier.assertNumNodes(1);
		verifier.assertNumEdges(0);
		verifier.assertNumMetaData(1);
		verifier.assertNodeExistence(node, true);
	}

	@Test
	public void canAddActiveNodeToGraphWithNeighbors() {
		// Arrange:
		final Node node1 = GraphUtils.createNodeFromHost("10.0.0.1");
		final Node node2 = GraphUtils.createNodeFromHost("10.0.0.2");
		final Node node3 = GraphUtils.createNodeFromHost("10.0.0.3");
		final Node node4 = GraphUtils.createNodeFromHost("10.0.0.3");
		final NodeCollection nodes = new NodeCollection();
		nodes.update(node2, NodeStatus.ACTIVE);
		nodes.update(node3, NodeStatus.INACTIVE);
		nodes.update(node4, NodeStatus.ACTIVE);

		// Act:
		final GraphBuilder builder = new GraphBuilder();
		builder.addToGraph(node1, nodes);
		final GraphViewModel viewModel = builder.create();
		final ViewModelVerifier verifier = new ViewModelVerifier(viewModel);

		// Assert:
		verifier.assertNumNodes(4);
		verifier.assertNumEdges(3);
		verifier.assertNumMetaData(4);
		verifier.assertNodeExistence(node1, true);
		verifier.assertNodeExistence(node2, true);
		verifier.assertNodeExistence(node3, false);
		verifier.assertNodeExistence(node4, true);
		verifier.assertEdgeExistence(node1, node2);
		verifier.assertEdgeExistence(node1, node3);
		verifier.assertEdgeExistence(node1, node4);
	}

	private static class ViewModelVerifier {
		private final GraphViewModel viewModel;

		private ViewModelVerifier(final GraphViewModel viewModel) {
			this.viewModel = viewModel;
		}

		private void assertNumNodes(final int expected) {
			Assert.assertThat(
					this.viewModel.getGraph().getNodes().size(),
					IsEqual.equalTo(expected));
		}

		private void assertNumEdges(final int expected) {
			Assert.assertThat(
					this.viewModel.getGraph().getEdges().size(),
					IsEqual.equalTo(expected));
		}

		private void assertNumMetaData(final int expected) {
			Assert.assertThat(
					this.viewModel.getMetaData().size(),
					IsEqual.equalTo(expected));
		}

		private void assertNodeExistence(final Node expectedNode, final boolean expectedStatus) {
			Assert.assertThat(
					this.viewModel.getGraph().getNodes().contains(new GraphNode(expectedNode)),
					IsEqual.equalTo(true));

			final GraphNodeMetaData metaData = this.viewModel.getMetaData().get(expectedNode.getIdentity().getAddress());
			Assert.assertThat(metaData.getAddress(), IsEqual.equalTo(expectedNode.getIdentity().getAddress()));
			Assert.assertThat(metaData.isActive(), IsEqual.equalTo(expectedStatus));
		}

		private void assertEdgeExistence(final Node rhs, final Node lhs) {
			final GraphEdge expectedEdge = new GraphEdge(new GraphNode(rhs), new GraphNode(lhs));
			Assert.assertThat(
					this.viewModel.getGraph().getEdges().contains(expectedEdge),
					IsEqual.equalTo(true));
		}
	}
}
