package org.nem.ncc.controller;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.*;
import org.nem.core.crypto.*;
import org.nem.core.model.*;
import org.nem.core.serialization.MissingRequiredPropertyException;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.WalletNamePasswordBag;
import org.nem.ncc.controller.viewmodels.*;
import org.nem.ncc.services.*;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.*;

import java.util.function.*;

public class WalletAccountControllerTest {

	//region addNewAccount

	@Test
	public void canAddNewAccountWithoutLabel() {
		this.assertNewAccountCanBeAdded(null);
	}

	@Test
	public void canAddNewAccountWithLabel() {
		this.assertNewAccountCanBeAdded("l");
	}

	private void assertNewAccountCanBeAdded(final String label) {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", "n");
		jsonObject.put("password", "p");
		if (null != label) {
			jsonObject.put("label", label);
		}
		final TestContext context = new TestContext(jsonObject);
		final AccountViewModel accountViewModel = Mockito.mock(AccountViewModel.class);
		final ArgumentCaptor<WalletAccount> walletAccountCaptor = ArgumentCaptor.forClass(WalletAccount.class);
		Mockito.when(context.accountMapper.toViewModel(walletAccountCaptor.capture())).thenReturn(accountViewModel);

		// Act:
		final AccountViewModel result = context.controller.addNewAccount(context.bag);

		// Assert:
		final WalletAccount walletAccount = walletAccountCaptor.getValue();
		Assert.assertThat(result, IsEqual.equalTo(accountViewModel));
		Mockito.verify(context.wallet, Mockito.times(1)).addOtherAccount(Mockito.any());
		Mockito.verify(context.walletServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(Mockito.any());
		Mockito.verify(context.addressBook, Mockito.times(1)).contains(Mockito.any());
		Mockito.verify(context.accountMapper, Mockito.times(1)).toViewModel(Mockito.any(WalletAccount.class));
		Mockito.verify(context.addressBook, Mockito.times(1))
				.addLabel(Mockito.eq(new AccountLabel(walletAccount.getAddress(), "", null == label ? "" : label)));
	}

	//endregion

	//region addExistingAccount

	@Test
	public void canAddExistingAccountWithKey() {
		this.assertExistingAccountWithKeyCanBeAdded(null);
	}

	@Test
	public void canAddExistingAccountWithKeyAndLabel() {
		this.assertExistingAccountWithKeyCanBeAdded("l");
	}

	public void assertExistingAccountWithKeyCanBeAdded(final String label) {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", "n");
		jsonObject.put("password", "p");
		jsonObject.put("accountKey", "0011223344");
		if (null != label) {
			jsonObject.put("label", label);
		}
		final TestContext context = new TestContext(jsonObject);

		final WalletAccount walletAccount = new WalletAccount(PrivateKey.fromHexString("0011223344"));
		final AccountViewModel accountViewModel = Mockito.mock(AccountViewModel.class);
		Mockito.when(context.accountMapper.toViewModel(walletAccount)).thenReturn(accountViewModel);

		// Act:
		final AccountViewModel result = context.controller.addExistingAccount(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(accountViewModel));
		Mockito.verify(context.wallet, Mockito.times(1)).addOtherAccount(walletAccount);
		Mockito.verify(context.walletServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(Mockito.any());
		Mockito.verify(context.addressBook, Mockito.times(1)).contains(Mockito.eq(walletAccount.getAddress()));
		Mockito.verify(context.accountMapper, Mockito.times(1)).toViewModel(walletAccount);
		Mockito.verify(context.addressBook, Mockito.times(1))
				.addLabel(Mockito.eq(new AccountLabel(walletAccount.getAddress(), "", null == label ? "" : label)));
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void cannotAddExistingAccountWithoutKey() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", "n");
		jsonObject.put("password", "p");
		jsonObject.put("label", "l");
		final TestContext context = new TestContext(jsonObject);

		final WalletAccount walletAccount = new WalletAccount(PrivateKey.fromHexString("0011223344"));
		final AccountViewModel accountViewModel = Mockito.mock(AccountViewModel.class);
		Mockito.when(context.accountMapper.toViewModel(walletAccount)).thenReturn(accountViewModel);

		// Act:
		context.controller.addExistingAccount(context.bag);
	}

	//endregion

	//region setPrimaryAccount / removeAccount

	@Test
	public void setPrimaryAccountDelegatesToServices() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = createJsonObjectWithAddress(address);
		final TestContext context = new TestContext(jsonObject);
		final WalletViewModel walletViewModel = Mockito.mock(WalletViewModel.class);
		Mockito.when(context.walletMapper.toViewModel(context.wallet)).thenReturn(walletViewModel);

		// Act:
		final WalletViewModel result = context.controller.setPrimaryAccount(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(walletViewModel));
		Mockito.verify(context.walletServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.wallet, Mockito.times(1)).setPrimaryAccount(address);
		Mockito.verify(context.walletMapper, Mockito.times(1)).toViewModel(context.wallet);
	}

	@Test
	public void removeAccountDelegatesToServices() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = createJsonObjectWithAddress(address);
		final TestContext context = new TestContext(jsonObject);
		final WalletViewModel walletViewModel = Mockito.mock(WalletViewModel.class);
		Mockito.when(context.walletMapper.toViewModel(context.wallet)).thenReturn(walletViewModel);

		// Act:
		final WalletViewModel result = context.controller.removeAccount(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(walletViewModel));
		Mockito.verify(context.walletServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.wallet, Mockito.times(1)).removeAccount(address);
		Mockito.verify(context.walletMapper, Mockito.times(1)).toViewModel(context.wallet);
	}

