package org.nem.monitor.visitors;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.monitor.node.*;

import java.util.*;
import java.util.function.Consumer;

public class NodeStatusToIconDescriptorAdapterTest {
	private static final IconDescriptor ALL_BAD_DESCRIPTOR = new IconDescriptor("all_bad.png", "Neither NCC nor NIS is running");
	private static final IconDescriptor NCC_RUNNING_DESCRIPTOR = new IconDescriptor("ncc_running.png", "Only NCC is running");
	private static final IconDescriptor NIS_RUNNING_DESCRIPTOR = new IconDescriptor("nis_running.png", "Only NIS is running");
	private static final IconDescriptor NCC_BOOTING_DESCRIPTOR = new IconDescriptor("ncc_booting.png", "NCC is booting");
	private static final IconDescriptor NIS_BOOTING_DESCRIPTOR = new IconDescriptor("nis_booting.png", "NIS is booting");
	private static final IconDescriptor ALL_GOOD_DESCRIPTOR = new IconDescriptor("all_good.png", "Both NCC and NIS are running");

	@Test
	public void initiallyAllBadIconIsChosen() {
		// Arrange:
		final TestContext context = new TestContext();

		// Assert:
		Assert.assertThat(context.getLastDescriptor(), IsEqual.equalTo(ALL_BAD_DESCRIPTOR));
	}

	@Test
	public void allGoodIconIsReturnedWhenNccAndNisAreBothRunning() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(new StatusPair(NemNodeStatus.RUNNING, NemNodeStatus.RUNNING));

		// Assert:
		assertDescriptorForPairs(pairs, ALL_GOOD_DESCRIPTOR);
	}

	@Test
	public void nccBootingIconHasHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemNodeStatus.BOOTING, NemNodeStatus.UNKNOWN),
				new StatusPair(NemNodeStatus.BOOTING, NemNodeStatus.BOOTING),
				new StatusPair(NemNodeStatus.BOOTING, NemNodeStatus.RUNNING),
				new StatusPair(NemNodeStatus.BOOTING, NemNodeStatus.STOPPED));

		// Assert:
		assertDescriptorForPairs(pairs, NCC_BOOTING_DESCRIPTOR);
	}

	@Test
	public void nisBootingIconHasSecondHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemNodeStatus.UNKNOWN, NemNodeStatus.BOOTING),
				new StatusPair(NemNodeStatus.RUNNING, NemNodeStatus.BOOTING),
				new StatusPair(NemNodeStatus.STOPPED, NemNodeStatus.BOOTING));

		// Assert:
		assertDescriptorForPairs(pairs, NIS_BOOTING_DESCRIPTOR);
	}

	@Test
	public void nccRunningIconHasThirdHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemNodeStatus.RUNNING, NemNodeStatus.UNKNOWN),
				new StatusPair(NemNodeStatus.RUNNING, NemNodeStatus.STOPPED));

		// Assert:
		assertDescriptorForPairs(pairs, NCC_RUNNING_DESCRIPTOR);
	}

	@Test
	public void nisRunningIconHasThirdHighestPrecedence() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemNodeStatus.UNKNOWN, NemNodeStatus.RUNNING),
				new StatusPair(NemNodeStatus.STOPPED, NemNodeStatus.RUNNING));

		// Assert:
		assertDescriptorForPairs(pairs, NIS_RUNNING_DESCRIPTOR);
	}

	@Test
	public void allBadIconIsDefaultAction() {
		// Arrange:
		final List<StatusPair> pairs = Arrays.asList(
				new StatusPair(NemNodeStatus.UNKNOWN, NemNodeStatus.UNKNOWN),
				new StatusPair(NemNodeStatus.UNKNOWN, NemNodeStatus.STOPPED),
				new StatusPair(NemNodeStatus.STOPPED, NemNodeStatus.UNKNOWN),
				new StatusPair(NemNodeStatus.STOPPED, NemNodeStatus.STOPPED));

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
		private final NemNodeStatus nccStatus;
		private final NemNodeStatus nisStatus;

		private StatusPair(final NemNodeStatus nccStatus, final NemNodeStatus nisStatus) {
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