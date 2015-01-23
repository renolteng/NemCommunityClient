package org.nem.ncc.model;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.test.CorruptStreams;

import java.io.*;
import java.util.function.Supplier;

public class StreamBackedConfigurationTest {
	private static final NodeEndpoint ENDPOINT = NodeEndpoint.fromHost("10.10.10.12");
	private static final NisBootInfo BOOT_INFO = new NisBootInfo(7, "1", "2");

	@Test
	public void configCanBeDeserializedWithoutLabels() {
		// Act:
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp"),
				"sp2",
				null);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp2"));
	}

	@Test
	public void updateSavesConfiguration() {
		// Arrange:
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp"),
				"sp2",
				() -> outputStream);

		// Act:
		config.update("en-CA", NodeEndpoint.fromHost("10.10.10.15"), new NisBootInfo(11, "5", "4"));
		final Configuration reloadedConfig = new StreamBackedConfiguration(
				"sp2",
				new ByteArrayInputStream(outputStream.toByteArray()),
				null);

		// Assert:
		Assert.assertThat(reloadedConfig.getLanguage(), IsEqual.equalTo("en-CA"));
	}

	@Test
	public void outputStreamSupplierIsCalledForEachSaveRequest() {
		// Arrange:
		final int[] numSupplierCalls = new int[] { 0 };
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp"),
				"sp2",
				() -> {
					++numSupplierCalls[0];
					return outputStream;
				});

		// Act:
		config.update("en-CA", NodeEndpoint.fromHost("10.10.10.15"), new NisBootInfo(11, "5", "4"));

		// Assert:
		Assert.assertThat(numSupplierCalls[0], IsEqual.equalTo(1));
	}

	@Test(expected = ConfigurationException.class)
	public void saveFailsIfOutputStreamThrowsException() throws IOException {
		// Arrange:
		final OutputStream outputStream = CorruptStreams.createWrite();
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp"),
				"sp2",
				() -> outputStream);

		// Act:
		config.update("en-CA", NodeEndpoint.fromHost("10.10.10.15"), new NisBootInfo(11, "5", "4"));
	}

	private static Configuration deserializeStreamBackedConfig(
			final Configuration config,
			final String storagePath,
			final Supplier<OutputStream> outputStreamSupplier) {
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(JsonSerializer.serializeToBytes(config));
		return new StreamBackedConfiguration(storagePath, inputStream, outputStreamSupplier);
	}
}