package org.nem.ncc.cache;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.model.ncc.AccountInfo;
import org.nem.core.serialization.*;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.test.*;

import java.io.*;
import java.util.*;

public class AccountsFileRepositoryTest {

	//region save

	@Test
	public void canSaveAccountsToWriteStream() {
		// Arrange:
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(3);
		final AccountsFileDescriptor descriptor = Mockito.mock(AccountsFileDescriptor.class);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		new AccountsFileRepository(descriptor).save(accounts);

		// Assert:
		Assert.assertThat(
				outputStream.toByteArray(),
				IsEqual.equalTo(JsonSerializer.serializeToBytes(new SerializableList<>(accounts))));
	}

	@Test
	public void saveFailureIsMappedToAppropriateException() {
		// Arrange:
		final List<AccountInfo> accounts = Utils.generateRandomAccountInfos(3);
		final AccountsFileDescriptor descriptor = Mockito.mock(AccountsFileDescriptor.class);
		Mockito.when(descriptor.openWrite()).thenReturn(CorruptStreams.createWrite());

		// Assert:
		ExceptionAssert.assertThrowsNccException(
				v -> new AccountsFileRepository(descriptor).save(accounts),
				NccException.Code.ACCOUNT_CACHE_ERROR);
	}

	//endregion

	//region load

	@Test
	public void canLoadAccountsFromReadStream() {
		// Arrange:
		final List<AccountInfo> originalAccounts = Utils.generateRandomAccountInfos(3);
		final AccountsFileDescriptor descriptor = Mockito.mock(AccountsFileDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(
				JsonSerializer.serializeToBytes(new SerializableList<>(originalAccounts)));
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);

		// Act:
		final Collection<AccountInfo> accounts = new AccountsFileRepository(descriptor).load();

		// Assert:
		Assert.assertThat(accounts, IsEquivalent.equivalentTo(originalAccounts));
	}

	@Test
	public void canLoadAccountsFromEmptyReadStream() {
		// Arrange:
		final AccountsFileDescriptor descriptor = Mockito.mock(AccountsFileDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] { });
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);

		// Act:
		final Collection<AccountInfo> accounts = new AccountsFileRepository(descriptor).load();

		// Assert:
		Assert.assertThat(accounts.size(), IsEqual.equalTo(0));
	}

	@Test
	public void loadFailureIsMappedToAppropriateException() {
		// Arrange:
		final AccountsFileDescriptor descriptor = Mockito.mock(AccountsFileDescriptor.class);
		Mockito.when(descriptor.openRead()).thenReturn(CorruptStreams.createRead());

		// Assert:
		ExceptionAssert.assertThrowsNccException(
				v -> new AccountsFileRepository(descriptor).load(),
				NccException.Code.ACCOUNT_CACHE_ERROR);
	}

	//endregion

	@Test
	public void accountsCanBeRoundTripped() {
		// Arrange:
		final AccountsFileDescriptor descriptor = Mockito.mock(AccountsFileDescriptor.class);
		final AccountsFileRepository repository = new AccountsFileRepository(descriptor);

		final List<AccountInfo> originalAccounts = Utils.generateRandomAccountInfos(3);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Mockito.when(descriptor.openWrite()).thenReturn(outputStream);

		// Act:
		repository.save(originalAccounts);

		Mockito.when(descriptor.openRead()).thenReturn(new ByteArrayInputStream(outputStream.toByteArray()));

		final Collection<AccountInfo> accounts = repository.load();

		// Assert:
		Assert.assertThat(accounts, IsEquivalent.equivalentTo(originalAccounts));
	}
}