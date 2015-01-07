package org.nem.ncc.services;

import org.nem.ncc.addressbook.AddressBook;
import org.nem.ncc.controller.viewmodels.AddressBookViewModel;

import java.util.ArrayList;

/**
 * Helper class that is able to map an address book to an AddressBookViewModel.
 */
public class AddressBookMapper {

	/**
	 * Converts the specified model to a view model.
	 *
	 * @param model The model.
	 * @return The view model.
	 */
	public AddressBookViewModel toViewModel(final AddressBook model) {
		return new AddressBookViewModel(
				model.getName(),
				new ArrayList<>(model.getAccountLabels()));
	}
}
