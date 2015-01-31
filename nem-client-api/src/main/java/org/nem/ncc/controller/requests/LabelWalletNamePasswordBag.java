package org.nem.ncc.controller.requests;

import org.nem.core.serialization.Deserializer;

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
		return label;
	}
}
