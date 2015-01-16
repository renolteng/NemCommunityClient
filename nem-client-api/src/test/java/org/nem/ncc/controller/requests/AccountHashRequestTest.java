package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.utils.HexEncoder;
import org.nem.ncc.test.Utils;

public class AccountHashRequestTest {
	private static final Hash TESTING_HASH = Hash.fromHexString("0123456789");

	//region constructor

	@Test
	public void requestCanBeCreated() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountHashRequest request = new AccountHashRequest(address, TESTING_HASH);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getHash(), IsEqual.equalTo(TESTING_HASH));
	}

	@Test
	public void requestCanBeCreatedWithoutHash() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountHashRequest request = new AccountHashRequest(address, null);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getHash(), IsNull.nullValue());
	}

	//endregion

	//region serialization

	@Test
	public void requestCanBeRoundTripped() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountHashRequest request = this.createRequestFromJson(address.getEncoded(), TESTING_HASH);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getHash(), IsEqual.equalTo(TESTING_HASH));
	}

	@Test
	public void requestCanBeRoundTrippedWithoutHash() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountHashRequest request = this.createRequestFromJson(address.getEncoded(), null);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getHash(), IsNull.nullValue());
	}

	//endregion

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundInvalidAccountId() {
		// Act:
		new AccountHashRequest(Address.fromEncoded("FOO"), null);
	}

	@Test(expected = CryptoException.class)
	public void requestCannotBeCreatedAroundInvalidHash() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", Utils.generateRandomAddress().getEncoded());
		jsonObject.put("hash", "zxcvb");

		// Act:
		new AccountHashRequest(new JsonDeserializer(jsonObject, null));
	}

	private AccountHashRequest createRequestFromJson(
			final String address,
			final Hash hash) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("hash", hash == null ? null : HexEncoder.getString(hash.getRaw()));
		return new AccountHashRequest(new JsonDeserializer(jsonObject, null));
	}
}