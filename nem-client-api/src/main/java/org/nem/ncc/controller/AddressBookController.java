package org.nem.ncc.controller;

import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.AddressBookNamePasswordBag;
import org.nem.ncc.controller.viewmodels.AddressBookViewModel;
import org.nem.ncc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to the REST resource "addressbook".
 */
@RestController
public class AddressBookController {
	private final AddressBookServices addressBookServices;
	private final AddressBookMapper addressBookMapper;

	/**
	 * Creates a new address book controller.
	 *
	 * @param addressBookServices The address book services.
	 * @param addressBookMapper The address book mapper.
	 */
	@Autowired(required = true)
	public AddressBookController(
			final AddressBookServices addressBookServices,
			final AddressBookMapper addressBookMapper) {
		this.addressBookServices = addressBookServices;
		this.addressBookMapper = addressBookMapper;
	}

	//region create / open / info / close

	/**
	 * Creates a new address book with one account in it. The address book is stored
	 * encrypted in the configured address book directory. The name of the address book file
	 * is the encoded (URL encoded) version of the address book name. The provided
	 * password is used to encrypt the address book.
	 *
	 * @param pair The address book name and password pair.
	 * @return A view of the created address book.
	 */
	@RequestMapping(value = "/addressbook/create", method = RequestMethod.POST)
	public AddressBookViewModel create(@RequestBody final AddressBookNamePasswordPair pair) {
		final AddressBook addressBook = this.addressBookServices.create(pair);
		return this.addressBookMapper.toViewModel(addressBook);
	}

	/**
	 * Open a addressBook. The address book is defined by the addressBook name. The address book has
	 * to be located in the address book storage location. The password must match the
	 * password of the given address book.
	 * TODO 20150107 BR: import not implemented yet.
	 * For opening an address book at a different
	 * location the function importAddressBook has to be used.
	 *
	 * @param pair The address book name and password pair.
	 * @return A view of the opened address book.
	 */
	@RequestMapping(value = "/addressbook/open", method = RequestMethod.POST)
	public AddressBookViewModel open(@RequestBody final AddressBookNamePasswordPair pair) {
		final AddressBook addressBook = this.addressBookServices.open(pair);
		return this.addressBookMapper.toViewModel(addressBook);
	}

	/**
	 * Returns information about a address book that is already open.
	 *
	 * @param name The address book name.
	 * @return A view of the address book.
	 */
	@RequestMapping(value = "/addressbook/info", method = RequestMethod.POST)
	public AddressBookViewModel info(@RequestBody final AddressBookName name) {
		final AddressBook addressBook = this.addressBookServices.get(name);
		return this.addressBookMapper.toViewModel(addressBook);
	}

	/**
	 * Closes a address book by removing it from the list of opened address books.
	 *
	 * @param name The address book name.
	 */
	@RequestMapping(value = "/addressbook/close", method = RequestMethod.POST)
	public void close(@RequestBody final AddressBookName name) {
		this.addressBookServices.close(name);
	}

	//endregion

	//region changePassword / changeName

	/**
	 * Changes the password of a address book.
	 *
	 * @param bag The request parameters.
	 */
	@RequestMapping(value = "/addressbook/password/change", method = RequestMethod.POST)
	public void changePassword(@RequestBody final AddressBookNamePasswordBag bag) {
		this.addressBookServices.move(bag, new AddressBookNamePasswordPair(bag.getName(), bag.getNewPassword()));
	}

	/**
	 * Changes the password of a address book.
	 *
	 * @param bag The request parameters.
	 */
	@RequestMapping(value = "/addressbook/name/change", method = RequestMethod.POST)
	public void changeName(@RequestBody final AddressBookNamePasswordBag bag) {
		this.addressBookServices.move(bag, new AddressBookNamePasswordPair(bag.getNewName(), bag.getPassword()));
	}

	//endregion
}
