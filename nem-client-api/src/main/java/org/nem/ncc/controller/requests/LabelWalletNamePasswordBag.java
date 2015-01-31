package org.nem.ncc.controller.requests;

import org.nem.core.serialization.Deserializer;

/**
 * Request object that that requires a wallet name and a label
 * but can contain any number of optional properties too.
 * TODO 20150131 J-B: Any reason not to just use WalletNamePasswordBag?
 * TODO 20150131 J-B: a few basic tests
 */
public class LabelWalletNamePasswordBag extends WalletNamePasswordBag {
	private final String label;

	/**
	 * Deserializes a wallet name / password bag.
	 *
	 * @param deserializer The deserializer.
	 */
	public LabelWalletNamePasswordBag(final Deserializer deserializer) {
		super(deserializer);
		this.label = deserializer.readString("label");
	}

	/**
	 * Gets label of wallet account
	 *
	 * @return The label.
	 */
	public String getWalletAccountLabel() {
		return this.label;
	}
}
