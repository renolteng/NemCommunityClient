package org.nem.ncc.storable.entity.storage;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.test.*;

import java.io.*;

public class SecureStorableEntityDescriptorTest {
	protected static final byte[] WRITTEN_BYTES = new byte[] { 5, 7, 123, 56, 11, 2, 53, 99, 100 };

	//region openRead

	@Test
	public void readStreamCanDecryptEncryptedPayloadWithCorrectPassword() throws IOException {
		// Arrange:
		final TestContext context = new TestContext("pwd");
		final ByteArrayInputStream memoryStream = new ByteArrayInputStream(this.createEncryptedPayload("pwd"));
		context.mockRead(memoryStream);

		// Act:
		final byte[] bytes = IOUtils.toByteArray(context.secureDescriptor.openRead());

		// Assert:
		Assert.assertThat(bytes, IsEqual.equalTo(WRITTEN_BYTES));
	}

	@Test
	public void readStreamCannotDecryptEncryptedPayloadWithIncorrectPassword() throws IOException {
		// Arrange:
		final TestContext context = new TestContext("pwd2");
		final ByteArrayInputStream memoryStream = new ByteArrayInputStream(this.createEncryptedPayload("pwd"));
		context.mockRead(memoryStream);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> ExceptionUtils.propagateVoid(() -> IOUtils.toByteArray(context.secureDescriptor.openRead())),
				StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_INCORRECT);
	}

	@Test
	public void readFailureIsMappedToAppropriateException() throws IOException {
		// Arrange:
		final TestContext context = new TestContext("pwd");
		context.mockRead(CorruptStreams.createRead());

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> ExceptionUtils.propagateVoid(() -> IOUtils.toByteArray(context.secureDescriptor.openRead())),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	//endregion

	//region openWrite

	@Test
	public void writeStreamEncryptsContentsWithPasswordAutomatically() throws IOException {
		// Arrange:
		final TestContext context = new TestContext("pwd");
		final ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
		context.mockWrite(memoryStream);

		// Act:
		try (final OutputStream os = context.secureDescriptor.openWrite()) {
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
	public void deleteDelegatesToWrappedDescriptor() {
		// Arrange:
		final TestContext context = new TestContext("pwd");

		// Act:
		context.secureDescriptor.delete();

		// Assert:
		Mockito.verify(context.descriptor, Mockito.times(1)).delete();
	}

	@Test
	public void serializeDelegatesToWrappedDescriptor() {
		// Arrange:
		final TestContext context = new TestContext("pwd");
		final Serializer serializer = Mockito.mock(Serializer.class);

		// Act:
		context.secureDescriptor.serialize(serializer);

		// Assert:
		Mockito.verify(context.descriptor, Mockito.times(1)).serialize(serializer);
	}

	//endregion

	@SuppressWarnings("unchecked")
	private byte[] createEncryptedPayload(final String password) throws IOException {
		final ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
		final StorableEntityDescriptor wrappedDescriptor = this.createDescriptor();
		Mockito.when(wrappedDescriptor.openWrite()).thenReturn(memoryStream);
		final StorableEntityDescriptor descriptor = new SecureStorableEntityDescriptor(wrappedDescriptor, new StorableEntityPassword(password));

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			os.write(new byte[] { 5, 7, 123, 56 });
			os.write(new byte[] { 11, 2 });
			os.write(new byte[] { 53, 99, 100 });
		}

		return memoryStream.toByteArray();
	}

	protected StorableEntityDescriptor createDescriptor() {
		return Mockito.mock(StorableEntityDescriptor.class);
	}

	// TODO 20150106 BR: this code is ugly. Need to improve.
	@SuppressWarnings("unchecked")
	protected void createSecureDescriptor(final TestContext context) {
		context.descriptor = this.createDescriptor();
		context.secureDescriptor = new SecureStorableEntityDescriptor(context.descriptor, new StorableEntityPassword(context.password));
	}

	protected class TestContext {
		protected final String password;
		protected StorableEntityDescriptor descriptor;
		protected SecureStorableEntityDescriptor secureDescriptor;

		protected TestContext(final String password) {
			this.password = password;
			SecureStorableEntityDescriptorTest.this.createSecureDescriptor(this);
		}

		public void setDescriptor(final StorableEntityDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		public void setSecureDescriptor(final SecureStorableEntityDescriptor secureDescriptor) {
			this.secureDescriptor = secureDescriptor;
		}

		public String getPassword() {
			return this.password;
		}

		protected void mockRead(final InputStream stream) {
			Mockito.when(this.descriptor.openRead()).thenReturn(stream);
		}

		protected void mockWrite(final OutputStream stream) {
			Mockito.when(this.descriptor.openWrite()).thenReturn(stream);
		}
	}
}
