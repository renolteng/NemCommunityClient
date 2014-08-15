package org.nem.ncc.wallet.storage;

import org.eclipse.jetty.util.UrlEncoded;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.wallet.WalletName;

import java.io.*;
import java.util.logging.Logger;

/**
 * A WalletDescriptor implementation that references files stored on disk.
 */
public class WalletFileDescriptor implements WalletDescriptor {
	private final static Logger LOGGER = Logger.getLogger(WalletFileDescriptor.class.getName());

	/**
	 * The extension wallet file extension.
	 */
	public final static String WALLET_EXTENSION = ".wlt";

	private final File file;
	private final WalletName walletName;

	/**
	 * Creates a new wallet file descriptor around a file.
	 *
	 * @param file The wallet location.
	 */
	public WalletFileDescriptor(final File file) {
		this.file = file;
		if (file.isDirectory()) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_CANNOT_BE_DIRECTORY);
		}

		final String fileName = this.file.getName();
		if (!WalletFileUtils.isValidWalletFileName(fileName)) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_HAS_INVALID_EXTENSION);
		}

		final String walletName = fileName.substring(0, fileName.length() - WALLET_EXTENSION.length());
		this.walletName = new WalletName(UrlEncoded.decodeString(walletName, 0, walletName.length(), UrlEncoded.ENCODING));
	}

	/**
	 * Gets the wallet location.
	 *
	 * @return The wallet location.
	 */
	public String getWalletLocation() {
		return this.file.getAbsolutePath();
	}

	@Override
	public WalletName getWalletName() {
		return this.walletName;
	}

	@Override
	public InputStream openRead() {
		LOGGER.info(String.format("Opening wallet for reading located at <%s>", this.getWalletLocation()));
		if (!this.file.exists()) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_DOES_NOT_EXIST);
		}

		return ExceptionUtils.propagate(
				() -> new FileInputStream(this.getWalletLocation()),
				ex -> new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_READ, ex));
	}

	@Override
	public OutputStream openWrite() {
		LOGGER.info(String.format("Opening wallet for writing located at <%s>", this.getWalletLocation()));
		return ExceptionUtils.propagate(
				() -> new FileOutputStream(this.getWalletLocation()),
				ex -> new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_SAVED, ex));
	}

	@Override
	public void delete() {
		LOGGER.info(String.format("Deleting wallet <%s> located at <%s>.", this.getWalletName(), this.getWalletLocation()));
		if (!this.file.delete()) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_DELETED);
		}
	}

	@Override
	public void serialize(final Serializer serializer) {
		WalletName.writeTo(serializer, "wallet", this.getWalletName());
		serializer.writeString("location", this.getWalletLocation());
	}
}
