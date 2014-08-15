package org.nem.ncc.wallet;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

public class WalletNameTest {

	@Test
	public void nameCanBeCreatedAroundValidNonWhitespaceString() {
		// Act:
		final WalletName name = new WalletName("foo");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("foo"));
	}

	@Test
	public void nameCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new WalletName((String)null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new WalletName(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new WalletName("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void nameCanBeDeserialized() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", "bar");

		// Act:
		final WalletName name = new WalletName(Utils.createDeserializer(jsonObject));

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("bar"));
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void nameCannotBeDeserializedWithoutName() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();

		// Act:
		new WalletName(Utils.createDeserializer(jsonObject));
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final WalletName name = new WalletName("foo");

		// Assert:
		Assert.assertThat(new WalletName("foo"), IsEqual.equalTo(name));
		Assert.assertThat(new WalletName("fob"), IsNot.not(IsEqual.equalTo(name)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(name)));
		Assert.assertThat("foo", IsNot.not(IsEqual.equalTo((Object)name)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final WalletName name = new WalletName("foo");
		final int hashCode = name.hashCode();

		// Assert:
		Assert.assertThat(new WalletName("foo").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new WalletName("fob").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawName() {
		// Arrange:
		final WalletName name = new WalletName("foo");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("foo"));
	}

	//region inline serialization

	@Test
	public void canWriteName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		final WalletName name = new WalletName("foo");

		// Act:
		WalletName.writeTo(serializer, "wn", name);

		// Assert:
		final JSONObject object = serializer.getObject();
		Assert.assertThat(object.size(), IsEqual.equalTo(1));
		Assert.assertThat(object.get("wn"), IsEqual.equalTo("foo"));
	}

	@Test
	public void canRoundtripName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		WalletName.writeTo(serializer, "wn", new WalletName("foo"));
		final WalletName name = WalletName.readFrom(Utils.createDeserializer(serializer.getObject()), "wn");

		// Assert:
		Assert.assertThat(name, IsEqual.equalTo(new WalletName("foo")));
	}

	//endregion
}