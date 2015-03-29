package org.nem.console.utils;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;

import java.io.*;

class EncryptedInputOutput {
	private static final int BLOCK_SIZE = 16;

	private final String password;
	private final int numHashes;

	public EncryptedInputOutput(final String password, final int numHashes) {
		this.password = password;
		this.numHashes = numHashes;

		if (this.numHashes < 1) {
			throw new IllegalArgumentException("password must be hashed at least once");
		}
	}

	public void writeTo(final String fileName, final byte[] data) {
		ExceptionUtils.propagateVoid(() -> this.writeToInternal(fileName, data));
	}

	private void writeToInternal(final String fileName, final byte[] data) throws IOException {
		FileMust.notExist(fileName);

		try (final OutputStream outputStream = new FileOutputStream(fileName)) {
			final PaddedBufferedBlockCipher cipher = this.getCipher(true);
			try (final CipherOutputStream cos = new CipherOutputStream(outputStream, cipher)) {
				cos.write(data);
			}
		}
	}

	public byte[] readFrom(final String fileName) {
		return ExceptionUtils.propagate(() -> this.readFromInternal(fileName));
	}

	private byte[] readFromInternal(final String fileName) throws IOException {
		FileMust.exist(fileName);

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
