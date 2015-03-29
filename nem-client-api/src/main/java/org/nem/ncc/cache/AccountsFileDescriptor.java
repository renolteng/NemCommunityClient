package org.nem.ncc.cache;

import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.NccException;

import java.io.*;

/**
 * An class that describes an accounts file.
 */
public class AccountsFileDescriptor {
	private final File file;

	/**
	 * Creates a new accounts file descriptor.
	 *
	 * @param file The backing file.
	 */
	public AccountsFileDescriptor(final File file) {
		this.file = file;
	}

	/**
	 * Opens a read stream that can be used to read the contents of the file.
	 *
	 * @return The input stream.
	 */
	public InputStream openRead() {
		return ExceptionUtils.propagate(
				() -> this.file.exists() ? new FileInputStream(this.file) : new ByteArrayInputStream(new byte[] {}),
				ex -> new NccException(NccException.Code.ACCOUNT_CACHE_ERROR));
	}

	/**
	 * Opens a write stream that can be used to modify the contents of the file.
	 *
	 * @return The output stream.
	 */
	public OutputStream openWrite() {
		return ExceptionUtils.propagate(
				() -> new FileOutputStream(this.file),
				ex -> new NccException(NccException.Code.ACCOUNT_CACHE_ERROR));
	}
}
