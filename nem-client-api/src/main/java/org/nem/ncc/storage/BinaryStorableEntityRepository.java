package org.nem.ncc.storage;

import org.apache.commons.io.IOUtils;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;

/**
 * A binary storable entity repository.
 */
public class BinaryStorableEntityRepository implements StorableEntityRepository {

	@Override
	public void save(final StorableEntityDescriptor descriptor, final StorableEntity storableEntity) {
		ExceptionUtils.propagateVoid(() -> {
			try (final OutputStream os = descriptor.openWrite()) {
				os.write(BinarySerializer.serializeToBytes(storableEntity));
			}
		}, ex -> new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED, ex));
	}

	@Override
	public StorableEntity load(final StorableEntityDescriptor descriptor) {
		try {
			try (final InputStream is = descriptor.openRead()) {
				final byte[] bytes = IOUtils.toByteArray(is);
				final ObjectDeserializer<StorableEntity> deserializer = descriptor.getEntityDeserializer();
				return deserializer.deserialize(new BinaryDeserializer(bytes, new DeserializationContext(null)));
			}
		} catch (final SerializationException | IOException ex) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ, ex);
		}
	}
}
