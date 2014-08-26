package org.nem.monitor.node;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

import java.nio.file.Paths;

public class NisNodePolicyTest {

	@Test
	public void getEndpointReturnsLocalNisEndpoint() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getEndpoint(), IsEqual.equalTo(new NodeEndpoint("http", "localhost", 7890)));
	}

	@Test
	public void getNodeTypeReturnsNisType() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getNodeType(), IsEqual.equalTo(NemNodeType.NIS));
	}

	@Test
	public void getLockFileReturnsNisLockFile() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getLockFile(), IsEqual.equalTo(Paths.get("my_nem_folder", "nis.lock").toFile()));
	}

	@Test
	public void mapToUrlPathDoesNotAdjustApiPath() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Act:
		final String heartbeatPath = policy.mapToUrlPath(NisApiId.NIS_REST_HEARTBEAT);

		// Assert:
		Assert.assertThat(heartbeatPath, IsEqual.equalTo("/heartbeat"));
	}

	@Test
	public void hasBrowserGuiReturnsFalse() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.hasBrowserGui(), IsEqual.equalTo(false));
	}

	private static NemNodePolicy createPolicy() {
		return new NisNodePolicy("my_nem_folder");
	}
}