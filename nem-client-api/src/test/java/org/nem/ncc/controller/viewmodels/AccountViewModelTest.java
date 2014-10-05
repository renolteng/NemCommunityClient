package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.model.AccountLabel;
import org.nem.ncc.test.Utils;

public class AccountViewModelTest {

	@Test
	public void viewModelCanBeCreatedAroundAccountAndStatus() {
		// Arrange:
		final AccountInfo account = createAccountInfo(null, Address.fromEncoded("xyz"));

		// Act:
		final AccountViewModel viewModel = new AccountViewModel(account, AccountStatus.LOCKED, null);

		// Assert:
		Assert.assertThat(viewModel.getAddress(), IsEqual.equalTo(Address.fromEncoded("xyz")));
		Assert.assertThat(viewModel.getLabel(), IsNull.nullValue());
		Assert.assertThat(viewModel.getRemoteStatus(), IsEqual.equalTo(AccountRemoteStatus.INACTIVE));
		Assert.assertThat(viewModel.getBalance(), IsEqual.equalTo(Amount.fromNem(271)));
		Assert.assertThat(viewModel.getForagedBlocks(), IsEqual.equalTo(new BlockAmount(3)));
		Assert.assertThat(viewModel.getImportance(), IsEqual.equalTo(3.7));
		Assert.assertThat(viewModel.getPublicKey(), IsNull.nullValue());
		Assert.assertThat(viewModel.getStatus(), IsEqual.equalTo(AccountStatus.LOCKED));
	}

	@Test
	public void viewModelCanBeCreatedWithLabel() {
		// Arrange:
		final AccountInfo account = createAccountInfo("my acc", Address.fromEncoded("xyz"));

		// Act:
		final AccountViewModel viewModel = new AccountViewModel(
				account,
				AccountStatus.LOCKED,
				new AccountLabel(account.getAddress(), "my pub label", "my pri label"));

		// Assert:
		Assert.assertThat(viewModel.getLabel(), IsEqual.equalTo("my pri label"));
	}

	@Test
	public void viewModelCanBeCreatedAroundAccountWithPublicKey() {
		// Arrange:
		final Address address = Utils.generateRandomAddressWithPublicKey();
		final AccountInfo account = createAccountInfo(null, address);

		// Act:
		final AccountViewModel viewModel = new AccountViewModel(account, AccountStatus.LOCKED, null);

		// Assert:
		Assert.assertThat(viewModel.getPublicKey(), IsNull.notNullValue());
		Assert.assertThat(viewModel.getPublicKey(), IsEqual.equalTo(address.getPublicKey()));
	}

	@Test
	public void viewModelCanBeCreatedAroundAccountMetaDataPair() {
		// Arrange:
		final AccountInfo account = createAccountInfo(null, Address.fromEncoded("xyz"));

		final AccountMetaDataPair pair = new AccountMetaDataPair(
				account,
				new AccountMetaData(AccountStatus.LOCKED));

		// Act:
		final AccountViewModel viewModel = new AccountViewModel(pair, null);

		// Assert:
		Assert.assertThat(viewModel.getAddress(), IsEqual.equalTo(Address.fromEncoded("xyz")));
		Assert.assertThat(viewModel.getLabel(), IsNull.nullValue());
		Assert.assertThat(viewModel.getRemoteStatus(), IsEqual.equalTo(AccountRemoteStatus.INACTIVE));
		Assert.assertThat(viewModel.getBalance(), IsEqual.equalTo(Amount.fromNem(271)));
		Assert.assertThat(viewModel.getForagedBlocks(), IsEqual.equalTo(new BlockAmount(3)));
		Assert.assertThat(viewModel.getImportance(), IsEqual.equalTo(3.7));
		Assert.assertThat(viewModel.getPublicKey(), IsNull.nullValue());
		Assert.assertThat(viewModel.getStatus(), IsEqual.equalTo(AccountStatus.LOCKED));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final Address address = Utils.generateRandomAddressWithPublicKey();
		final AccountInfo account = createAccountInfo(null, address);

		final AccountViewModel viewModel = new AccountViewModel(
				account,
				AccountStatus.LOCKED,
				new AccountLabel(account.getAddress(), "my pub label", "my pri label"));

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(8));
		Assert.assertThat(jsonObject.get("address"), IsEqual.equalTo(account.getAddress().getEncoded()));
		Assert.assertThat(jsonObject.get("label"), IsEqual.equalTo("my pri label"));
		Assert.assertThat(jsonObject.get("remoteStatus"), IsEqual.equalTo("INACTIVE"));
		Assert.assertThat(jsonObject.get("publicKey"), IsEqual.equalTo(address.getPublicKey().toString()));
		Assert.assertThat(jsonObject.get("balance"), IsEqual.equalTo(271000000L));
		Assert.assertThat(jsonObject.get("importance"), IsEqual.equalTo(3.7));
		Assert.assertThat(jsonObject.get("foragedBlocks"), IsEqual.equalTo(3L));
		Assert.assertThat(jsonObject.get("status"), IsEqual.equalTo("LOCKED"));
	}

	@Test
	public void viewModelCanBeSerializedWithoutLabel() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final Account account = new Account(keyPair);
		final AccountViewModel viewModel = new AccountViewModel(
				Utils.createAccountInfoFromAddress(account.getAddress()),
				AccountStatus.LOCKED,
				null);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(8));
		Assert.assertThat(jsonObject.get("label"), IsNull.nullValue());
	}

	private static AccountInfo createAccountInfo(final String label, final Address address) {
		return new AccountInfo(
				address,
				Amount.fromNem(271),
				new BlockAmount(3),
				AccountRemoteStatus.INACTIVE,
				label,
				3.7);
	}
}