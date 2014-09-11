package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import java.util.*;
import java.util.logging.Logger;

/**
 * An aggregate NodeStatusVisitor that guarantees child visitors are only called on a status change.
 */
public class AggregateNemStatusVisitor implements NodeStatusVisitor {
	private static final Logger LOGGER = Logger.getLogger(AggregateNemStatusVisitor.class.getName());

	private final Collection<NodeStatusVisitor> visitors;
	private final Map<NemNodeType, NemStatus> lastKnownTypes;

	/**
	 * Creates a new aggregate visitor.
	 *
	 * @param visitors The child visitors.
	 */
	public AggregateNemStatusVisitor(final Collection<NodeStatusVisitor> visitors) {
		this.visitors = visitors;
		this.lastKnownTypes = new HashMap<>();
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemStatus status) {
		if (this.lastKnownTypes.getOrDefault(type, null) == status) {
			return;
		}

		LOGGER.info(String.format("status change detected: %s -> %s", type, status));
		this.lastKnownTypes.put(type, status);
		this.visitors.forEach(v -> v.notifyStatus(type, status));
	}
}
