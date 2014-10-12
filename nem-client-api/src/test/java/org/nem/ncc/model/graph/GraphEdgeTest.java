package org.nem.ncc.model.graph;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.test.*;

public class GraphEdgeTest {

	//region constructor

	@Test
	public void canCreateGraphEdge() {
		// Arrange:
		final GraphNode source = GraphUtils.createGraphNodeWithId("alice");
		final GraphNode target = GraphUtils.createGraphNodeWithId("bob");

		// Act:
		final GraphEdge graphEdge = new GraphEdge(source, target);

		// Assert:
		Assert.assertThat(graphEdge.getId(), IsEqual.equalTo("ALICE-BOB"));
		Assert.assertThat(graphEdge.getSource(), IsSame.sameInstance(source));
		Assert.assertThat(graphEdge.getTarget(), IsSame.sameInstance(target));
	}

	//endregion

	// region serialization

	@Test
	public void canRoundTripGraphEdge() {
		// Arrange:
		final GraphNode source = GraphUtils.createGraphNode("alice");
		final GraphNode target = GraphUtils.createGraphNode("bob");
		final GraphEdge originalGraphEdge = new GraphEdge(source, target);

		// Act
		final Deserializer deserializer = Utils.roundtripSerializableEntity(originalGraphEdge, null);
		final GraphEdge graphEdge = new GraphEdge(deserializer);

		// Assert:
		Assert.assertThat(graphEdge.getId(), IsEqual.equalTo(originalGraphEdge.getId()));
		Assert.assertThat(graphEdge.getSource(), IsEqual.equalTo(originalGraphEdge.getSource()));
		Assert.assertThat(graphEdge.getTarget(), IsEqual.equalTo(originalGraphEdge.getTarget()));
	}

	// endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final GraphNode source = GraphUtils.createGraphNodeWithId("s");
		final GraphNode target = GraphUtils.createGraphNodeWithId("t");
		final GraphNode other = GraphUtils.createGraphNodeWithId("o");
		final GraphEdge graphEdge = new GraphEdge(source, target);

		// Assert:
		Assert.assertThat(graphEdge, IsEqual.equalTo(new GraphEdge(source, target)));
		Assert.assertThat(graphEdge, IsNot.not(IsEqual.equalTo(new GraphEdge(target, source))));
		Assert.assertThat(graphEdge, IsNot.not(IsEqual.equalTo(new GraphEdge(source, other))));
		Assert.assertThat(graphEdge, IsNot.not(IsEqual.equalTo(new GraphEdge(other, target))));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final GraphNode source = GraphUtils.createGraphNodeWithId("s");
		final GraphNode target = GraphUtils.createGraphNodeWithId("t");
		final GraphNode other = GraphUtils.createGraphNodeWithId("o");
		final GraphEdge graphEdge = new GraphEdge(source, target);
		final int hashCode = graphEdge.hashCode();

		// Assert:
		Assert.assertThat(hashCode, IsEqual.equalTo(new GraphEdge(source, target).hashCode()));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(new GraphEdge(target, source).hashCode())));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(new GraphEdge(source, other).hashCode())));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(new GraphEdge(other, target).hashCode())));
	}

	//endregion

	//region string representation

	@Test
	public void graphEdgeStringRepresentationIsCorrect() {
		// Arrange:
		final GraphNode source = GraphUtils.createGraphNodeWithId("alice");
		final GraphNode target = GraphUtils.createGraphNodeWithId("bob");

		// Act:
		final GraphEdge graphEdge = new GraphEdge(source, target);

		// Assert:
		Assert.assertThat(graphEdge.toString(), IsEqual.equalTo("edge: ALICE-BOB"));
	}

	//endregion
}
