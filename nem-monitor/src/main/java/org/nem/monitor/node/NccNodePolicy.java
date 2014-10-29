package org.nem.monitor.node;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

import java.io.File;
import java.nio.file.Paths;

/**
 * A strategy for handling an NCC node.
 */
public class NccNodePolicy implements NemNodePolicy {
	private final String nemFolder;
	private final boolean launchNcc;

	/**
	 * Creates a new policy.
	 *
	 * @param nemFolder The nem folder.
	 */
	public NccNodePolicy(final String nemFolder, final boolean launchNcc) {
		this.nemFolder = nemFolder;
		this.launchNcc = launchNcc;
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
	public String mapToUrlPath(final NisApiId apiId) {
		return "ncc/api" + apiId;
	}

	@Override
	public boolean hasBrowserGui() {
		return true;
	}

	@Override
	public boolean launchViaJnlp() {
		return launchNcc;
	}

	@Override
	public String launcherClass() {
		return null;
	}
}