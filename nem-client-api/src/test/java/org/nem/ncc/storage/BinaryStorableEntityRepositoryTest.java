package org.nem.ncc.storage;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.BinarySerializer;
import org.nem.ncc.test.*;

import java.io.*;

public class BinaryStorableEntityRepositoryTest {

	@Test
	public void canSaveBinaryStorableEntityToWriteStream() {
		// Arrange:
		final StorableEntity entity = StorableEntityUtils.createStorableEntity(new StorableEntityName("blah"));
		final StorableEntityDescriptor descriptor = Mockito.mock(StorableEntityDescriptor.class);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		new BinaryStorableEntityRepository().save(descriptor, entity);

		// Assert:
		Assert.assertThat(outputStream.toByteArray(), IsEqual.equalTo(BinarySerializer.serializeToBytes(entity)));
	}

	@Test
	public void saveFailureIsMappedToAppropriateException() {
		// Arrange:
		final StorableEntity entity = StorableEntityUtils.createStorableEntity(new StorableEntityName("blah"));
		final StorableEntityDescriptor descriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(descriptor.openWrite()).thenReturn(CorruptStreams.createWrite());

		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new BinaryStorableEntityRepository().save(descriptor, entity),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED);
	}

	@Test
	public void canLoadBinaryStorableEntityFromReadStream() {
		// Arrange:
		final StorableEntity originalEntity = StorableEntityUtils.createStorableEntityWithEntries(new StorableEntityName("blah"), 5);
		final StorableEntityDescriptor descriptor = Mockito.mock(StorableEntityDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(BinarySerializer.serializeToBytes(originalEntity));
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);
		Mockito.when(descriptor.getEntity()).thenReturn(StorableEntityUtils.createStorableEntity(new StorableEntityName("blah")));

		// Act:
		final StorableEntity entity = new BinaryStorableEntityRepository().load(descriptor);

		// Assert:
		// TODO 20150101 BR: Any way to do this with IsEquivalent matcher?
		Assert.assertThat(StorableEntityUtils.isEqual(entity, originalEntity), IsEqual.equalTo(true));
	}

	@Test
	public void loadFailureIsMappedToAppropriateException() {
		// Arrange:
		final StorableEntityDescriptor descriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(descriptor.openRead()).thenReturn(CorruptStreams.createRead());

		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new BinaryStorableEntityRepository().load(descriptor),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	@Test
	public void loadSerializationFailureIsMappedToAppropriateException() {
		// Arrange: (storable entity deserialization will fail because the binary stream contains no data)
		final StorableEntityDescriptor descriptor = Mockito.mock(StorableEntityDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] { });
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);
		Mockito.when(descriptor.getEntity()).thenReturn(StorableEntityUtils.createStorableEntity(new StorableEntityName("blah")));

		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new BinaryStorableEntityRepository().load(descriptor),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	@Test
	public void binaryStorableEntityCanBeRoundTripped() {
		// Arrange:
		final StorableEntityRepository repository = new BinaryStorableEntityRepository();

		final StorableEntity originalEntity = StorableEntityUtils.createStorableEntityWithEntries(new StorableEntityName("blah"), 5);
		final StorableEntityDescriptor descriptor = Mockito.mock(StorableEntityDescriptor.class);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);
		Mockito.when(descriptor.getEntity()).thenReturn(originalEntity);

		// Act:
		repository.save(descriptor, originalEntity);

		Mockito.when(descriptor.openRead()).thenReturn(new ByteArrayInputStream(outputStream.toByteArray()));
		Mockito.when(descriptor.getEntity()).thenReturn(StorableEntityUtils.createStorableEntity(new StorableEntityName("blah")));

		final StorableEntity entity = repository.load(descriptor);

		// Assert:
		// TODO 20150101 BR: Any way to do this with IsEquivalent matcher?
		Assert.assertThat(StorableEntityUtils.isEqual(entity, originalEntity), IsEqual.equalTo(true));
	}
}
