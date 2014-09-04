package org.nem.monitor.visitors;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import java.util.*;
import java.util.function.Consumer;

public class NodeStatusToIconDescriptorAdapterTest {
	private static final IconDescriptor ALL_BAD_DESCRIPTOR = new IconDescriptor("all_bad.png", "Neither NCC nor NIS is running");
	private static final IconDescriptor NIS_STARTING_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("nis_starting.png", "NIS is booting, NCC is stopped");
	private static final IconDescriptor NIS_RUNNING_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("nis_running.png", "NIS is running (local node not booted), NCC is stopped");
	private static final IconDescriptor NIS_BOOTED_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("nis_running_node_booted_ncc_stopped.png", "NIS is running (local node booted), NCC is stopped");
	private static final IconDescriptor NIS_SYNCHRONIZED_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("nis_running_node_synchronized_ncc_stopped.png", "NIS is running and is synchronized, NCC is stopped");
	private static final IconDescriptor NIS_STOPPED_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("ncc_starting.png", "NIS is stopped, NCC is starting");
	private static final IconDescriptor NIS_STARTING_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("nis_starting_ncc_starting.png", "NIS is starting, NCC is starting");
	private static final IconDescriptor NIS_RUNNING_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("nis_running_ncc_starting.png", "NIS is running (local node not booted), NCC is booting");
	private static final IconDescriptor NIS_BOOTED_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("nis_running_node_booted_ncc_starting.png", "NIS is running (local node booted), NCC is starting");
	private static final IconDescriptor NIS_SYNCHRONIZED_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("nis_running_node_synchronized_ncc_starting.png", "NIS is running and is synchronized, NCC is starting");
	private static final IconDescriptor NIS_STOPPED_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("ncc_running.png", "NIS is stopped, NCC is running");
	private static final IconDescriptor NIS_STARTING_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("nis_starting_ncc_running.png", "NIS is starting, NCC is running");
	private static final IconDescriptor NIS_RUNNING_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("nis_running_ncc_running.png", "NIS is running (local node not booted), NCC is running");
	private static final IconDescriptor NIS_BOOTED_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("nis_running_node_booted_ncc_running.png", "NIS is running (local node booted), NCC is running");
	private static final IconDescriptor NIS_SYNCHRONIZED_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("nis_running_node_synchronized_ncc_running.png", "NIS is running and is synchronized, NCC is running");

	@Test
	public void initiallyAllBadIconIsChosen() {
		// Arrange:
		final TestContext context = new TestContext();

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(ALL_BAD_DESCRIPTOR));
	}

	@Test
	public void allGoodIconIsReturnedWhenNccIsRunningAndNisIsSynchronized() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(new StatusPair(NemStatus.RUNNING, NemStatus.SYNCHRONIZED));

		// Assert:
		assertDescriptorForPairs(pairs, NIS_SYNCHRONIZED_NCC_RUNNING_DESCRIPTOR);
	}
	// TODO-CR: 20140904 BR -> J what are these tests for?
/*
	@Test
	@Ignore
	public void nccSTARTINGIconHasHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemStatus.STARTING, NemStatus.UNKNOWN),
				new StatusPair(NemStatus.STARTING, NemStatus.STARTING),
				new StatusPair(NemStatus.STARTING, NemStatus.RUNNING),
				new StatusPair(NemStatus.STARTING, NemStatus.STOPPED));

		// Assert:
		assertDescriptorForPairs(pairs, NCC_STARTING_DESCRIPTOR);
	}

	@Test
	@Ignore
	public void nisSTARTINGIconHasSecondHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemStatus.UNKNOWN, NemStatus.STARTING),
				new StatusPair(NemStatus.RUNNING, NemStatus.STARTING),
				new StatusPair(NemStatus.STOPPED, NemStatus.STARTING));

		// Assert:
		assertDescriptorForPairs(pairs, NIS_STARTING_DESCRIPTOR);
	}

	@Test
	@Ignore
	public void nccRunningIconHasThirdHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemStatus.RUNNING, NemStatus.UNKNOWN),
				new StatusPair(NemStatus.RUNNING, NemStatus.STOPPED));

		// Assert:
		assertDescriptorForPairs(pairs, NCC_RUNNING_DESCRIPTOR);
	}

	@Test
	@Ignore
	public void nisRunningIconHasThirdHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemStatus.UNKNOWN, NemStatus.RUNNING),
				new StatusPair(NemStatus.STOPPED, NemStatus.RUNNING));

		// Assert:
		assertDescriptorForPairs(pairs, NIS_RUNNING_DESCRIPTOR);
	}
*/
	@Test
	public void allBadIconIsDefaultAction() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemStatus.UNKNOWN, NemStatus.UNKNOWN),
				new StatusPair(NemStatus.UNKNOWN, NemStatus.STOPPED),
				new StatusPair(NemStatus.STOPPED, NemStatus.UNKNOWN),
				new StatusPair(NemStatus.STOPPED, NemStatus.STOPPED));

		// Assert:
		assertDescriptorForPairs(pairs, ALL_BAD_DESCRIPTOR);
	}

	private static void assertDescriptorForPairs(final List<StatusPair> pairs, final IconDescriptor expectedDescriptor) {
		// Arrange:
		final TestContext context = new TestContext();

		for (final StatusPair pair : pairs) {
			// Act:
			context.visitor.notifyStatus(NemNodeType.NCC, pair.nccStatus);
			context.visitor.notifyStatus(NemNodeType.NIS, pair.nisStatus);

			// Assert:
			Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(expectedDescriptor));
		}
	}

	private static class StatusPair {
		private final NemStatus nccStatus;
		private final NemStatus nisStatus;

		private StatusPair(final NemStatus nccStatus, final NemStatus nisStatus) {
			this.nccStatus = nccStatus;
			this.nisStatus = nisStatus;
		}
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