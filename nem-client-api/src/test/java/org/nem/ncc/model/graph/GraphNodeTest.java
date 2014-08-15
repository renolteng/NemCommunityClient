package org.nem.ncc.model.graph;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;
import org.nem.core.node.*;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.test.*;

public class GraphNodeTest {

	// region constructor

	@Test
	public void canCreateGraphNodeFromId() {
		// Act:
		final Address address = Address.fromEncoded("TBLOODZW6W4DUVL4NGAQXHZXFQJLNHPDXHULLHZW");
		final GraphNode graphNode = new GraphNode(address);

		// Assert:
		Assert.assertThat(graphNode.getId(), IsEqual.equalTo(address));
		Assert.assertThat(graphNode.getLabel(), IsEqual.equalTo(""));
	}

	@Test
	public void canCreateGraphNodeFromNode() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final NodeIdentity identity = new NodeIdentity(keyPair, "bob");
		final Node node = new Node(identity, NodeEndpoint.fromHost("localhost"));

		// Act:
		final GraphNode graphNode = new GraphNode(node);

		// Assert:
		Assert.assertThat(graphNode.getId(), IsEqual.equalTo(identity.getAddress()));
		Assert.assertThat(graphNode.getLabel(), IsEqual.equalTo("bob"));
	}

	// endregion

	// region serialization

	@Test
	public void canRoundTripGraphNode() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final NodeIdentity identity = new NodeIdentity(keyPair, "bob");
		final Node node = new Node(identity, NodeEndpoint.fromHost("localhost"));
		final GraphNode originalGraphNode = new GraphNode(node);

		// Act
		final Deserializer deserializer = Utils.roundtripSerializableEntity(originalGraphNode, null);
		final GraphNode graphNode = new GraphNode(deserializer);

		// Assert:
		Assert.assertThat(graphNode.getId(), IsEqual.equalTo(identity.getAddress()));
		Assert.assertThat(graphNode.getLabel(), IsEqual.equalTo("bob"));
	}

	// endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final KeyPair keyPair = new KeyPair(Utils.generateRandomPublicKey());
		final GraphNode originalGraphNode = new GraphNode(GraphUtils.createNode(keyPair));
		final GraphNode equalGraphNode = new GraphNode(GraphUtils.createNode(keyPair));
		final GraphNode unequalGraphNode = new GraphNode(GraphUtils.createNode());

		// Assert:
		Assert.assertThat(originalGraphNode, IsEqual.equalTo(equalGraphNode));
		Assert.assertThat(originalGraphNode, IsNot.not(IsEqual.equalTo(unequalGraphNode)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final KeyPair keyPair = new KeyPair(Utils.generateRandomPublicKey());
		final GraphNode originalGraphNode = new GraphNode(GraphUtils.createNode(keyPair));
		final GraphNode equalGraphNode = new GraphNode(GraphUtils.createNode(keyPair));
		final GraphNode unequalGraphNode = new GraphNode(GraphUtils.createNode());

		// Assert:
		Assert.assertThat(originalGraphNode.hashCode(), IsEqual.equalTo(equalGraphNode.hashCode()));
		Assert.assertThat(originalGraphNode.hashCode(), IsNot.not(IsEqual.equalTo(unequalGraphNode.hashCode())));
	}

	//endregion

	//region string representation

	@Test
	public void graphNodeStringRepresentationIsCorrect() {
		// Arrange
		final GraphNode graphNode = new GraphNode(Address.fromEncoded("blah"));

		// Assert:
		Assert.assertThat(graphNode.toString(), IsEqual.equalTo("node: BLAH"));
	}

	//endregion
}
