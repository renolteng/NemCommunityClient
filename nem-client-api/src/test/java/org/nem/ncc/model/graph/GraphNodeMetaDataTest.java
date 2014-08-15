package org.nem.ncc.model.graph;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.test.*;

public class GraphNodeMetaDataTest {

	// region constructor

	@Test
	public void canCreateGraphNodeMetaData() {
		// Arrange:
		final GraphNodeMetaData metaData = GraphUtils.createGraphNodeMetaData(true);

		// Assert:
		Assert.assertThat(metaData.getAddress(), IsEqual.equalTo(Address.fromEncoded("TBLOODZW6W4DUVL4NGAQXHZXFQJLNHPDXHULLHZW")));
		Assert.assertThat(metaData.getPlatform(), IsEqual.equalTo("Linux"));
		Assert.assertThat(metaData.getVersion(), IsEqual.equalTo("0.0.74"));
		Assert.assertThat(metaData.getNodeEndpoint(), IsEqual.equalTo(NodeEndpoint.fromHost("79.220.196.143")));
		Assert.assertThat(metaData.isActive(), IsEqual.equalTo(true));
	}

	// endregion

	// region serialization

	@Test
	public void canRoundTripGraphNodeMetaDataForActiveNode() {
		// Assert:
		assertRoundTrip(true);
	}

	@Test
	public void canRoundTripGraphNodeMetaDataForInactiveNode() {
		// Assert:
		assertRoundTrip(false);
	}

	private static void assertRoundTrip(final boolean isActive) {
		// Arrange:
		final GraphNodeMetaData originalMetaData = GraphUtils.createGraphNodeMetaData(isActive);

		// Act:
		final Deserializer deserializer = Utils.roundtripSerializableEntity(originalMetaData, null);
		final GraphNodeMetaData metaData = new GraphNodeMetaData(deserializer);

		// Assert:
		Assert.assertThat(metaData.getAddress(), IsEqual.equalTo(originalMetaData.getAddress()));
		Assert.assertThat(metaData.getPlatform(), IsEqual.equalTo(originalMetaData.getPlatform()));
		Assert.assertThat(metaData.getVersion(), IsEqual.equalTo(originalMetaData.getVersion()));
		Assert.assertThat(metaData.getNodeEndpoint(), IsEqual.equalTo(originalMetaData.getNodeEndpoint()));
		Assert.assertThat(metaData.isActive(), IsEqual.equalTo(isActive));
	}

	// endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final GraphNodeMetaData meta = createMetaData("a", "p", "v", "127.0.0.1", true);

		// Assert:
		Assert.assertThat(meta, IsEqual.equalTo(createMetaData("a", "p", "v", "127.0.0.1", true)));
		Assert.assertThat(meta, IsEqual.equalTo(createMetaData("a", "p", "v", "127.0.0.1", false)));
		Assert.assertThat(meta, IsNot.not(IsEqual.equalTo(createMetaData("b", "p", "v", "127.0.0.1", true))));
		Assert.assertThat(meta, IsNot.not(IsEqual.equalTo(createMetaData("a", "q", "v", "127.0.0.1", true))));
		Assert.assertThat(meta, IsNot.not(IsEqual.equalTo(createMetaData("a", "p", "r", "127.0.0.1", true))));
		Assert.assertThat(meta, IsNot.not(IsEqual.equalTo(createMetaData("a", "p", "v", "127.0.0.2", true))));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final GraphNodeMetaData meta = createMetaData("a", "p", "v", "127.0.0.1", true);
		final int hashCode = meta.hashCode();

		// Assert:
		Assert.assertThat(hashCode, IsEqual.equalTo(createMetaData("a", "p", "v", "127.0.0.1", true).hashCode()));
		Assert.assertThat(hashCode, IsEqual.equalTo(createMetaData("a", "p", "v", "127.0.0.1", false).hashCode()));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(createMetaData("b", "p", "v", "127.0.0.1", true).hashCode())));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(createMetaData("a", "q", "v", "127.0.0.1", true).hashCode())));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(createMetaData("a", "p", "r", "127.0.0.1", true).hashCode())));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(createMetaData("a", "p", "v", "127.0.0.2", true).hashCode())));
	}

	private static GraphNodeMetaData createMetaData(
			final String address,
			final String platform,
			final String version,
			final String host,
			final boolean isActive) {
		return new GraphNodeMetaData(
				Address.fromEncoded(address),
				platform,
				version,
				NodeEndpoint.fromHost(host),
				isActive);
	}

	//endregion

	//region string representation

	@Test
	public void graphNodeMetaDataStringRepresentationIsCorrect() {
		// Arrange:
		final GraphNodeMetaData metaData = GraphUtils.createGraphNodeMetaData(true);

		// Assert:
		final String expectedResult =
				"address: TBLOODZW6W4DUVL4NGAQXHZXFQJLNHPDXHULLHZW" + System.lineSeparator() +
						"platform: Linux" + System.lineSeparator() +
						"version: 0.0.74" + System.lineSeparator() +
						"endpoint: http://79.220.196.143:7890/" + System.lineSeparator() +
						"active: true";
		Assert.assertThat(metaData.toString(), IsEqual.equalTo(expectedResult));
	}

	//endregion
}
