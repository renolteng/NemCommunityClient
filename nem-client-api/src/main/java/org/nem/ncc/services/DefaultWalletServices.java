package org.nem.ncc.services;

import org.nem.core.model.Address;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.wallet.*;
import org.nem.ncc.wallet.storage.*;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.zip.*;

/**
 * Implements default wallet services.
 */
public class DefaultWalletServices implements WalletServices {
	private final WalletRepository repository;
	private final WalletDescriptorFactory descriptorFactory;
	private final Map<WalletName, Wallet> wallets = new ConcurrentHashMap<>();

	/**
	 * Creates a new default wallet services.
	 *
	 * @param repository The repository.
	 * @param descriptorFactory The descriptor factory.
	 */
	public DefaultWalletServices(
			final WalletRepository repository,
			final WalletDescriptorFactory descriptorFactory) {
		this.repository = repository;
		this.descriptorFactory = descriptorFactory;
	}

	/**
	 * Gets the names of all open wallets.
	 *
	 * @return The names of all open wallets.
	 */
	public List<WalletName> getOpenWalletNames() {
		return this.wallets.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public Wallet get(final WalletName name) {
		final Wallet wallet = this.wallets.get(name);
		if (null == wallet) {
			throw new NccException(NccException.Code.WALLET_IS_NOT_OPEN);
		}

		return wallet;
	}

	@Override
	public WalletAccount tryFindOpenAccount(final Address address) {
		for (final Map.Entry<WalletName, Wallet> entry : this.wallets.entrySet()) {
			final WalletAccount account = entry.getValue().tryGetWalletAccount(address);
			if (null != account) {
				return account;
			}
		}

		return null;
	}

	@Override
	public Wallet open(final WalletNamePasswordPair pair) {
		final Wallet wallet = this.wallets.getOrDefault(pair.getName(), null);
		final WalletDescriptor descriptor = this.descriptorFactory.openExisting(pair, new WalletFileExtension());
		if (null != wallet) {
			// ensure that the wallet can be loaded; this also serves as a password check
			this.repository.load(descriptor);
			return wallet;
		}

		return this.wrapWallet(this.repository.load(descriptor), descriptor);
	}

	@Override
	public Wallet create(final WalletNamePasswordPair pair) {
		final WalletDescriptor descriptor = this.descriptorFactory.createNew(pair, new WalletFileExtension());
		final AutoSavingWallet wallet = this.wrapWallet(new MemoryWallet(pair.getName()), descriptor);
		wallet.save();
		return wallet;
	}

	@Override
	public void close(final WalletName name) {
		this.wallets.remove(name);
	}

	private AutoSavingWallet wrapWallet(final StorableWallet wallet, final WalletDescriptor descriptor) {
		final AutoSavingWallet autoSavingWallet = new AutoSavingWallet(wallet, descriptor, this.repository);
		this.wallets.put(descriptor.getWalletName(), autoSavingWallet);
		return autoSavingWallet;
	}

	@Override
	public void move(final WalletNamePasswordPair originalPair, final WalletNamePasswordPair desiredPair) {
		final boolean hasNameChange = !originalPair.getName().equals(desiredPair.getName());
		final WalletDescriptor newWalletDescriptor = hasNameChange
				? this.descriptorFactory.createNew(desiredPair, new WalletFileExtension())
				: this.descriptorFactory.openExisting(desiredPair, new WalletFileExtension());

		// be sure to reload the wallet here so that an auto-saved wallet is not wrapped
		final WalletDescriptor originalWalletDescriptor = this.descriptorFactory.openExisting(originalPair, new WalletFileExtension());
		final Wallet originalWallet = this.repository.load(originalWalletDescriptor);
		final AutoSavingWallet wallet = this.wrapWallet(
				new MemoryWallet(desiredPair.getName(), originalWallet.getPrimaryAccount(), originalWallet.getOtherAccounts()),
				newWalletDescriptor);
		wallet.save();

		if (hasNameChange) {
			this.wallets.remove(originalPair.getName());
			originalWalletDescriptor.delete();
		}
	}

	@Override
	public void addToZip(final ZipOutputStream zipOutputStream, final WalletName name) {
		final WalletNamePasswordPair pair = new WalletNamePasswordPair(name, new WalletPassword("not relevant"));
		final WalletDescriptor descriptor = this.descriptorFactory.openExisting(pair, new WalletFileExtension());

		ExceptionUtils.propagateVoid(() -> {
			FileInputStream inputStream = new FileInputStream(descriptor.getWalletLocation());
			final ZipEntry zipEntry = new ZipEntry(name.toString() + WalletFileExtension.DEFAULT_FILE_EXTENSION);
			zipOutputStream.putNextEntry(zipEntry);
			final byte[] byteBuffer = new byte[1024];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(byteBuffer)) != -1) {
				zipOutputStream.write(byteBuffer, 0, bytesRead);
			}

			inputStream.close();
			zipOutputStream.flush();
			zipOutputStream.closeEntry();
		});
	}
}