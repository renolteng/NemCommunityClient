package org.nem.ncc.wallet.storage;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.*;

import java.io.*;

public class SecureWalletDescriptorTest {
	private static final byte[] WRITTEN_BYTES = new byte[] { 5, 7, 123, 56, 11, 2, 53, 99, 100 };

	//region openRead

	@Test
	public void readStreamCanDecryptEncryptedPayloadWithCorrectPassword() throws IOException {
		// Arrange:
		final ByteArrayInputStream memoryStream = new ByteArrayInputStream(createEncryptedPayload(new WalletPassword("pwd")));
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(wrappedDescriptor.openRead()).thenReturn(memoryStream);
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd"));

		// Act:
		final byte[] bytes = IOUtils.toByteArray(descriptor.openRead());

		// Assert:
		Assert.assertThat(bytes, IsEqual.equalTo(WRITTEN_BYTES));
	}

	@Test
	public void readStreamCannotDecryptEncryptedPayloadWithIncorrectPassword() throws IOException {
		// Arrange:
		final ByteArrayInputStream memoryStream = new ByteArrayInputStream(createEncryptedPayload(new WalletPassword("pwd")));
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(wrappedDescriptor.openRead()).thenReturn(memoryStream);
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd2"));

		// Act:
		ExceptionAssert.assertThrowsWalletStorageException(
				v -> ExceptionUtils.propagateVoid(() -> IOUtils.toByteArray(descriptor.openRead())),
				WalletStorageException.Code.WALLET_PASSWORD_INCORRECT);
	}

	@Test
	public void readFailureIsMappedToAppropriateException() throws IOException {
		// Arrange:
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(wrappedDescriptor.openRead()).thenReturn(CorruptStreams.createRead());
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd"));

		// Act:
		ExceptionAssert.assertThrowsWalletStorageException(
				v -> ExceptionUtils.propagateVoid(() -> IOUtils.toByteArray(descriptor.openRead())),
				WalletStorageException.Code.WALLET_COULD_NOT_BE_READ);
	}

	//endregion

	//region openWrite

	@Test
	public void writeStreamEncryptsContentsWithPasswordAutomatically() throws IOException {
		// Arrange:
		final ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(wrappedDescriptor.openWrite()).thenReturn(memoryStream);
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd"));

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
	public void getWalletNameDelegatesToWrappedDescriptor() {
		// Arrange:
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(wrappedDescriptor.getWalletName()).thenReturn(new WalletName("bar.wlt"));
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd"));

		// Act:
		final WalletName name = descriptor.getWalletName();

		// Assert:
		Mockito.verify(wrappedDescriptor, Mockito.times(1)).getWalletName();
		Assert.assertThat(name, IsEqual.equalTo(new WalletName("bar.wlt")));
	}

	@Test
	public void deleteDelegatesToWrappedDescriptor() {
		// Arrange:
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd"));

		// Act:
		descriptor.delete();

		// Assert:
		Mockito.verify(wrappedDescriptor, Mockito.times(1)).delete();
	}

	@Test
	public void serializeDelegatesToWrappedDescriptor() {
		// Arrange:
		final Serializer serializer = Mockito.mock(Serializer.class);
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, new WalletPassword("pwd"));

		// Act:
		descriptor.serialize(serializer);

		// Assert:
		Mockito.verify(wrappedDescriptor, Mockito.times(1)).serialize(serializer);
	}

	//endregion

	private static byte[] createEncryptedPayload(final WalletPassword password) throws IOException {
		final ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
		final WalletDescriptor wrappedDescriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(wrappedDescriptor.openWrite()).thenReturn(memoryStream);
		final WalletDescriptor descriptor = new SecureWalletDescriptor(wrappedDescriptor, password);

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			os.write(new byte[] { 5, 7, 123, 56 });
			os.write(new byte[] { 11, 2 });
			os.write(new byte[] { 53, 99, 100 });
		}

		return memoryStream.toByteArray();
	}
}