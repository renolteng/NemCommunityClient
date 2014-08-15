package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.time.*;
import org.nem.ncc.test.Utils;

public class AccountTimeStampRequestTest {
	private static final long TWENTY_FIVE_SECONDS_AFTER_EPOCH = SystemTimeProvider.getEpochTimeMillis() + 25 * 1000;

	//region constructor

	@Test
	public void requestCanBeCreated() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountTimeStampRequest request = new AccountTimeStampRequest(address, TWENTY_FIVE_SECONDS_AFTER_EPOCH);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTimeStamp(), IsEqual.equalTo(new TimeInstant(25)));
	}

	@Test
	public void requestCanBeCreatedWithoutTimeStamp() {
		// Act:
		final Address address = Utils.generateRandomAddress();
		final AccountTimeStampRequest request = new AccountTimeStampRequest(address, null);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTimeStamp(), IsNull.nullValue());
	}

	//endregion

	//region serialization

	@Test
	public void requestCanBeRoundTripped() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountTimeStampRequest request = this.createRequestFromJson(address.getEncoded(), TWENTY_FIVE_SECONDS_AFTER_EPOCH);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTimeStamp(), IsEqual.equalTo(new TimeInstant(25)));
	}

	@Test
	public void requestCanBeRoundTrippedWithoutTimeStamp() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();

		// Act:
		final AccountTimeStampRequest request = this.createRequestFromJson(address.getEncoded(), null);

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(address));
		Assert.assertThat(request.getTimeStamp(), IsNull.nullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundInvalidAccountId() {
		// Act:
		new AccountTimeStampRequest(Address.fromEncoded("FOO"), null);
	}

	//endregion

	private AccountTimeStampRequest createRequestFromJson(
			final String address,
			final Long timeStamp) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("timeStamp", timeStamp);
		return new AccountTimeStampRequest(new JsonDeserializer(jsonObject, null));
	}
}