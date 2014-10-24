package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.*;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.test.*;

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
	public void ctorThrowsIfPublicKeyIsNotDerivedFromPrivateKey() {
		// Arrange:
		final KeyPairViewModel viewModel = new KeyPairViewModel(new KeyPair(), (byte)0x98);
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);
		jsonObject.replace("privateKey", new KeyPair().getPrivateKey().toString());

		// Act:
		ExceptionAssert.assertThrowsNccException(
				v -> new KeyPairViewModel(new JsonDeserializer(jsonObject, null)),
				NccException.Code.PRIVATE_KEY_PUBLIC_KEY_MISMATCH);
	}

	@Test
	public void ctorThrowsIfAddressIsNotDerivedFromPublicKey() {
		// Arrange:
		final KeyPairViewModel viewModel = new KeyPairViewModel(new KeyPair(), (byte)0x98);
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);
		jsonObject.replace("address", Utils.generateRandomAddress().getEncoded());

		// Act:
		// TODO 20141023 J-B: when asserting NCC exceptions are thrown, use assertThrowsNccException (already fixed but added as an FYI)
		ExceptionAssert.assertThrowsNccException(
				v -> new KeyPairViewModel(new JsonDeserializer(jsonObject, null)),
				NccException.Code.PUBLIC_KEY_ADDRESS_MISMATCH);
	}

	@Test
	public void ctorGivesPrecedenceToAddressMismatchOverKeyMismatch() {
		// Arrange:
		final KeyPairViewModel viewModel = new KeyPairViewModel(new KeyPair(), (byte)0x98);
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);
		jsonObject.replace("privateKey", new KeyPair().getPrivateKey().toString());
		jsonObject.replace("address", Utils.generateRandomAddress().getEncoded());

		// Act:
		ExceptionAssert.assertThrowsNccException(
				v -> new KeyPairViewModel(new JsonDeserializer(jsonObject, null)),
				NccException.Code.PUBLIC_KEY_ADDRESS_MISMATCH);
	}

	@Test
	public void viewModelCanBeRoundTripped() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final KeyPairViewModel originalViewModel = new KeyPairViewModel(keyPair, (byte)0x68);

		// Act
		final KeyPairViewModel viewModel = new KeyPairViewModel(Utils.roundtripSerializableEntity(originalViewModel, null));

		// Assert:
		Assert.assertThat(viewModel.getKeyPair().getPrivateKey(), IsEqual.equalTo(originalViewModel.getKeyPair().getPrivateKey()));
		Assert.assertThat(viewModel.getKeyPair().getPublicKey(), IsEqual.equalTo(originalViewModel.getKeyPair().getPublicKey()));
		Assert.assertThat(viewModel.getNetworkVersion(), IsEqual.equalTo(originalViewModel.getNetworkVersion()));
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
