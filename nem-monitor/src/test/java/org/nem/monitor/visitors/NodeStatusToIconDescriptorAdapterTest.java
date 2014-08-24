package org.nem.monitor.visitors;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.monitor.node.*;

import java.util.function.Consumer;

public class NodeStatusToIconDescriptorAdapterTest {
	private static final IconDescriptor ALL_BAD_DESCRIPTOR = new IconDescriptor("all_bad.png", "Neither NCC nor NIS is running");
	private static final IconDescriptor NCC_ONLY_DESCRIPTOR = new IconDescriptor("ncc_only.png", "Only NCC is running");
	private static final IconDescriptor NIS_ONLY_DESCRIPTOR = new IconDescriptor("nis_online.png", "Only NIS is running");
	private static final IconDescriptor ALL_GOOD_DESCRIPTOR = new IconDescriptor("all_good.png", "Both NCC and NIS are running");

	@Test
	public void constructorInitializesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(ALL_BAD_DESCRIPTOR));
	}

	@Test
	public void connectingToNccNodeUpdatesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(NCC_ONLY_DESCRIPTOR));
	}

	@Test
	public void connectingToNisNodeUpdatesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.RUNNING);

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(NIS_ONLY_DESCRIPTOR));
	}

	@Test
	public void connectingToNccAndNisNodesUpdatesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.RUNNING);

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(ALL_GOOD_DESCRIPTOR));
	}

	@Test
	public void disconnectingFromNccNodeUpdatesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(NIS_ONLY_DESCRIPTOR));
	}

	@Test
	public void disconnectingFromNisNodeUpdatesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(NCC_ONLY_DESCRIPTOR));
	}

	@Test
	public void disconnectingFromNccAndNisNodesUpdatesIconDescriptor() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.STOPPED);

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(ALL_BAD_DESCRIPTOR));
	}

	private static class TestContext {
		@SuppressWarnings("unchecked")
		private final Consumer<IconDescriptor> imageDescriptorConsumer = Mockito.mock(Consumer.class);
		private final NodeStatusVisitor visitor = new NodeStatusToIconDescriptorAdapter(this.imageDescriptorConsumer);

		private IconDescriptor getLastDescriptor() {
			final ArgumentCaptor<IconDescriptor> descriptorCaptor = ArgumentCaptor.forClass(IconDescriptor.class);
			Mockito.verify(this.imageDescriptorConsumer, Mockito.atLeastOnce()).accept(descriptorCaptor.capture());
			return descriptorCaptor.getValue();
		}
	}
}