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
		this.createEntityPassword("foo");
	}

	@Test
	public void passwordCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> this.createEntityPassword((String)null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityPassword(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityPassword("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityPassword password = this.createEntityPassword("foo");

		// Assert:
		Assert.assertThat(this.createEntityPassword("foo"), IsEqual.equalTo(password));
		Assert.assertThat(this.createEntityPassword("fob"), IsNot.not(IsEqual.equalTo(password)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(password)));
		Assert.assertThat("foo", IsNot.not(IsEqual.equalTo((Object)password)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final StorableEntityPassword password = this.createEntityPassword("foo");
		final int hashCode = password.hashCode();

		// Assert:
		Assert.assertThat(this.createEntityPassword("foo").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(this.createEntityPassword("fob").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawPassword() {
		// Arrange:
		final StorableEntityPassword password = this.createEntityPassword("foo");

		// Assert:
		Assert.assertThat(password.toString(), IsEqual.equalTo("foo"));
	}

	//region inline serialization

	@Test
	public void canWritePassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		final StorableEntityPassword password = this.createEntityPassword("foo");

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
		StorableEntityPassword.writeTo(serializer, "sep", this.createEntityPassword("foo"));
		final StorableEntityPassword password = StorableEntityPassword.readFrom(Utils.createDeserializer(serializer.getObject()), "sep");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(this.createEntityPassword("foo")));
	}

	@Test
	public void canRoundtripOptionalPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		StorableEntityPassword.writeTo(serializer, "sep", this.createEntityPassword("foo"));
		final StorableEntityPassword password = StorableEntityPassword.readFromOptional(
				Utils.createDeserializer(serializer.getObject()), "sep");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(this.createEntityPassword("foo")));
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

	protected StorableEntityPassword createEntityPassword(final String name) {
		return new StorableEntityPassword<>(name);
	}
}
