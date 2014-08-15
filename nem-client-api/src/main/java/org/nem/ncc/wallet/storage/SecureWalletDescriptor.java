package org.nem.ncc.wallet.storage;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.io.*;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.*;
import org.nem.core.crypto.Hashes;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.*;
import org.nem.ncc.wallet.*;

import java.io.*;

/**
 * WalletDescriptor that automatically encrypts and decrypts data using a password.
 */
public class SecureWalletDescriptor implements WalletDescriptor {
	private static final int BLOCK_SIZE = 16;

	private final WalletDescriptor descriptor;
	private final WalletPassword password;

	/**
	 * Creates a new secure wallet descriptor.
	 *
	 * @param descriptor The underlying descriptor.
	 * @param password The password.
	 */
	public SecureWalletDescriptor(
			final WalletDescriptor descriptor,
			final WalletPassword password) {
		this.descriptor = descriptor;
		this.password = password;
	}

	@Override
	public WalletName getWalletName() {
		return this.descriptor.getWalletName();
	}

	@Override
	public InputStream openRead() {
		return ExceptionUtils.propagate(
				this::openReadInternal,
				ex -> new WalletStorageException(
						InvalidCipherTextIOException.class.isAssignableFrom(ex.getClass())
								? WalletStorageException.Code.WALLET_PASSWORD_INCORRECT
								: WalletStorageException.Code.WALLET_COULD_NOT_BE_READ,
						ex));
	}

	private InputStream openReadInternal() throws IOException {
		final PaddedBufferedBlockCipher cipher = this.getCipher(false);
		try (final InputStream inputStream = this.descriptor.openRead()) {
			try (final CipherInputStream cis = new CipherInputStream(inputStream, cipher)) {
				final byte[] decryptedBytes = IOUtils.toByteArray(cis);
				return new ByteArrayInputStream(decryptedBytes);
			}
		}
	}

	@Override
	public OutputStream openWrite() {
		return new ByteArrayOutputStream() {
			@Override
			public void close() throws IOException {
				SecureWalletDescriptor.this.closeInternal(this.toByteArray());
				super.close();
			}
		};
	}

	private void closeInternal(final byte[] content) throws IOException {
		final PaddedBufferedBlockCipher cipher = this.getCipher(true);
		try (final OutputStream outputStream = this.descriptor.openWrite()) {
			try (final CipherOutputStream cos = new CipherOutputStream(outputStream, cipher)) {
				cos.write(content);
			}
		}
	}

	@Override
	public void delete() {
		this.descriptor.delete();
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.descriptor.serialize(serializer);
	}

	private PaddedBufferedBlockCipher getCipher(final boolean encrypt) {
		final KeyParameter key = new KeyParameter(Hashes.sha3(StringEncoder.getBytes(this.password.toString())));

		// create and initialize the cipher
		final PaddedBufferedBlockCipher resultCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		final ParametersWithIV parameterIV = new ParametersWithIV(key, new byte[BLOCK_SIZE]);
		resultCipher.init(encrypt, parameterIV);
		return resultCipher;
	}
}
