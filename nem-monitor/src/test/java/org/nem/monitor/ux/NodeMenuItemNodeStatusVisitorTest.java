package org.nem.monitor.ux;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.monitor.node.*;

public class NodeMenuItemNodeStatusVisitorTest {

	@Test
	public void constructorInitializesMenuItemLabels() {
		// Arrange:
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(NemNodeType.NCC);

		// Assert:
		Assert.assertThat(visitor.getStatusMenuItem().getLabel(), IsEqual.equalTo("Connecting to NCC ..."));
		Assert.assertThat(visitor.getActionMenuItem().getLabel(), IsEqual.equalTo("Connecting to NCC ..."));
	}

	@Test
	public void stateChangeToRunningUpdatesMenuItemLabels() {
		// Arrange:
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(NemNodeType.NCC);

		// Act:
		visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);

		// Assert:
		Assert.assertThat(visitor.getStatusMenuItem().getLabel(), IsEqual.equalTo("NCC is running"));
		Assert.assertThat(visitor.getActionMenuItem().getLabel(), IsEqual.equalTo("Stop NCC"));
	}

	@Test
	public void stateChangeToStoppedUpdatesMenuItemLabels() {
		// Arrange:
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(NemNodeType.NCC);

		// Act:
		visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(visitor.getStatusMenuItem().getLabel(), IsEqual.equalTo("NCC is not running"));
		Assert.assertThat(visitor.getActionMenuItem().getLabel(), IsEqual.equalTo("Start NCC"));
	}

	@Test
	public void stateChangeToUnknownUpdatesMenuItemLabels() {
		// Arrange:
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(NemNodeType.NCC);

		// Act:
		visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);
		visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.UNKNOWN);

		// Assert:
		Assert.assertThat(visitor.getStatusMenuItem().getLabel(), IsEqual.equalTo("Connecting to NCC ..."));
		Assert.assertThat(visitor.getActionMenuItem().getLabel(), IsEqual.equalTo("Connecting to NCC ..."));
	}

	@Test
	public void stateChangeForOtherNodeDoesNotUpdateMenuItemLabels() {
		// Arrange:
		final NodeMenuItemNodeStatusVisitor visitor = new NodeMenuItemNodeStatusVisitor(NemNodeType.NCC);

		// Act:
		visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(visitor.getStatusMenuItem().getLabel(), IsEqual.equalTo("Connecting to NCC ..."));
		Assert.assertThat(visitor.getActionMenuItem().getLabel(), IsEqual.equalTo("Connecting to NCC ..."));
	}
}