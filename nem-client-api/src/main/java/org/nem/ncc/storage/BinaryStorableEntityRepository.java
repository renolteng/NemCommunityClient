package org.nem.ncc.storage;

import org.apache.commons.io.IOUtils;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;

/**
 * A binary storable entity repository.
 */
public class BinaryStorableEntityRepository<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityDescriptor extends StorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension>>
		implements StorableEntityRepository<TEntity, TEntityName, TEntityFileExtension, TEntityDescriptor> {

	@Override
	public void save(final TEntityDescriptor descriptor, final TEntity storableEntity) {
		ExceptionUtils.propagateVoid(() -> {
			try (final OutputStream os = descriptor.openWrite()) {
				os.write(BinarySerializer.serializeToBytes(storableEntity));
			}
		}, ex -> new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED, ex));
	}

	@Override
	public TEntity load(final TEntityDescriptor descriptor) {
		try {
			try (final InputStream is = descriptor.openRead()) {
				final byte[] bytes = IOUtils.toByteArray(is);
				final ObjectDeserializer<TEntity> deserializer = descriptor.getDeserializer();
				return deserializer.deserialize(new BinaryDeserializer(bytes, new DeserializationContext(null)));
			}
		} catch (final SerializationException | IOException ex) {
			throw new StorableEntityStorageException(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ, ex);
		}
	}
}
