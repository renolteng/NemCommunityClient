package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.JsonSerializer;

public class PartialTransferInformationViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final boolean isEncryptionSupported = true;
		final PartialTransferInformationViewModel viewModel = createViewModel(345, isEncryptionSupported);

		// Assert:
		Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromMicroNem(1720)));
		Assert.assertThat(viewModel.getMultisigFee(), IsEqual.equalTo(Amount.fromMicroNem(345)));
		Assert.assertThat(viewModel.isEncryptionSupported(), IsEqual.equalTo(true));
	}

	@Test
	public void viewModelCanBeSerializedWhenEncryptionIsSupported() {
		// Arrange:
		final PartialTransferInformationViewModel viewModel = createViewModel(345, true);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(3));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
		Assert.assertThat(jsonObject.get("multisigFee"), IsEqual.equalTo(345L));
		Assert.assertThat(jsonObject.get("encryptionSupported"), IsEqual.equalTo(1));
	}

	@Test
	public void viewModelCanBeSerializedWhenEncryptionIsNotSupported() {
		// Arrange:
		final PartialTransferInformationViewModel viewModel = createViewModel(777, false);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(3));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
		Assert.assertThat(jsonObject.get("multisigFee"), IsEqual.equalTo(777L));
		Assert.assertThat(jsonObject.get("encryptionSupported"), IsEqual.equalTo(0));
	}

	private static PartialTransferInformationViewModel createViewModel(final int multisigFee, final boolean isEncryptionSupported) {
		return new PartialTransferInformationViewModel(Amount.fromMicroNem(1720), Amount.fromMicroNem(multisigFee), isEncryptionSupported);
	}
}