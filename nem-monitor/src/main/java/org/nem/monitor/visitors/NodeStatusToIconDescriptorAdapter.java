package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.config.LanguageSupport;
import org.nem.monitor.node.NemNodeType;

import java.util.*;
import java.util.function.*;

/**
 * NodeStatusVisitor that maps status changes to appropriate icons.
 */
public class NodeStatusToIconDescriptorAdapter implements NodeStatusVisitor {
	private static final List<BiFunction<NemStatus, NemStatus, IconDescriptor>> iconDescriptorPredicates = Arrays.asList(
			(nccStatus, nisStatus) -> (NemStatus.STARTING == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.starting", "status.ncc.is.stopped"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.RUNNING == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.running", "status.ncc.is.stopped"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.BOOTED == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.booted", "status.ncc.is.stopped"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.SYNCHRONIZED == nisStatus && NemStatus.STOPPED == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.synchronized", "status.ncc.is.stopped"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STOPPED == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.stopped", "status.ncc.is.starting"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STARTING == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.starting", "status.ncc.is.starting"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.RUNNING == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.running", "status.ncc.is.starting"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.BOOTED == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.booted", "status.ncc.is.starting"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.SYNCHRONIZED == nisStatus && NemStatus.STARTING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.synchronized", "status.ncc.is.starting"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STOPPED == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.stopped", "status.ncc.is.running"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.STARTING == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.starting", "status.ncc.is.running"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.RUNNING == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.running", "status.ncc.is.running"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.BOOTED == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.booted", "status.ncc.is.running"))
					: null,
			(nccStatus, nisStatus) -> (NemStatus.SYNCHRONIZED == nisStatus && NemStatus.RUNNING == nccStatus)
					? new IconDescriptor(getImageName(nccStatus, nisStatus), getDescription("status.nis.is.synchronized", "status.ncc.is.running"))
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

		return new IconDescriptor("icon_00.png", getDescription("status.nis.is.stopped", "status.ncc.is.stopped"));
	}

	private static String getImageName(final NemStatus nccStatus, final NemStatus nisStatus) {
		return String.format("icon_%d%d.png", nccStatus.getValue(), nisStatus.getValue());
	}

	private static String getDescription(final String msg1, final String msg2) {
		return String.format("%s, %s", LanguageSupport.message(msg1), LanguageSupport.message(msg2));
	}
}
