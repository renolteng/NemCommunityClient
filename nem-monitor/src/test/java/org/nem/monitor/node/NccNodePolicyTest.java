package org.nem.monitor.node;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

import org.hamcrest.core.IsEqual;
import org.junit.*;

import java.nio.file.Paths;

public class NccNodePolicyTest {

	@Test
	public void getEndpointReturnsLocalNccEndpoint() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getEndpoint(), IsEqual.equalTo(new NodeEndpoint("http", "localhost", 8989)));
	}

	@Test
	public void getNodeTypeReturnsNccType() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getNodeType(), IsEqual.equalTo(NemNodeType.NCC));
	}

	@Test
	public void getLockFileReturnsNccLockFile() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getLockFile(), IsEqual.equalTo(Paths.get("my_nem_folder", "ncc.lock").toFile()));
	}

	@Test
	public void mapToUrlPathPrefixesPathWithNccApiPath() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Act:
		final String heartbeatPath = policy.mapToUrlPath(NisApiId.NIS_REST_HEARTBEAT.toString());

		// Assert:
		Assert.assertThat(heartbeatPath, IsEqual.equalTo("ncc/api/heartbeat"));
	}

	@Test
	public void hasBrowserGuiReturnsTrue() {
		// Arrange:
		final NemNodePolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.hasBrowserGui(), IsEqual.equalTo(true));
	}

	private static NemNodePolicy createPolicy() {
		return new NccNodePolicy("my_nem_folder");
	}
}