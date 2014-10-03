package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.*;

public class RemoteHarvestRequest extends AccountWalletRequest {
	private final WalletPassword password;
	private NodeEndpoint endpoint;

	/**
	 * Creates a password / account / wallet / endpoint URL request.
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
		super(address, walletName);
		this.password = password;
		this.endpoint = endpoint;
	}
	
	/**
	 * Deserializes a password / account / wallet / endpoint URL request.
	 *
	 * @param deserializer The deserializer.
	 */
	public RemoteHarvestRequest(final Deserializer deserializer) {
		super(deserializer);
		this.password = WalletPassword.readFrom(deserializer, "password");
		this.endpoint = NodeEndpoint.fromHost(deserializer.readString("host"));
	}

	/**
	 * Gets the wallet password.
	 *
	 * @return The wallet password.
	 */
	public WalletPassword getPassword() {
		return this.password;
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
