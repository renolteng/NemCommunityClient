package org.nem.monitor.visitors;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.core.model.NemStatus;
import org.nem.monitor.config.LanguageSupport;
import org.nem.monitor.node.NemNodeType;

import java.util.*;
import java.util.function.Consumer;

public class NodeStatusToIconDescriptorAdapterTest {
	private static final IconDescriptor ALL_BAD_DESCRIPTOR =
			new IconDescriptor("icon_00.png", getDescription("status.nis.is.stopped", "status.ncc.is.stopped"));
	private static final IconDescriptor NIS_STARTING_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("icon_12.png", getDescription("status.nis.is.starting", "status.ncc.is.stopped"));
	private static final IconDescriptor NIS_RUNNING_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("icon_13.png", getDescription("status.nis.is.running", "status.ncc.is.stopped"));
	private static final IconDescriptor NIS_BOOTED_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("icon_14.png", getDescription("status.nis.is.booted", "status.ncc.is.stopped"));
	private static final IconDescriptor NIS_SYNCHRONIZED_NCC_STOPPED_DESCRIPTOR =
			new IconDescriptor("icon_15.png",getDescription("status.nis.is.synchronized", "status.ncc.is.stopped"));
	private static final IconDescriptor NIS_STOPPED_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("icon_21.png", getDescription("status.nis.is.stopped", "status.ncc.is.starting"));
	private static final IconDescriptor NIS_STARTING_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("icon_22.png", getDescription("status.nis.is.starting", "status.ncc.is.starting"));
	private static final IconDescriptor NIS_RUNNING_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("icon_23.png", getDescription("status.nis.is.running", "status.ncc.is.starting"));
	private static final IconDescriptor NIS_BOOTED_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("icon_24.png", getDescription("status.nis.is.booted", "status.ncc.is.starting"));
	private static final IconDescriptor NIS_SYNCHRONIZED_NCC_STARTING_DESCRIPTOR =
			new IconDescriptor("icon_25.png", getDescription("status.nis.is.synchronized", "status.ncc.is.starting"));
	private static final IconDescriptor NIS_STOPPED_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("icon_31.png", getDescription("status.nis.is.stopped", "status.ncc.is.running"));
	private static final IconDescriptor NIS_STARTING_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("icon_32.png", getDescription("status.nis.is.starting", "status.ncc.is.running"));
	private static final IconDescriptor NIS_RUNNING_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("icon_33.png", getDescription("status.nis.is.running", "status.ncc.is.running"));
	private static final IconDescriptor NIS_BOOTED_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("icon_34.png", getDescription("status.nis.is.booted", "status.ncc.is.running"));
	private static final IconDescriptor NIS_SYNCHRONIZED_NCC_RUNNING_DESCRIPTOR =
			new IconDescriptor("icon_35.png", getDescription("status.nis.is.synchronized", "status.ncc.is.running"));

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

	private static String getImageName(final NemStatus nccStatus, final NemStatus nisStatus) {
		return String.format("icon_%d%d.png", nccStatus.getValue(), nisStatus.getValue());
	}

	private static String getDescription(final String msg1, final String msg2) {
		return String.format("%s, %s", LanguageSupport.message(msg1), LanguageSupport.message(msg2));
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