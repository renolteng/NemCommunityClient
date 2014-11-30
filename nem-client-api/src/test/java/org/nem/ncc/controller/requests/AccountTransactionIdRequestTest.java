package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.ncc.test.Utils;

public class AccountTransactionIdRequestTest {
	private static final Long TRANSACTION_ID = 123L;

	//region constructor

	@Test
	public void requestCanBeCreated() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountTransactionIdRequest request = new AccountTransactionIdRequest(address, TRANSACTION_ID);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTransactionId(), IsEqual.equalTo(TRANSACTION_ID));
	}

	@Test
	public void requestCanBeCreatedWithoutTransactionId() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountTransactionIdRequest request = new AccountTransactionIdRequest(address, null);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTransactionId(), IsNull.nullValue());
	}

	//endregion

	//region serialization

	@Test
	public void requestCanBeRoundTripped() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountTransactionIdRequest request = this.createRequestFromJson(address.getEncoded(), TRANSACTION_ID);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTransactionId(), IsEqual.equalTo(TRANSACTION_ID));
	}

	@Test
	public void requestCanBeRoundTrippedWithoutTransactionId() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountTransactionIdRequest request = this.createRequestFromJson(address.getEncoded(), null);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTransactionId(), IsNull.nullValue());
	}

	//endregion

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundInvalidAccountId() {
		// Act:
		new AccountTransactionIdRequest(Address.fromEncoded("FOO"), null);
	}

	@Test(expected = NumberFormatException.class)
	public void requestCannotBeCreatedAroundInvalidTransactionId() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", Utils.generateRandomAddress().getEncoded());
		jsonObject.put("transactionId", "zzz");

		// Act:
		new AccountTransactionIdRequest(new JsonDeserializer(jsonObject, null));
	}

	private AccountTransactionIdRequest createRequestFromJson(
			final String address,
			final Long transactionId) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("transactionId", transactionId == null ? null : transactionId.toString());
		return new AccountTransactionIdRequest(new JsonDeserializer(jsonObject, null));
	}
}
