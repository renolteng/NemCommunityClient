package org.nem.ncc.storage;

import org.nem.core.serialization.Deserializer;

import java.util.function.Function;

/**
 * A pair of a storable entity name and a storable entity password.
 */
public class StorableEntityNamePasswordPair<
		TEntityName extends StorableEntityName,
		TEntityPassword extends StorableEntityPassword,
		TDerived extends StorableEntityNamePasswordPair> {
	private final TEntityName name;
	private final TEntityPassword password;
	private final Class<TDerived> derivedClass;

	/**
	 * Creates a new storable entity name / password pair.
	 *
	 * @param name The storable entity name.
	 * @param password The storable entity password.
	 */
	public StorableEntityNamePasswordPair(
			final TEntityName name,
			final TEntityPassword password) {
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
			final TEntityName name,
			final TEntityPassword password,
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
	 * @param nameActivator The name activator.
	 * @param passwordActivator The password activator.
	 */
	public StorableEntityNamePasswordPair(
			final String name,
			final String password,
			final Function<String, TEntityName> nameActivator,
			final Function<String, TEntityPassword> passwordActivator) {
		this(nameActivator.apply(name), passwordActivator.apply(password), null);
	}

	/**
	 * Deserializes a new storable entity name / password pair.
	 *
	 * @param deserializer The deserializer.
	 * @param nameActivator The name activator.
	 * @param passwordActivator The password activator.
	 * @param derivedClass The derived class.
	 */
	public StorableEntityNamePasswordPair(
			final Deserializer deserializer,
			final Function<String, TEntityName> nameActivator,
			final Function<String, TEntityPassword> passwordActivator,
			final Class<TDerived> derivedClass) {
		this.name = nameActivator.apply(deserializer.readString("storableEntity"));
		this.password = passwordActivator.apply(deserializer.readString("password"));
		this.derivedClass = derivedClass;
	}

	/**
	 * Gets the storable entity name.
	 *
	 * @return The storable entity name.
	 */
	public TEntityName getName() {
		return this.name;
	}

	/**
	 * Gets the storable entity password.
	 *
	 * @return The storable entity password.
	 */
	public TEntityPassword getPassword() {
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
