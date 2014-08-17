package org.nem.ncc.controller;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.*;
import org.nem.ncc.connector.PrimaryNisConnector;
import org.nem.ncc.controller.requests.*;
import org.nem.ncc.controller.viewmodels.*;
import org.nem.ncc.model.NisApiId;
import org.nem.ncc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles requests related to the REST resource "account".
 */
@RestController
public class AccountController {
	private final AccountServices accountServices;
	private final AccountMapper accountMapper;
	private final WalletServices walletServices;
	private final ChainServices chainServices;
	private final PrimaryNisConnector nisConnector;

	/**
	 * Creates a new account controller.
	 *
	 * @param accountServices The account services.
	 * @param accountMapper The account mapper.
	 * @param walletServices The wallet services.
	 * @param chainServices The chain services.
	 * @param nisConnector The NIS connector.
	 */
	@Autowired(required = true)
	public AccountController(
			final AccountServices accountServices,
			final AccountMapper accountMapper,
			final WalletServices walletServices,
			final ChainServices chainServices,
			final PrimaryNisConnector nisConnector) {
		this.accountServices = accountServices;
		this.accountMapper = accountMapper;
		this.walletServices = walletServices;
		this.chainServices = chainServices;
		this.nisConnector = nisConnector;
	}

	/**
	 * Gets information about the specified account.
	 * Looks-up an account which is known by its address id
	 *
	 * @param aidRequest The account identifier.
	 * @return The account information.
	 */
	@RequestMapping(value = "/account/find", method = RequestMethod.POST)
	public AccountViewModel getAccountInfo(@RequestBody final AccountIdRequest aidRequest) {
		final Address address = aidRequest.getAccountId();
		return this.accountMapper.toViewModel(address);
	}

	/**
	 * Gets information about the specified account including transactions.
	 *
	 * @param atsRequest The account identifier.
	 * @return The account information.
	 */
	@RequestMapping(value = "/account/transactions", method = RequestMethod.POST)
	@Deprecated
	public AccountTransactionsPair getAccountTransactions(@RequestBody final AccountTimeStampRequest atsRequest) {
		final Address address = atsRequest.getAccountId();
		final AccountViewModel account = this.getAccountInfo(atsRequest);

		final BlockHeight lastBlockHeight = this.nisConnector.forward(this.chainServices::getLastBlockHeightAsync);
		final List<TransferViewModel> allTransfers = new ArrayList<>();
		allTransfers.addAll(
				this.accountServices.getUnconfirmedTransactions(address).stream()
						.map(t -> new TransferViewModel(t, account.getAddress()))
						.collect(Collectors.toList()));
		allTransfers.addAll(
				this.accountServices.getConfirmedTransactions(address, atsRequest.getTimeStamp()).stream()
						.map(p -> new TransferViewModel(p, account.getAddress(), lastBlockHeight))
						.collect(Collectors.toList()));

		return new AccountTransactionsPair(account, allTransfers);
	}

	/**
	 * Gets information about the specified account and unconfirmed transactions.
	 * TODO: this method doesn't handle any paging now, but it shouldn't be a problem for now.
	 *
	 * @param ahRequest The account identifier.
	 * @return The account information.
	 */
	@RequestMapping(value = "/account/transactions/unconfirmed", method = RequestMethod.POST)
	public AccountTransactionsPair getAccountTransactionsUnconfirmed(@RequestBody final AccountHashRequest ahRequest) {
		final Address address = ahRequest.getAccountId();
		final AccountViewModel account = this.getAccountInfo(ahRequest);

		final List<TransferViewModel> allTransfers = new ArrayList<>();
		allTransfers.addAll(
				this.accountServices.getUnconfirmedTransactions(address).stream()
						.map(t -> new TransferViewModel(t, account.getAddress()))
						.collect(Collectors.toList()));

		return new AccountTransactionsPair(account, allTransfers);
	}

