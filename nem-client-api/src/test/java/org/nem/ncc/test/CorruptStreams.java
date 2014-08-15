package org.nem.ncc.test;

import java.io.*;

/**
 * Static factory class for corrupt streams that throw IOExceptions.
 */
public class CorruptStreams {

	/**
	 * Creates an InputStream that throws an IOException whenever read is called.
	 *
	 * @return The stream.
	 */
	public static InputStream createRead() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				throw new IOException();
			}
		};
	}

	/**
	 * Creates an OutputStream that throws an IOException whenever write is called.
	 *
	 * @return The stream.
	 */
	public static OutputStream createWrite() {
		return new OutputStream() {
			@Override
			public void write(final int b) throws IOException {
				throw new IOException();
			}
		};
	}
}
