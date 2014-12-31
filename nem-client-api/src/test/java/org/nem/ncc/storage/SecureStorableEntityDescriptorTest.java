package org.nem.ncc.storage;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.test.*;

import java.io.*;

public class SecureStorableEntityDescriptorTest {
	private static final byte[] WRITTEN_BYTES = new byte[] { 5, 7, 123, 56, 11, 2, 53, 99, 100 };

	//region openRead

	@Test
	public void readStreamCanDecryptEncryptedPayloadWithCorrectPassword() throws IOException {
		// Arrange:
		final ByteArrayInputStream memoryStream = new ByteArrayInputStream(createEncryptedPayload(new StorableEntityPassword("pwd")));
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(wrappedDescriptor.openRead()).thenReturn(memoryStream);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd"));

		// Act:
		final byte[] bytes = IOUtils.toByteArray(descriptor.openRead());

		// Assert:
		Assert.assertThat(bytes, IsEqual.equalTo(WRITTEN_BYTES));
	}

	@Test
	public void readStreamCannotDecryptEncryptedPayloadWithIncorrectPassword() throws IOException {
		// Arrange:
		final ByteArrayInputStream memoryStream = new ByteArrayInputStream(createEncryptedPayload(new StorableEntityPassword("pwd")));
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(wrappedDescriptor.openRead()).thenReturn(memoryStream);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd2"));

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> ExceptionUtils.propagateVoid(() -> IOUtils.toByteArray(descriptor.openRead())),
				StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_INCORRECT);
	}

	@Test
	public void readFailureIsMappedToAppropriateException() throws IOException {
		// Arrange:
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(wrappedDescriptor.openRead()).thenReturn(CorruptStreams.createRead());
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd"));

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> ExceptionUtils.propagateVoid(() -> IOUtils.toByteArray(descriptor.openRead())),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	//endregion

	//region openWrite

	@Test
	public void writeStreamEncryptsContentsWithPasswordAutomatically() throws IOException {
		// Arrange:
		final ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(wrappedDescriptor.openWrite()).thenReturn(memoryStream);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd"));

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			os.write(new byte[] { 5, 7, 123, 56 });
			os.write(new byte[] { 11, 2 });
			os.write(new byte[] { 53, 99, 100 });
		}

		// Assert:
		Assert.assertThat(
				memoryStream.toByteArray(),
				IsNot.not(IsEqual.equalTo(WRITTEN_BYTES)));
	}

	//endregion

	//region simple delegation

	@Test
	public void getStorableEntityNameDelegatesToWrappedDescriptor() {
		// Arrange:
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(wrappedDescriptor.getStorableEntityName()).thenReturn(new StorableEntityName("bar.wlt"));
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd"));

		// Act:
		final StorableEntityName name = descriptor.getStorableEntityName();

		// Assert:
		Mockito.verify(wrappedDescriptor, Mockito.times(1)).getStorableEntityName();
		Assert.assertThat(name, IsEqual.equalTo(new StorableEntityName("bar.wlt")));
	}

	@Test
	public void deleteDelegatesToWrappedDescriptor() {
		// Arrange:
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd"));

		// Act:
		descriptor.delete();

		// Assert:
		Mockito.verify(wrappedDescriptor, Mockito.times(1)).delete();
	}

	@Test
	public void serializeDelegatesToWrappedDescriptor() {
		// Arrange:
		final Serializer serializer = Mockito.mock(Serializer.class);
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword("pwd"));

		// Act:
		descriptor.serialize(serializer);

		// Assert:
		Mockito.verify(wrappedDescriptor, Mockito.times(1)).serialize(serializer);
	}

	//endregion

	private static byte[] createEncryptedPayload(final StorableEntityPassword password) throws IOException {
		final ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
		final StorableEntityDescriptor wrappedDescriptor = Mockito.mock(StorableEntityDescriptor.class);
		Mockito.when(wrappedDescriptor.openWrite()).thenReturn(memoryStream);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, password);

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			os.write(new byte[] { 5, 7, 123, 56 });
			os.write(new byte[] { 11, 2 });
			os.write(new byte[] { 53, 99, 100 });
		}

		return memoryStream.toByteArray();
	}
}
