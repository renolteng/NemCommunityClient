package org.nem.ncc.addressbook;

import org.nem.core.model.Address;
import org.nem.core.serialization.Serializer;
import org.nem.ncc.addressbook.storage.AddressBookDescriptor;

import java.util.Collection;

/**
 * Address book implementation that automatically saves the address book after changes are made.
 */
public class AutoSavingAddressBook implements AddressBook {
	private final StorableAddressBook addressBook;
	private final AddressBookDescriptor descriptor;
	private final AddressBookRepository repository;

	/**
	 * Creates a new auto-saving address book.
	 *
	 * @param addressBook The original address book.
	 * @param descriptor The descriptor.
	 * @param repository The repository to use for saving.
	 */
	public AutoSavingAddressBook(
			final StorableAddressBook addressBook,
			final AddressBookDescriptor descriptor,
			final AddressBookRepository repository) {
		this.addressBook = addressBook;
		this.descriptor = descriptor;
		this.repository = repository;
	}

	@Override
	public AddressBookName getName() {
		return this.addressBook.getName();
	}

	@Override
	public int size() {
		return this.addressBook.size();
	}

	@Override
	public Collection<AccountLabel> getAccountLabels() {
		return this.addressBook.getAccountLabels();
	}

	@Override
	public AccountLabel getLabel(final Address address) {
		return this.addressBook.getLabel(address);
	}

	@Override
	public void setLabel(final Address address, final String label, final String privateLabel) {
		this.addressBook.setLabel(address, label, privateLabel);
		this.save();
	}

	@Override
	public void addLabel(final AccountLabel accountLabel) {
		this.addressBook.addLabel(accountLabel);
		this.save();
	}

	@Override
	public void removeLabel(final Address address) {
		this.addressBook.removeLabel(address);
		this.save();
	}

	@Override
	public boolean contains(final Address address) {
		return this.addressBook.contains(address);
	}

	@Override
	public void serialize(final Serializer serializer) {
		this.addressBook.serialize(serializer);
	}

	/**
	 * Saves this address book to the underlying repository.
	 */
	public void save() {
		if (null != this.repository) {
			this.repository.save(this.descriptor, this.addressBook);
		}
	}
}
