package org.nem.ncc.storage;

import org.nem.core.serialization.Deserializer;

/**
 * A pair of a storable entity name and a storable entity password.
 */
public class StorableEntityNamePasswordPair<TDerived extends StorableEntityNamePasswordPair> {
	private final StorableEntityName name;
	private final StorableEntityPassword password;
	private final Class<TDerived> derivedClass;

	/**
	 * Creates a new storable entity name / password pair.
	 *
	 * @param name The storable entity name.
	 * @param password The storable entity password.
	 */
	public StorableEntityNamePasswordPair(
			final StorableEntityName name,
			final StorableEntityPassword password) {
		this(name, password, null);
	}

	/**
	 * Creates a new storable entity name / password pair.
	 *
	 * @param name The storable entity name.
	 * @param password The storable entity password.
	 * @param derivedClass The derived class.
	 */
	public StorableEntityNamePasswordPair(
			final StorableEntityName name,
			final StorableEntityPassword password,
			final Class<TDerived> derivedClass) {
		this.name = name;
		this.password = password;
		this.derivedClass = derivedClass;
	}

	/**
	 * Creates a new storable entity name / password pair.
	 *
	 * @param name The storable entity name.
	 * @param password The storable entity password.
	 */
	public StorableEntityNamePasswordPair(
			final String name,
			final String password) {
		this(new StorableEntityName<>(name), new StorableEntityPassword<>(password), null);
	}

	/**
	 * Deserializes a new storable entity name / password pair.
	 *
	 * @param deserializer The deserializer.
	 */
	public StorableEntityNamePasswordPair(
			final Deserializer deserializer,
			final Class<TDerived> derivedClass) {
		this.name = StorableEntityName.readFrom(deserializer, "storableEntity");
		this.password = StorableEntityPassword.readFrom(deserializer, "password");
		this.derivedClass = derivedClass;
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
		final Class clazz = null == this.derivedClass? StorableEntityNamePasswordPair.class : this.derivedClass;
		if (!clazz.isInstance(obj)) {
			return false;
		}

		final StorableEntityNamePasswordPair rhs = (StorableEntityNamePasswordPair)obj;
		return this.name.equals(rhs.name) && this.password.equals(rhs.password);
	}
}
