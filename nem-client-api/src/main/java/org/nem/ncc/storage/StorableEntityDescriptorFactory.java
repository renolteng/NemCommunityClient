package org.nem.ncc.storage;

/**
 * Factory for creating storable entity descriptors.
 */
public interface StorableEntityDescriptorFactory {

	/**
	 * Creates a storable entity descriptor corresponding to a new storable entity.
	 *
	 * @param pair The storable entity name and password.
	 * @return The descriptor.
	 */
	public StorableEntityDescriptor createNew(final StorableEntityNamePasswordPair pair);

	/**
	 * Opens a storable entity descriptor corresponding to en existing storable entity.
	 *
	 * @param pair The storable entity name and password.
	 * @return The descriptor.
	 */
	public StorableEntityDescriptor openExisting(final StorableEntityNamePasswordPair pair);
}
