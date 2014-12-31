package org.nem.ncc.storage;

import org.nem.core.serialization.*;
import org.nem.core.utils.StringUtils;

/**
 * Represents a name for a storable entity.
 */
public class StorableEntityName {
	private final String name;

	/**
	 * Creates a storable entity name.
	 *
	 * @param name The name.
	 */
	public StorableEntityName(final String name) {
		if (StringUtils.isNullOrWhitespace(name)) {
			throw new IllegalArgumentException("name must be non-whitespace");
		}

		this.name = name;
	}

	public StorableEntityName(final Deserializer deserializer) {
		this.name = deserializer.readString("storableEntity");
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof StorableEntityName)) {
			return false;
		}

		final StorableEntityName rhs = (StorableEntityName)obj;
		return this.name.equals(rhs.name);
	}

	@Override
	public String toString() {
		return this.name;
	}

	//region inline serialization

	/**
	 * Writes a storable entity name object.
	 *
	 * @param serializer The serializer to use.
	 * @param label The optional label.
	 * @param name The object.
	 */
	public static void writeTo(final Serializer serializer, final String label, final StorableEntityName name) {
		serializer.writeString(label, name.toString());
	}

	/**
	 * Reads a storable entity name object.
	 *
	 * @param deserializer The deserializer to use.
	 * @param label The optional label.
	 * @return The read object.
	 */
	public static StorableEntityName readFrom(final Deserializer deserializer, final String label) {
		return new StorableEntityName(deserializer.readString(label));
	}

	//endregion
}
