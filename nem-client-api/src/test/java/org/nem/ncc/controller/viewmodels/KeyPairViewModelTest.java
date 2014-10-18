package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;
import org.nem.core.serialization.JsonSerializer;

public class KeyPairViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();

		// Act:
		final KeyPairViewModel viewModel = new KeyPairViewModel(keyPair, (byte)17);

		// Assert:
		Assert.assertThat(viewModel.getKeyPair(), IsEqual.equalTo(keyPair));
		Assert.assertThat(viewModel.getNetworkVersion(), IsEqual.equalTo((byte)17));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final KeyPairViewModel viewModel = new KeyPairViewModel(keyPair, (byte)17);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(3));
		Assert.assertThat(jsonObject.get("privateKey"), IsEqual.equalTo(keyPair.getPrivateKey().toString()));
		Assert.assertThat(jsonObject.get("publicKey"), IsEqual.equalTo(keyPair.getPublicKey().toString()));
		Assert.assertThat(jsonObject.get("address"), IsEqual.equalTo(Address.fromPublicKey((byte)17, keyPair.getPublicKey()).toString()));
	}
}
