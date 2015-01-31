package org.nem.ncc.controller;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.LabelWalletNamePasswordBag;
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
		return this.addAccount(bag, new WalletAccount());
	}

	/**
	 * Adds an existing account to the wallet.
	 *
	 * @param bag The request parameters.
	 * @return A view of the new account.
	 */
	@RequestMapping(value = "/wallet/account/add", method = RequestMethod.POST)
	public AccountViewModel addExistingAccount(@RequestBody final LabelWalletNamePasswordBag bag) {
		final AddressBook addressBook = this.addressBookServices.open(new AddressBookNamePasswordPair(
				new AddressBookName(bag.getName().toString()), new AddressBookPassword(bag.getPassword().toString())));

		final Address address = Address.fromPublicKey(new KeyPair(bag.getAccountPrivateKey()).getPublicKey());
		final AccountLabel accountLabel = new AccountLabel(address, "", bag.getWalletAccountLabel());
		try {
			addressBook.addLabel(accountLabel);
		} catch (final AddressBookException ex) {
			// it means entry is already in address book we won't update it here.
		}

		return this.addAccount(bag, new WalletAccount(bag.getAccountPrivateKey()));
	}

	private AccountViewModel addAccount(final WalletNamePasswordBag bag, final WalletAccount account) {
		final Wallet wallet = this.walletServices.open(bag);
		wallet.addOtherAccount(account);

		return this.accountMapper.toViewModel(account);
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
		final Wallet wallet = this.walletServices.open(bag);
		wallet.removeAccount(bag.getAccountAddress());
		return this.walletMapper.toViewModel(wallet);
	}
}
