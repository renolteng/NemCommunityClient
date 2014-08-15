package org.nem.ncc.controller;

import org.nem.core.serialization.SerializableList;
import org.nem.ncc.wallet.WalletLocator;
import org.nem.ncc.wallet.storage.WalletDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related wallet lists.
 */
@RestController
public class WalletsController {
	private final WalletLocator locator;

	/**
	 * Creates a new wallets controller.
	 *
	 * @param locator The wallet locator.
	 */
	@Autowired(required = true)
	public WalletsController(final WalletLocator locator) {
		this.locator = locator;
	}

	/**
	 * Gets all wallets that are located at the configured storage location for wallets.
	 *
	 * @return A list of wallet descriptors.
	 */
	@RequestMapping(value = "/wallet/list", method = RequestMethod.GET)
	public SerializableList<WalletDescriptor> getAllWallets() {
		return new SerializableList<>(this.locator.getAllWallets(), "wallets");
	}
}
