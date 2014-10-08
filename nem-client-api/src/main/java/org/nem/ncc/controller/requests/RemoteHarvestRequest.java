package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.*;

// TODO 20141005 J-G do we actually need this class?
// > remote NIS is already working and i can't imagine a scenario
// > where someone would want to connect remotely to one nis and harvest remotely on another
// > (well at least a good scenario)
//
// TODO 20141006 G-J: /me neither - can't think of such scenario :}
// anyway later we might want to provide some "round-robin" for servers, that will be
// used for accessing wallet via remote server, and the same probably shouldn't
// happen in case of harvesting (I mean round-robin), as if user is harvesting on one machine,
// he should probably stick to it (otherwise I think we could quickly have problems with
// locking/unlocking)
//
// TODO 20141007 J-G: i agree that this would make sense if we had round-robining
// but since we don't we really don't have a need for it now
// i usually prefer to not add code that we don't need
// since this is already here (as long as its tested), it's probably not harmful to keep

/**
 * A view model
 */
public class RemoteHarvestRequest extends AccountWalletPasswordRequest {
	private NodeEndpoint endpoint;

	/**
	 * Creates remote harvest request.
	 *
	 * @param address The account address.
	 * @param walletName The wallet name.
	 * @param password The password.
	 * @param endpoint URL of the endpoint node
	 */
	public RemoteHarvestRequest(final Address address, 
			final WalletName walletName, 
			final WalletPassword password, 
			final NodeEndpoint endpoint) {
		super(address, walletName, password);
		this.endpoint = endpoint;
	}
	
	/**
	 * Deserializes remote harvest request.
	 *
	 * @param deserializer The deserializer.
	 */
	public RemoteHarvestRequest(final Deserializer deserializer) {
		super(deserializer);
		this.endpoint = NodeEndpoint.fromHost(deserializer.readString("host"));
	}

	/**
	 * Gets the endpoint.
	 *
	 * @return The endpoint.
	 */
	public NodeEndpoint getEndpoint() {
		return endpoint;
	}
}
