package org.nem.ncc.addressbook;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.core.model.Address;
import org.nem.ncc.addressbook.storage.AddressBookDescriptor;
import org.nem.ncc.test.*;

import java.util.Collection;

public class AutoSavingAddressBookTest extends AddressBookTest {

	//region createAddressBook

	@Override
	protected AddressBook createAddressBook(final AddressBookName name) {
		return createAutoSavingAddressBook(new MemoryAddressBook(name));
	}

	@Override
	protected AddressBook createAddressBook(final AddressBookName name, final Collection<AccountLabel> accountLabels) {
		return createAutoSavingAddressBook(new MemoryAddressBook(name, accountLabels));
	}

	private static AddressBook createAutoSavingAddressBook(final StorableAddressBook addressBook) {
		final AddressBookDescriptor descriptor = Mockito.mock(AddressBookDescriptor.class);
		final AddressBookRepository repository = Mockito.mock(AddressBookRepository.class);
		return new AutoSavingAddressBook(addressBook, descriptor, repository);
	}

	//endregion

	//region saving

	@Test
	public void saveDelegatesToRepository() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.addressBook.save();

		// Assert:
		Mockito.verify(context.repository, Mockito.times(1)).save(context.descriptor, context.wrappedAddressBook);
	}

	@Test
	public void addLabelCallsSaveOnSuccess() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.addressBook.addLabel(new AccountLabel(Utils.generateRandomAddress(), "foo", "bar"));

		// Assert:
		Mockito.verify(context.repository, Mockito.times(1)).save(context.descriptor, context.wrappedAddressBook);
	}

	@Test
	public void addLabelDoesNotCallSaveOnFailure() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		ExceptionAssert.assertThrows(v -> context.addressBook.addLabel(null), IllegalArgumentException.class);

		// Assert:
		Mockito.verify(context.repository, Mockito.times(0)).save(context.descriptor, context.wrappedAddressBook);
	}

	@Test
	public void setLabelCallsSaveOnSuccess() {
		// Arrange:
		final TestContext context = new TestContext();
		final Address address = Utils.generateRandomAddress();
		context.addressBook.addLabel(new AccountLabel(address, "foo", "bar"));

		// Act:
		context.addressBook.setLabel(address, "foobar", "foobaz");

		// Assert:
		Mockito.verify(context.repository, Mockito.times(2)).save(context.descriptor, context.wrappedAddressBook);
	}

	@Test
	public void setLabelDoesNotCallSaveOnFailure() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		ExceptionAssert.assertThrows(v -> context.addressBook.setLabel(Utils.generateRandomAddress(), "foo", "bar"), AddressBookException.class);

		// Assert:
		Mockito.verify(context.repository, Mockito.times(0)).save(context.descriptor, context.wrappedAddressBook);
	}

	@Test
	public void removeLabelCallsSaveOnSuccess() {
		// Arrange:
		final TestContext context = new TestContext();
		final Address address = Utils.generateRandomAddress();
		context.addressBook.addLabel(new AccountLabel(address, "foo", "bar"));

		// Act:
		context.addressBook.removeLabel(address);

		// Assert:
		Mockito.verify(context.repository, Mockito.times(2)).save(context.descriptor, context.wrappedAddressBook);
	}

	@Test
	public void removeLabelDoesNotCallSaveOnFailure() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		ExceptionAssert.assertThrows(v -> context.addressBook.removeLabel(Utils.generateRandomAddress()), AddressBookException.class);

		// Assert:
		Mockito.verify(context.repository, Mockito.times(0)).save(context.descriptor, context.wrappedAddressBook);
	}

	//endregion

	private class TestContext {
		final StorableAddressBook wrappedAddressBook = new MemoryAddressBook(new AddressBookName("blah"));
		final AddressBookDescriptor descriptor = Mockito.mock(AddressBookDescriptor.class);
		final AddressBookRepository repository = Mockito.mock(AddressBookRepository.class);
		final AutoSavingAddressBook addressBook = new AutoSavingAddressBook(wrappedAddressBook, descriptor, repository);
	}
}
