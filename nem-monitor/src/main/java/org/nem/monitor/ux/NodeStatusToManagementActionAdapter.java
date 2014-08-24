package org.nem.monitor.ux;

import org.nem.monitor.NodeStatusVisitor;
import org.nem.monitor.node.*;

import java.awt.event.*;

/**
 * An NodeStatusVisitor that maps the current status of a node to an appropriate action.
 */
public class NodeStatusToManagementActionAdapter implements NodeStatusVisitor, ActionListener {
	private final NemNodeType nodeType;
	private final NodeManager manager;
	private NemNodeStatus status;

	/**
	 * Creates a new adapter.
	 *
	 * @param nodeType The node type.
	 * @param manager The node manager.
	 */
	public NodeStatusToManagementActionAdapter(final NemNodeType nodeType, final NodeManager manager) {
		this.nodeType = nodeType;
		this.manager = manager;
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemNodeStatus status) {
		if (this.nodeType != type) {
			return;
		}

		this.status = status;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		switch (this.status) {
			case RUNNING:
				this.manager.shutdown();
				break;

			case STOPPED:
				this.manager.launch();
				break;
		}
	}
}
