package org.nem.ncc.storage;

/**
 * Factory for creating storable entity descriptors.
 * TODO 20141231 BR: Should I create a StorableEntityNameFileExtensionPasswordTriple class?
 */
public interface StorableEntityDescriptorFactory {

	/**
	 * Creates a storable entity descriptor corresponding to a new storable entity.
	 *
	 * @param pair The storable entity name and password.
	 * @param fileExtension The storable entity file extension.
	 * @return The descriptor.
	 */
	public StorableEntityDescriptor createNew(final StorableEntityNamePasswordPair pair, final StorableEntityFileExtension fileExtension);

	/**
	 * Opens a storable entity descriptor corresponding to en existing storable entity.
	 *
	 * @param pair The storable entity name and password.
	 * @param fileExtension The storable entity file extension.
	 * @return The descriptor.
	 */
	public StorableEntityDescriptor openExisting(final StorableEntityNamePasswordPair pair, final StorableEntityFileExtension fileExtension);
}
