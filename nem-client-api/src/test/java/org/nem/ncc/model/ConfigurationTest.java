package org.nem.ncc.model;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.function.Consumer;

public class ConfigurationTest {

	@Test
	public void configCanBeCreated() {
		// Act:
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCanBeRoundTrippedWithoutLabels() {
		// Arrange:
		final Configuration originalConfig = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");

		// Act:
		final Configuration config = new Configuration(
				Utils.createDeserializer(JsonSerializer.serializeToJson(originalConfig)),
				"sp2");

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp2"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCanBeRoundTrippedWithLabels() {
		// Arrange:
		final Configuration originalConfig = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");
		originalConfig.setLabel(Address.fromEncoded("sigma"), "s", "ps");
		originalConfig.setLabel(Address.fromEncoded("alpha"), "a", "pa");

		// Act:
		final Configuration config = new Configuration(
				Utils.createDeserializer(JsonSerializer.serializeToJson(originalConfig)),
				"sp2");

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
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

	@Test
	public void configCanBeDeserializedWithAllRequiredParameters() {
		// Act:
		final Configuration config = this.createConfigFromJson("de-DE", new NisBootInfo(7, "1", "2", "3"), true);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("sp"));
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void configCannotBeDeserializedWithMissingRequiredParameters() {
		// Arrange:
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createConfigFromJson(null, new NisBootInfo(7, "1", "2", "3"), true),
				v -> this.createConfigFromJson("de-DE", null, true),
				v -> this.createConfigFromJson("de-DE", new NisBootInfo(7, "1", "2", "3"), false));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(v -> action.accept(null), SerializationException.class);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void configCannotBeDeserializedWithNullStoragePath() {
		// Arrange:
		final Deserializer deserializer = Utils.createDeserializer(JsonSerializer.serializeToJson(
				new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), null)));

		// Act:
		new Configuration(deserializer, null);
	}

	private Configuration createConfigFromJson(
			final String language,
			final NisBootInfo bootInfo,
			final boolean hasAccountLabels) {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		if (null != language) {
			jsonObject.put("language", language);
		}

		if (null != bootInfo) {
			jsonObject.put("nisBootInfo", JsonSerializer.serializeToJson(bootInfo));
		}

		if (hasAccountLabels) {
			jsonObject.put("accountLabels", new JSONArray());
		}

		return new Configuration(Utils.createDeserializer(jsonObject), "sp");
	}

	//region label management

	@Test
	public void accountLabelCanBeSet() {
		// Arrange:
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");

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
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");
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
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");

		// Act:
		final AccountLabel label = config.getLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(label, IsNull.nullValue());
	}

	@Test
	public void accountLabelCanBeRemoved() {
		// Arrange:
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");
		config.setLabel(Address.fromEncoded("alpha"), "a", "pa");

		// Act:
		config.removeLabel(Address.fromEncoded("alpha"));

		// Assert:
		Assert.assertThat(config.getNumLabels(), IsEqual.equalTo(0));
	}

	@Test
	public void unsetAccountLabelCannotBeRetrievedAfterRemoval() {
		// Arrange:
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");
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
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");

		// Act:
		config.update("en-CA", new NisBootInfo(12, "A", "C", "3"));

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("en-CA"));
		Assert.assertThat(config.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(12));
	}

	//endregion
}