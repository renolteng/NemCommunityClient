package org.nem.ncc.wallet;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.BinarySerializer;
import org.nem.ncc.storage.StorableEntityStorageException;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.storage.WalletDescriptor;

import java.io.*;

public class BinaryWalletRepositoryTest {

	@Test
	public void canSaveBinaryWalletToWriteStream() {
		// Arrange:
		final StorableWallet wallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		new BinaryWalletRepository().save(descriptor, wallet);

		// Assert:
		Assert.assertThat(outputStream.toByteArray(), IsEqual.equalTo(BinarySerializer.serializeToBytes(wallet)));
	}

	@Test
	public void saveFailureIsMappedToAppropriateException() {
		// Arrange:
		final StorableWallet wallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(descriptor.openWrite()).thenReturn(CorruptStreams.createWrite());

		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new BinaryWalletRepository().save(descriptor, wallet),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED);
	}

	@Test
	public void canLoadBinaryWalletFromReadStream() {
		// Arrange:
		final StorableWallet originalWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(BinarySerializer.serializeToBytes(originalWallet));
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);
		Mockito.when(descriptor.getDeserializer()).thenReturn(MemoryWallet::new);

		// Act:
		final Wallet wallet = new BinaryWalletRepository().load(descriptor);

		// Assert:
		Assert.assertThat(wallet.getName(), IsEqual.equalTo(new WalletName("blah")));
	}

	@Test
	public void loadFailureIsMappedToAppropriateException() {
		// Arrange:
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(descriptor.openRead()).thenReturn(CorruptStreams.createRead());

		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new BinaryWalletRepository().load(descriptor),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	@Test
	public void loadSerializationFailureIsMappedToAppropriateException() {
		// Arrange: (wallet deserialization will fail because the binary stream contains no data)
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] { });
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);
		Mockito.when(descriptor.getDeserializer()).thenReturn(MemoryWallet::new);

		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new BinaryWalletRepository().load(descriptor),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	@Test
	public void binaryWalletCanBeRoundTripped() {
		// Arrange:
		final WalletRepository walletRepository = new BinaryWalletRepository();

		final StorableWallet originalWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		walletRepository.save(descriptor, originalWallet);

		Mockito.when(descriptor.openRead()).thenReturn(new ByteArrayInputStream(outputStream.toByteArray()));
		Mockito.when(descriptor.getDeserializer()).thenReturn(MemoryWallet::new);

		final StorableWallet wallet = walletRepository.load(descriptor);

		// Assert:
		Assert.assertThat(wallet.getName(), IsEqual.equalTo(new WalletName("blah")));
	}
}