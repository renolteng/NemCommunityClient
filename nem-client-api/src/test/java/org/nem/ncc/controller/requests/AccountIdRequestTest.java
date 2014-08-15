package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.Utils;

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

	private AccountIdRequest createRequestFromJson(final String address) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		return new AccountIdRequest(new JsonDeserializer(jsonObject, null));
	}
}