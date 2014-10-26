package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import java.util.*;

/**
 * Simple class that contains node status descriptions.
 */
public class NodeStatusPercentage {
	private int percentage;

	/**
	 * Creates a new percentage.
	 *
	 */
	public NodeStatusPercentage(final Map<NemNodeType, NemStatus> overallStatus) {
		NemStatus status = overallStatus.get(NemNodeType.NIS);
		percentage = 0;
		switch(status) {
			case STOPPED:
			case UNKNOWN:
				break;
			case STARTING:
				percentage = 10;
				break;
			case RUNNING:
				percentage = 20;
				break;
			case BOOTED:
				percentage = 50;
				break;
			case SYNCHRONIZED:
				percentage = 80;
				break;
		}
		
		status = overallStatus.get(NemNodeType.NCC);
		switch(status) {
			case STOPPED:
			case UNKNOWN:
			case BOOTED:
			case SYNCHRONIZED:
				break;
			case STARTING:
				percentage = percentage + 10;
				break;
			case RUNNING:
				percentage = percentage + 20;
				break;
		}
	}

	public int getPercentage() {
		return percentage;
	}
}
