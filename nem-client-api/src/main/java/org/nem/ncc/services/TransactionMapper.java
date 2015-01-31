package org.nem.ncc.services;

import org.nem.core.crypto.*;
import org.nem.core.messages.*;
import org.nem.core.model.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.AccountLookup;
import org.nem.core.time.*;
import org.nem.core.utils.StringEncoder;
import org.nem.ncc.controller.requests.*;
import org.nem.ncc.controller.viewmodels.*;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.wallet.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class that is able to map a TransactionViewModel to a Transaction.
 * TODO 20150131 J-G: i guess we need some new tests for this class?
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
		final Transaction transaction = this.toModel(request, request.getPassword());
		return transaction;
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final MultisigSignatureRequest request) {
		final Transaction transaction = this.toModel(request, request.getPassword());
		return transaction;
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final MultisigModificationRequest request) {
		final Transaction transaction = this.toModel(request, request.getPassword());
		return transaction;
	}

	/**
	 * Converts the specified request to a view model.
	 * TODO 20141025 J-J: not sure if this makes sense in this class
	 *
	 * @param request The request.
	 * @return The view model.
	 */
	public PartialTransferInformationViewModel toViewModel(final PartialTransferInformationRequest request) {
		// use a fake accounts so that encryption and signing are always possible
		final Account dummyAccount = new Account(new KeyPair());
		final Message message = this.createMessage(request.getMessage(), request.shouldEncrypt(), dummyAccount, dummyAccount);

		// if the amount isn't provided, assume a zero amount
		final Amount amount = null == request.getAmount() ? Amount.ZERO : request.getAmount();
		final TransferTransaction transaction = new TransferTransaction(
				TimeInstant.ZERO,
				dummyAccount,
				dummyAccount,
				amount,
				message);
		final MultisigTransaction multisigTransaction = new MultisigTransaction(
				TimeInstant.ZERO,
				dummyAccount,
				transaction
		);

		final boolean isEncryptionSupported =
				null != request.getRecipientAddress() && this.accountLookup.findByAddress(request.getRecipientAddress()).hasPublicKey();
		return new PartialTransferInformationViewModel(
				transaction.getFee(),
				multisigTransaction.getFee(),
				isEncryptionSupported);
	}

	public PartialFeeInformationViewModel toViewModel(final PartialSignatureInformationRequest request) {
		final Account cosignatory = this.accountLookup.findByAddress(request.getCosignatoryAddress());
		final MultisigSignatureTransaction transaction = new MultisigSignatureTransaction(
				TimeInstant.ZERO,
				cosignatory,
				cosignatory, // DUMMY
				HashUtils.nextHash(Hash.ZERO, (new KeyPair()).getPublicKey()));
		return new PartialFeeInformationViewModel(
				transaction.getFee()
		);
	}

	public PartialFeeInformationViewModel toViewModel(final PartialModificationInformationRequest request) {
		final Account multisig = this.accountLookup.findByAddress(request.getMultisigAddress());

		final List<MultisigModification> modifications = request.getCosignatoriesAddresses().stream()
				.map(o -> this.accountLookup.findByAddress(o))
				.map(o -> new MultisigModification(MultisigModificationType.Add, o))
				.collect(Collectors.toList());

		final MultisigAggregateModificationTransaction transaction = new MultisigAggregateModificationTransaction(
				TimeInstant.ZERO,
				multisig,
				modifications
		);

		return new PartialFeeInformationViewModel(transaction.getFee());
	}

	/**
	 * Converts the specified request to a model.
	 *
	 * @param request The request.
	 * @return The model.
	 */
	public Transaction toModel(final TransferImportanceRequest request, final ImportanceTransferTransaction.Mode mode) {
		final Account sender = this.getSenderAccount(request.getWalletName(), request.getAddress(), request.getPassword());
		final Account remoteAccount = this.getRemoteAccount(request.getWalletName(), request.getAddress(), request.getPassword());

		final TimeInstant timeStamp = this.timeProvider.getCurrentTime();
		final ImportanceTransferTransaction transaction = new ImportanceTransferTransaction(
				timeStamp,
				sender,
				mode,
				remoteAccount);

		transaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
		return transaction;
	}

	private Transaction toModel(final TransferSendRequest request, final WalletPassword password) {
		final boolean isMultisig = request.getType() == TransactionViewModel.Type.Multisig_Transfer.getValue();
		final Account signer = this.getSenderAccount(request.getWalletName(), request.getSenderAddress(), password);
		final Account recipient = this.accountLookup.findByAddress(request.getRecipientAddress());
		final Message message = this.createMessage(request.getMessage(), request.shouldEncrypt(), signer, recipient);

		final TimeInstant timeStamp = this.timeProvider.getCurrentTime();

		if (isMultisig) {
			final Account multisig = this.accountLookup.findByAddress(request.getMultisigAddress());
			final TransferTransaction transaction = new TransferTransaction(
					timeStamp,
					multisig,
					recipient,
					request.getAmount(),
					message);
			transaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
			transaction.setFee(request.getFee());

			final MultisigTransaction multisigTransaction = new MultisigTransaction(timeStamp,
					signer,
					transaction);
			multisigTransaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
			multisigTransaction.setFee(request.getMultisigFee());
			return multisigTransaction;
		} else {
			final TransferTransaction transaction = new TransferTransaction(
					timeStamp,
					signer,
					recipient,
					request.getAmount(),
					message);
			transaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
			transaction.setFee(request.getFee());
			return transaction;
		}
	}

	private Transaction toModel(final MultisigSignatureRequest request, final WalletPassword password) {
		final Account signer = this.getSenderAccount(request.getWalletName(), request.getSenderAddress(), password);
		final Account multisigAccount = this.accountLookup.findByAddress(request.getMultisigAddress());
		final TimeInstant timeStamp = this.timeProvider.getCurrentTime();

		final MultisigSignatureTransaction multisigTransaction = new MultisigSignatureTransaction(
				timeStamp,
				signer,
				multisigAccount,
				request.getInnerTransactionHash());
		multisigTransaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
		multisigTransaction.setFee(request.getFee());
		return multisigTransaction;
	}

	private Transaction toModel(final MultisigModificationRequest request, final WalletPassword password) {
		final Account signer = this.getSenderAccount(request.getWalletName(), request.getSenderAddress(), password);
		final TimeInstant timeStamp = this.timeProvider.getCurrentTime();

		final List<MultisigModification> modifications = request.getCosignatoriesAddresses().stream()
				.map(o -> this.accountLookup.findByAddress(o))
				.map(o -> new MultisigModification(MultisigModificationType.Add, o))
				.collect(Collectors.toList());

		final MultisigAggregateModificationTransaction multisigTransaction = new MultisigAggregateModificationTransaction(
				timeStamp,
				signer,
				modifications);
		multisigTransaction.setDeadline(timeStamp.addHours(request.getHoursDue()));
		multisigTransaction.setFee(request.getFee());
		return multisigTransaction;
	}

	private Account getSenderAccount(final WalletName walletName, final Address accountId, final WalletPassword password) {
		final PrivateKey privateKey = this.getSenderWallet(walletName, password).getAccountPrivateKey(accountId);
		return new Account(new KeyPair(privateKey));
	}

	private Account getRemoteAccount(final WalletName walletName, final Address accountId, final WalletPassword password) {
		final Wallet wallet = this.getSenderWallet(walletName, password);
		final WalletAccount account = wallet.tryGetWalletAccount(accountId);
		final PrivateKey privateKey = account.getRemoteHarvestingPrivateKey();
		return new Account(new KeyPair(privateKey));
	}

	private Wallet getSenderWallet(final WalletName walletName, final WalletPassword password) {
		final Wallet wallet = this.walletServices.open(new WalletNamePasswordPair(walletName, password));
		if (null == wallet) {
			throw new IllegalArgumentException("unable to open wallet");
		}

		return wallet;
	}

	private Message createMessage(
			final String message,
			final boolean shouldEncrypt,
			final Account sender,
			final Account recipient) {
		if (null == message) {
			return null;
		}

		final byte[] messageBytes = StringEncoder.getBytes(message);
		if (!shouldEncrypt) {
			return new PlainMessage(messageBytes);
		}

		if (!recipient.hasPublicKey()) {
			throw new NccException(NccException.Code.NO_PUBLIC_KEY);
		}

		return SecureMessage.fromDecodedPayload(sender, recipient, messageBytes);
	}
}
