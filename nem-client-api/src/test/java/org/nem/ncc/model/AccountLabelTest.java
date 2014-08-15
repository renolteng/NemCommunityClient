package org.nem.ncc.model;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.Utils;

public class AccountLabelTest {

	//region constructor / serialization

	@Test
	public void labelCanBeCreated() {
		// Act:
		final AccountLabel label = new AccountLabel(Address.fromEncoded("add"), "pub", "pri");

		// Assert:
		Assert.assertThat(label.getAddress(), IsEqual.equalTo(Address.fromEncoded("add")));
		Assert.assertThat(label.getPublicLabel(), IsEqual.equalTo("pub"));
		Assert.assertThat(label.getPrivateLabel(), IsEqual.equalTo("pri"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void labelCannotBeCreatedWithoutAddress() {
		// Arrange:
		new AccountLabel(null, "pub", "pri");
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void labelCannotBeDeserializedWithoutAddress() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("publicLabel", "pub");
		jsonObject.put("privateLabel", "pri");

		// Act:
		new AccountLabel(new JsonDeserializer(jsonObject, null));
	}

	@Test
	public void labelCanBeDeserializedWithOnlyAddress() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("address", "add");

		// Act:
		final AccountLabel label = new AccountLabel(new JsonDeserializer(jsonObject, null));

		// Assert:
		Assert.assertThat(label.getAddress(), IsEqual.equalTo(Address.fromEncoded("add")));
		Assert.assertThat(label.getPublicLabel(), IsNull.nullValue());
		Assert.assertThat(label.getPrivateLabel(), IsNull.nullValue());
	}

	@Test
	public void labelCanBeDeserialized() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("address", "add");
		jsonObject.put("publicLabel", "pub");
		jsonObject.put("privateLabel", "pri");

		// Act:
		final AccountLabel label = new AccountLabel(new JsonDeserializer(jsonObject, null));

		// Assert:
		Assert.assertThat(label.getAddress(), IsEqual.equalTo(Address.fromEncoded("add")));
		Assert.assertThat(label.getPublicLabel(), IsEqual.equalTo("pub"));
		Assert.assertThat(label.getPrivateLabel(), IsEqual.equalTo("pri"));
	}

	@Test
	public void labelCanBeRoundTrippedWithLabels() {
		// Arrange:
		final AccountLabel originalLabel = new AccountLabel(Address.fromEncoded("add"), "pub", "pri");

		// Act:
		final AccountLabel label = new AccountLabel(Utils.roundtripSerializableEntity(originalLabel, null));

		// Assert:
		Assert.assertThat(label.getAddress(), IsEqual.equalTo(Address.fromEncoded("add")));
		Assert.assertThat(label.getPublicLabel(), IsEqual.equalTo("pub"));
		Assert.assertThat(label.getPrivateLabel(), IsEqual.equalTo("pri"));
	}

	//endregion

	//region getLabel

	@Test
	public void primaryLabelIsPrivateLabelWhenSpecified() {
		// Assert:
		Assert.assertThat(createLabel("add", "pub", "pri").getLabel(), IsEqual.equalTo("pri"));
		Assert.assertThat(createLabel("add", null, "pri").getLabel(), IsEqual.equalTo("pri"));
		Assert.assertThat(createLabel("add", "pub", "").getLabel(), IsEqual.equalTo(""));
	}

	@Test
	public void primaryLabelIsPublicLabelWhenPrivateLabelIsNotSpecified() {
		// Assert:
		Assert.assertThat(createLabel("add", "pub", null).getLabel(), IsEqual.equalTo("pub"));
		Assert.assertThat(createLabel("add", null, null).getLabel(), IsNull.nullValue());
	}

	//endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final AccountLabel label = new AccountLabel(Address.fromEncoded("add"), "pub", "pri");

		// Assert:
		Assert.assertThat(createLabel("add", "pub", "pri"), IsEqual.equalTo(label));
		Assert.assertThat(createLabel("add2", "pub", "pri"), IsNot.not(IsEqual.equalTo(label)));
		Assert.assertThat(createLabel("add", "pub2", "pri"), IsNot.not(IsEqual.equalTo(label)));
		Assert.assertThat(createLabel("add", "pub", "pri2"), IsNot.not(IsEqual.equalTo(label)));
		Assert.assertThat(createLabel("add", null, null), IsNot.not(IsEqual.equalTo(label)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(label)));
		Assert.assertThat(Address.fromEncoded("add"), IsNot.not(IsEqual.equalTo((Object)label)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final AccountLabel label = new AccountLabel(Address.fromEncoded("add"), "pub", "pri");
		final int hashCode = label.hashCode();

		// Assert:
		Assert.assertThat(createLabel("add", "pub", "pri").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(createLabel("add2", "pub", "pri").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(createLabel("add", "pub2", "pri").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(createLabel("add", "pub", "pri2").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(createLabel("add", null, null).hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	//endregion

	private static AccountLabel createLabel(final String address, final String publicKey, final String privateKey) {
		return new AccountLabel(Address.fromEncoded(address), publicKey, privateKey);
	}
}