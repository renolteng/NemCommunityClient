package org.nem.ncc.controller.viewmodels;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.time.*;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.WalletName;

import java.util.*;
import java.util.stream.Collectors;

public class WalletViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final AccountViewModel primaryAccount = createAccountViewModel();
		final List<AccountViewModel> otherAccounts = Arrays.asList(
				createAccountViewModel(),
				createAccountViewModel(),
				createAccountViewModel());
		final WalletViewModel viewModel = new WalletViewModel(
				new WalletName("blah"),
				primaryAccount,
				otherAccounts,
				new TimeInstant(62));

		// Assert:
		Assert.assertThat(viewModel.getName(), IsEqual.equalTo(new WalletName("blah")));
		Assert.assertThat(viewModel.getPrimaryAccount(), IsSame.sameInstance(primaryAccount));
		Assert.assertThat(viewModel.getOtherAccounts(), IsSame.sameInstance(otherAccounts));
		Assert.assertThat(viewModel.getUpdateTime(), IsEqual.equalTo(new TimeInstant(62)));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final AccountViewModel primaryAccount = createAccountViewModel();
		final List<AccountViewModel> otherAccounts = Arrays.asList(
				createAccountViewModel(),
				createAccountViewModel(),
				createAccountViewModel());
		final WalletViewModel viewModel = new WalletViewModel(
				new WalletName("blah"),
				primaryAccount,
				otherAccounts,
				new TimeInstant(62));

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(4));
		Assert.assertThat(jsonObject.get("name"), IsEqual.equalTo("blah"));
		Assert.assertThat(getAddress(jsonObject.get("primaryAccount")), IsEqual.equalTo(primaryAccount.getAddress().toString()));
		Assert.assertThat(
				getAddresses(jsonObject.get("otherAccounts")),
				IsEquivalent.equivalentTo(
						otherAccounts.stream()
								.map(wa -> wa.getAddress().toString())
								.collect(Collectors.toList())));
		Assert.assertThat(jsonObject.get("lastRefresh"), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 62 * 1000));
	}

	private static AccountViewModel createAccountViewModel() {
		return Utils.createAccountViewModelFromAddress(Utils.generateRandomAddress());
	}

	private static String getAddress(final Object object) {
		return (String)((JSONObject)object).get("address");
	}

	private static List<String> getAddresses(final Object object) {
		final JSONArray jsonAccounts = (JSONArray)object;
		return jsonAccounts.stream().map(WalletViewModelTest::getAddress).collect(Collectors.toList());
	}
}