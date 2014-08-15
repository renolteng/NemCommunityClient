package org.nem.ncc.wallet;

import org.nem.ncc.wallet.storage.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A file-based WalletLocator implementation.
 */
public class WalletFileLocator implements WalletLocator {
	private final File directory;

	/**
	 * Creates a new wallet file locator.
	 *
	 * @param directory The search directory.
	 */
	public WalletFileLocator(final File directory) {
		this.directory = directory;
	}

	@Override
	public List<WalletDescriptor> getAllWallets() {
		final String[] files = this.directory.list((dir, name) -> WalletFileUtils.isValidWalletFileName(name));
		return Arrays.asList(files).stream()
				.map(f -> new WalletFileDescriptor(new File(f)))
				.collect(Collectors.toList());
	}
}
