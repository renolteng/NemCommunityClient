package org.nem.monitor.launcher;

import org.nem.monitor.node.NemNodeType;

/**
 * A webstart based node launcher.
 */
public class WebStartNodeLauncher implements NodeLauncher {
	private final WebStartLauncher launcher;
	private final String nisJnlpUrl;
	private final String nccJnlpUrl;

	/**
	 * Creates a new node launcher.
	 *
	 * @param launcher The webstart launcher.
	 * @param nisJnlpUrl The NIS JNLP url.
	 * @param nccJnlpUrl The NCC JNLP url.
	 */
	public WebStartNodeLauncher(
			final WebStartLauncher launcher,
			final String nisJnlpUrl,
			final String nccJnlpUrl) {
		this.launcher = launcher;
		this.nisJnlpUrl = nisJnlpUrl;
		this.nccJnlpUrl = nccJnlpUrl;
	}

	@Override
	public void launch(final NemNodeType type) {
		switch (type) {
			case NIS:
				this.launcher.launch(this.nisJnlpUrl);
				break;

			case NCC:
				this.launcher.launch(this.nccJnlpUrl);
				break;

			default:
				throw new IllegalArgumentException("unknown node type specified");
		}
	}
}