package org.nem.ncc.wallet;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.StorableEntityPasswordTest;
import org.nem.ncc.test.Utils;

public class WalletPasswordTest extends StorableEntityPasswordTest {

	@Override
	protected WalletPassword createEntityPassword(final String name) {
		return new WalletPassword(name);
	}

	// TODO 20150115 J-B: any reason you don't want to test serialization / deserialization in the base classes?
	// > applies to all serialization / deserialization tests not in test base classes
	// TODO 20150116 BR -> J: The base class doesn't know how to construct a WalletPassword. I would need to supply a constructor to the base class.
	// You think that would be better?

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