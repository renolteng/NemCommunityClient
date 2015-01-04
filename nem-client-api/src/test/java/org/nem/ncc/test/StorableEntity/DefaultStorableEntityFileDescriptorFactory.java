package org.nem.ncc.test.StorableEntity;

import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.storage.*;

import java.io.File;
import java.util.function.Function;

public class DefaultStorableEntityFileDescriptorFactory
		extends  StorableEntityFileDescriptorFactory<
		DefaultStorableEntity,
		StorableEntityName,
		StorableEntityPassword,
		StorableEntityFileExtension,
		DefaultStorableEntityNamePasswordPair,
		DefaultStorableEntityFileDescriptor> {

	public DefaultStorableEntityFileDescriptorFactory(
			final File directory,
			final ObjectDeserializer<DefaultStorableEntity> deserializer,
			final Function<String, StorableEntityName> nameActivator,
			final Function<String, StorableEntityFileExtension> fileExtensionActivator,
			final QuadFunction<
					File,
					ObjectDeserializer<DefaultStorableEntity>,
					Function<String, StorableEntityName>,
					Function<String, StorableEntityFileExtension>,
					DefaultStorableEntityFileDescriptor> descriptorActivator) {
		super(
				directory,
				deserializer,
				nameActivator,
				fileExtensionActivator,
				descriptorActivator);
	}
}
