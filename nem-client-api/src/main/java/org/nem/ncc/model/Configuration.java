package org.nem.ncc.model;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;

/**
 * Configuration that is persisted across sessions.
 */
public class Configuration implements SerializableEntity {
	private final ConfigurationPatch patch = new ConfigurationPatch();
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
		this.nemFolder = nemFolder;
		this.patch.setLanguage(language);
		this.patch.setNisEndpoint(nisEndpoint);
		this.patch.setNisBootInfo(nisBootInfo);
		this.patch.setFirstStart(0);
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

		this.patch.deserialize(deserializer, true);

		// TODO 20150329 J-G,J: add a test for default values
		// fix default values
		if (null == this.patch.getNisEndpoint()) {
			this.patch.setNisEndpoint(NodeEndpoint.fromHost("localhost"));
		}
		if (null == this.patch.getFirstStart()) {
			this.patch.setFirstStart(0);
		}
	}

	// TODO 20150329 J-G,J: add tests for firstStart

	/**
	 * Gets the configured language.
	 *
	 * @return The language.
	 */
	public String getLanguage() {
		return this.patch.getLanguage();
	}

	/**
	 * Gets the configured NIS server's endpoint.
	 *
	 * @return The NIS server's endpoint.
	 */
	public NodeEndpoint getNisEndpoint() {
		return this.patch.getNisEndpoint();
	}

	/**
	 * Gets the configured NIS boot info.
	 *
	 * @return The NIS boot info.
	 */
	public NisBootInfo getNisBootInfo() {
		return this.patch.getNisBootInfo();
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
		if (null == this.getNisEndpoint()) {
			return true;
		}

		// TODO J-B: i think we should add a getNormalizedHost to the endpoint and compare that
		final String host = this.getNisEndpoint().getBaseUrl().getHost();
		return NodeEndpoint.fromHost("localhost").getBaseUrl().getHost().equals(host) ||
				NodeEndpoint.fromHost("127.0.0.1").getBaseUrl().getHost().equals(host);
	}

	/**
	 * Gets the configuration patch.
	 *
	 * @return The configuration patch.
	 */
	public ConfigurationPatch getPatch() {
		return this.patch;
	}

	/**
	 * Updates the persistent configuration based with the supplied parameters.
	 *
	 * @param patch The configuration patch.
	 */
	public void update(final ConfigurationPatch patch) {
		this.patch.update(patch);
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.patch.serialize(serializer);
	}
}
