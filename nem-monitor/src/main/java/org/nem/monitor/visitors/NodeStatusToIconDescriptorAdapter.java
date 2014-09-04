package org.nem.monitor.visitors;

import org.nem.monitor.node.*;

import java.util.*;
import java.util.function.*;

/**
 * NodeStatusVisitor that maps status changes to appropriate icons.
 */
public class NodeStatusToIconDescriptorAdapter implements NodeStatusVisitor {
	private static final List<BiFunction<NemNodeStatus, NemNodeStatus, IconDescriptor>> iconDescriptorPredicates = Arrays.asList(
			(nccStatus, nisStatus) -> (NemNodeStatus.STOPPED == nisStatus && NemNodeStatus.BOOTING == nccStatus)
					? new IconDescriptor("ncc_booting.png", "NIS is stopped, NCC is booting")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.BOOTING == nisStatus && NemNodeStatus.STOPPED == nccStatus)
					? new IconDescriptor("nis_booting.png", "NIS is booting, NCC is stopped")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.BOOTING == nisStatus && NemNodeStatus.BOOTING == nccStatus)
					? new IconDescriptor("nis_booting_ncc_booting.png", "NIS is booting, NCC is booting")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.STOPPED == nisStatus && NemNodeStatus.RUNNING == nccStatus)
					? new IconDescriptor("ncc_running.png", "NIS is stopped, NCC is running")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.RUNNING == nisStatus && NemNodeStatus.STOPPED == nccStatus)
					? new IconDescriptor("nis_running.png", "NIS is running, NCC is stopped")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.RUNNING == nisStatus && NemNodeStatus.RUNNING == nccStatus)
					? new IconDescriptor("all_good.png", "Both NCC and NIS are running")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.BOOTING == nisStatus && NemNodeStatus.RUNNING == nccStatus)
					? new IconDescriptor("nis_booting_ncc_running.png", "NIS is booting, NCC is running")
					: null,
			(nccStatus, nisStatus) -> (NemNodeStatus.RUNNING == nisStatus && NemNodeStatus.BOOTING == nccStatus)
					? new IconDescriptor("nis_running_ncc_booting.png", "NIS is running, NCC is booting")
					: null);

	private final Consumer<IconDescriptor> iconDescriptorConsumer;
	private NemNodeStatus nccStatus;
	private NemNodeStatus nisStatus;

	/**
	 * Creates a new visitor.
	 *
	 * @param iconDescriptorConsumer The function to call when an icon change is triggered.
	 */
	public NodeStatusToIconDescriptorAdapter(final Consumer<IconDescriptor> iconDescriptorConsumer) {
		this.iconDescriptorConsumer = iconDescriptorConsumer;

		this.notifyStatus(NemNodeType.NIS, NemNodeStatus.UNKNOWN);
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemNodeStatus status) {
		switch (type) {
			case NCC:
				this.nccStatus = status;
				break;

			case NIS:
				this.nisStatus = status;
				break;
		}

		this.iconDescriptorConsumer.accept(this.getDescriptor());
	}

	private IconDescriptor getDescriptor() {
		for (final BiFunction<NemNodeStatus, NemNodeStatus, IconDescriptor> func : iconDescriptorPredicates) {
			final IconDescriptor descriptor = func.apply(this.nccStatus, this.nisStatus);
			if (null != descriptor) {
				return descriptor;
			}
		}

		return new IconDescriptor("all_bad.png", "Neither NCC nor NIS is running");
	}
}
