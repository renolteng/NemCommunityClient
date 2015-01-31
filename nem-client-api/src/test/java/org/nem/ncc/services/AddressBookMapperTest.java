package org.nem.ncc.services;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.viewmodels.AddressBookViewModel;
import org.nem.ncc.test.*;

import java.util.*;

public class AddressBookMapperTest {

	@Test
	public void modelCanBeMappedToViewModel() {
		// Arrange:
		final List<AccountLabel> accountLabels = Arrays.asList(
				createAccountLabel(),
				createAccountLabel(),
				createAccountLabel());
		final AddressBook addressBook = new MemoryAddressBook(
				new AddressBookName("blah"),
				accountLabels);

		// Act:
		final AddressBookViewModel viewModel = new AddressBookMapper().toViewModel(addressBook);

		// Assert:
		Assert.assertThat(viewModel.getName(), IsEqual.equalTo(new AddressBookName("blah")));
		Assert.assertThat(viewModel.getAccountLabels(), IsEquivalent.equivalentTo(addressBook.getAccountLabels()));
	}

	private static AccountLabel createAccountLabel() {
		final Address address = Utils.generateRandomAddress();
		return new AccountLabel(address, address.getEncoded().substring(0, 5), address.getEncoded().substring(5, 10));
	}
}
