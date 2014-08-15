package org.nem.ncc.model.graph;

import org.nem.core.model.Address;
import org.nem.core.node.*;
import org.nem.core.serialization.*;

import java.util.Arrays;

/**
 * Class for holding additional information about a graph node.
 */
public class GraphNodeMetaData implements SerializableEntity {
	private final Address address;
	private final String platform;
	private final String version;
	private final NodeEndpoint endpoint;
	private final boolean isActive;

	/**
	 * Creates a new meta data object for a graph node.
	 *
	 * @param node The node.
	 * @param isActive true if the node is considered active.
	 */
	public GraphNodeMetaData(final Node node, final boolean isActive) {
		this.address = node.getIdentity().getAddress();
		this.platform = node.getMetaData().getPlatform();
		this.version = node.getMetaData().getVersion().toString();
		this.endpoint = node.getEndpoint();
		this.isActive = isActive;
	}

	/**
	 * Creates a new meta data object for a graph node.
	 *
	 * @param address The node's address.
	 * @param platform The platform.
	 * @param version The version.
	 * @param endpoint The endpoint.
	 * @param isActive true if the node is considered active.
	 */
	public GraphNodeMetaData(
			final Address address,
			final String platform,
			final String version,
			final NodeEndpoint endpoint,
			final boolean isActive) {
		this.address = address;
		this.platform = platform;
		this.version = version;
		this.endpoint = endpoint;
		this.isActive = isActive;
	}

	/**
	 * Deserializes a meta data object.
	 *
	 * @param deserializer The deserializer.
	 */
	public GraphNodeMetaData(final Deserializer deserializer) {
		this.address = Address.fromEncoded(deserializer.readString("address"));
		this.platform = deserializer.readString("platform");
		this.version = deserializer.readString("version");
		this.endpoint = deserializer.readObject("endpoint", NodeEndpoint::new);
		this.isActive = 0 != deserializer.readInt("active");
	}

	/**
	 * Gets the node's address.
	 *
	 * @return The address.
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Gets the platform the node is running on.
	 *
	 * @return The platform.
	 */
	public String getPlatform() {
		return this.platform;
	}

	/**
	 * Gets the the software version the node is running.
	 *
	 * @return The version.
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Gets the node's endpoint.
	 *
	 * @return The endpoint.
	 */
	public NodeEndpoint getNodeEndpoint() {
		return this.endpoint;
	}

	/**
	 * Gets a value indicating whether or not the node is active
	 *
	 * @return true if the node is active.
	 */
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Address.writeTo(serializer, "address", this.address);
		serializer.writeString("platform", this.platform);
		serializer.writeString("version", this.version);
		serializer.writeObject("endpoint", this.endpoint);
		serializer.writeInt("active", this.isActive ? 1 : 0);
	}

	@Override
	public int hashCode() {
		return this.address.hashCode() ^
				this.platform.hashCode() ^
				this.version.hashCode() ^
				this.endpoint.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof GraphNodeMetaData)) {
			return false;
		}

		final GraphNodeMetaData rhs = (GraphNodeMetaData)obj;
		return this.address.equals(rhs.address) &&
				this.platform.equals(rhs.platform) &&
				this.version.equals(rhs.version) &&
				this.endpoint.equals(rhs.endpoint);
	}

	@Override
	public String toString() {
		return org.springframework.util.StringUtils.collectionToDelimitedString(Arrays.asList(
				String.format("address: %s", this.address),
				String.format("platform: %s", this.platform),
				String.format("version: %s", this.version),
				String.format("endpoint: %s", this.endpoint),
				String.format("active: %b", this.isActive)
		), System.lineSeparator());
	}
}
