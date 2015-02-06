package org.nem.ncc.addressbook;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A memory-backed address book implementation.
 */
public class MemoryAddressBook implements StorableAddressBook {
	private final AddressBookName name;
	private final AddressBookFileExtension fileExtension = new AddressBookFileExtension();
	private final Map<Address, AccountLabel> accountLabels;

	/**
	 * Creates a new address book with the specified name.
	 *
	 * @param name The address book name.
	 */
	public MemoryAddressBook(final AddressBookName name) {
		this(name, new ArrayList<>());
	}

	/**
	 * Creates a new address book with the specified name and account labels.
	 *
	 * @param name The address book name.
	 * @param accountLabels The account labels.
	 */
	public MemoryAddressBook(final AddressBookName name, final Collection<AccountLabel> accountLabels) {
		if (null == name) {
			throw new IllegalArgumentException("name must be non-null");
		}

		if (null == accountLabels) {
			throw new IllegalArgumentException("accountLabels must be non-null");
		}

		this.name = name;
		this.accountLabels = new ConcurrentHashMap<>();
		this.addAccountLabels(accountLabels);
	}

	/**
	 * Deserializes an address book.
	 *
	 * @param deserializer The deserializer.
	 */
	public MemoryAddressBook(final Deserializer deserializer) {
		this.name = AddressBookName.readFrom(deserializer, "addressBook");
		this.accountLabels = new ConcurrentHashMap<>();
		this.addAccountLabels(deserializer.readObjectArray("accountLabels", AccountLabel::new));
	}

	private void addAccountLabels(final Collection<AccountLabel> accountLabels) {
		accountLabels.forEach(this::addLabel);
	}

	@Override
	public StorableAddressBook deserialize(final Deserializer deserializer) {
		return new MemoryAddressBook(deserializer);
	}

	@Override
	public AddressBookName getName() {
		return this.name;
	}

	@Override
	public AddressBookFileExtension getFileExtension() {
		return this.fileExtension;
	}

	@Override
	public int size() {
		return this.accountLabels.size();
	}

	@Override
	public Collection<AccountLabel> getAccountLabels() {
		return new ArrayList<>(this.accountLabels.values());
	}

	@Override
	public AccountLabel getLabel(final Address address) {
		final AccountLabel accountLabel = this.accountLabels.get(address);
		if (null == accountLabel) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_NOT_IN_ADDRESS_BOOK);
		}

		return accountLabel;
	}

	@Override
	public void setLabel(final Address address, final String label, final String privateLabel) {
		final AccountLabel accountLabel = this.accountLabels.get(address);
		if (null == accountLabel) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_NOT_IN_ADDRESS_BOOK);
		}

		this.accountLabels.put(address, new AccountLabel(address, label, privateLabel));
	}

	@Override
	public void addLabel(final AccountLabel accountLabel) {
		if (null == accountLabel) {
			throw new IllegalArgumentException("accountLabel must be non-null");
		}

		if (!accountLabel.getAddress().isValid()) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_NOT_VALID);
		}

		if (this.accountLabels.containsKey(accountLabel.getAddress())) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_BOOK_ALREADY_CONTAINS_ADDRESS);
		}

		this.accountLabels.put(accountLabel.getAddress(), accountLabel);
	}

	@Override
	public void removeLabel(final Address address) {
		final AccountLabel accountLabel = this.accountLabels.get(address);
		if (null == accountLabel) {
			throw new AddressBookException(AddressBookException.Code.ADDRESS_NOT_IN_ADDRESS_BOOK);
		}

		this.accountLabels.remove(address);
	}

	@Override
	public boolean contains(final Address address) {
		return null != this.accountLabels.get(address);
	}

	@Override
	public void serialize(final Serializer serializer) {
		AddressBookName.writeTo(serializer, "addressBook", this.name);
		serializer.writeObjectArray("accountLabels", this.accountLabels.values());
	}
}
