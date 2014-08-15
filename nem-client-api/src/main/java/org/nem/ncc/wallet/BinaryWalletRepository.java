package org.nem.ncc.wallet;

import org.apache.commons.io.IOUtils;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.wallet.storage.*;

import java.io.*;

/**
 * A binary wallet repository.
 */
public class BinaryWalletRepository implements WalletRepository {

	@Override
	public void save(final WalletDescriptor descriptor, final Wallet wallet) {
		ExceptionUtils.propagateVoid(() -> {
			try (final OutputStream os = descriptor.openWrite()) {
				os.write(BinarySerializer.serializeToBytes(wallet));
			}
		}, ex -> new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_SAVED, ex));
	}

	@Override
	public Wallet load(final WalletDescriptor descriptor) {
		try {
			try (final InputStream is = descriptor.openRead()) {
				final byte[] bytes = IOUtils.toByteArray(is);
				return new MemoryWallet(new BinaryDeserializer(bytes, new DeserializationContext(null)));
			}
		} catch (final SerializationException | IOException ex) {
			throw new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_READ, ex);
		}
	}
}