package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.ncc.wallet.WalletName;

/**
 * A request containing information necessary to boot a node.
 */
public class BootNodeRequest extends AccountWalletRequest {
	private final String nodeName;

	/**
	 * Creates a new boot node view model.
	 *
	 * @param address The account address.
	 * @param walletName The wallet name.
	 * @param nodeName The node name.
	 */
	public BootNodeRequest(
			final Address address,
			final WalletName walletName,
			final String nodeName) {
		super(address, walletName);
		this.nodeName = nodeName;
	}

	/**
	 * Deserializes a boot node view model.
	 *
	 * @param deserializer The deserializer.
	 */
	public BootNodeRequest(final Deserializer deserializer) {
		super(deserializer);
		this.nodeName = deserializer.readString("node_name");
	}

	/**
	 * Gets the node name.
	 *
	 * @return The node name.
	 */
	public String getNodeName() {
		return this.nodeName;
	}
}
