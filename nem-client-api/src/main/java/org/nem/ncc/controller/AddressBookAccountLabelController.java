package org.nem.ncc.controller;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.AddressBookNamePasswordBag;
import org.nem.ncc.controller.viewmodels.AddressBookViewModel;
import org.nem.ncc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to the account labels in an address book.
 */
@RestController
public class AddressBookAccountLabelController {
	private final AddressBookServices addressBookServices;
	private final AddressBookMapper addressBookMapper;

	@Autowired(required = true)
	public AddressBookAccountLabelController(
			final AddressBookServices addressBookServices,
			final AddressBookMapper addressBookMapper) {
		this.addressBookServices = addressBookServices;
		this.addressBookMapper = addressBookMapper;
	}

	/**
	 * Adds an account label to the address book.
	 *
	 * @param bag The request parameters.
	 * @return The new account label.
	 */
	@RequestMapping(value = "/addressbook/accountlabel/add", method = RequestMethod.POST)
	public AccountLabel addAccountLabel(@RequestBody final AddressBookNamePasswordBag bag) {
		final AccountLabel accountLabel = new AccountLabel(bag.getAddress(), bag.getPublicLabel(), bag.getPrivateLabel());
		final AddressBook addressBook = this.addressBookServices.open(bag);
		addressBook.addLabel(accountLabel);
		return accountLabel;
	}

	/**
	 * Removes an account label from the address book.
	 *
	 * @param bag The request parameters.
	 * @return The address book view model.
	 */
	@RequestMapping(value = "/addressbook/accountlabel/remove", method = RequestMethod.POST)
	public AddressBookViewModel removeAccountLabel(@RequestBody final AddressBookNamePasswordBag bag) {
		final AddressBook addressBook = this.addressBookServices.open(bag);
		addressBook.removeLabel(bag.getAddress());
		return this.addressBookMapper.toViewModel(addressBook);
	}

	/**
	 * Changes an account label in the address book.
	 *
	 * @param bag The request parameters.
	 * @return The new account label.
	 */
	@RequestMapping(value = "/addressbook/accountlabel/change", method = RequestMethod.POST)
	public AccountLabel changeAccountLabel(@RequestBody final AddressBookNamePasswordBag bag) {
		final AddressBook addressBook = this.addressBookServices.open(bag);
		addressBook.setLabel(bag.getAddress(), bag.getPublicLabel(), bag.getPrivateLabel());
		return addressBook.getLabel(bag.getAddress());
	}

	/**
	 * Gets an account label from the address book.
	 *
	 * @param bag The request parameters.
	 * @return The account label.
	 */
	@RequestMapping(value = "/addressbook/accountlabel/find", method = RequestMethod.POST)
	public AccountLabel findAccountLabel(@RequestBody final AddressBookNamePasswordBag bag) {
		final AddressBook addressBook = this.addressBookServices.open(bag);
		return addressBook.getLabel(bag.getAddress());
	}
}
