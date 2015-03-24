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

class EncryptedInputOutput {
	private static final int BLOCK_SIZE = 16;

	private final String password;

	public EncryptedInputOutput(final String password) {
		this.password = password;
	}

	public void writeTo(final String fileName, final byte[] data) {
		ExceptionUtils.propagateVoid(() -> this.writeToInternal(fileName, data));
	}

	public void writeToInternal(final String fileName, final byte[] data) throws IOException{
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
		final PaddedBufferedBlockCipher cipher = this.getCipher(false);
		try (final InputStream inputStream = new FileInputStream(fileName)) {
			try (final CipherInputStream cis = new CipherInputStream(inputStream, cipher)) {
				return IOUtils.toByteArray(cis);
			}
		}
	}

	private PaddedBufferedBlockCipher getCipher(final boolean encrypt) {
		final KeyParameter key = new KeyParameter(Hashes.sha3_256(StringEncoder.getBytes(this.password)));

		// create and initialize the cipher
		final PaddedBufferedBlockCipher resultCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
		final ParametersWithIV parameterIV = new ParametersWithIV(key, new byte[BLOCK_SIZE]);
		resultCipher.init(encrypt, parameterIV);
		return resultCipher;
	}
}
