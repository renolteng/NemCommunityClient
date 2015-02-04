package org.nem.ncc.controller.viewmodels;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.test.*;

import java.util.List;
import java.util.stream.*;

public class AccountViewModelTest {

	@Test
	public void viewModelCanBeCreatedAroundExplicitParameters() {
		// Arrange:
		final AccountInfo account = createAccountInfo(null, Address.fromEncoded("xyz"));
		final List<AccountInfo> multisigAccounts = createAccountInfos();

		// Act:
		final AccountViewModel viewModel = createAccountViewModel(account, multisigAccounts);

		// Assert:
		Assert.assertThat(viewModel.getAddress(), IsEqual.equalTo(Address.fromEncoded("xyz")));
		Assert.assertThat(viewModel.getRemoteStatus(), IsEqual.equalTo(AccountRemoteStatus.INACTIVE));
		Assert.assertThat(viewModel.getBalance(), IsEqual.equalTo(Amount.fromNem(271)));
		Assert.assertThat(viewModel.getHarvestedBlocks(), IsEqual.equalTo(new BlockAmount(3)));
		Assert.assertThat(viewModel.getImportance(), IsEqual.equalTo(3.7));
		Assert.assertThat(viewModel.getPublicKey(), IsNull.nullValue());
		Assert.assertThat(viewModel.getStatus(), IsEqual.equalTo(AccountStatus.LOCKED));
		Assert.assertThat(viewModel.getMultisigAccounts(), IsEquivalent.equivalentTo(multisigAccounts));
	}

	@Test
	public void viewModelCanBeCreatedAroundAccountWithPublicKey() {
		// Arrange:
		final Address address = Utils.generateRandomAddressWithPublicKey();
		final AccountInfo account = createAccountInfo(null, address);

		// Act:
		final AccountViewModel viewModel = createAccountViewModel(account, createAccountInfos());

		// Assert:
		Assert.assertThat(viewModel.getPublicKey(), IsNull.notNullValue());
		Assert.assertThat(viewModel.getPublicKey(), IsEqual.equalTo(address.getPublicKey()));
	}

