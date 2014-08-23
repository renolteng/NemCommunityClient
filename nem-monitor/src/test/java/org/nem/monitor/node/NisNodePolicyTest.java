package org.nem.monitor.node;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

public class NisNodePolicyTest {

	@Test
	public void getEndpointReturnsLocalNisEndpoint() {
		// Arrange:
		final NemNodePolicy policy = new NisNodePolicy();

		// Assert:
		Assert.assertThat(policy.getEndpoint(), IsEqual.equalTo(new NodeEndpoint("http", "localhost", 7890)));
	}

	@Test
	public void getNodeTypeReturnsNisType() {
		// Arrange:
		final NemNodePolicy policy = new NisNodePolicy();

		// Assert:
		Assert.assertThat(policy.getNodeType(), IsEqual.equalTo(NemNodeType.NIS));
	}

	@Test
	public void mapToUrlPathDoesNotAdjustApiPath() {
		// Arrange:
		final NemNodePolicy policy = new NisNodePolicy();

		// Act:
		final String heartbeatPath = policy.mapToUrlPath(NisApiId.NIS_REST_HEARTBEAT);

		// Assert:
		Assert.assertThat(heartbeatPath, IsEqual.equalTo("/heartbeat"));
	}
}