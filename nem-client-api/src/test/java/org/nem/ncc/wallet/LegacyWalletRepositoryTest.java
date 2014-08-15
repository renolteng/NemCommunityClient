package org.nem.ncc.wallet;

import net.minidev.json.*;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.utils.Base64Encoder;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.storage.*;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class LegacyWalletRepositoryTest {

	//region save

	@Test(expected = UnsupportedOperationException.class)
	public void saveIsNotSupported() {
		// Arrange:
		final Wallet wallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(descriptor.openWrite()).thenReturn(CorruptStreams.createWrite());

		// Act:
		new LegacyWalletRepository().save(descriptor, wallet);
	}

	//endregion

	//region load

	@Test
	public void canLoadJsonWalletFromReadStream() {
		// Arrange:,
		final WalletAccount primaryAccount = new WalletAccount();
		final Collection<WalletAccount> otherAccounts = Arrays.asList(new WalletAccount(), new WalletAccount(), new WalletAccount());
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(
				createJsonWalletObject("blah", primaryAccount, otherAccounts).toJSONString().getBytes());
		Mockito.when(descriptor.openRead()).thenReturn(inputStream);

		// Act:
		final Wallet wallet = new LegacyWalletRepository().load(descriptor);

		// Assert:
		Assert.assertThat(wallet.getName(), IsEqual.equalTo(new WalletName("blah")));
		Assert.assertThat(wallet.getPrimaryAccount(), IsEqual.equalTo(primaryAccount));
		Assert.assertThat(wallet.getOtherAccounts(), IsEquivalent.equivalentTo(otherAccounts));
	}

	private static JSONObject createJsonWalletObject(
			final String name,
			final WalletAccount primaryAccount,
			final Collection<WalletAccount> otherAccounts) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("primaryAccount", createJsonAccountObject(primaryAccount));
		jsonObject.put("additionalAccounts", createJsonAccountObjectArray(otherAccounts));
		return jsonObject;
	}

	private static JSONObject createJsonAccountObject(final WalletAccount account) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", Base64Encoder.getString(account.getPrivateKey().getRaw().toByteArray()));
		return jsonObject;
	}

	private static JSONArray createJsonAccountObjectArray(final Collection<WalletAccount> accounts) {
		return accounts.stream()
				.map(LegacyWalletRepositoryTest::createJsonAccountObject)
				.collect(Collectors.toCollection(JSONArray::new));
	}

	@Test
	public void loadFailsIfStreamDoesNotContainJsonData() {
		// Arrange:
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(descriptor.openRead()).thenReturn(new ByteArrayInputStream(new byte[] { }));

		// Assert:
		ExceptionAssert.assertThrowsWalletStorageException(
				v -> new LegacyWalletRepository().load(descriptor),
				WalletStorageException.Code.WALLET_COULD_NOT_BE_READ);
	}

	@Test
	public void loadFailureIsMappedToAppropriateException() {
		// Arrange:
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		Mockito.when(descriptor.openRead()).thenReturn(CorruptStreams.createRead());

		// Assert:
		ExceptionAssert.assertThrowsWalletStorageException(
				v -> new LegacyWalletRepository().load(descriptor),
				WalletStorageException.Code.WALLET_COULD_NOT_BE_READ);
	}

	//endregion
}