package org.nem.ncc.model;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.function.Consumer;

public class ConfigurationTest {

	private static final NodeEndpoint ENDPOINT = NodeEndpoint.fromHost("10.10.10.12");
	private static final NisBootInfo BOOT_INFO = new NisBootInfo(7, "1", "2");

	//region creation / roundtrip

	@Test
	public void configCanBeCreated() {
		// Act:
		final Configuration config = new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp");

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCanBeRoundTrippedWithoutLabels() {
		// Arrange:
		final Configuration originalConfig = new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp");

		// Act:
		final Configuration config = new Configuration(
				Utils.createDeserializer(JsonSerializer.serializeToJson(originalConfig)),
				"sp2");

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp2"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCanBeRoundTrippedWithLabels() {
		// Arrange:
		final Configuration originalConfig = new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp");
		originalConfig.setLabel(Address.fromEncoded("sigma"), "s", "ps");
		originalConfig.setLabel(Address.fromEncoded("alpha"), "a", "pa");

		// Act:
		final Configuration config = new Configuration(
				Utils.createDeserializer(JsonSerializer.serializeToJson(originalConfig)),
				"sp2");

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp2"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(2));
		Assert.assertThat(
				config.getLabel(Address.fromEncoded("sigma")),
				IsEqual.equalTo(new AccountLabel(Address.fromEncoded("sigma"), "s", "ps")));
		Assert.assertThat(
				config.getLabel(Address.fromEncoded("alpha")),
				IsEqual.equalTo(new AccountLabel(Address.fromEncoded("alpha"), "a", "pa")));
	}

	//endregion

	//region deserialization

	@Test
	public void configCanBeDeserializedWithAllRequiredParameters() {
		// Act:
		final Configuration config = this.createConfigFromJson("de-DE", ENDPOINT, BOOT_INFO, true);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCanBeDeserializedWithoutRemoteServer() {
		// Act:
		final Configuration config = this.createConfigFromJson("de-DE", null, BOOT_INFO, true);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(NodeEndpoint.fromHost("localhost")));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCannotBeDeserializedWithMissingRequiredParameters() {
		// Arrange:
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createConfigFromJson(null, ENDPOINT, BOOT_INFO, true),
				v -> this.createConfigFromJson("de-DE", ENDPOINT, null, true),
				v -> this.createConfigFromJson("de-DE", ENDPOINT, BOOT_INFO, false));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(v -> action.accept(null), SerializationException.class);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void configCannotBeDeserializedWithNullStoragePath() {
		// Arrange:
		final Deserializer deserializer = Utils.createDeserializer(JsonSerializer.serializeToJson(
				new Configuration("de-DE", ENDPOINT, BOOT_INFO, null)));

		// Act:
		new Configuration(deserializer, null);
	}

	private Configuration createConfigFromJson(
			final String language,
			final NodeEndpoint remoteServer,
			final NisBootInfo bootInfo,
			final boolean hasAccountLabels) {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		if (null != language) {
			jsonObject.put("language", language);
		}

		if (null != remoteServer) {
			jsonObject.put("remoteServer", JsonSerializer.serializeToJson(remoteServer));
		}

		if (null != bootInfo) {
			jsonObject.put("nisBootInfo", JsonSerializer.serializeToJson(bootInfo));
		}

		if (hasAccountLabels) {
			jsonObject.put("accountLabels", new JSONArray());
		}

		return new Configuration(Utils.createDeserializer(jsonObject), "sp");
	}

	//endregion

	//region isNisLocal

	@Test
	public void isNisLocalReturnsTrueWhenRemoteEndpointIsNullOrLocal() {
		// Assert:
		Assert.assertThat(isNisLocal(null), IsEqual.equalTo(true));
		Assert.assertThat(isNisLocal(NodeEndpoint.fromHost("localhost")), IsEqual.equalTo(true));
		Assert.assertThat(isNisLocal(NodeEndpoint.fromHost("127.0.0.1")), IsEqual.equalTo(true));
		Assert.assertThat(isNisLocal(NodeEndpoint.fromHost("10.0.10.10")), IsEqual.equalTo(false));
	}

	@Test
	public void isNisLocalReturnsTrueWhenLocalRemoteEndpointDoesNotUseDefaultPort() {
		// Assert:
		Assert.assertThat(isNisLocal(new NodeEndpoint("http", "localhost", 7891)), IsEqual.equalTo(true));
	}

	@Test
	public void isNisLocalReturnsTrueWhenLocalRemoteEndpointDoesNotUseDefaultProtocol() {
		// Assert:
		Assert.assertThat(isNisLocal(new NodeEndpoint("ftp", "localhost", 7890)), IsEqual.equalTo(true));
	}

	private static boolean isNisLocal(final NodeEndpoint endpoint) {
		final Configuration config = new Configuration("de-DE", endpoint, BOOT_INFO, "sp");
		return config.isNisLocal();
	}

	//endregion

	//region label management

	@Test
	public void accountLabelCanBeSet() {
		// Arrange:
		final Configuration config = createDefaultConfiguration();

		// Act:
		config.setLabel(Address.fromEncoded("alpha"), "a", "pa");
		final AccountLabel label = config.getLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(1));
		Assert.assertThat(label, IsEqual.equalTo(new AccountLabel(Address.fromEncoded("alpha"), "a", "pa")));
	}

	@Test
	public void accountLabelCanBeUpdated() {
		// Arrange:
		final Configuration config = createDefaultConfiguration();
		config.setLabel(Address.fromEncoded("alpha"), "a", "pa");

		// Act:
		config.setLabel(Address.fromEncoded("alpha"), "a", "pa");
		config.setLabel(Address.fromEncoded("alpha"), "b", "pb");
		final AccountLabel label = config.getLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(1));
		Assert.assertThat(label, IsEqual.equalTo(new AccountLabel(Address.fromEncoded("alpha"), "b", "pb")));
	}

	@Test
	public void unsetAccountLabelCannotBeRetrieved() {
		// Arrange:
		final Configuration config = createDefaultConfiguration();

		// Act:
		final AccountLabel label = config.getLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(label, IsNull.nullValue());
	}

	@Test
	public void accountLabelCanBeRemoved() {
		// Arrange:
		final Configuration config = createDefaultConfiguration();
		config.setLabel(Address.fromEncoded("alpha"), "a", "pa");

		// Act:
		config.removeLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void unsetAccountLabelCannotBeRetrievedAfterRemoval() {
		// Arrange:
		final Configuration config = createDefaultConfiguration();
		config.removeLabel(Address.fromEncoded("alpha"));

		// Act:
		final AccountLabel label = config.getLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(label, IsNull.nullValue());
	}

	//endregion

	//region update

	@Test
	public void configurationCanBeUpdated() {
		// Arrange:
		final Configuration config = createDefaultConfiguration();

		// Act:
		config.update("en-CA", NodeEndpoint.fromHost("127.0.0.1"), new NisBootInfo(12, "A", "C"));

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("en-CA"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(NodeEndpoint.fromHost("127.0.0.1")));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(12));
	}

	//endregion

	private static Configuration createDefaultConfiguration() {
		return new Configuration("de-DE", ENDPOINT, BOOT_INFO, "sp");
	}
}