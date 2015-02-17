package org.nem.ncc.storable.entity;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.function.Consumer;

public abstract class StorableEntityNamePasswordPairTest<
		TEntityName extends StorableEntityName,
		TEntityPassword extends StorableEntityPassword,
		TEntityNamePasswordPair extends StorableEntityNamePasswordPair<TEntityName, TEntityPassword, ?>> {

	//region construction

	@Test
	public void pairCanBeCreated() {
		// Act:
		final TEntityName name = this.createEntityName("name");
		final TEntityPassword password = this.createEntityPassword("password");
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair(name, password);

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(name));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(password));
	}

	@Test
	public void pairCanBeCreatedUsingConstructorWithStringParameters() {
		// Act:
		final StorableEntityNamePasswordPair pair = this.createEntityNamePasswordPair("name", "password");

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(this.createEntityName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(this.createEntityPassword("password")));
	}

	//endregion

	//region serialization

	@Test
	public void pairCanBeDeserializedWithAllParameters() {
		// Act:
		// TODO 20150216 J-J: this test is failing.
		final StorableEntityNamePasswordPair pair = this.createPairFromJson("name", "password");

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(this.createEntityName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(this.createEntityPassword("password")));
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
		return this.createEntityNamePasswordPair(Utils.createDeserializer(jsonObject));
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

	protected abstract TEntityNamePasswordPair createEntityNamePasswordPair(final TEntityName name, final TEntityPassword password);

	protected abstract TEntityNamePasswordPair createEntityNamePasswordPair(final String name, final String password);

	protected abstract TEntityNamePasswordPair createEntityNamePasswordPair(final Deserializer deserializer);

	private TEntityName createEntityName(final String name) {
		return this.createEntityNamePasswordPair(name, "xyz").getName();
	}

	private TEntityPassword createEntityPassword(final String password) {
		return this.createEntityNamePasswordPair("xyz", password).getPassword();
	}
}
