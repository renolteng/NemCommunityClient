package org.nem.ncc.model;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;

/**
 * Configuration that is persisted across sessions.
 */
public class Configuration implements SerializableEntity {
	private String language;
	private NodeEndpoint nisEndpoint;
	private NisBootInfo nisBootInfo;
	private final String nemFolder;

	/**
	 * Creates a new configuration object.
	 *
	 * @param language The language.
	 * @param nisBootInfo The NIS boot information.
	 * @param nemFolder The NEM folder where everything gets stored (incl. wallets).
	 */
	public Configuration(
			final String language,
			final NodeEndpoint nisEndpoint,
			final NisBootInfo nisBootInfo,
			final String nemFolder) {
		this.language = language;
		this.nisEndpoint = nisEndpoint;
		this.nisBootInfo = nisBootInfo;
		this.nemFolder = nemFolder;
	}

	/**
	 * Deserializes a configuration object.
	 *
	 * @param deserializer The deserializer.
	 * @param nemFolder The NEM folder where everything gets stored (incl. wallets).
	 */
	public Configuration(final Deserializer deserializer, final String nemFolder) {
		if (null == nemFolder) {
			throw new IllegalArgumentException("storagePath cannot be null");
		}

		this.nemFolder = nemFolder;
		this.language = deserializer.readString("language");
		this.nisEndpoint = deserializer.readOptionalObject("remoteServer", NodeEndpoint::new);
		if (null == this.nisEndpoint) {
			this.nisEndpoint = NodeEndpoint.fromHost("localhost");
		}

		this.nisBootInfo = deserializer.readObject("nisBootInfo", NisBootInfo::new);
	}

	/**
	 * Gets the configured language.
	 *
	 * @return The language.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Gets the configured NIS server's endpoint.
	 *
	 * @return The NIS server's endpoint.
	 */
	public NodeEndpoint getNisEndpoint() {
		return this.nisEndpoint;
	}

	/**
	 * Gets the configured NIS boot info.
	 *
	 * @return The NIS boot info.
	 */
	public NisBootInfo getNisBootInfo() {
		return this.nisBootInfo;
	}

	/**
	 * Gets the configured NEM folder path.
	 *
	 * @return The NEM folder path.
	 */
	public String getNemFolder() {
		return this.nemFolder;
	}

	/**
	 * Gets a value indicating whether or not NIS is local.
	 *
	 * @return true if NIS is local.
	 */
	public boolean isNisLocal() {
		if (null == this.nisEndpoint) {
			return true;
		}

		// TODO J-B: i think we should add a getNormalizedHost to the endpoint and compare that
		final String host = this.nisEndpoint.getBaseUrl().getHost();
		return NodeEndpoint.fromHost("localhost").getBaseUrl().getHost().equals(host) ||
				NodeEndpoint.fromHost("127.0.0.1").getBaseUrl().getHost().equals(host);
	}

	/**
	 * Gets the configuration patch.
	 *
	 * @return The configuration patch.
	 */
	public ConfigurationPatch getPatch() {
		final ConfigurationPatch patch = new ConfigurationPatch();
		patch.setLanguage(this.language);
		patch.setNisEndpoint(this.nisEndpoint);
		patch.setNisBootInfo(this.nisBootInfo);
		return patch;
	}

	/**
	 * Updates the persistent configuration based with the supplied parameters.
	 *
	 * @param patch The configuration patch.
	 */
	public void update(final ConfigurationPatch patch) {
		this.language = patch.getLanguage();
		this.nisEndpoint = patch.getNisEndpoint();
		this.nisBootInfo = patch.getNisBootInfo();
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("language", this.language);
		serializer.writeObject("remoteServer", this.nisEndpoint);
		serializer.writeObject("nisBootInfo", this.nisBootInfo);
	}
}