	private AccountTransactionsPair getAccountTransactions(final TransactionDirection direction, final AccountHashRequest ahRequest) {
		final Address address = ahRequest.getAccountId();
		final AccountViewModel account = this.getAccountInfo(ahRequest);

		final BlockHeight lastBlockHeight = this.nisConnector.forward(this.chainServices::getLastBlockHeightAsync);
		final List<TransferViewModel> allTransfers = new ArrayList<>();

		allTransfers.addAll(
				this.accountServices.getTransactions(direction, address, ahRequest.getHash()).stream()
						.map(p -> new TransferViewModel(p, account.getAddress(), lastBlockHeight))
						.collect(Collectors.toList()));

		return new AccountTransactionsPair(account, allTransfers);
	}

	/**
	 * Gets information about the specified account and incoming and outgoing transactions.
	 *
	 * @param ahRequest The account identifier.
	 * @return The account information.
	 */
	@RequestMapping(value = "/account/transactions/all", method = RequestMethod.POST)
	public AccountTransactionsPair getAccountTransactionsAll(@RequestBody final AccountHashRequest ahRequest) {
		return this.getAccountTransactions(TransactionDirection.ALL, ahRequest);
	}

	/**
	 * Gets information about the specified account and incoming transactions.
	 *
	 * @param ahRequest The account identifier.
	 * @return The account information.
	 */
	@RequestMapping(value = "/account/transactions/incoming", method = RequestMethod.POST)
	public AccountTransactionsPair getAccountTransactionsIncoming(@RequestBody final AccountHashRequest ahRequest) {
		return this.getAccountTransactions(TransactionDirection.INCOMING, ahRequest);
	}

	/**
	 * Gets information about the specified account and outgoing transactions.
	 *
	 * @param ahRequest The account identifier.
	 * @return The account information.
	 */
	@RequestMapping(value = "/account/transactions/outgoing", method = RequestMethod.POST)
	public AccountTransactionsPair getAccountTransactionsOutgoing(@RequestBody final AccountHashRequest ahRequest) {
		return this.getAccountTransactions(TransactionDirection.OUTGOING, ahRequest);
	}

	/**
	 * Retrieves a list of infos on harvested blocks for an account.
	 *
	 * @param atsRequest The account / timestamp view model.
	 * @return The list of harvest infos.
	 */
	@RequestMapping(value = "/account/harvests", method = RequestMethod.POST)
	public SerializableList<HarvestInfoViewModel> getAccountHarvests(@RequestBody final AccountTimeStampRequest atsRequest) {
		final Deserializer deserializer = this.nisConnector.get(NisApiId.NIS_REST_ACCOUNT_HARVESTS, formatHarvestQuery(atsRequest));
		final List<HarvestInfo> harvestInfos = deserializer.readObjectArray("data", HarvestInfo::new);
		return new SerializableList<>(harvestInfos.stream().map(HarvestInfoViewModel::new).collect(Collectors.toList()));
	}

	private static String formatHarvestQuery(final AccountTimeStampRequest atsRequest) {
		final StringBuilder builder = new StringBuilder();
		builder.append("address=");
		builder.append(atsRequest.getAccountId());

		if (null != atsRequest.getTimeStamp()) {
			builder.append("&timeStamp=");
			builder.append(atsRequest.getTimeStamp());
		}

		return builder.toString();
	}

	/**
	 * Unlock the account on the connected NIS server (start foraging)
	 *
	 * @param awRequest The account / wallet view model.
	 */
	@RequestMapping(value = "/wallet/account/unlock", method = RequestMethod.POST)
	public void unlock(@RequestBody final AccountWalletRequest awRequest) {
		this.nisConnector.voidPost(NisApiId.NIS_REST_ACCOUNT_UNLOCK, new HttpJsonPostRequest(this.getPrivateKey(awRequest)));
	}

	/**
	 * Lock the account on the connected NIS server (stop foraging)
	 *
	 * @param awRequest The account / wallet view model.
	 */
	@RequestMapping(value = "/wallet/account/lock", method = RequestMethod.POST)
	public void lock(@RequestBody final AccountWalletRequest awRequest) {
		this.nisConnector.voidPost(NisApiId.NIS_REST_ACCOUNT_LOCK, new HttpJsonPostRequest(this.getPrivateKey(awRequest)));
	}

	private PrivateKey getPrivateKey(final AccountWalletRequest awRequest) {
		return this.walletServices.get(awRequest.getWalletName())
				.getAccountPrivateKey(awRequest.getAccountId());
	}
}
