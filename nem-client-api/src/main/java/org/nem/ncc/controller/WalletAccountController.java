package org.nem.ncc.controller;

import org.nem.core.crypto.*;
import org.nem.core.model.*;
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
		final WalletAccount account = new WalletAccount();
		this.addToAddressBook(bag, account.getAddress());
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
		this.addToAddressBook(bag, Address.fromPublicKey(new KeyPair(bag.getAccountPrivateKey()).getPublicKey()));
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
		this.removeFromAddressBook(bag);
		final Wallet wallet = this.walletServices.open(bag);
		wallet.removeAccount(bag.getAccountAddress());
		return this.walletMapper.toViewModel(wallet);
	}

	/**
	 * Reveals details about an account including the private key.
	 *
	 * @param bag The request parameters.
	 * @return Details about the account.
	 */
	@RequestMapping(value = "/wallet/account/reveal", method = RequestMethod.POST)
	public KeyPairViewModel revealAccount(@RequestBody final WalletNamePasswordBag bag) {
		final Wallet wallet = this.walletServices.open(bag);
		final PrivateKey privateKey = wallet.getAccountPrivateKey(bag.getAccountAddress());
		// TODO 20150315 J-G: shouldn't this be getDefault?
		// > also, should add test for this
		return new KeyPairViewModel(new KeyPair(privateKey), NetworkInfos.getMainNetworkInfo().getVersion());
	}

	private void addToAddressBook(
			final WalletNamePasswordBag bag,
			final Address address) {
		final AddressBook addressBook = this.addressBookServices.open(new AddressBookNamePasswordPair(
				new AddressBookName(bag.getName().toString()),
				new AddressBookPassword(bag.getPassword().toString())));
		if (addressBook.contains(address)) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_BOOK_ALREADY_CONTAINS_ADDRESS);
		}

		final AccountLabel accountLabel = new AccountLabel(address, "", bag.getLabel());
		addressBook.addLabel(accountLabel);
	}

	private void removeFromAddressBook(final WalletNamePasswordBag bag) {
		final AddressBook addressBook = this.addressBookServices.open(new AddressBookNamePasswordPair(
				new AddressBookName(bag.getName().toString()),
				new AddressBookPassword(bag.getPassword().toString())));
		if (addressBook.contains(bag.getAccountAddress())) {
			addressBook.removeLabel(bag.getAccountAddress());
		}
	}

	private AccountViewModel addAccount(final WalletNamePasswordBag bag, final WalletAccount account) {
		final Wallet wallet = this.walletServices.open(bag);
		wallet.addOtherAccount(account);
		return this.accountMapper.toViewModel(account);
	}
}
