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
		final StorableEntityName name = new StorableEntityName("foo");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("foo"));
	}

	@Test
	public void nameCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new StorableEntityName((String)null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityName(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityName("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void nameCanBeDeserialized() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("storableEntity", "bar");

		// Act:
		final StorableEntityName name = new StorableEntityName<>(Utils.createDeserializer(jsonObject), "storableEntity", null);

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("bar"));
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void nameCannotBeDeserializedWithoutName() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();

		// Act:
		new StorableEntityName<>(Utils.createDeserializer(jsonObject), "storableEntity", null);
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityName name = new StorableEntityName("foo");

		// Assert:
		Assert.assertThat(new StorableEntityName("foo"), IsEqual.equalTo(name));
		Assert.assertThat(new StorableEntityName("fob"), IsNot.not(IsEqual.equalTo(name)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(name)));
		Assert.assertThat("foo", IsNot.not(IsEqual.equalTo((Object)name)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final StorableEntityName name = new StorableEntityName("foo");
		final int hashCode = name.hashCode();

		// Assert:
		Assert.assertThat(new StorableEntityName("foo").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new StorableEntityName("fob").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawName() {
		// Arrange:
		final StorableEntityName name = new StorableEntityName("foo");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo("foo"));
	}

	//region inline serialization

	@Test
	public void canWriteName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		final StorableEntityName name = new StorableEntityName("foo");

		// Act:
		StorableEntityName.writeTo(serializer, "sen", name);

		// Assert:
		final JSONObject object = serializer.getObject();
		Assert.assertThat(object.size(), IsEqual.equalTo(1));
		Assert.assertThat(object.get("sen"), IsEqual.equalTo("foo"));
	}

	@Test
	public void canRoundtripName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		StorableEntityName.writeTo(serializer, "sen", new StorableEntityName("foo"));
		final StorableEntityName name = StorableEntityName.readFrom(Utils.createDeserializer(serializer.getObject()), "sen");

		// Assert:
		Assert.assertThat(name, IsEqual.equalTo(new StorableEntityName("foo")));
	}

	//endregion
}
