package org.nem.monitor;


/**
 * An abstraction on top of ProcessBuilder for web start processes.
 */
public class WebStartProcessBuilder extends NemProcessBuilder{
	/**
	 * Creates a new builder.
	 *
	 * @param jnlpUrl The url to the java network launching protocol.
	 */
	public WebStartProcessBuilder(final String jnlpUrl) {
		super("javaws", jnlpUrl);
	}
}
