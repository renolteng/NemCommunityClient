package org.nem.ncc.test;

import org.nem.core.serialization.*;
import org.nem.ncc.storage.*;

public class StorableEntityUtils {
	public static final StorableEntityFileExtension FILE_EXTENSION = new StorableEntityFileExtension(".bar");

	public static StorableEntity createStorableEntity(final String name) {
		return new DefaultStorableEntity(new StorableEntityName(name), FILE_EXTENSION);
	}

	public static StorableEntity createStorableEntity(final String name, final String fileExtension) {
		return new DefaultStorableEntity(new StorableEntityName(name), new StorableEntityFileExtension(fileExtension));
	}

	public static StorableEntity createStorableEntity(final StorableEntityName name) {
		return new DefaultStorableEntity(name, FILE_EXTENSION);
	}

	private static class DefaultStorableEntity implements StorableEntity {
		private final StorableEntityName name;
		private final StorableEntityFileExtension fileExtension;
		private final String labelName;

		private DefaultStorableEntity(final StorableEntityName name, final StorableEntityFileExtension fileExtension) {
			this.name = name;
			this.fileExtension = fileExtension;
			this.labelName = "defaultStorableEntity";
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
			return new DefaultStorableEntity(new StorableEntityName("foo"), FILE_EXTENSION);
		}

		@Override
		public void serialize(final Serializer serializer) {
		}
	}
}
