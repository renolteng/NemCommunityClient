package org.nem.ncc.storage;

/**
 * Repository class for loading and saving storable entities.
 */
public interface StorableEntityRepository<TEntity extends StorableEntity> {

	/**
	 * Saves the storable entity to the specified descriptor.
	 *
	 * @param descriptor The output descriptor.
	 * @param storableEntity The storable entity.
	 */
	public void save(final StorableEntityDescriptor descriptor, final StorableEntity storableEntity);

	/**
	 * Loads a storable entity from the specified descriptor.
	 *
	 * @param descriptor The input descriptor.
	 * @return The storable entity.
	 */
	public StorableEntity load(final StorableEntityDescriptor descriptor);
}
