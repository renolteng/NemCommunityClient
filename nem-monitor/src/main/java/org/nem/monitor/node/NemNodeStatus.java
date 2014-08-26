package org.nem.monitor.node;

/**
 * Possible NEM node statuses.
 */
public enum NemNodeStatus {

	/**
	 * The node is in an unknown state.
	 */
	UNKNOWN,

	/**
	 * The node is booting.
	 */
	BOOTING,

	/**
	 * The node is running.
	 */
	RUNNING,

	/**
	 * The node is stopped.
	 */
	STOPPED
}