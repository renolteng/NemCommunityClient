package org.nem.ncc.test.StorableEntity;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.StorableEntityFileDescriptor;

import java.io.File;
import java.util.function.Function;

public class DefaultStorableEntityFileDescriptor extends StorableEntityFileDescriptor<DefaultStorableEntity, StorableEntityName, StorableEntityFileExtension> {
	public DefaultStorableEntityFileDescriptor(
			final File file,
			final ObjectDeserializer<DefaultStorableEntity> deserializer,
			final Function<String, StorableEntityName> nameActivator,
			final Function<String, StorableEntityFileExtension> fileExtensionActivator) {
		super(file, deserializer, nameActivator, fileExtensionActivator);
	}
}
