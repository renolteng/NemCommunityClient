package org.nem.ncc.wallet;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.test.ExceptionAssert;

import java.util.*;
import java.util.function.Consumer;

public class WalletNamePasswordPairTest {

	//region construction

	@Test
	public void pairCanBeCreated() {
		// Act:
		final WalletNamePasswordPair pair = new WalletNamePasswordPair(
				new WalletName("name"),
				new WalletPassword("password"));

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(new WalletName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(new WalletPassword("password")));
	}

	@Test
	public void pairCanBeCreatedUsingConstructorWithStringParameters() {
		// Act:
		final WalletNamePasswordPair pair = new WalletNamePasswordPair("name", "password");

		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(new WalletName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(new WalletPassword("password")));
	}

	//endregion

	//region serialization

	@Test
	public void pairCanBeDeserializedWithAllParameters() {
		// Act:
		final WalletNamePasswordPair pair = this.createPairFromJson("name", "password");
		// Assert:
		Assert.assertThat(pair.getName(), IsEqual.equalTo(new WalletName("name")));
		Assert.assertThat(pair.getPassword(), IsEqual.equalTo(new WalletPassword("password")));
	}

	@Test
	public void pairCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createPairFromJson(null, "pass"),
				v -> this.createPairFromJson("wal", null));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(
					v -> action.accept(null),
					SerializationException.class);
		}
	}

	private WalletNamePasswordPair createPairFromJson(final String name, final String password) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", name);
		jsonObject.put("password", password);
		return new WalletNamePasswordPair(new JsonDeserializer(jsonObject, null));
	}

	//endregion

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final WalletNamePasswordPair pair = new WalletNamePasswordPair("name", "password");

		// Assert:
		Assert.assertThat(new WalletNamePasswordPair("name", "password"), IsEqual.equalTo(pair));
		Assert.assertThat(new WalletNamePasswordPair("lame", "password"), IsNot.not(IsEqual.equalTo(pair)));
		Assert.assertThat(new WalletNamePasswordPair("name", "word"), IsNot.not(IsEqual.equalTo(pair)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(pair)));
		Assert.assertThat("name", IsNot.not(IsEqual.equalTo((Object)pair)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange::
		final WalletNamePasswordPair pair = new WalletNamePasswordPair("name", "password");
		final int hashCode = pair.hashCode();

		// Assert:
		Assert.assertThat(new WalletNamePasswordPair("name", "password").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new WalletNamePasswordPair("lame", "password").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(new WalletNamePasswordPair("name", "word").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	//endregion
}