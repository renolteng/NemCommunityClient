package org.nem.ncc.controller;

import org.nem.core.connect.*;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.model.ncc.NemRequestResult;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.connector.PrimaryNisConnector;
import org.nem.ncc.controller.annotations.RequiresTrustedNis;
import org.nem.ncc.controller.requests.BootNodeRequest;
import org.nem.ncc.services.WalletServices;
import org.nem.ncc.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to the REST resource "node".
 */
@RestController
public class NodeController {
	private final WalletServices walletServices;
	private final PrimaryNisConnector nisConnector;

	/**
	 * Creates a new node controller.
	 *
	 * @param walletServices The wallet services.
	 * @param nisConnector The NIS connector.
	 */
	@Autowired(required = true)
	public NodeController(
			final WalletServices walletServices,
			final PrimaryNisConnector nisConnector) {
		this.walletServices = walletServices;
		this.nisConnector = nisConnector;
	}

	/**
	 * Boots the local node.
	 *
	 * @param bootNodeRequest Information about the node to boot.
	 */
	@RequestMapping(value = "/node/boot", method = RequestMethod.POST)
	@RequiresTrustedNis
	public void bootNode(@RequestBody final BootNodeRequest bootNodeRequest) {
		final Wallet wallet = this.walletServices.get(bootNodeRequest.getWalletName());
		final PrivateKey privateKey = wallet.getAccountPrivateKey(bootNodeRequest.getAddress());
		this.nisConnector.voidPost(NisApiId.NIS_REST_NODE_BOOT, this.createBootNodeRequest(privateKey, bootNodeRequest.getNodeName()));
	}

	private HttpPostRequest createBootNodeRequest(final PrivateKey privateKey, final String nodeName) {
		final JsonSerializer serializer = new JsonSerializer();
		serializer.writeObject("identity", childSerializer -> {
			childSerializer.writeBigInteger("private-key", privateKey.getRaw());
			childSerializer.writeString("name", nodeName);
		});
		serializer.writeObject("endpoint", childSerializer -> {
			childSerializer.writeString("protocol", "http");
			childSerializer.writeString("host", "localhost");
			childSerializer.writeInt("port", 7890);
		});
		serializer.writeObject(
				"metaData",
				childSerializer -> childSerializer.writeString("application", "NIS"));

		return new HttpJsonPostRequest(serializer.getObject());
	}

	/**
	 * Checks the status of the local node.
	 */
	@RequestMapping(value = "/node/status", method = RequestMethod.GET)
	public NemRequestResult checkNodeStatus() {
		return new NemRequestResult(this.nisConnector.get(NisApiId.NIS_REST_STATUS, null));
	}
}
