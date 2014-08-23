package org.nem.monitor.node;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

/**
 * A strategy for handling a NIS node.
 */
public class NisNodePolicy implements NemNodePolicy {

	@Override
	public NodeEndpoint getEndpoint() {
		return NodeEndpoint.fromHost("localhost");
	}

	@Override
	public NemNodeType getNodeType() {
		return NemNodeType.NIS;
	}

	@Override
	public String mapToUrlPath(final NisApiId apiId) {
		return apiId.toString();
	}
}
