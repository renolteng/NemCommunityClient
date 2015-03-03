package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.Utils;

public class AccountDatabaseIdRequestTest {
	private static final Long TRANSACTION_ID = 123L;

	//region constructor

	@Test
	public void requestCanBeCreated() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountDatabaseIdRequest request = new AccountDatabaseIdRequest(address, TRANSACTION_ID);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getDatabaseId(), IsEqual.equalTo(TRANSACTION_ID));
	}

	@Test
	public void requestCanBeCreatedWithoutDatabaseId() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountDatabaseIdRequest request = new AccountDatabaseIdRequest(address, null);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getDatabaseId(), IsNull.nullValue());
	}

	//endregion

	//region serialization

	@Test
	public void requestCanBeDeserialized() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountDatabaseIdRequest request = this.createRequestFromJson(address.getEncoded(), TRANSACTION_ID);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getDatabaseId(), IsEqual.equalTo(TRANSACTION_ID));
	}

	@Test
	public void requestCanBeDeserializedWithoutDatabaseId() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountDatabaseIdRequest request = this.createRequestFromJson(address.getEncoded(), null);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(address));
		Assert.assertThat(request.getDatabaseId(), IsNull.nullValue());
	}

	//endregion

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundInvalidAccountId() {
		// Act:
		new AccountDatabaseIdRequest(Address.fromEncoded("FOO"), null);
	}

	@Test(expected = TypeMismatchException.class)
	public void requestCannotBeCreatedAroundInvalidDatabaseId() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", Utils.generateRandomAddress().getEncoded());
		jsonObject.put("id", "zzz");

		// Act:
		new AccountDatabaseIdRequest(new JsonDeserializer(jsonObject, null));
	}

	private AccountDatabaseIdRequest createRequestFromJson(
			final String address,
			final Long databaseId) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("id", databaseId);
		return new AccountDatabaseIdRequest(new JsonDeserializer(jsonObject, null));
	}
}
