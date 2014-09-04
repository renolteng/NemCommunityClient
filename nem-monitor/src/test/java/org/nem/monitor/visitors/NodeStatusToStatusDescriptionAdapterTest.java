package org.nem.monitor.visitors;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import java.util.function.Consumer;

public class NodeStatusToStatusDescriptionAdapterTest {

	@Test
	public void constructorInitializesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("Connecting to NIS ..."));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NIS ..."));
	}

	@Test
	public void stateChangeToStartingUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.STARTING);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NIS is starting"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NIS ..."));
	}

	@Test
	public void stateChangeToRunningUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.RUNNING);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NIS is running"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Stop NIS"));
	}

	@Test
	public void stateChangeToBootedUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.BOOTED);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NIS is running and is booted"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Stop NIS"));
	}

	@Test
	public void stateChangeToSynchronizedUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.SYNCHRONIZED);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NIS is running and is synchronized"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Stop NIS"));
	}

	@Test
	public void stateChangeToStoppedUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("NIS is not running"));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Start NIS"));
	}

	@Test
	public void stateChangeToUnknownUpdatesDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.STOPPED);
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.UNKNOWN);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("Connecting to NIS ..."));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NIS ..."));
	}

	@Test
	public void stateChangeForOtherNodeDoesNotUpdateDescription() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescription().getStatusMessage(), IsEqual.equalTo("Connecting to NIS ..."));
		Assert.assertThat(context.getLastDescription().getActionMessage(), IsEqual.equalTo("Connecting to NIS ..."));
	}

	private static class TestContext {
		@SuppressWarnings("unchecked")
		private final Consumer<NodeStatusDescription> statusDescriptionConsumer = Mockito.mock(Consumer.class);
		private final NodeStatusVisitor visitor = new NodeStatusToStatusDescriptionAdapter(NemNodeType.NIS, this.statusDescriptionConsumer);

		private NodeStatusDescription getLastDescription() {
			final ArgumentCaptor<NodeStatusDescription> descriptionCaptor = ArgumentCaptor.forClass(NodeStatusDescription.class);
			Mockito.verify(this.statusDescriptionConsumer, Mockito.atLeastOnce()).accept(descriptionCaptor.capture());
			return descriptionCaptor.getValue();
		}
	}
}