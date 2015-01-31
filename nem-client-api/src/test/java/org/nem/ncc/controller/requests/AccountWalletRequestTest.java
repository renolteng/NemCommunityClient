package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.WalletName;

import java.util.*;
import java.util.function.Consumer;

public class AccountWalletRequestTest {
	private final Address TEST_ADDRESS = Utils.generateRandomAddress();
	private final WalletName TEST_WALLET_NAME = new WalletName("wal");

	@Test
	public void requestCanBeCreated() {
		// Act:
		final AccountWalletRequest request = new AccountWalletRequest(this.TEST_ADDRESS, this.TEST_WALLET_NAME);

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
	}

	@Test
	public void requestCanBeDeserialized() {
		// Act:
		final AccountWalletRequest request = this.createRequestFromJson(this.TEST_ADDRESS.getEncoded(), this.TEST_WALLET_NAME.toString());

		// Assert:
		Assert.assertThat(request.getAddress(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createRequestFromJson(address.getEncoded(), null),
				v -> this.createRequestFromJson(null, "wal"));

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
		new AccountWalletRequest(Address.fromEncoded("FOO"), null);
	}

	private AccountWalletRequest createRequestFromJson(
			final String address,
			final String walletName) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("wallet", walletName);
		return new AccountWalletRequest(new JsonDeserializer(jsonObject, null));
	}
}