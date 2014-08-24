package org.nem.monitor.ux;

import java.util.Objects;

/**
 * Simple class that contains node status descriptions.
 */
public class NodeStatusDescription {
	private final String statusMessage;
	private final String actionMessage;

	/**
	 * Creates a new description.
	 *
	 * @param statusMessage The status message.
	 * @param actionMessage The action message.
	 */
	public NodeStatusDescription(final String statusMessage, final String actionMessage) {
		this.statusMessage = statusMessage;
		this.actionMessage = actionMessage;
	}

	/**
	 * Gets the action message.
	 *
	 * @return The action message.
	 */
	public String getActionMessage() {
		return this.actionMessage;
	}

	/**
	 * Gets the status message.
	 *
	 * @return The status message.
	 */
	public String getStatusMessage() {
		return this.statusMessage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.statusMessage, this.actionMessage);
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof NodeStatusDescription)) {
			return false;
		}

		final NodeStatusDescription rhs = (NodeStatusDescription)obj;
		return Objects.equals(this.statusMessage, rhs.statusMessage) &&
				Objects.equals(this.actionMessage, rhs.actionMessage);
	}
}
