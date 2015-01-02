package org.nem.ncc.test;

import org.nem.ncc.storage.*;

public class StorableEntityUtils {


	/**
	 * Creates a storable entity.
	 *
	 * @param name The name for the entity.
	 * @param fileExtension The file extension for the entity.
	 * @return The storable entity.
	 */
	public static DefaultStorableEntity createStorableEntity(final String name, final String fileExtension) {
		return new DefaultStorableEntity(new StorableEntityName(name), new StorableEntityFileExtension(fileExtension));
	}

	/**
	 * Creates a storable entity with default file extension.
	 *
	 * @param name The name for the entity.
	 * @return The storable entity.
	 */
	public static DefaultStorableEntity createStorableEntity(final StorableEntityName name) {
		return new DefaultStorableEntity(name);
	}

	/**
	 * Creates a storable entity with default file extension and adds count entries to it.
	 *
	 * @param name The name for the entity.
	 * @param count The number of entries to add.
	 * @return The storable entity.
	 */
	public static DefaultStorableEntity createStorableEntityWithEntries(final StorableEntityName name, final int count) {
		final DefaultStorableEntity entity = new DefaultStorableEntity(name);
		entity.addEntries(count);
		return entity;
	}
}
