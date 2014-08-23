package org.nem.monitor;

import org.nem.monitor.node.*;

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
	public void notifyStatus(final NemNodeType type, NemNodeStatus status);
}
