package org.nem.ncc.wallet;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

public class WalletPasswordTest {

	@Test
	public void passwordCanBeCreatedAroundValidNonWhitespaceString() {
		// Act:
		new WalletPassword("foo");
	}

	@Test
	public void passwordCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new WalletPassword(null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new WalletPassword(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new WalletPassword("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final WalletPassword password = new WalletPassword("foo");

		// Assert:
		Assert.assertThat(new WalletPassword("foo"), IsEqual.equalTo(password));
		Assert.assertThat(new WalletPassword("fob"), IsNot.not(IsEqual.equalTo(password)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(password)));
		Assert.assertThat("foo", IsNot.not(IsEqual.equalTo((Object)password)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final WalletPassword password = new WalletPassword("foo");
		final int hashCode = password.hashCode();

		// Assert:
		Assert.assertThat(new WalletPassword("foo").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new WalletPassword("fob").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawPassword() {
		// Arrange:
		final WalletPassword password = new WalletPassword("foo");

		// Assert:
		Assert.assertThat(password.toString(), IsEqual.equalTo("foo"));
	}

	//region inline serialization

	@Test
	public void canWritePassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		final WalletPassword password = new WalletPassword("foo");

		// Act:
		WalletPassword.writeTo(serializer, "wp", password);

		// Assert:
		final JSONObject object = serializer.getObject();
		Assert.assertThat(object.size(), IsEqual.equalTo(1));
		Assert.assertThat(object.get("wp"), IsEqual.equalTo("foo"));
	}

	@Test
	public void canRoundtripRequiredPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		WalletPassword.writeTo(serializer, "wp", new WalletPassword("foo"));
		final WalletPassword password = WalletPassword.readFrom(Utils.createDeserializer(serializer.getObject()), "wp");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(new WalletPassword("foo")));
	}

	@Test
	public void canRoundtripOptionalPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		WalletPassword.writeTo(serializer, "wp", new WalletPassword("foo"));
		final WalletPassword password = WalletPassword.readFromOptional(
				Utils.createDeserializer(serializer.getObject()), "wp");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(new WalletPassword("foo")));
	}

	@Test
	public void canDeserializeNullOptionalPassword() {
		// Arrange:
		final WalletPassword password = WalletPassword.readFromOptional(Utils.createDeserializer(new JSONObject()), "wp");

		// Assert:
		Assert.assertThat(password, IsNull.nullValue());
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void cannotDeserializeNullRequiredPassword() {
		// Act:
		WalletPassword.readFrom(Utils.createDeserializer(new JSONObject()), "wp");
	}

	//endregion
}