	@Test
	public void viewModelCanBeCreatedAroundAccountMetaDataPair() {
		// Arrange:
		final AccountInfo account = createAccountInfo(null, Address.fromEncoded("xyz"));
		final List<AccountInfo> multisigAccounts = createAccountInfos();
		final AccountMetaDataPair pair = new AccountMetaDataPair(
				account,
				new AccountMetaData(AccountStatus.LOCKED, AccountRemoteStatus.INACTIVE, multisigAccounts));

		// Act:
		final AccountViewModel viewModel = new AccountViewModel(pair);

		// Assert:
		Assert.assertThat(viewModel.getAddress(), IsEqual.equalTo(Address.fromEncoded("xyz")));
		Assert.assertThat(viewModel.getRemoteStatus(), IsEqual.equalTo(AccountRemoteStatus.INACTIVE));
		Assert.assertThat(viewModel.getBalance(), IsEqual.equalTo(Amount.fromNem(271)));
		Assert.assertThat(viewModel.getHarvestedBlocks(), IsEqual.equalTo(new BlockAmount(3)));
		Assert.assertThat(viewModel.getImportance(), IsEqual.equalTo(3.7));
		Assert.assertThat(viewModel.getPublicKey(), IsNull.nullValue());
		Assert.assertThat(viewModel.getStatus(), IsEqual.equalTo(AccountStatus.LOCKED));
		Assert.assertThat(viewModel.getMultisigAccounts(), IsEquivalent.equivalentTo(multisigAccounts));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final Address address = Utils.generateRandomAddressWithPublicKey();
		final List<AccountInfo> multisigAccounts = createAccountInfos();
		final AccountInfo account = createAccountInfo(null, address);

		final AccountViewModel viewModel = createAccountViewModel(account, multisigAccounts);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(8));
		Assert.assertThat(jsonObject.get("address"), IsEqual.equalTo(account.getAddress().getEncoded()));
		Assert.assertThat(jsonObject.get("remoteStatus"), IsEqual.equalTo("INACTIVE"));
		Assert.assertThat(jsonObject.get("publicKey"), IsEqual.equalTo(address.getPublicKey().toString()));
		Assert.assertThat(jsonObject.get("balance"), IsEqual.equalTo(271000000L));
		Assert.assertThat(jsonObject.get("importance"), IsEqual.equalTo(3.7));
		Assert.assertThat(jsonObject.get("harvestedBlocks"), IsEqual.equalTo(3L));
		Assert.assertThat(jsonObject.get("status"), IsEqual.equalTo("LOCKED"));
		assertCosignatoriesMatch((JSONArray)jsonObject.get("multisigAccounts"), multisigAccounts);
	}

	@Test
	public void viewModelCanBeWithoutMultisigAccounts() {
		// Arrange:
		final Address address = Utils.generateRandomAddressWithPublicKey();
		final AccountInfo account = createAccountInfo(null, address);

		final AccountViewModel viewModel = createAccountViewModel(account, null);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(7));
		Assert.assertThat(jsonObject.get("address"), IsEqual.equalTo(account.getAddress().getEncoded()));
		Assert.assertThat(jsonObject.get("remoteStatus"), IsEqual.equalTo("INACTIVE"));
		Assert.assertThat(jsonObject.get("publicKey"), IsEqual.equalTo(address.getPublicKey().toString()));
		Assert.assertThat(jsonObject.get("balance"), IsEqual.equalTo(271000000L));
		Assert.assertThat(jsonObject.get("importance"), IsEqual.equalTo(3.7));
		Assert.assertThat(jsonObject.get("harvestedBlocks"), IsEqual.equalTo(3L));
		Assert.assertThat(jsonObject.get("status"), IsEqual.equalTo("LOCKED"));
		Assert.assertThat(jsonObject.get("multisigAccounts"), IsNull.nullValue());
	}

	private static void assertCosignatoriesMatch(final JSONArray jsonCosignatories, final List<AccountInfo> originalCosignatories) {
		Assert.assertThat(jsonCosignatories.size(), IsEqual.equalTo(originalCosignatories.size()));
		for (int i = 0; i < jsonCosignatories.size(); ++i) {
			assertAccountInfoMatches((JSONObject)jsonCosignatories.get(i), originalCosignatories.get(i));
		}
	}

	private static void assertAccountInfoMatches(final JSONObject jsonAccountInfo, final AccountInfo originalAccountInfo) {
		Assert.assertThat(jsonAccountInfo.size(), IsEqual.equalTo(6));
		Assert.assertThat(jsonAccountInfo.get("address"), IsEqual.equalTo(originalAccountInfo.getAddress().getEncoded()));
		Assert.assertThat(jsonAccountInfo.get("publicKey"), IsNull.nullValue());
		Assert.assertThat(jsonAccountInfo.get("balance"), IsEqual.equalTo(originalAccountInfo.getBalance().getNumMicroNem()));
		Assert.assertThat(jsonAccountInfo.get("importance"), IsEqual.equalTo(originalAccountInfo.getImportance()));
		Assert.assertThat(jsonAccountInfo.get("harvestedBlocks"), IsEqual.equalTo(originalAccountInfo.getNumHarvestedBlocks().getRaw()));
		Assert.assertThat(jsonAccountInfo.get("label"), IsEqual.equalTo(originalAccountInfo.getLabel()));
	}

	private static AccountViewModel createAccountViewModel(final AccountInfo account, final List<AccountInfo> multisigAccounts) {
		return new AccountViewModel(
				account,
				AccountStatus.LOCKED,
				AccountRemoteStatus.INACTIVE,
				multisigAccounts);
	}

	private static AccountInfo createAccountInfo(final String label, final Address address) {
		return new AccountInfo(
				address,
				Amount.fromNem(271),
				new BlockAmount(3),
				label,
				3.7);
	}

	private static List<AccountInfo> createAccountInfos() {
		return IntStream.range(0, 3)
				.mapToObj(i -> createAccountInfo(String.format("cosignatory%d", i + 1), Utils.generateRandomAddress()))
				.collect(Collectors.toList());
	}
}