package org.nem.ncc.storable.entity;

import org.nem.core.utils.StringUtils;

/**
 * Represents a file extension for a storable entity.
 */
public class StorableEntityFileExtension<TDerived extends StorableEntityFileExtension> {
	public static final int EXTENSION_LENGTH = 4;
	private final String fileExtension;
	private final Class<TDerived> derivedClass;

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
		this.derivedClass = derivedClass;
	}

	/**
	 * Gets a value indicating whether the supplies file name has a valid extension.
	 *
	 * @param fileName The file name.
	 * @return true if the extension is valid, false otherwise.
	 */
	public static boolean hasValidExtension(final String fileName) {
		final int index = fileName.lastIndexOf(".");
		return -1 != index && EXTENSION_LENGTH == fileName.length() - index;
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
