package org.nem.monitor.node;

import org.nem.core.node.NodeEndpoint;

import java.io.File;
import java.nio.file.Paths;

/**
 * A strategy for handling a NIS node.
 */
public class NisNodePolicy implements NemNodePolicy {
	private final String nemFolder;

	/**
	 * Creates a new policy.
	 *
	 * @param nemFolder The nem folder.
	 */
	public NisNodePolicy(final String nemFolder) {
		this.nemFolder = nemFolder;
	}

	@Override
	public NodeEndpoint getEndpoint() {
		return NodeEndpoint.fromHost("localhost");
	}

	@Override
	public NemNodeType getNodeType() {
		return NemNodeType.NIS;
	}

	@Override
	public File getLockFile() {
		return Paths.get(this.nemFolder, "nis.lock").toFile();
	}

	@Override
	public String mapToUrlPath(final String apiUrl) {
		return apiUrl;
	}

	@Override
	public boolean hasBrowserGui() {
		return false;
	}
}
