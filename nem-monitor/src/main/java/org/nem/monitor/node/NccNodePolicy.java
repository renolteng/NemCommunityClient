package org.nem.monitor.node;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

/**
 * A strategy for handling an NCC node.
 */
public class NccNodePolicy implements NemNodePolicy {

	@Override
	public NodeEndpoint getEndpoint() {
		return new NodeEndpoint("http", "localhost", 8989);
	}

	@Override
	public NemNodeType getNodeType() {
		return NemNodeType.NCC;
	}

	@Override
	public String mapToUrlPath(final NisApiId apiId) {
		return "ncc/api" + apiId;
	}
}