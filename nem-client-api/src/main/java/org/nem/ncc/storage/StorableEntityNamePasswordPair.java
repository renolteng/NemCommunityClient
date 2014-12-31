package org.nem.ncc.storage;

import org.nem.core.serialization.Deserializer;

/**
 * A pair of a storable entity name and a storable entity password.
 */
public class StorableEntityNamePasswordPair {
	private final StorableEntityName name;
	private final StorableEntityPassword password;

	/**
	 * Creates a new storable entity name / password pair.
	 *
	 * @param name The storable entity name.
	 * @param password The storable entity password.
	 */
	public StorableEntityNamePasswordPair(final StorableEntityName name, final StorableEntityPassword password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * Creates a new storable entity name / password pair.
	 *
	 * @param name The storable entity name.
	 * @param password The storable entity password.
	 */
	public StorableEntityNamePasswordPair(final String name, final String password) {
		this(new StorableEntityName(name), new StorableEntityPassword(password));
	}

	/**
	 * Deserializes a new storable entity name / password pair.
	 *
	 * @param deserializer The deserializer.
	 */
	public StorableEntityNamePasswordPair(final Deserializer deserializer) {
		this.name = StorableEntityName.readFrom(deserializer, "storableEntity");
		this.password = StorableEntityPassword.readFrom(deserializer, "password");
	}

	/**
	 * Gets the storable entity name.
	 *
	 * @return The storable entity name.
	 */
	public StorableEntityName getName() {
		return this.name;
	}

	/**
	 * Gets the storable entity password.
	 *
	 * @return The storable entity password.
	 */
	public StorableEntityPassword getPassword() {
		return this.password;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() ^ this.password.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof StorableEntityNamePasswordPair)) {
			return false;
		}

		final StorableEntityNamePasswordPair rhs = (StorableEntityNamePasswordPair)obj;
		return this.name.equals(rhs.name) && this.password.equals(rhs.password);
	}
}
