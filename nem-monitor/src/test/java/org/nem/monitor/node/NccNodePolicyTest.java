package org.nem.monitor.node;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

public class NccNodePolicyTest {

	@Test
	public void getEndpointReturnsLocalNccEndpoint() {
		// Arrange:
		final NemNodePolicy policy = new NccNodePolicy();

		// Assert:
		Assert.assertThat(policy.getEndpoint(), IsEqual.equalTo(new NodeEndpoint("http", "localhost", 8989)));
	}

	@Test
	public void getNodeTypeReturnsNccType() {
		// Arrange:
		final NemNodePolicy policy = new NccNodePolicy();

		// Assert:
		Assert.assertThat(policy.getNodeType(), IsEqual.equalTo(NemNodeType.NCC));
	}

	@Test
	public void mapToUrlPathPrefixesPathWithNccApiPath() {
		// Arrange:
		final NemNodePolicy policy = new NccNodePolicy();

		// Act:
		final String heartbeatPath = policy.mapToUrlPath(NisApiId.NIS_REST_HEARTBEAT);

		// Assert:
		Assert.assertThat(heartbeatPath, IsEqual.equalTo("ncc/api/heartbeat"));
	}

	@Test
	public void hasBrowserGuiReturnsTrue() {
		// Arrange:
		final NemNodePolicy policy = new NccNodePolicy();

		// Assert:
		Assert.assertThat(policy.hasBrowserGui(), IsEqual.equalTo(true));
	}
}