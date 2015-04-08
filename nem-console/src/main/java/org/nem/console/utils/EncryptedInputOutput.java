package org.nem.console.utils;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.io.*;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.*;
import org.nem.core.crypto.Hashes;
import org.nem.core.utils.*;

import java.io.*;

/**
 * Helper class for reading and writing encrypted data.
 */
class EncryptedInputOutput {
	private static final int BLOCK_SIZE = 16;

	private final String password;
	private final int numHashes;

	/**
	 * Creates a new encrypted input / output object.
	 *
	 * @param password The password.
	 * @param numHashes The number of hashes.
	 */
	public EncryptedInputOutput(final String password, final int numHashes) {
		this.password = password;
		this.numHashes = numHashes;

		if (StringUtils.isNullOrEmpty(this.password)) {
			throw new IllegalArgumentException("password cannot be empty");
		}

		if (this.numHashes < 1) {
			throw new IllegalArgumentException("password must be hashed at least once");
		}
	}

	/**
	 * Writes a blob of data to the specified encrypted file.
	 *
	 * @param fileName The file to which to write.
	 * @param data The plain data.
	 */
	public void writeTo(final String fileName, final byte[] data) {
		ExceptionUtils.propagateVoid(
				() -> this.writeToInternal(fileName, data),
				ex -> new StorageException(String.format("writing to '%s' failed", fileName), ex));
	}

	private void writeToInternal(final String fileName, final byte[] data) throws IOException {
		FileMust.notExist(fileName); // TODO: this doesn't belong here

		try (final OutputStream outputStream = new FileOutputStream(fileName)) {
			final PaddedBufferedBlockCipher cipher = this.getCipher(true);
			try (final CipherOutputStream cos = new CipherOutputStream(outputStream, cipher)) {
				cos.write(data);
			}
		}
	}

	/**
	 * Reads a block of blob from data to the specified encrypted file.
	 *
	 * @param fileName The file to which to write.
	 * @return The data.
	 */
	public byte[] readFrom(final String fileName) {
		return ExceptionUtils.propagate(
				() -> this.readFromInternal(fileName),
				ex -> new StorageException(String.format("reading from '%s' failed", fileName), ex));
	}

	private byte[] readFromInternal(final String fileName) throws IOException {
		FileMust.exist(fileName); // TODO: this doesn't belong here

		final PaddedBufferedBlockCipher cipher = this.getCipher(false);
		try (final InputStream inputStream = new FileInputStream(fileName)) {
			try (final CipherInputStream cis = new CipherInputStream(inputStream, cipher)) {
				return IOUtils.toByteArray(cis);
			}
		}
	}

	private PaddedBufferedBlockCipher getCipher(final boolean encrypt) {
		byte[] hash = Hashes.sha3_256(StringEncoder.getBytes(this.password));
		for (int i = 0; i < this.numHashes - 1; ++i) {
			hash = Hashes.sha3_256(hash);
		}

		final KeyParameter key = new KeyParameter(hash);

		// create and initialize the cipher
		final PaddedBufferedBlockCipher resultCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		final ParametersWithIV parameterIV = new ParametersWithIV(key, new byte[BLOCK_SIZE]);
		resultCipher.init(encrypt, parameterIV);
		return resultCipher;
	}
}
