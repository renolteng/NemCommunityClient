package org.nem.ncc.model;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;

/**
 * Creates configuration for booting a NIS node.
 */
public class NisBootInfo implements SerializableEntity {

	/**
	 * Value indicating that NCC should boot NIS.
	 */
	public final static int BOOT_STRATEGY_NO_BOOT = 0;

	/**
	 * Value indicating that NCC should boot NIS.
	 */
	public final static int BOOT_STRATEGY_BOOT = 1;

	private final int bootStrategy;
	private final String accountId;
	private final String nodeName;

	/**
	 * Creates a new NIS boot info.
	 *
	 * @param bootStrategy The boot strategy.
	 * @param accountId The
	 * @param nodeName The NIS node name.
	 */
	public NisBootInfo(
			final int bootStrategy,
			final String accountId,
			final String nodeName) {
		this.bootStrategy = bootStrategy;
		this.accountId = accountId;
		this.nodeName = nodeName;
	}

	/**
	 * Creates new NIS boot info that auto boots the local node.
	 *
	 * @return The NIS boot info.
	 */
	public static NisBootInfo createLocal() {
		return new NisBootInfo(BOOT_STRATEGY_BOOT, null, null);
	}

	/**
	 * Deserializes a NIS boot info.
	 *
	 * @param deserializer The deserializer.
	 */
	public NisBootInfo(final Deserializer deserializer) {
		// To provide a smooth transition from old structure
		final Integer bootStrategy = deserializer.readOptionalInt("bootNis");
		this.bootStrategy = null == bootStrategy ? 0 : bootStrategy;
		this.accountId = deserializer.readOptionalString("account");
		this.nodeName = deserializer.readOptionalString("nodeName");
	}

	/**
	 * Gets the NIS boot strategy.
	 *
	 * @return The boot strategy.
	 */
	public int getBootStrategy() {
		return this.bootStrategy;
	}

	/**
	 * Gets the remote NIS endpoint.
	 *
	 * @return The remote NIS endpoint.
	 */
	public NodeEndpoint getRemoteEndpoint() {
		return NodeEndpoint.fromHost("localhost");
	}

	/**
	 * Gets the account id used when booting NIS.
	 *
	 * @return The account id.
	 */
	public String getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the node name used when booting NIS.
	 *
	 * @return The node name.
	 */
	public String getNodeName() {
		return this.nodeName;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeInt("bootNis", this.bootStrategy);
		serializer.writeString("account", this.accountId);
		serializer.writeString("nodeName", this.nodeName);
	}
}
