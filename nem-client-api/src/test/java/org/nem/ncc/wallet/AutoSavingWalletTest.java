package org.nem.ncc.wallet;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.storage.WalletDescriptor;

import java.util.Collection;

public class AutoSavingWalletTest extends WalletTest {

	//region createWallet

	@Override
	protected Wallet createWallet(final WalletName name) {
		return createAutoSavingWallet(new MemoryWallet(name));
	}

	@Override
	protected Wallet createWallet(final WalletName name, final WalletAccount primaryAccount) {
		return createAutoSavingWallet(new MemoryWallet(name, primaryAccount));
	}

	@Override
	protected Wallet createWallet(final WalletName name, final WalletAccount primaryAccount, final Collection<WalletAccount> otherAccounts) {
		return createAutoSavingWallet(new MemoryWallet(name, primaryAccount, otherAccounts));
	}

	private static Wallet createAutoSavingWallet(final Wallet wallet) {
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		return new AutoSavingWallet(wallet, descriptor, repository);
	}

	//endregion

	//region saving

	@Test
	public void saveDelegatesToRepository() {
		// Arrange:
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		wallet.save();

		// Assert:
		Mockito.verify(repository, Mockito.times(1)).save(descriptor, wallet);
	}

	@Test
	public void addOtherAccountCallsSaveOnSuccess() {
		// Arrange:
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		wallet.addOtherAccount(new WalletAccount());

		// Assert:
		Mockito.verify(repository, Mockito.times(1)).save(descriptor, wallet);
	}

	@Test
	public void addOtherAccountCallsSaveOnSuccessDoesNotCallSaveOnFailure() {
		// Arrange:
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		ExceptionAssert.assertThrows(v -> wallet.addOtherAccount(null), IllegalArgumentException.class);

		// Assert:
		Mockito.verify(repository, Mockito.times(0)).save(descriptor, wallet);
	}

	@Test
	public void setPrimaryAccountCallsSaveOnSuccess() {
		// Arrange:
		final WalletAccount otherAccount = new WalletAccount();
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		wrappedWallet.addOtherAccount(otherAccount);

		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		wallet.setPrimaryAccount(otherAccount.getAddress());

		// Assert:
		Mockito.verify(repository, Mockito.times(1)).save(descriptor, wallet);
	}

	@Test
	public void setPrimaryAccountDoesNotCallSaveOnFailure() {
		// Arrange:
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		ExceptionAssert.assertThrows(v -> wallet.setPrimaryAccount(null), IllegalArgumentException.class);

		// Assert:
		Mockito.verify(repository, Mockito.times(0)).save(descriptor, wallet);
	}

	@Test
	public void removeAccountCallsSaveOnSuccess() {
		// Arrange:
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		final WalletAccount otherAccount = new WalletAccount();
		wrappedWallet.addOtherAccount(otherAccount);

		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		wallet.removeAccount(otherAccount.getAddress());

		// Assert:
		Mockito.verify(repository, Mockito.times(1)).save(descriptor, wallet);
	}

	@Test
	public void removeAccountDoesNotCallSaveOnFailure() {
		// Arrange:
		final Wallet wrappedWallet = new MemoryWallet(new WalletName("blah"));
		final WalletDescriptor descriptor = Mockito.mock(WalletDescriptor.class);
		final WalletRepository repository = Mockito.mock(WalletRepository.class);
		final AutoSavingWallet wallet = new AutoSavingWallet(wrappedWallet, descriptor, repository);

		// Act:
		ExceptionAssert.assertThrows(v -> wallet.removeAccount(Utils.generateRandomAddress()), WalletException.class);

		// Assert:
		Mockito.verify(repository, Mockito.times(0)).save(descriptor, wallet);
	}

	//endregion
}