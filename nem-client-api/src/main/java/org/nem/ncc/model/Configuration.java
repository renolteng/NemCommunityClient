package org.nem.ncc.model;

import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;

import java.util.*;

/**
 * Configuration that is persisted across sessions.
 */
public class Configuration implements SerializableEntity, AccountLabels {
	private final HashMap<Address, AccountLabel> accountLabels;
	private String language;
	private NodeEndpoint remoteServer;
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
			final NodeEndpoint remoteServer,
			final NisBootInfo nisBootInfo,
			final String nemFolder) {
		this.language = language;
		this.remoteServer = remoteServer;
		this.nisBootInfo = nisBootInfo;
		this.nemFolder = nemFolder;
		this.accountLabels = new HashMap<>();
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
		this.remoteServer = deserializer.readOptionalObject("remoteServer", NodeEndpoint::new);
		this.nisBootInfo = deserializer.readObject("nisBootInfo", NisBootInfo::new);

		this.accountLabels = new HashMap<>();
		final List<AccountLabel> labels = deserializer.readObjectArray("accountLabels", AccountLabel::new);
		for (final AccountLabel label : labels) {
			this.accountLabels.put(label.getAddress(), label);
		}
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
	 * Gets the configured remote server's endpoint.
	 *
	 * @return The remote server's endpoint.
	 */
	public NodeEndpoint getRemoteServer() {
		return this.remoteServer;
	}

	/**
	 * Gets the configured remote server's endpoint.
	 *
	 * @return The remote server's endpoint.
	 */
	public boolean isNisLocal() {
		return null == this.remoteServer ||
				this.remoteServer.getBaseUrl().getHost().isEmpty() ||
				this.remoteServer.getBaseUrl().getHost().equals("localhost") ||
				this.remoteServer.getBaseUrl().getHost().equals("127.0.0.1");
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

	//region AccountLabels implementation

	@Override
	public int getNumLabels() {
		return this.accountLabels.size();
	}

	@Override
	public AccountLabel getLabel(final Address address) {
		return this.accountLabels.get(address);
	}

	@Override
	public void setLabel(final Address address, final String label, final String privateLabel) {
		this.accountLabels.put(address, new AccountLabel(address, label, privateLabel));
	}

	@Override
	public void removeLabel(final Address address) {
		this.accountLabels.remove(address);
	}

	//endregion

	/**
	 * Updates the persistent configuration based with the supplied parameters.
	 *
	 * @param language The language.
	 * @param remoteServer The remote server's endpoint.
	 * @param nisBootInfo The NIS boot info.
	 */
	public void update(final String language, final NodeEndpoint remoteServer, final NisBootInfo nisBootInfo) {
		this.language = language;
		this.remoteServer = remoteServer;
		this.nisBootInfo = nisBootInfo;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeString("language", this.language);
		serializer.writeObject("remoteServer", this.remoteServer);
		serializer.writeObject("nisBootInfo", this.nisBootInfo);
		serializer.writeObjectArray("accountLabels", this.accountLabels.values());
	}
}
