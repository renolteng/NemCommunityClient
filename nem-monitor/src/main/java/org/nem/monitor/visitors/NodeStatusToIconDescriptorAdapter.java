package org.nem.monitor.visitors;

import org.nem.monitor.node.*;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * NodeStatusVisitor that maps status changes to appropriate icons.
 */
public class NodeStatusToIconDescriptorAdapter implements NodeStatusVisitor {

	private static final HashMap<Integer, IconDescriptor> codeToStateInfoMap = new HashMap<Integer, IconDescriptor>() {
		{ this.put(0, new IconDescriptor("all_bad.png", "Neither NCC nor NIS is running")); }

		{ this.put(1, new IconDescriptor("ncc_only.png", "Only NCC is running")); }

		{ this.put(2, new IconDescriptor("nis_online.png", "Only NIS is running")); }

		{ this.put(3, new IconDescriptor("all_good.png", "Both NCC and NIS are running")); }
	};

	private final Consumer<IconDescriptor> iconDescriptorConsumer;
	private boolean isNccRunning;
	private boolean isNisRunning;

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
			case NIS:
				this.isNisRunning = NemNodeStatus.RUNNING == status;
				break;

			case NCC:
				this.isNccRunning = NemNodeStatus.RUNNING == status;
				break;
		}

		this.iconDescriptorConsumer.accept(codeToStateInfoMap.get(this.getCode()));
	}

	private int getCode() {
		return (this.isNccRunning ? 1 : 0) + (this.isNisRunning ? 2 : 0);
	}
}