	@Test
	public void removeAccountRemovesLabelFromAddressBook() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = createJsonObjectWithAddress(address);
		final TestContext context = new TestContext(jsonObject);
		final WalletViewModel walletViewModel = Mockito.mock(WalletViewModel.class);
		Mockito.when(context.walletMapper.toViewModel(context.wallet)).thenReturn(walletViewModel);
		Mockito.when(context.addressBook.contains(address)).thenReturn(true);

		// Act:
		final WalletViewModel result = context.controller.removeAccount(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(walletViewModel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(Mockito.any());
		Mockito.verify(context.addressBook, Mockito.times(1)).contains(address);
		Mockito.verify(context.addressBook, Mockito.times(1)).removeLabel(address);
	}

	//endregion

	//region reveal (remote) private key

	@Test
	public void revealAccountReturnsViewModelOfPrivateKeyPair() {
		// Assert:
		assertCanRevealKnownAccount(WalletAccountController::revealAccount, WalletAccount::getPrivateKey);
	}

	@Test
	public void revealAccountFailsIfAddressIsUnknown() {
		// Assert:
		assertCannotRevealUnknownAccount(WalletAccountController::revealAccount);
	}

	@Test
	public void revealRemoteAccountReturnsViewModelOfPrivateKeyPair() {
		// Assert:
		assertCanRevealKnownAccount(WalletAccountController::revealRemoteAccount, WalletAccount::getRemoteHarvestingPrivateKey);
	}

	@Test
	public void revealRemoteAccountFailsIfAddressIsUnknown() {
		// Assert:
		assertCannotRevealUnknownAccount(WalletAccountController::revealRemoteAccount);
	}

	private static void assertCannotRevealUnknownAccount(
			final BiFunction<WalletAccountController, WalletNamePasswordBag, KeyPairViewModel> revealAccount) {
		// Arrange:
		final TestContext context = new TestContext();
		final WalletNamePasswordBag bag = new WalletNamePasswordBag(Utils.createDeserializer(createJsonObjectWithAddress(Utils.generateRandomAddress())));

		// Assert:
		ExceptionAssert.assertThrows(v -> revealAccount.apply(context.controller, bag),	WalletException.class);
	}

	private static void assertCanRevealKnownAccount(
			final BiFunction<WalletAccountController, WalletNamePasswordBag, KeyPairViewModel> revealAccount,
			final Function<WalletAccount, PrivateKey> getPrivateKey) {
		// Arrange:
		final TestContext context = new TestContext();
		final KeyPair keyPair = new KeyPair(getPrivateKey.apply(context.walletAccount));

		// Act:
		final KeyPairViewModel viewModel = revealAccount.apply(context.controller, context.bag);

		// Assert:
		Assert.assertThat(viewModel.getNetworkVersion(), IsEqual.equalTo(NetworkInfos.getDefault().getVersion()));
		Assert.assertThat(viewModel.getKeyPair().getPrivateKey(), IsNull.notNullValue());
		Assert.assertThat(viewModel.getKeyPair().getPrivateKey(), IsEqual.equalTo(keyPair.getPrivateKey()));
		Assert.assertThat(viewModel.getKeyPair().getPublicKey(), IsNull.notNullValue());
		Assert.assertThat(viewModel.getKeyPair().getPublicKey(), IsEqual.equalTo(keyPair.getPublicKey()));
	}

	//endregion

	private static JSONObject createJsonObjectWithAddress(final Address address) {
		final JSONObject jsonObject = createJsonObject();
		jsonObject.put("account", address.getEncoded());
		return jsonObject;
	}

	private static JSONObject createJsonObject() {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", "n");
		jsonObject.put("password", "p");
		jsonObject.put("label", "l");
		return jsonObject;
	}

	private static class TestContext {
		private final WalletServices walletServices = Mockito.mock(WalletServices.class);
		private final WalletAccount walletAccount = new WalletAccount(new KeyPair().getPrivateKey(), new KeyPair().getPrivateKey());
		private final WalletMapper walletMapper = Mockito.mock(WalletMapper.class);
		private final AccountMapper accountMapper = Mockito.mock(AccountMapper.class);
		private final AddressBookServices addressBookServices = Mockito.mock(AddressBookServices.class);
		private final AddressBook addressBook = Mockito.mock(AddressBook.class);
		private final WalletAccountController controller = new WalletAccountController(
				this.walletServices,
				this.walletMapper,
				this.accountMapper,
				this.addressBookServices);

		private final Wallet wallet = Mockito.mock(Wallet.class);
		private final WalletNamePasswordBag bag;

		private TestContext() {
			final JSONObject jsonObject = createJsonObject();
			jsonObject.put("account", this.walletAccount.getAddress().getEncoded());
			this.bag = new WalletNamePasswordBag(Utils.createDeserializer(jsonObject));
			this.setupMocks();
		}

		private TestContext(final JSONObject jsonObject) {
			this.bag = new WalletNamePasswordBag(Utils.createDeserializer(jsonObject));
			this.setupMocks();
		}

		private void setupMocks() {
			Mockito.when(this.walletServices.open(this.bag)).thenReturn(this.wallet);
			Mockito.when(this.addressBookServices.open(Mockito.any())).thenReturn(this.addressBook);
			Mockito.when(this.wallet.tryGetWalletAccount(this.walletAccount.getAddress())).thenReturn(this.walletAccount);
		}
	}
}