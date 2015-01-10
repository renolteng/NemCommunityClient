package org.nem.ncc.storable.entity;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.ExceptionAssert;

import java.util.*;
import java.util.function.Consumer;

public class StorableEntityNamePasswordPairTest {

	//region construction

	@Test
	public void pairCanBeCreated() {
		// Act:
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("name"),
				new StorableEntityPassword("password"));

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(new StorableEntityName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(new StorableEntityPassword("password")));
	}

	@Test
	public void pairCanBeCreatedUsingConstructorWithStringParameters() {
		// Act:
		final StorableEntityNamePasswordPair pair = this.createEntityNamePasswordPair("name", "password");

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(new StorableEntityName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(new StorableEntityPassword("password")));
	}

	//endregion

	//region serialization

	@Test
	public void pairCanBeDeserializedWithAllParameters() {
		// Act:
		final StorableEntityNamePasswordPair pair = this.createPairFromJson("name", "password");
		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(new StorableEntityName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(new StorableEntityPassword("password")));
	}

	@Test
	public void pairCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createPairFromJson(null, "pass"),
				v -> this.createPairFromJson("sen", null));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(
					v -> action.accept(null),
					SerializationException.class);
		}
	}

	private StorableEntityNamePasswordPair createPairFromJson(final String name, final String password) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("storableEntity", name);
		jsonObject.put("password", password);
		return new StorableEntityNamePasswordPair<>(
				new JsonDeserializer(jsonObject, null),
				StorableEntityName::new,
				StorableEntityPassword::new,
				null);
	}

	//endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityNamePasswordPair pair = this.createEntityNamePasswordPair("name", "password");

		// Assert:
		Assert.assertThat(this.createEntityNamePasswordPair("name", "password"), IsEqual.equalTo(pair));
		Assert.assertThat(this.createEntityNamePasswordPair("lame", "password"), IsNot.not(IsEqual.equalTo(pair)));
		Assert.assertThat(this.createEntityNamePasswordPair("name", "word"), IsNot.not(IsEqual.equalTo(pair)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(pair)));
		Assert.assertThat("name", IsNot.not(IsEqual.equalTo((Object)pair)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange::
		final StorableEntityNamePasswordPair pair = this.createEntityNamePasswordPair("name", "password");
		final int hashCode = pair.hashCode();

		// Assert:
		Assert.assertThat(this.createEntityNamePasswordPair("name", "password").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(this.createEntityNamePasswordPair("lame", "password").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(this.createEntityNamePasswordPair("name", "word").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	//endregion

	protected StorableEntityNamePasswordPair createEntityNamePasswordPair(final String name, final String password) {
		return new StorableEntityNamePasswordPair<>(
				name,
				password,
				StorableEntityName::new,
				StorableEntityPassword::new);
	}
}
