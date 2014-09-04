package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import java.util.*;
import java.util.function.*;

/**
 * NodeStatusVisitor that maps status changes to appropriate icons.
 */
public class NodeStatusToIconDescriptorAdapter implements NodeStatusVisitor {
	private static final List<BiFunction<NemStatus, NemStatus, IconDescriptor>> iconDescriptorPredicates = Arrays.asList(
			(nccStatus, nisStatus) -> (NemStatus.STARTING == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor("nis_starting.png", "NIS is booting, NCC is stopped")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.RUNNING == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor("nis_running.png", "NIS is running (local node not booted), NCC is stopped")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.BOOTED == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor("nis_running_node_booted_ncc_stopped.png", "NIS is running (local node booted), NCC is stopped")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.SYNCHRONIZED == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor("nis_running_node_synchronized_ncc_stopped.png", "NIS is running and is synchronized, NCC is stopped")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STOPPED == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor("ncc_starting.png", "NIS is stopped, NCC is starting")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STARTING == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor("nis_starting_ncc_starting.png", "NIS is starting, NCC is starting")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.RUNNING == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor("nis_running_ncc_starting.png", "NIS is running (local node not booted), NCC is booting")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.BOOTED == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor("nis_running_node_booted_ncc_starting.png", "NIS is running (local node booted), NCC is starting")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.SYNCHRONIZED == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor("nis_running_node_synchronized_ncc_starting.png", "NIS is running and is synchronized, NCC is starting")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STOPPED == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor("ncc_running.png", "NIS is stopped, NCC is running")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STARTING == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor("nis_starting_ncc_running.png", "NIS is starting, NCC is running")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.RUNNING == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor("nis_running_ncc_running.png", "NIS is running (local node not booted), NCC is running")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.BOOTED == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor("nis_running_node_booted_ncc_running.png", "NIS is running (local node booted), NCC is running")
					: null,
			(nccStatus, nisStatus) -> (NemStatus.SYNCHRONIZED == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor("nis_running_node_synchronized_ncc_running.png", "NIS is running and is synchronized, NCC is running")
					: null);

	private final Consumer<IconDescriptor> iconDescriptorConsumer;
	private NemStatus nccStatus;
	private NemStatus nisStatus;

	/**
	 * Creates a new visitor.
	 *
	 * @param iconDescriptorConsumer The function to call when an icon change is triggered.
	 */
	public NodeStatusToIconDescriptorAdapter(final Consumer<IconDescriptor> iconDescriptorConsumer) {
		this.iconDescriptorConsumer = iconDescriptorConsumer;

		this.notifyStatus(NemNodeType.NIS, NemStatus.UNKNOWN);
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemStatus status) {
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
		for (final BiFunction<NemStatus, NemStatus, IconDescriptor> func : iconDescriptorPredicates) {
			final IconDescriptor descriptor = func.apply(this.nccStatus, this.nisStatus);
			if (null != descriptor) {
				return descriptor;
			}
		}

		return new IconDescriptor("all_bad.png", "Neither NCC nor NIS is running");
	}
}
