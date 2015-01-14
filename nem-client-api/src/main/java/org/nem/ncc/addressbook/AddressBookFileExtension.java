package org.nem.ncc.addressbook;

import org.nem.ncc.storable.entity.*;

/**
 * Represents a file extension for an address book.
 */
public class AddressBookFileExtension extends StorableEntityFileExtension<AddressBookFileExtension> {
	public static final String DEFAULT_FILE_EXTENSION = ".adb";

	/**
	 * Creates a new address book file extension.
	 */
	public AddressBookFileExtension() {
		this(DEFAULT_FILE_EXTENSION);
	}

	/**
	 * Creates a new address book file extension.
	 *
	 * @param fileExtension The file extension.
	 */
	public AddressBookFileExtension(final String fileExtension) {
		super(fileExtension, AddressBookFileExtension.class);
	}

	/**
	 * Gets the default file extension.
	 *
	 * @return The file extension.
	 */
	public static AddressBookFileExtension getDefaultFileExtension() {
		return new AddressBookFileExtension(DEFAULT_FILE_EXTENSION);
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
	protected StorableEntityStorageException getException(final int value, final Exception ex) {
		return null == ex
				? new AddressBookStorageException(value + AddressBookStorageException.OFFSET)
				: new AddressBookStorageException(value + AddressBookStorageException.OFFSET, ex);
	}
}
