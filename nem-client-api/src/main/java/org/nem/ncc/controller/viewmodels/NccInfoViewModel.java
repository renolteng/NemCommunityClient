package org.nem.ncc.controller.viewmodels;

import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.serialization.*;
import org.nem.ncc.model.Configuration;

/**
 * Creates a view model representing NCC information.
 */
public class NccInfoViewModel implements SerializableEntity {
	private final ApplicationMetaData applicationMetaData;
	private final String remoteServer;
	private final String language;

	/**
	 * Creates a new NCC info view model.
	 *
	 * @param metaData The application metadata.
	 * @param configuration The configuration.
	 */
	public NccInfoViewModel(final ApplicationMetaData metaData, final Configuration configuration) {
		this.applicationMetaData = metaData;
		this.remoteServer = configuration.getRemoteServer().getBaseUrl().toString();
		this.language = configuration.getLanguage();
	}

	/**
	 * Gets the application metadata.
	 *
	 * @return The application metadata.
	 */
	public ApplicationMetaData getMetaData() {
		return this.applicationMetaData;
	}

	/**
	 * Gets the remote server.
	 *
	 * @return The remote server.
	 */
	public String getRemoteServer() {
		return this.remoteServer;
	}

	/**
	 * Gets the language.
	 *
	 * @return The language.
	 */
	public String getLanguage() {
		return this.language;
	}

	@Override
	public void serialize(final Serializer serializer) {
		serializer.writeObject("metaData", this.applicationMetaData);
		serializer.writeString("remoteServer", this.remoteServer);
		serializer.writeString("language", this.language);
	}
}
