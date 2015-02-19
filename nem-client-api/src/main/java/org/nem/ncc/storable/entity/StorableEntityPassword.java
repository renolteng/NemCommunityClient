package org.nem.ncc.storable.entity;

import org.nem.core.serialization.*;
import org.nem.core.utils.StringUtils;

/**
 * Represents a storable entity password.
 */
public abstract class StorableEntityPassword<TDerived extends StorableEntityPassword> {
	private final String password;
	private final Class<TDerived> derivedClass;

	/**
	 * Creates a new storable entity password.
	 *
	 * @param password The password.
	 */
	protected StorableEntityPassword(final String password) {
		this(password, null);
	}

	/**
	 * Creates a new storable entity password.
	 *
	 * @param password The password.
	 * @param derivedClass The derived class.
	 */
	protected StorableEntityPassword(final String password, final Class<TDerived> derivedClass) {
		if (StringUtils.isNullOrWhitespace(password)) {
			throw new IllegalArgumentException("password must be non-whitespace");
		}

		this.password = password;
		this.derivedClass = derivedClass;
	}

	/**
	 * Creates a storable entity password.
	 *
	 * @param deserializer The deserializer.
	 * @param label The label to read from.
	 * @param derivedClass The derived class.
	 */
	protected StorableEntityPassword(
			final Deserializer deserializer,
			final String label,
			final Class<TDerived> derivedClass) {
		this.password = deserializer.readString(label);
		this.derivedClass = derivedClass;
	}

	@Override
	public int hashCode() {
		return this.password.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		final Class clazz = null == this.derivedClass ? StorableEntityPassword.class : this.derivedClass;
		if (!clazz.isInstance(obj)) {
			return false;
		}

		final StorableEntityPassword rhs = (StorableEntityPassword)obj;
		return this.password.equals(rhs.password);
	}

	@Override
	public String toString() {
		return this.password;
	}

	//region inline serialization

	/**
	 * Writes a storable entity password object.
	 *
	 * @param serializer The serializer to use.
	 * @param label The optional label.
	 * @param password The object.
	 */
	public static void writeTo(final Serializer serializer, final String label, final StorableEntityPassword password) {
		serializer.writeString(label, password.toString());
	}

	//endregion
}
