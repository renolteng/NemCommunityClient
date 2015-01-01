package org.nem.ncc.test;

import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.*;
import org.nem.ncc.storage.*;

import java.util.*;

public class StorableEntityUtils {

	/**
	 * The default file extension for the DefaultStorableEntity class.
	 */
	public static final StorableEntityFileExtension FILE_EXTENSION = new StorableEntityFileExtension(".bar");

	/**
	 * Creates a storable entity.
	 *
	 * @param name The name for the entity.
	 * @param fileExtension The file extension for the entity.
	 * @return The storable entity.
	 */
	public static StorableEntity createStorableEntity(final String name, final String fileExtension) {
		return new DefaultStorableEntity(new StorableEntityName(name), new StorableEntityFileExtension(fileExtension));
	}

	/**
	 * Creates a storable entity with default file extension.
	 *
	 * @param name The name for the entity.
	 * @return The storable entity.
	 */
	public static StorableEntity createStorableEntity(final StorableEntityName name) {
		return new DefaultStorableEntity(name, FILE_EXTENSION);
	}

	/**
	 * Creates a storable entity with default file extension and adds count entries to it.
	 *
	 * @param name The name for the entity.
	 * @param count The number of entries to add.
	 * @return The storable entity.
	 */
	public static StorableEntity createStorableEntityWithEntries(final StorableEntityName name, final int count) {
		final DefaultStorableEntity entity = new DefaultStorableEntity(name, FILE_EXTENSION);
		entity.addHeights(count);
		return entity;
	}

	/**
	 * TODO 20140101 BR: Would probably be better to check equivalence.
	 * Checks two default storable entities for equality.
	 *
	 * @param entity1 The first storable entity.
	 * @param entity2 The second storable entity.
	 * @return true if the entities are equal, false otherwise.
	 */
	public static boolean isEqual(final StorableEntity entity1, final StorableEntity entity2) {
		if (!(entity1 instanceof DefaultStorableEntity) || !(entity2 instanceof DefaultStorableEntity)) {
			throw new RuntimeException("can only check equality of DefaultStorableEntity objects");
		}

		final DefaultStorableEntity lhs = (DefaultStorableEntity)entity1;
		final DefaultStorableEntity rhs = (DefaultStorableEntity)entity2;
		return lhs.name.equals(rhs.name) &&
				lhs.fileExtension.equals(rhs.fileExtension) &&
				lhs.labelName.equals(rhs.labelName) &&
				lhs.heights.equals(rhs.heights);
	}

	private static class DefaultStorableEntity implements StorableEntity {
		private final List<BlockHeight> heights;
		private final StorableEntityName name;
		private final StorableEntityFileExtension fileExtension;
		private final String labelName;

		private DefaultStorableEntity(final StorableEntityName name, final StorableEntityFileExtension fileExtension) {
			this.heights = new ArrayList<>();
			this.name = name;
			this.fileExtension = fileExtension;
			this.labelName = "defaultStorableEntity";
		}

		private DefaultStorableEntity(final Deserializer deserializer) {
			this.fileExtension = FILE_EXTENSION;
			this.labelName = "defaultStorableEntity";
			this.name = StorableEntityName.readFrom(deserializer, this.labelName);
			this.heights = deserializer.readObjectArray("heights", BlockHeight::new);
		}

		private void addHeights(final int count) {
			for (int i=1; i<= count; i++) {
				this.heights.add(new BlockHeight(i));
			}
		}

		@Override
		public String getLabelName() {
			return this.labelName;
		}

		@Override
		public StorableEntityName getName() {
			return this.name;
		}

		@Override
		public StorableEntityFileExtension getFileExtension() {
			return this.fileExtension;
		}

		@Override
		public StorableEntity deserialize(final Deserializer deserializer) {
			return new DefaultStorableEntity(deserializer);
		}

		@Override
		public void serialize(final Serializer serializer) {
			StorableEntityName.writeTo(serializer, this.labelName, this.name);
			serializer.writeObjectArray("heights", this.heights);
		}
	}
}
