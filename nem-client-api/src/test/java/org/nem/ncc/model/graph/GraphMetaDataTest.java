package org.nem.ncc.model.graph;

import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.test.Utils;

import java.util.*;
import java.util.function.BiConsumer;

public class GraphMetaDataTest {

	// region constructor

	@Test
	public void canCreateGraphMetaData() {
		// Act:
		final GraphMetaData graphMetaData = new GraphMetaData();

		// Assert:
		Assert.assertThat(graphMetaData.size(), IsEqual.equalTo(0));
	}

	// endregion

	// region add

	@Test
	public void canAddGraphNodeMetaData() {
		// Assert:
		assertAddMultiple((graphMetaData, nodeMetaDataList) -> nodeMetaDataList.forEach(graphMetaData::add));
	}

	@Test
	public void canAddMultipleGraphNodeMetaData() {
		// Assert:
		assertAddMultiple((graphMetaData, nodeMetaDataList) -> graphMetaData.addAll(nodeMetaDataList));
	}

	private static void assertAddMultiple(final BiConsumer<GraphMetaData, Collection<GraphNodeMetaData>> consumer) {
		// Arrange:
		final GraphMetaData graphMetaData = new GraphMetaData();
		final List<GraphNodeMetaData> nodeMetaDataList = Arrays.asList(
				createNodeMetaDataWithAddress("Alice"),
				createNodeMetaDataWithAddress("Trudy"),
				createNodeMetaDataWithAddress("Bob"));

		// Act:
		consumer.accept(graphMetaData, nodeMetaDataList);

		// Assert:
		Assert.assertThat(graphMetaData.size(), IsEqual.equalTo(3));
		for (final GraphNodeMetaData nodeMetaData : nodeMetaDataList) {
			Assert.assertThat(graphMetaData.get(nodeMetaData.getAddress()), IsEqual.equalTo(nodeMetaData));
		}
	}

	// endregion

	// region serialization

	@Test
	public void canRoundTripGraphMetaData() {
		// Arrange:
		final GraphMetaData originalGraphMetaData = new GraphMetaData();
		final List<GraphNodeMetaData> nodeMetaDataList = Arrays.asList(
				createNodeMetaDataWithAddress("Alice"),
				createNodeMetaDataWithAddress("Trudy"),
				createNodeMetaDataWithAddress("Bob"));
		originalGraphMetaData.addAll(nodeMetaDataList);

		// Act
		final Deserializer deserializer = Utils.roundtripSerializableEntity(originalGraphMetaData, null);
		final GraphMetaData graphMetaData = new GraphMetaData(deserializer);

		// Assert:
		Assert.assertThat(graphMetaData.size(), IsEqual.equalTo(3));
		for (final GraphNodeMetaData nodeMetaData : nodeMetaDataList) {
			Assert.assertThat(
					graphMetaData.get(nodeMetaData.getAddress()).getAddress(),
					IsEqual.equalTo(nodeMetaData.getAddress()));
		}
	}

	// endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final GraphMetaData graphMetaData1 = new GraphMetaData();
		final GraphMetaData graphMetaData2 = new GraphMetaData();
		final GraphMetaData graphMetaData3 = new GraphMetaData();
		final GraphNodeMetaData nodeMetaData1 = createNodeMetaDataWithAddress("Alice");
		final GraphNodeMetaData nodeMetaData2 = createNodeMetaDataWithAddress("Bob");

		// Act
		graphMetaData1.add(nodeMetaData1);
		graphMetaData2.add(nodeMetaData1);
		graphMetaData3.add(nodeMetaData2);

		// Assert:
		Assert.assertThat(graphMetaData1, IsEqual.equalTo(graphMetaData2));
		Assert.assertThat(graphMetaData1, IsNot.not(IsEqual.equalTo(graphMetaData3)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final GraphMetaData graphMetaData1 = new GraphMetaData();
		final GraphMetaData graphMetaData2 = new GraphMetaData();
		final GraphMetaData graphMetaData3 = new GraphMetaData();
		final GraphNodeMetaData nodeMetaData1 = createNodeMetaDataWithAddress("Alice");
		final GraphNodeMetaData nodeMetaData2 = createNodeMetaDataWithAddress("Bob");

		// Act
		graphMetaData1.add(nodeMetaData1);
		graphMetaData2.add(nodeMetaData1);
		graphMetaData3.add(nodeMetaData2);

		// Assert:
		Assert.assertThat(graphMetaData1.hashCode(), IsEqual.equalTo(graphMetaData2.hashCode()));
		Assert.assertThat(graphMetaData1.hashCode(), IsNot.not(IsEqual.equalTo(graphMetaData3.hashCode())));
	}

	//endregion

	//region string representation

	@Test
	public void graphMetaDataStringRepresentationIsCorrect() {
		// Arrange
		final GraphMetaData graphMetaData = new GraphMetaData();

		// Act
		graphMetaData.add(createNodeMetaDataWithAddress("Alice"));
		graphMetaData.add(createNodeMetaDataWithAddress("Trudy"));
		graphMetaData.add(createNodeMetaDataWithAddress("Bob"));

		// Assert:
		final String expectedResult =
				"[3 node meta data]" + System.lineSeparator() +
						"{Alice}" + System.lineSeparator() +
						"{Bob}" + System.lineSeparator() +
						"{Trudy}";
		Assert.assertThat(graphMetaData.toString(), IsEqual.equalTo(expectedResult));
	}

	//endregion

	private static GraphNodeMetaData createNodeMetaDataWithAddress(final String address) {
		final GraphNodeMetaData metaData = Mockito.spy(new GraphNodeMetaData(
				Address.fromEncoded(address),
				"Windows",
				"1.0.0",
				NodeEndpoint.fromHost("localhost"),
				true));
		Mockito.when(metaData.toString()).thenReturn(String.format("{%s}", address));
		return metaData;
	}
}
