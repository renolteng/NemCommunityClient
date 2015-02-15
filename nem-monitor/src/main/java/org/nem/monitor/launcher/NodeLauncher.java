package org.nem.monitor.launcher;

import org.nem.monitor.node.*;

/**
 * Interface for launching external NEM nodes.
 */
public interface NodeLauncher {

	/**
	 * Launches a node of the specified type.
	 *
	 * @param nodeType The node type.
	 */
	public void launch(final NemNodeType nodeType);
}
