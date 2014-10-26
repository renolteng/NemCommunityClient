package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import org.apache.commons.collections4.map.HashedMap;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Visitor that keeps node menu item text in sync with status changes.
 */
public class NodeStatusToProgressBarAdapter implements NodeStatusVisitor {
	private final Map<NemNodeType, NemStatus> overallStatus;
	private final Consumer<NodeStatusPercentage> statusPercentageConsumer;
	/**
	 * Creates a new visitor.
	 *
	 * @param nodeType The node type being monitored.
	 * @param statusDescriptionConsumer The function to call when a description change is triggered.
	 */
	public NodeStatusToProgressBarAdapter(final Consumer<NodeStatusPercentage> statusPercentageConsumer) {
		this.overallStatus = new HashedMap<>();
		this.statusPercentageConsumer = statusPercentageConsumer;
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemStatus status) {
		overallStatus.put(type, status);
		statusPercentageConsumer.accept(new NodeStatusPercentage(overallStatus));
	}
}
