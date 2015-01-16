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

public class BootNodeRequestTest {
	private final Address TEST_ADDRESS = Utils.generateRandomAddress();
	private final WalletName TEST_WALLET_NAME = new WalletName("wal");

	@Test
	public void requestCanBeCreated() {
		// Act:
		final BootNodeRequest bootNodeRequest = new BootNodeRequest(this.TEST_ADDRESS, this.TEST_WALLET_NAME, "name");

		// Assert:
		Assert.assertThat(bootNodeRequest.getAddress(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(bootNodeRequest.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
		Assert.assertThat(bootNodeRequest.getNodeName(), IsEqual.equalTo("name"));
	}

	@Test
	public void requestCanBeDeserialized() {
		// Act:
		final BootNodeRequest bootNodeRequest = this.createRequestFromJson(this.TEST_ADDRESS.getEncoded(), this.TEST_WALLET_NAME.toString(), "name");

		// Assert:
		Assert.assertThat(bootNodeRequest.getAddress(), IsEqual.equalTo(this.TEST_ADDRESS));
		Assert.assertThat(bootNodeRequest.getWalletName(), IsEqual.equalTo(this.TEST_WALLET_NAME));
		Assert.assertThat(bootNodeRequest.getNodeName(), IsEqual.equalTo("name"));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createRequestFromJson(address.getEncoded(), null, "name"),
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
		new BootNodeRequest(Address.fromEncoded("FOO"), this.TEST_WALLET_NAME, "name");
	}

	private BootNodeRequest createRequestFromJson(
			final String address,
			final String walletName,
			final String nodeName) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", address);
		jsonObject.put("wallet", walletName);
		jsonObject.put("nodeName", nodeName);
		return new BootNodeRequest(new JsonDeserializer(jsonObject, null));
	}
}