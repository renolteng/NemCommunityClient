package org.nem.ncc.storable.entity;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.test.*;

import java.io.*;

public abstract class BinaryStorableEntityRepositoryTest<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityDescriptor extends StorableEntityDescriptor<TEntity, ?, ?>,
		TBinaryEntityRepository extends BinaryStorableEntityRepository<TEntity, ?, ?, TEntityDescriptor>> {

	@Test
	public void canSaveBinaryStorableEntityToWriteStream() {
		// Arrange:
		final TEntity entity = this.createEntity("blah");
		final TEntityDescriptor descriptor = this.createDescriptor();
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		this.createRepository().save(descriptor, entity);

		// Assert:
		Assert.assertThat(outputStream.toByteArray(), IsEqual.equalTo(BinarySerializer.serializeToBytes(entity)));
	}

	@Test
	public void saveFailureIsMappedToAppropriateException() {
		// Arrange:
		final TEntity entity = this.createEntity("blah");
		final TEntityDescriptor descriptor = this.createDescriptor();
		Mockito.when(descriptor.openWrite()).thenReturn(CorruptStreams.createWrite());

		// Assert:
		ExceptionAssert.assertThrowsStorageException(
				v -> this.createRepository().save(descriptor, entity),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED.value()));
	}

	@Test
	public void canLoadBinaryStorableEntityFromReadStream() {
		// Arrange:
		final TEntity originalEntity = this.createEntityWithEntries("blah", 5);
		final TEntityDescriptor descriptor = this.createDescriptor();
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(BinarySerializer.serializeToBytes(originalEntity));
		final TEntity entity = this.createEntity("blah");
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);
		Mockito.when(descriptor.getDeserializer()).thenReturn(entity);

		// Act:
		final TEntity loadedEntity = this.createRepository().load(descriptor);

		// Assert:
		Assert.assertThat(this.areEquivalent(originalEntity, loadedEntity), IsEqual.equalTo(true));
	}

	@Test
	public void loadFailureIsMappedToAppropriateException() {
		// Arrange:
		final TEntityDescriptor descriptor = this.createDescriptor();
		Mockito.when(descriptor.openRead()).thenReturn(CorruptStreams.createRead());

		// Assert:
		ExceptionAssert.assertThrowsStorageException(
				v -> this.createRepository().load(descriptor),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ.value()));
	}

	@Test
	public void loadSerializationFailureIsMappedToAppropriateException() {
		// Arrange: (storable entity deserialization will fail because the binary stream contains no data)
		final TEntityDescriptor descriptor = this.createDescriptor();
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] { });
		final TEntity entity = this.createEntity("blah");
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);
		Mockito.when(descriptor.getDeserializer()).thenReturn(entity);

		// Assert:
		ExceptionAssert.assertThrowsStorageException(
				v -> this.createRepository().load(descriptor),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ.value()));
	}

	@Test
	public void binaryStorableEntityCanBeRoundTripped() {
		// Arrange:
		final TBinaryEntityRepository repository = this.createRepository();

		final TEntity originalEntity = this.createEntityWithEntries("blah", 5);
		final TEntityDescriptor descriptor = this.createDescriptor();
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		repository.save(descriptor, originalEntity);

		final TEntity entity = this.createEntity("blah");
		Mockito.when(descriptor.openRead()).thenReturn(new ByteArrayInputStream(outputStream.toByteArray()));
		Mockito.when(descriptor.getDeserializer()).thenReturn(entity);

		final TEntity loadedEntity = repository.load(descriptor);

		// Assert:
		Assert.assertThat(this.areEquivalent(originalEntity, loadedEntity), IsEqual.equalTo(true));
	}

	protected abstract TEntity createEntity(final String name);

	protected abstract TEntity createEntityWithEntries(final String name, final int numEntries);

	protected abstract boolean areEquivalent(final TEntity lhs, final TEntity rhs);

	protected abstract TEntityDescriptor createDescriptor();

	protected abstract TBinaryEntityRepository createRepository();

	protected abstract Class<? extends StorableEntityStorageException> getExceptionClass();

	protected abstract Integer getExceptionValue(final Integer originalValue);
}
