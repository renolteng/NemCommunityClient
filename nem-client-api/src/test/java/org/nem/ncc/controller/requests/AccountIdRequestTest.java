package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.Utils;

import java.math.BigInteger;

public class AccountIdRequestTest {

	@Test
	public void requestCanBeCreatedAroundValidAccountId() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountIdRequest request = new AccountIdRequest(address);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundNullAccountId() {
		// Act:
		new AccountIdRequest((Address)null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundInvalidAccountId() {
		// Act:
		new AccountIdRequest(Address.fromEncoded("FOO"));
	}

	@Test
	public void requestCanBeDeserializedGivenValidAccountId() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountIdRequest request = this.createRequestFromJson(address.getEncoded());

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void requestCannotBeDeserializedGivenNullAccountId() {
		// Act:
		this.createRequestFromJson(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeDeserializedGivenInvalidAccountId() {
		// Act:
		this.createRequestFromJson("Foo");
	}

	@Test
	public void requestCanBeRoundTripped() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final AccountIdRequest originalRequest = new AccountIdRequest(address);
		final Deserializer deserializer = Utils.roundtripSerializableEntity(originalRequest, null);

		// Act:
		final AccountIdRequest request = new AccountIdRequest(deserializer);

		// Assert::
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
	}

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final AccountIdRequest request = new AccountIdRequest(address);

		// Assert:
		Assert.assertThat(request, IsEqual.equalTo(new AccountIdRequest(address)));
		Assert.assertThat(request, IsEqual.equalTo(new AccountIdRequest(Address.fromEncoded(address.getEncoded()))));
		Assert.assertThat(request, IsNot.not(IsEqual.equalTo(new AccountIdRequest(Utils.generateRandomAddress()))));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(request)));
		Assert.assertThat(new BigInteger("1235"), IsNot.not(IsEqual.equalTo((Object)request)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final AccountIdRequest request = new AccountIdRequest(address);
		final int hashCode = request.hashCode();

		// Assert:
		Assert.assertThat(hashCode, IsEqual.equalTo(new AccountIdRequest(address).hashCode()));
		Assert.assertThat(hashCode, IsEqual.equalTo(new AccountIdRequest(Address.fromEncoded(address.getEncoded())).hashCode()));
		Assert.assertThat(hashCode, IsNot.not(IsEqual.equalTo(new AccountIdRequest(Utils.generateRandomAddress()).hashCode())));
	}

	//endregion
	private AccountIdRequest createRequestFromJson(final String address) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		return new AccountIdRequest(new JsonDeserializer(jsonObject, null));
	}
}