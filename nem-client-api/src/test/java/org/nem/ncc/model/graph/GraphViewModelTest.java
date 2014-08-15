package org.nem.ncc.model.graph;

import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.test.*;

public class GraphViewModelTest {
	// region constructor

	@Test
	public void canCreateGraphViewModel() {
		// Arrange:
		final Graph graph = Mockito.mock(Graph.class);
		final GraphMetaData graphMetaData = Mockito.mock(GraphMetaData.class);

		// Act:
		final GraphViewModel graphViewModel = new GraphViewModel(graph, graphMetaData);

		// Assert:
		Assert.assertThat(graphViewModel.getGraph(), IsSame.sameInstance(graph));
		Assert.assertThat(graphViewModel.getMetaData(), IsSame.sameInstance(graphMetaData));
	}

	// endregion

	// region serialization

	@Test
	public void canRoundTripGraphViewModel() {
		// Arrange:
		final GraphViewModel originalGraphViewModel = GraphUtils.createGraphViewModel(null, null);

		// Act
		final Deserializer deserializer = Utils.roundtripSerializableEntity(originalGraphViewModel, null);
		final GraphViewModel graphViewModel = new GraphViewModel(deserializer);

		// Assert:
		Assert.assertThat(graphViewModel, IsEqual.equalTo(originalGraphViewModel));
	}

	// endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final Graph graph1 = Mockito.mock(Graph.class);
		final Graph graph2 = Mockito.mock(Graph.class);
		final GraphMetaData graphMetaData1 = Mockito.mock(GraphMetaData.class);
		final GraphMetaData graphMetaData2 = Mockito.mock(GraphMetaData.class);
		final GraphViewModel graphViewModel = new GraphViewModel(graph1, graphMetaData1);

		// Assert:
		Assert.assertThat(graphViewModel, IsEqual.equalTo(new GraphViewModel(graph1, graphMetaData1)));
		Assert.assertThat(graphViewModel, IsNot.not(IsEqual.equalTo(new GraphViewModel(graph2, graphMetaData1))));
		Assert.assertThat(graphViewModel, IsNot.not(IsEqual.equalTo(new GraphViewModel(graph1, graphMetaData2))));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final Graph graph1 = Mockito.mock(Graph.class);
		final Graph graph2 = Mockito.mock(Graph.class);
		final GraphMetaData graphMetaData1 = Mockito.mock(GraphMetaData.class);
		final GraphMetaData graphMetaData2 = Mockito.mock(GraphMetaData.class);
		final GraphViewModel graphViewModel = new GraphViewModel(graph1, graphMetaData1);
		final int hashCode = graphViewModel.hashCode();

		// Assert:
		Assert.assertThat(hashCode, IsEqual.equalTo(new GraphViewModel(graph1, graphMetaData1).hashCode()));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(new GraphViewModel(graph2, graphMetaData1).hashCode())));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(new GraphViewModel(graph1, graphMetaData2).hashCode())));
	}

	//endregion

	//region string representation

	@Test
	public void graphViewModelStringRepresentationIsCorrect() {
		// Arrange:
		final Graph graph = Mockito.mock(Graph.class);
		final GraphMetaData graphMetaData = Mockito.mock(GraphMetaData.class);
		final GraphViewModel graphViewModel = new GraphViewModel(graph, graphMetaData);

		Mockito.when(graph.toString()).thenReturn("{graph-string}");
		Mockito.when(graphMetaData.toString()).thenReturn("{graphMetaData-string}");

		// Act:
		final String result = graphViewModel.toString();

		// Assert:
		final String expectedResult = String.format(
				"[graph]%s{graph-string}%s[meta]%s{graphMetaData-string}",
				System.lineSeparator(),
				System.lineSeparator(),
				System.lineSeparator());
		Assert.assertThat(result, IsEqual.equalTo(expectedResult));
	}

	//endregion
}
