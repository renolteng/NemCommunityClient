package org.nem.console.models;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;

public class AliasedKeyPairTest {
	private static final PrivateKey PRIVATE_KEY = PrivateKey.fromHexString("7248b184821b5be4a15f4176254371ddb5443f6d1f19aed5ed46bdc86895721f");
	private static final Address ADDRESS = Address.fromEncoded("TALXZK4FQVDCKK77YXPXFOQOUI2V74FQ5XU45YC7");

	@Test
	public void canCreateAliasedKeyPair() {
		// Act:
		final AliasedKeyPair pair = new AliasedKeyPair("NICE", new KeyPair(PRIVATE_KEY));

		// Assert:
		Assert.assertThat(pair.alias(), IsEqual.equalTo("NICE"));
		Assert.assertThat(pair.address(), IsEqual.equalTo(ADDRESS));
		Assert.assertThat(pair.keyPair().getPrivateKey(), IsEqual.equalTo(PRIVATE_KEY));
	}

	@Test
	public void canDeserializeAliasedKeyPair() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("alias", "NICE");
		jsonObject.put("sk", PRIVATE_KEY.toString());

		// Act:
		final AliasedKeyPair pair = deserialize(jsonObject);

		// Assert:
		Assert.assertThat(pair.alias(), IsEqual.equalTo("NICE"));
		Assert.assertThat(pair.address(), IsEqual.equalTo(ADDRESS));
		Assert.assertThat(pair.keyPair().getPrivateKey(), IsEqual.equalTo(PRIVATE_KEY));
	}

	@Test
	public void canSerializeAliasedKeyPair() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer(true);
		final AliasedKeyPair pair = new AliasedKeyPair("NICE", new KeyPair(PRIVATE_KEY));

		// Act:
		pair.serialize(serializer);
		final JSONObject jsonObject = serializer.getObject();

		// Assert:
		assertNumSerializedProperties(jsonObject, 2);
		Assert.assertThat(jsonObject.get("alias"), IsEqual.equalTo("NICE"));
		Assert.assertThat(jsonObject.get("sk"), IsEqual.equalTo(PRIVATE_KEY.toString()));
	}

	@Test
	public void canRoundTripAliasedKeyPair() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer(true);
		final AliasedKeyPair originalPair = new AliasedKeyPair("NICE", new KeyPair(PRIVATE_KEY));

		// Act:
		originalPair.serialize(serializer);
		final AliasedKeyPair pair = deserialize(serializer.getObject());

		// Assert:
		Assert.assertThat(pair.alias(), IsEqual.equalTo("NICE"));
		Assert.assertThat(pair.address(), IsEqual.equalTo(ADDRESS));
		Assert.assertThat(pair.keyPair().getPrivateKey(), IsEqual.equalTo(PRIVATE_KEY));
	}

	private static void assertNumSerializedProperties(final JSONObject jsonObject, final int numExpected) {
		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(numExpected + 1));
		Assert.assertThat(jsonObject.containsKey("_propertyOrderArray"), IsEqual.equalTo(true));
	}

	private static AliasedKeyPair deserialize(final JSONObject jsonObject) {
		return new AliasedKeyPair(new JsonDeserializer(jsonObject, null));
	}
}