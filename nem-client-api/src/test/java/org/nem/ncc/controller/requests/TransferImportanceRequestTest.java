package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.*;

import java.util.*;
import java.util.function.Consumer;

public class TransferImportanceRequestTest {
	private final Address TEST_ADDRESS = Utils.generateRandomAddress();
	private final WalletName TEST_WALLET_NAME = new WalletName("wal");
	private final WalletPassword TEST_WALLET_PASS = new WalletPassword("From forth the fatal loins");
	private final int TEST_HOURS_DUE = 8;

	@Test
	public void requestCanBeCreated() {
		// Act:
		final TransferImportanceRequest request = this.createRequest();

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
		Assert.assertThat(request.getWalletPassword(), IsEqual.equalTo(this.TEST_WALLET_PASS));
		Assert.assertThat(request.getHoursDue(), IsEqual.equalTo(this.TEST_HOURS_DUE));
	}

	@Test
	public void requestCanBeDeserialized() {
		// Act:
		final TransferImportanceRequest request = this.createRequestFromJson(
				this.TEST_ADDRESS.getEncoded(),
				this.TEST_WALLET_NAME.toString(),
				this.TEST_WALLET_PASS.toString(),
				this.TEST_HOURS_DUE);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
		Assert.assertThat(request.getWalletPassword(), IsEqual.equalTo(this.TEST_WALLET_PASS));
		Assert.assertThat(request.getHoursDue(), IsEqual.equalTo(this.TEST_HOURS_DUE));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createRequestFromJson(address.getEncoded(), null, null, 0),
				v -> this.createRequestFromJson(null, "wal", null, 0),
				v -> this.createRequestFromJson(address.getEncoded(), "wal", null, 0));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(
					v -> action.accept(null),
					SerializationException.class);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void requestCannotBeCreatedAroundInvalidAccountId() {
		// Act:
		new AccountWalletPasswordRequest(Address.fromEncoded("FOO"), null, null);
	}

	private TransferImportanceRequest createRequest() {
		return new TransferImportanceRequest(
				this.TEST_ADDRESS,
				this.TEST_WALLET_NAME,
				this.TEST_WALLET_PASS,
				this.TEST_HOURS_DUE);
	}

	private TransferImportanceRequest createRequestFromJson(
			final String address,
			final String walletName,
			final String walletPassword,
			final int hoursDue) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("wallet", walletName);
		jsonObject.put("password", walletPassword);
		jsonObject.put("hoursDue", hoursDue);
		return new TransferImportanceRequest(new JsonDeserializer(jsonObject, null));
	}
}
