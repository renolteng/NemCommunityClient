package org.nem.ncc.storable.entity;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

public class StorableEntityNameTest {

	@Test
	public void nameCanBeCreatedAroundValidNonWhitespaceString() {
		// Act:
		final StorableEntityName name = this.createEntityName("foo");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("foo"));
	}

	@Test
	public void nameCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> this.createEntityName((String)null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityName(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityName("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void nameCanBeDeserialized() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "bar");

		// Act:
		final StorableEntityName name = this.createEntityName(Utils.createDeserializer(jsonObject));

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("bar"));
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void nameCannotBeDeserializedWithoutName() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();

		// Act:
		this.createEntityName(Utils.createDeserializer(jsonObject));
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityName name = this.createEntityName("foo");

		// Assert:
		Assert.assertThat(this.createEntityName("foo"), IsEqual.equalTo(name));
		Assert.assertThat(this.createEntityName("fob"), IsNot.not(IsEqual.equalTo(name)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(name)));
		Assert.assertThat("foo", IsNot.not(IsEqual.equalTo((Object)name)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final StorableEntityName name = this.createEntityName("foo");
		final int hashCode = name.hashCode();

		// Assert:
		Assert.assertThat(this.createEntityName("foo").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(this.createEntityName("fob").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawName() {
		// Arrange:
		final StorableEntityName name = this.createEntityName("foo");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("foo"));
	}

	//region inline serialization

	@Test
	public void canWriteName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		final StorableEntityName name = this.createEntityName("foo");

		// Act:
		StorableEntityName.writeTo(serializer, "name", name);

		// Assert:
		final JSONObject object = serializer.getObject();
		Assert.assertThat(object.size(), IsEqual.equalTo(1));
		Assert.assertThat(object.get("name"), IsEqual.equalTo("foo"));
	}

	@Test
	public void canRoundtripName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		StorableEntityName.writeTo(serializer, "name", this.createEntityName("foo"));
		final StorableEntityName name = StorableEntityName.readFrom(Utils.createDeserializer(serializer.getObject()), "name");

		// Assert:
		Assert.assertThat(name, IsEqual.equalTo(this.createEntityName("foo")));
	}

	//endregion
	
	protected StorableEntityName createEntityName(final String name) {
		return new StorableEntityName(name);
	}

	protected StorableEntityName createEntityName(final Deserializer deserializer) {
		return new StorableEntityName<>(deserializer, "name", null);
	}
}
