package org.nem.ncc.addressbook;

import org.nem.core.serialization.SerializableEntity;

/**
 * Represents a NEM address book that can contain zero or more entries.
 */
public interface AddressBook  extends SerializableEntity, AccountLabels {
}
