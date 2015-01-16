package org.nem.ncc.controller.viewmodels;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final List<AccountLabel> accountLabels = Arrays.asList(
				createAccountLabel(),
				createAccountLabel(),
				createAccountLabel());
		final AddressBookViewModel viewModel = new AddressBookViewModel(
				new AddressBookName("blah"),
				accountLabels);

		// Assert:
		Assert.assertThat(viewModel.getName(), IsEqual.equalTo(new AddressBookName("blah")));
		Assert.assertThat(viewModel.getAccountLabels(), IsSame.sameInstance(accountLabels));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final List<AccountLabel> accountLabels = Arrays.asList(
				createAccountLabel(),
				createAccountLabel(),
				createAccountLabel());
		final AddressBookViewModel viewModel = new AddressBookViewModel(
				new AddressBookName("blah"),
				accountLabels);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonObject.get("addressBook"), IsEqual.equalTo("blah"));
		Assert.assertThat(getAccountLabels((JSONArray)jsonObject.get("accountLabels")), IsEquivalent.equivalentTo(accountLabels));
	}

	private static AccountLabel createAccountLabel() {
		final Address address = Utils.generateRandomAddress();
		return new AccountLabel(address, address.getEncoded().substring(0, 5), address.getEncoded().substring(5, 10));
	}

	// TODO 20150115 J-B: consider refactoring as this appears to be in multiple places
	private static List<AccountLabel> getAccountLabels(final JSONArray array) {
		return array.stream()
				.map(o -> {
					final JSONObject object = (JSONObject)o;
					return new AccountLabel(
							Address.fromEncoded((String)object.get("address")),
							(String)object.get("publicLabel"),
							(String)object.get("privateLabel"));
				})
				.collect(Collectors.toList());
	}
}
