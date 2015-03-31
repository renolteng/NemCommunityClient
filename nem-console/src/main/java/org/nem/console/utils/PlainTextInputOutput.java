package org.nem.console.utils;

import org.nem.core.utils.ExceptionUtils;

import java.io.*;

/**
 * Helper class for reading and writing plain-text data.
 */
class PlainTextInputOutput {

	/**
	 * Writes a blob of data to the specified file.
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
		FileMust.notExist(fileName);
		try (final OutputStream outputStream = new FileOutputStream(fileName)) {
			outputStream.write(data);
		}
	}
}
