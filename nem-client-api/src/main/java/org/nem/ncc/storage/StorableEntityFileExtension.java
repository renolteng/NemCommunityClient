package org.nem.ncc.storage;

import org.nem.core.utils.StringUtils;
/**
 * Represents a file extension for a storable entity.
 */
public class StorableEntityFileExtension {
	public static final int EXTENSION_LENGTH = 4;
	private final String fileExtension;

	/**
	 * Creates a storable entity file extension.
	 *
	 * @param fileExtension The file extension.
	 */
	public StorableEntityFileExtension(final String fileExtension) {
		if (StringUtils.isNullOrWhitespace(fileExtension)) {
			throw new IllegalArgumentException("file extension must be non-whitespace");
		}

		if (!fileExtension.startsWith(".")) {
			throw new IllegalArgumentException("file extension must start with dot");
		}

		if (EXTENSION_LENGTH != fileExtension.length()) {
			throw new IllegalArgumentException("file extension must consist of a dot followed by three characters");
		}

		this.fileExtension = fileExtension;
	}

	public static boolean hasValidExtension(final String fileName) {
		final int index = fileName.lastIndexOf(".");
		return -1 != index && EXTENSION_LENGTH == fileName.length() - index;
	}

	@Override
	public String toString() {
		return this.fileExtension;
	}
}
