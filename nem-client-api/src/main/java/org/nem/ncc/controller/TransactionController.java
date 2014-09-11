package org.nem.ncc.controller;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.crypto.Signer;
import org.nem.core.model.*;
import org.nem.core.model.ncc.NemRequestResult;
import org.nem.core.serialization.BinarySerializer;
import org.nem.ncc.connector.PrimaryNisConnector;
import org.nem.ncc.controller.requests.*;
import org.nem.ncc.controller.viewmodels.FeeViewModel;
import org.nem.ncc.exceptions.NisException;
import org.nem.ncc.services.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to the REST resource "wallet/account/transaction".
 */
@RestController
public class TransactionController {
	private final TransactionMapper transactionMapper;
	private final PrimaryNisConnector nisConnector;

	/**
	 * Creates a new transaction controller.
	 *
	 * @param transactionMapper The transaction mapper.
	 * @param nisConnector The NIS connector.
	 */
	@Autowired(required = true)
	public TransactionController(
			final TransactionMapper transactionMapper,
			final PrimaryNisConnector nisConnector) {
		this.transactionMapper = transactionMapper;
		this.nisConnector = nisConnector;
	}

	/**
	 * Sends a new transaction (i.e., sending NEM, messages, assets).
	 *
	 * @param transferRequest The transaction information.
	 */
	@RequestMapping(value = "/wallet/account/transaction/send", method = RequestMethod.POST)
	public void sendTransaction(@RequestBody final TransferSendRequest transferRequest) {
		// prepare transaction
		final Transaction transaction = this.transactionMapper.toModel(transferRequest);
		final byte[] transferBytes = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());
		final RequestPrepare preparedTransaction = new RequestPrepare(transferBytes);

		// sign transaction and send to nis
		final Signer signer = new Signer(transaction.getSigner().getKeyPair());
		final RequestAnnounce announce = new RequestAnnounce(
				preparedTransaction.getData(),
				signer.sign(preparedTransaction.getData()).getBytes());
		final NemRequestResult result = new NemRequestResult(this.nisConnector.post(
				NisApiId.NIS_REST_TRANSACTION_ANNOUNCE,
				new HttpJsonPostRequest(announce)));
		if (result.isError()) {
			throw new NisException(result);
		}
	}

	/**
	 * Request the calculation of the minimum fee for sending the transaction
	 *
	 * @param request The transaction information.
	 * @return The minimum fee.
	 */
	@RequestMapping(value = "/wallet/account/transaction/fee", method = RequestMethod.POST)
	public FeeViewModel getMinimumFee(@RequestBody final TransferFeeRequest request) {
		return new FeeViewModel(this.transactionMapper.toModel(request).getFee());
	}
}
