package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.JsonSerializer;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class ValidatedTransferViewModelTest {

	@Test
	public void viewModelCanBeCreatedAroundAccountWithPublicKey() {
		// Arrange:
		final Account account = new Account(new KeyPair());
		
		// Act:
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720), account);

		// Assert:
		Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromMicroNem(1720)));
		Assert.assertThat(viewModel.isEncryptionPossible(), IsEqual.equalTo(true));
	}

	@Test
	public void viewModelCanBeCreatedAroundAccountWithoutPublicKey() {
		// Arrange:
		final Account account = new Account(Address.fromEncoded("abc"));

		// Act:
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720), account);

		// Assert:
		Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromMicroNem(1720)));
		Assert.assertThat(viewModel.isEncryptionPossible(), IsEqual.equalTo(false));
	}

	@Test
	public void viewModelCanBeCreatedAroundNullAccount() {
		// Act:
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720), null);

		// Assert:
		Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromMicroNem(1720)));
		Assert.assertThat(viewModel.isEncryptionPossible(), IsEqual.equalTo(false));
	}

	@Test
	public void viewModelCanBeSerializedWhenEncryptionIsPossible() {
		// Arrange:
		final Account account = new Account(new KeyPair());
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720), account);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
		Assert.assertThat(jsonObject.get("encryptionPossible"), IsEqual.equalTo(1));
	}

	@Test
	public void viewModelCanBeSerializedWhenEncryptionIsNotPossible() {
		// Arrange:
		final ValidatedTransferViewModel viewModel = new ValidatedTransferViewModel(Amount.fromMicroNem(1720), null);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(1720L));
		Assert.assertThat(jsonObject.get("encryptionPossible"), IsEqual.equalTo(0));
	}
}