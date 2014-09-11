package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

/**
 * A visitor that is notified when node statuses change.
 */
public interface NodeStatusVisitor {

	/**
	 * Notifies the visitor of a node's status.
	 *
	 * @param type The node type.
	 * @param status The node status.
	 */
	public void notifyStatus(final NemNodeType type, NemStatus status);
}
