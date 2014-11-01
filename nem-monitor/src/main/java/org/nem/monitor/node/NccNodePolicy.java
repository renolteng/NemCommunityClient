package org.nem.monitor.node;

import org.nem.core.node.NodeEndpoint;

import java.io.File;
import java.nio.file.Paths;

/**
 * A strategy for handling an NCC node.
 */
public class NccNodePolicy implements NemNodePolicy {
	private final String nemFolder;

	/**
	 * Creates a new policy.
	 *
	 * @param nemFolder The nem folder.
	 */
	public NccNodePolicy(final String nemFolder) {
		this.nemFolder = nemFolder;
	}

	@Override
	public NodeEndpoint getEndpoint() {
		return new NodeEndpoint("http", "localhost", 8989);
	}

	@Override
	public NemNodeType getNodeType() {
		return NemNodeType.NCC;
	}

	@Override
	public File getLockFile() {
		return Paths.get(this.nemFolder, "ncc.lock").toFile();
	}

	@Override
	public String mapToUrlPath(final String apiUrl) {
		return "ncc/api" + apiUrl;
	}

	@Override
	public boolean hasBrowserGui() {
		return true;
	}
}