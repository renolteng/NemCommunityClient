package org.nem.monitor.ux;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.monitor.NodeStatusVisitor;
import org.nem.monitor.node.*;

import java.util.function.Consumer;

public class NodeStatusToStatusDescriptionAdapterTest {

	@Test
	public void constructorInitializesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("Connecting to NCC ..."));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NCC ..."));
	}

	@Test
	public void stateChangeToRunningUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NCC is running"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Stop NCC"));
	}

	@Test
	public void stateChangeToStoppedUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NCC is not running"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Start NCC"));
	}

	@Test
	public void stateChangeToUnknownUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.UNKNOWN);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("Connecting to NCC ..."));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NCC ..."));
	}

	@Test
	public void stateChangeForOtherNodeDoesNotUpdateDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("Connecting to NCC ..."));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NCC ..."));
	}

	private static class TestContext {
		@SuppressWarnings("unchecked")
		private final Consumer<NodeStatusDescription> statusDescriptionConsumer = Mockito.mock(Consumer.class);
		private final NodeStatusVisitor visitor = new NodeStatusToStatusDescriptionAdapter(NemNodeType.NCC, this.statusDescriptionConsumer);

		private NodeStatusDescription getLastDescription() {
			final ArgumentCaptor<NodeStatusDescription> descriptionCaptor = ArgumentCaptor.forClass(NodeStatusDescription.class);
			Mockito.verify(this.statusDescriptionConsumer, Mockito.atLeastOnce()).accept(descriptionCaptor.capture());
			return descriptionCaptor.getValue();
		}
	}
}