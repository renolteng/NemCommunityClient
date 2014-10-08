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

public class AccountWalletPasswordRequestTest {
	private final Address TEST_ADDRESS = Utils.generateRandomAddress();
	private final WalletName TEST_WALLET_NAME = new WalletName("wal");
	private final WalletPassword TEST_WALLET_PASS = new WalletPassword("From forth the fatal loins");

	@Test
	public void requestCanBeCreated() {
		// Act:
		final AccountWalletPasswordRequest request = this.createRequest();

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
		Assert.assertThat(request.getWalletPassword(), IsEqual.equalTo(this.TEST_WALLET_PASS));
	}

	@Test
	public void requestCanBeDeserialized() {
		// Act:
		final AccountWalletPasswordRequest request = this.createRequestFromJson(this.TEST_ADDRESS.getEncoded(), this.TEST_WALLET_NAME.toString(), this.TEST_WALLET_PASS.toString());

		// Assert:
		Assert.assertThat(request.getAccountId(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createRequestFromJson(address.getEncoded(), null, null),
				v -> this.createRequestFromJson(null, "wal", null),
				v -> this.createRequestFromJson(address.getEncoded(), "wal", null));

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


	private AccountWalletPasswordRequest createRequest() {
		return new AccountWalletPasswordRequest(this.TEST_ADDRESS, this.TEST_WALLET_NAME, this.TEST_WALLET_PASS);
	}

	private AccountWalletPasswordRequest createRequestFromJson(
			final String address,
			final String walletName,
			final String walletPassword) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("wallet", walletName);
		jsonObject.put("password", walletPassword);
		return new AccountWalletPasswordRequest(new JsonDeserializer(jsonObject, null));
	}
}

