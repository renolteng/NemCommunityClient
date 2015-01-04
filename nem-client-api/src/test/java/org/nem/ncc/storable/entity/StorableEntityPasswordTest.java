package org.nem.ncc.storable.entity;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;
public class StorableEntityPasswordTest {

	@Test
	public void passwordCanBeCreatedAroundValidNonWhitespaceString() {
		// Act:
		new StorableEntityPassword("foo");
	}

	@Test
	public void passwordCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new StorableEntityPassword(null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityPassword(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityPassword("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityPassword password = new StorableEntityPassword("foo");

		// Assert:
		Assert.assertThat(new StorableEntityPassword("foo"), IsEqual.equalTo(password));
		Assert.assertThat(new StorableEntityPassword("fob"), IsNot.not(IsEqual.equalTo(password)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(password)));
		Assert.assertThat("foo", IsNot.not(IsEqual.equalTo((Object)password)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final StorableEntityPassword password = new StorableEntityPassword("foo");
		final int hashCode = password.hashCode();

		// Assert:
		Assert.assertThat(new StorableEntityPassword("foo").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new StorableEntityPassword("fob").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawPassword() {
		// Arrange:
		final StorableEntityPassword password = new StorableEntityPassword("foo");

		// Assert:
		Assert.assertThat(password.toString(), IsEqual.equalTo("foo"));
	}

	//region inline serialization

	@Test
	public void canWritePassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		final StorableEntityPassword password = new StorableEntityPassword("foo");

		// Act:
		StorableEntityPassword.writeTo(serializer, "sep", password);

		// Assert:
		final JSONObject object = serializer.getObject();
		Assert.assertThat(object.size(), IsEqual.equalTo(1));
		Assert.assertThat(object.get("sep"), IsEqual.equalTo("foo"));
	}

	@Test
	public void canRoundtripRequiredPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		StorableEntityPassword.writeTo(serializer, "sep", new StorableEntityPassword("foo"));
		final StorableEntityPassword password = StorableEntityPassword.readFrom(Utils.createDeserializer(serializer.getObject()), "sep");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(new StorableEntityPassword("foo")));
	}

	@Test
	public void canRoundtripOptionalPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		StorableEntityPassword.writeTo(serializer, "sep", new StorableEntityPassword("foo"));
		final StorableEntityPassword password = StorableEntityPassword.readFromOptional(
				Utils.createDeserializer(serializer.getObject()), "sep");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(new StorableEntityPassword("foo")));
	}

	@Test
	public void canDeserializeNullOptionalPassword() {
		// Arrange:
		final StorableEntityPassword password = StorableEntityPassword.readFromOptional(Utils.createDeserializer(new JSONObject()), "sep");

		// Assert:
		Assert.assertThat(password, IsNull.nullValue());
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void cannotDeserializeNullRequiredPassword() {
		// Act:
		StorableEntityPassword.readFrom(Utils.createDeserializer(new JSONObject()), "sep");
	}

	//endregion
}
