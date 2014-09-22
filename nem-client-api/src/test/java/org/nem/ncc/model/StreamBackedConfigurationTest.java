package org.nem.ncc.model;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.test.CorruptStreams;

import java.io.*;
import java.util.function.Supplier;

public class StreamBackedConfigurationTest {

	@Test
	public void configCanBeDeserializedWithoutLabels() {
		// Act:
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "1", "2"), "sp"),
				"sp2",
				null);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getRemoteServer().getBaseUrl().getHost(), IsEqual.equalTo("10.10.10.12"));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp2"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCanBeDeserializedWithLabels() {
		// Arrange:
		final Configuration originalConfig = new Configuration("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "1", "2"), "sp");
		originalConfig.setLabel(Address.fromEncoded("sigma"), "s", "ps");
		originalConfig.setLabel(Address.fromEncoded("alpha"), "a", "pa");

		// Act:
		final Configuration config = deserializeStreamBackedConfig(originalConfig, "sp2", null);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getRemoteServer().getBaseUrl().getHost(), IsEqual.equalTo("10.10.10.12"));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp2"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(2));
	}

	@Test
	public void setLabelSavesConfiguration() {
		// Arrange:
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "1", "2"), "sp"),
				"sp2",
				() -> outputStream);

		// Act:
		config.setLabel(Address.fromEncoded("sigma"), "s", "ps");
		final Configuration reloadedConfig = new StreamBackedConfiguration(
				"sp2",
				new ByteArrayInputStream(outputStream.toByteArray()),
				null);

		// Assert:
		Assert.assertThat(reloadedConfig.getNumLabels(), IsEqual.equalTo(1));
	}

	@Test
	public void updateSavesConfiguration() {
		// Arrange:
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "1", "2"), "sp"),
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
				new Configuration("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "1", "2"), "sp"),
				"sp2",
				() -> {
					++numSupplierCalls[0];
					return outputStream;
				});

		// Act:
		config.setLabel(Address.fromEncoded("sigma"), "s", "ps");
		config.setLabel(Address.fromEncoded("alpha"), "a", "pa");
		config.update("en-CA", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(11, "5", "4"));

		// Assert:
		Assert.assertThat(numSupplierCalls[0], IsEqual.equalTo(3));
	}

	@Test(expected = ConfigurationException.class)
	public void saveFailsIfOutputStreamThrowsException() throws IOException {
		// Arrange:
		final OutputStream outputStream = CorruptStreams.createWrite();
		final Configuration config = deserializeStreamBackedConfig(
				new Configuration("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "1", "2"), "sp"),
				"sp2",
				() -> outputStream);

		// Act:
		config.setLabel(Address.fromEncoded("sigma"), "s", "ps");
	}

	private static Configuration deserializeStreamBackedConfig(
			final Configuration config,
			final String storagePath,
			final Supplier<OutputStream> outputStreamSupplier) {
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(JsonSerializer.serializeToBytes(config));
		return new StreamBackedConfiguration(storagePath, inputStream, outputStreamSupplier);
	}
}