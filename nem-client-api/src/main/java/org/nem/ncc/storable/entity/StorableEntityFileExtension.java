package org.nem.ncc.storable.entity;

import org.nem.core.utils.StringUtils;

/**
 * Represents a file extension for a storable entity.
 */
public class StorableEntityFileExtension<TDerived extends StorableEntityFileExtension> {
	public static final int EXTENSION_LENGTH = 4;
	public static final String DEFAULT_FILE_EXTENSION = ".ste";
	private final String fileExtension;
	private final Class<TDerived> derivedClass;

	/**
	 * Creates a storable entity file extension.
	 */
	public StorableEntityFileExtension() {
		this(DEFAULT_FILE_EXTENSION, null);
	}

	/**
	 * Creates a storable entity file extension.
	 *
	 * @param fileExtension The file extension.
	 */
	public StorableEntityFileExtension(final String fileExtension) {
		this(fileExtension, null);
	}

	/**
	 * Creates a storable entity file extension.
	 *
	 * @param fileExtension The file extension.
	 * @param derivedClass The derived class.
	 */
	public StorableEntityFileExtension(final String fileExtension, final Class<TDerived> derivedClass) {
		this.checkExtension(fileExtension);
		this.fileExtension = fileExtension;
		this.derivedClass = derivedClass;
	}

	private void checkExtension(final String fileExtension) {
		if (StringUtils.isNullOrWhitespace(fileExtension)) {
			throw new StorableEntityStorageException(
					StorableEntityStorageException.Code.STORABLE_ENTITY_HAS_INVALID_EXTENSION,
					new IllegalArgumentException("file extension must be non-whitespace"));
		}

		if (!fileExtension.startsWith(".")) {
			throw new StorableEntityStorageException(
					StorableEntityStorageException.Code.STORABLE_ENTITY_HAS_INVALID_EXTENSION,
					new IllegalArgumentException("file extension must start with dot"));
		}

		if (EXTENSION_LENGTH != fileExtension.length()) {
			throw new StorableEntityStorageException(
					StorableEntityStorageException.Code.STORABLE_ENTITY_HAS_INVALID_EXTENSION,
					new IllegalArgumentException("file extension must consist of a dot followed by three characters"));
		}
	}

	/**
	 * Gets a value indicating whether the extension is the default extension.
	 *
	 * @return true if the extension is the default extension, false otherwise.
	 */
	public boolean isDefaultExtension() {
		return this.toString().toLowerCase().equals(DEFAULT_FILE_EXTENSION);
	}

	/**
	 * Gets the default file extension.
	 *
	 * @return The file extension.
	 */
	public static StorableEntityFileExtension getDefaultFileExtension() {
		return new StorableEntityFileExtension(DEFAULT_FILE_EXTENSION);
	}

	/**
	 * Gets a value indicating whether the supplies file name is valid and has the default extension.
	 *
	 * @param fileName The file name.
	 * @return true if the file name is valid and has the default extension, false otherwise.
	 */
	public static boolean isValidAndHasDefaultExtension(final String fileName) {
		return fileName.toLowerCase().endsWith(DEFAULT_FILE_EXTENSION) && fileName.indexOf(".") > 0;
	}

	@Override
	public int hashCode() {
		return this.fileExtension.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		final Class clazz = null == this.derivedClass? StorableEntityFileExtension.class : this.derivedClass;
		if (!clazz.isInstance(obj)) {
			return false;
		}

		final StorableEntityFileExtension rhs = (StorableEntityFileExtension)obj;
		return this.fileExtension.equals(rhs.fileExtension);
	}

	@Override
	public String toString() {
		return this.fileExtension;
	}
}
