package org.nem.monitor;

import org.nem.core.connect.*;
import org.nem.core.metadata.MetaDataFactory;
import org.nem.core.node.NodeVersion;
import org.nem.core.serialization.Deserializer;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.utils.ExceptionUtils;

import java.net.URL;

/**
 * Helper class for getting version information.
 */
public class VersionUtils {
	private final static String VERSION_PROVIDER_URL = "http://bob.nem.ninja/version.json";
	private final static String VERSION_FLAVOR = "stable";

	/**
	 * Gets the local NEM version.
	 *
	 * @return The local NEM version.
	 */
	public static NodeVersion getLocalVersion() {
		final String localVersion = MetaDataFactory.loadApplicationMetaData(HttpMethodClient.class, new SystemTimeProvider()).getVersion();
		return NodeVersion.parse(localVersion);
	}

	/**
	 * Gets the latest NEM version.
	 *
	 * @return The latest NEM version.
	 */
	public static NodeVersion getLatestVersion() {
		final URL url = ExceptionUtils.propagate(() -> new URL(VERSION_PROVIDER_URL));
		final HttpMethodClient<ErrorResponseDeserializerUnion> client = new HttpMethodClient<>();
		return client.get(url, new HttpErrorResponseDeserializerUnionStrategy(null))
				.getFuture()
				.thenApply(union -> {
					final Deserializer deserializer = union.getDeserializer();
					return NodeVersion.parse(deserializer.readString(VERSION_FLAVOR));
				}).join();
	}
}
