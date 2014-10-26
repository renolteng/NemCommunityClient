package org.nem.monitor;

import java.io.IOException;


/**
 * An abstraction on top of ProcessBuilder for web start processes.
 */
public class NemClientStartProcessBuilder {
	final private NemProcessBuilder nisProcess;
	final private NemProcessBuilder nccProcess;
	
	/**
	 * Creates a new builder.
	 *
	 * @param jnlpUrl The url to the java network launching protocol.
	 */
	public NemClientStartProcessBuilder() {
		nisProcess = new NemProcessBuilder("java", "org.nem.core.deploy.CommonStarter");
		nccProcess = new NemProcessBuilder("java", "org.nem.core.deploy.CommonStarter");
	}
	
	public void startNis() throws IOException {
		nisProcess.start();
	}

	public void startNcc() throws IOException {
		nccProcess.start();
	}
}
