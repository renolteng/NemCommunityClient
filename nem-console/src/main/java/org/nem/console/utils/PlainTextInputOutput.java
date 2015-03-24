package org.nem.console.utils;

import org.nem.core.utils.*;

import java.io.*;

class PlainTextInputOutput {

	public void writeTo(final String fileName, final byte[] data) {
		ExceptionUtils.propagateVoid(() -> this.writeToInternal(fileName, data));
	}

	public void writeToInternal(final String fileName, final byte[] data) throws IOException {
		try (final OutputStream outputStream = new FileOutputStream(fileName)) {
			outputStream.write(data);
		}
	}
}
