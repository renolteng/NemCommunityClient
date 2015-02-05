package org.nem.ncc.controller;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.WalletNamePasswordBag;
import org.nem.ncc.controller.viewmodels.*;
import org.nem.ncc.services.*;
import org.nem.ncc.wallet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletAccountController {
	private final WalletServices walletServices;
	private final WalletMapper walletMapper;
	private final AccountMapper accountMapper;

	private final AddressBookServices addressBookServices;

	/**
	 * Handles requests related to the REST resource "wallet/account".
	 */
	@Autowired(required = true)
	public WalletAccountController(
			final WalletServices walletServices,
			final WalletMapper walletMapper,
			final AccountMapper accountMapper,
			final AddressBookServices addressBookServices) {
		this.walletServices = walletServices;
		this.walletMapper = walletMapper;
		this.accountMapper = accountMapper;
		this.addressBookServices = addressBookServices;
	}

	/**
	 * Adds a new account to the wallet.
	 *
	 * @param bag The request parameters.
	 * @return A view of the new account.
	 */
	@RequestMapping(value = "/wallet/account/new", method = RequestMethod.POST)
	public AccountViewModel addNewAccount(@RequestBody final WalletNamePasswordBag bag) {
		// TODO 20150131 J-B: previously i could set a label when adding an account?
		// > did we lose that functionality?
		// TODO 20150202 BR -> J: not sure if we had that feature. But I think an optional label parameter in WalletNamePasswordBag would be good (see also below).
		final WalletAccount account = new WalletAccount();
		this.addToAddressBook(
				new AddressBookName(bag.getName().toString()),
				new AddressBookPassword(bag.getPassword().toString()),
				account.getAddress(),
				bag.getLabel());
		return this.addAccount(bag, account);
	}

	/**
	 * Adds an existing account to the wallet.
	 *
	 * @param bag The request parameters.
	 * @return A view of the new account.
	 */
	@RequestMapping(value = "/wallet/account/add", method = RequestMethod.POST)
	public AccountViewModel addExistingAccount(@RequestBody final WalletNamePasswordBag bag) {
		// TODO 20150131 J-B: it also seems like labels are REQUIRED now; is that intentional?
		// TODO 20150202 BR -> G: Do you want the label to be optional or required? If optional we could just add it to WalletNamePasswordBag.
		this.addToAddressBook(
				new AddressBookName(bag.getName().toString()),
				new AddressBookPassword(bag.getPassword().toString()),
				Address.fromPublicKey(new KeyPair(bag.getAccountPrivateKey()).getPublicKey()),
				bag.getLabel());
		return this.addAccount(bag, new WalletAccount(bag.getAccountPrivateKey()));
	}

	/**
	 * Sets an existing account to be the primary account.
	 *
	 * @param bag The request parameters.
	 * @return A view of the updated wallet.
	 */
	@RequestMapping(value = "/wallet/account/primary", method = RequestMethod.POST)
	public WalletViewModel setPrimaryAccount(@RequestBody final WalletNamePasswordBag bag) {
		final Wallet wallet = this.walletServices.open(bag);
		wallet.setPrimaryAccount(bag.getAccountAddress());
		return this.walletMapper.toViewModel(wallet);
	}

	/**
	 * Removes an existing account from a wallet.
	 *
	 * @param bag The request parameters.
	 * @return A view of the updated wallet.
	 */
	@RequestMapping(value = "/wallet/account/remove", method = RequestMethod.POST)
	public WalletViewModel removeAccount(@RequestBody final WalletNamePasswordBag bag) {
		this.removeFromAddressBook(
				new AddressBookName(bag.getName().toString()),
				new AddressBookPassword(bag.getPassword().toString()),
				bag.getAccountAddress());
		final Wallet wallet = this.walletServices.open(bag);
		wallet.removeAccount(bag.getAccountAddress());
		return this.walletMapper.toViewModel(wallet);
	}

	private void addToAddressBook(
			final AddressBookName name,
			final AddressBookPassword password,
			final Address address,
			final String label) {
		final AddressBook addressBook = this.addressBookServices.open(new AddressBookNamePasswordPair(name, password));
		if (addressBook.contains(address)) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_BOOK_ALREADY_CONTAINS_ADDRESS);
		}

		final AccountLabel accountLabel = new AccountLabel(address, "", label);
		addressBook.addLabel(accountLabel);
	}

	private void removeFromAddressBook(
			final AddressBookName name,
			final AddressBookPassword password,
			final Address address) {
		final AddressBook addressBook = this.addressBookServices.open(new AddressBookNamePasswordPair(name, password));
		if (addressBook.contains(address)) {
			addressBook.removeLabel(address);
		}
	}

	private AccountViewModel addAccount(final WalletNamePasswordBag bag, final WalletAccount account) {
		final Wallet wallet = this.walletServices.open(bag);
		wallet.addOtherAccount(account);
		return this.accountMapper.toViewModel(account);
	}

}
