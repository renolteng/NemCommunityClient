package org.nem.ncc.model;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

import java.util.Objects;

/**
 * Represents an account label.
 */
public class AccountLabel implements SerializableEntity {
	private final Address address;
	private final String publicLabel;
	private final String privateLabel;

	/**
	 * Creates a new account label.
	 *
	 * @param address The account address.
	 * @param publicLabel The public label.
	 * @param privateLabel The private label.
	 */
	public AccountLabel(final Address address, final String publicLabel, final String privateLabel) {
		if (null == address) {
			throw new IllegalArgumentException("address must be non-null");
		}

		this.address = address;
		this.publicLabel = publicLabel;
		this.privateLabel = privateLabel;
	}

	/**
	 * Deserializes an account label.
	 *
	 * @param deserializer The deserializer.
	 */
	public AccountLabel(final Deserializer deserializer) {
		this.address = Address.readFrom(deserializer, "address");
		this.publicLabel = deserializer.readOptionalString("publicLabel");
		this.privateLabel = deserializer.readOptionalString("privateLabel");
	}

	/**
	 * Gets the account address.
	 *
	 * @return The account address.
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Gets the public label.
	 *
	 * @return The public label.
	 */
	public String getPublicLabel() {
		return this.publicLabel;
	}

	/**
	 * Gets the label.
	 *
	 * @return The label.
	 */
	public String getLabel() {
		return null != this.privateLabel ? this.privateLabel : this.publicLabel;
	}

	/**
	 * Gets the private label.
	 *
	 * @return The private label.
	 */
	public String getPrivateLabel() {
		return this.privateLabel;
	}

	@Override
	public void serialize(final Serializer serializer) {
		Address.writeTo(serializer, "address", this.address);
		serializer.writeString("publicLabel", this.publicLabel);
		serializer.writeString("privateLabel", this.privateLabel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.address, this.publicLabel, this.privateLabel);
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof AccountLabel)) {
			return false;
		}

		final AccountLabel rhs = (AccountLabel)obj;
		return Objects.equals(this.address, rhs.address) &&
				Objects.equals(this.publicLabel, rhs.publicLabel) &&
				Objects.equals(this.privateLabel, rhs.privateLabel);
	}
}
