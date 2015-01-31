package org.nem.ncc.model;

import net.minidev.json.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.util.function.Supplier;

/**
 * A stream-backed configuration that auto-saves changes.
 */
public class StreamBackedConfiguration extends Configuration {
	private final Supplier<OutputStream> outputStreamSupplier;

	/**
	 * Creates a new stream-backed configuration object.
	 *
	 * @param storagePath The NEM storage path.
	 * @param inputStream The input stream.
	 * @param outputStreamSupplier The supplier that returns a new output stream when called.
	 */
	public StreamBackedConfiguration(
			final String storagePath,
			final InputStream inputStream,
			final Supplier<OutputStream> outputStreamSupplier) {
		super(inputStreamToDeserializer(inputStream), storagePath);
		this.outputStreamSupplier = outputStreamSupplier;
	}

	private static Deserializer inputStreamToDeserializer(final InputStream inputStream) {
		return new JsonDeserializer(
				ExceptionUtils.propagate(() -> (JSONObject)JSONValue.parse(inputStream)),
				null);
	}

	@Override
	public void update(final String language, final NodeEndpoint remoteServer, final NisBootInfo nisBootInfo) {
		super.update(language, remoteServer, nisBootInfo);
		this.save();
	}

	private void save() {
		try (final OutputStream outputStream = this.outputStreamSupplier.get()) {
			outputStream.write(JsonSerializer.serializeToBytes(this));
		} catch (final IOException ex) {
			throw new ConfigurationException("Configuration could not be saved", ex);
		}
	}
}
