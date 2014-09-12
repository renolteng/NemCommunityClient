package org.nem.ncc.services;

import org.nem.core.crypto.*;
import org.nem.core.messages.*;
import org.nem.core.model.*;
import org.nem.core.serialization.AccountLookup;
import org.nem.core.time.*;
import org.nem.core.utils.StringEncoder;
import org.nem.ncc.controller.requests.*;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.wallet.*;

/**
 * Helper class that is able to map a TransactionViewModel to a Transaction.
 */
public class TransactionMapper {
	private final WalletServices walletServices;
	private final AccountLookup accountLookup;
	private final TimeProvider timeProvider;

	/**
	 * Creates a new transaction mapper.
	 *
	 * @param walletServices The wallet services.
	 * @param accountLookup The account lookup.
	 * @param timeProvider The time provider.
	 */
	public TransactionMapper(
			final WalletServices walletServices,
			final AccountLookup accountLookup,
			final TimeProvider timeProvider) {
		this.walletServices = walletServices;
		this.accountLookup = accountLookup;
		this.timeProvider = timeProvider;
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final TransferSendRequest request) {
		final TransferTransaction transaction = this.toModel(request, request.getPassword());
		transaction.setFee(request.getFee());
		return transaction;
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final TransferValidateRequest request) {
		return this.toModel(request, null);
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final TransferImportanceRequest request, final int mode) {
		return toModel(request, request.getPassword(), mode);
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final AccountWalletRequest request, final WalletPassword password, final int mode) {
		final Account sender = this.getSenderAccount(request, password);
		final Account remoteAccount = this.getRemoteAccount(request, password);

		final TimeInstant timeStamp = this.timeProvider.getCurrentTime();
		final ImportanceTransferTransaction transaction = new ImportanceTransferTransaction(
				timeStamp,
				sender,
				mode,
				remoteAccount);

		return transaction;
	}

	private TransferTransaction toModel(final TransferFeeRequest request, final WalletPassword password) {
		final Account sender = this.getSenderAccount(request.toAccountWalletRequest(), password);
		final Account recipient = this.accountLookup.findByAddress(request.getRecipientAddress());
		final Message message = this.createMessage(request, sender, recipient);

		final TimeInstant timeStamp = this.timeProvider.getCurrentTime();
		final TransferTransaction transaction = new TransferTransaction(
				timeStamp,
				sender,
				recipient,
				request.getAmount(),
				message);

		transaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
		return transaction;
	}

	private Account getSenderAccount(final AccountWalletRequest request, final WalletPassword password) {
		final PrivateKey privateKey = this.getSenderWallet(request.getWalletName(), password)
				.getAccountPrivateKey(request.getAccountId());
		return new Account(new KeyPair(privateKey));
	}

	private Account getRemoteAccount(final AccountWalletRequest request, final WalletPassword password) {
		final Wallet wallet = this.getSenderWallet(request.getWalletName(), password);
		final WalletAccount account = wallet.tryGetWalletAccount(request.getAccountId());
		final PrivateKey privateKey = account.getRemoteHarvestingPrivateKey();
		return new Account(new KeyPair(privateKey));
	}

	private Wallet getSenderWallet(final WalletName walletName, final WalletPassword password) {
		final Wallet wallet = null != password
				? this.walletServices.open(new WalletNamePasswordPair(walletName, password))
				: this.walletServices.get(walletName);

		if (null == wallet) {
			throw new IllegalArgumentException("unable to open wallet");
		}

		return wallet;
	}

	private Message createMessage(
			final TransferValidateRequest request,
			final Account sender,
			final Account recipient) {
		final String message = request.getMessage();
		if (null == message) {
			return null;
		}

		final byte[] messageBytes = StringEncoder.getBytes(request.getMessage());
		if (!request.shouldEncrypt()) {
			return new PlainMessage(messageBytes);
		}

		final KeyPair keyPair = recipient.getKeyPair();
		if (null == keyPair) {
			throw new NccException(NccException.Code.NO_PUBLIC_KEY);
		}

		return SecureMessage.fromDecodedPayload(sender, recipient, messageBytes);
	}
}
