package org.nem.ncc.storage;

import org.nem.core.serialization.ObjectDeserializer;

/**
 * Repository class for loading and saving storable entities.
 */
public interface StorableEntityRepository<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityDescriptor extends StorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension>> {

	/**
	 * Saves the storable entity to the specified descriptor.
	 *
	 * @param descriptor The output descriptor.
	 * @param storableEntity The storable entity.
	 */
	public void save(final TEntityDescriptor descriptor, final TEntity storableEntity);

	/**
	 * Loads a storable entity from the specified descriptor.
	 *
	 * @param descriptor The input descriptor.
	 * @return The storable entity.
	 */
	public TEntity load(final TEntityDescriptor descriptor);
}
