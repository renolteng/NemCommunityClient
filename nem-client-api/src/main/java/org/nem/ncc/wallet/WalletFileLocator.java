package org.nem.ncc.wallet;

import org.nem.ncc.storable.entity.StorableEntityFileLocator;
import org.nem.ncc.wallet.storage.*;

import java.io.File;
import java.util.List;

/**
 * A file-based WalletLocator implementation.
 */
public class WalletFileLocator
		extends StorableEntityFileLocator<StorableWallet, WalletName, WalletFileExtension, WalletDescriptor>
		implements WalletLocator {

	/**
	 * Creates a new wallet file locator.
	 *
	 * @param directory The search directory.
	 */
	public WalletFileLocator(final File directory) {
		super(
				directory,
				(dir, name) -> WalletFileExtension.isValidAndHasDefaultExtension(name),
				MemoryWallet::new,
				WalletName::new,
				WalletFileExtension::new,
				WalletFileDescriptor::new);
	}

	@Override
	public List<WalletDescriptor> getAllWallets() {
		return super.getAll();
	}
}
