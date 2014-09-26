package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.JsonSerializer;

public class FeeViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720));

		// Assert:
		Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromMicroNem(1720)));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Act:
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720));

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(1));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
	}
}