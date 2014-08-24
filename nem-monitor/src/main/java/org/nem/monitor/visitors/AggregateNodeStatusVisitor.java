package org.nem.monitor.visitors;

import org.nem.monitor.node.*;

import java.util.*;
import java.util.logging.Logger;

/**
 * An aggregate NodeStatusVisitor that guarantees child visitors are only called on a status change.
 */
public class AggregateNodeStatusVisitor implements NodeStatusVisitor {
	private static final Logger LOGGER = Logger.getLogger(AggregateNodeStatusVisitor.class.getName());

	private final Collection<NodeStatusVisitor> visitors;
	private final Map<NemNodeType, NemNodeStatus> lastKnownTypes;

	/**
	 * Creates a new aggregate visitor.
	 *
	 * @param visitors The child visitors.
	 */
	public AggregateNodeStatusVisitor(final Collection<NodeStatusVisitor> visitors) {
		this.visitors = visitors;
		this.lastKnownTypes = new HashMap<>();
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemNodeStatus status) {
		if (this.lastKnownTypes.getOrDefault(type, null) == status) {
			return;
		}

		LOGGER.info(String.format("status change detected: %s -> %s", type, status));
		this.lastKnownTypes.put(type, status);
		this.visitors.forEach(v -> v.notifyStatus(type, status));
	}
}
