package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.JsonSerializer;

public class PartialTransferInformationViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final PartialTransferInformationViewModel viewModel = new PartialTransferInformationViewModel(Amount.fromMicroNem(1720), true);

		// Assert:
		Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromMicroNem(1720)));
		Assert.assertThat(viewModel.isEncryptionSupported(), IsEqual.equalTo(true));
	}

	@Test
	public void viewModelCanBeSerializedWhenEncryptionIsSupported() {
		// Arrange:
		final PartialTransferInformationViewModel viewModel = new PartialTransferInformationViewModel(Amount.fromMicroNem(1720), true);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
		Assert.assertThat(jsonObject.get("encryptionSupported"), IsEqual.equalTo(1));
	}

	@Test
	public void viewModelCanBeSerializedWhenEncryptionIsNotSupported() {
		// Arrange:
		final PartialTransferInformationViewModel viewModel = new PartialTransferInformationViewModel(Amount.fromMicroNem(1720), false);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
		Assert.assertThat(jsonObject.get("encryptionSupported"), IsEqual.equalTo(0));
	}
}