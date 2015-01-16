package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

public class PartialSignatureInformationRequest {
	private final Address multisigAddress;
	private final Address cosignatoryAddress;

	public PartialSignatureInformationRequest(final Address multisigAddress, final Address cosignatoryAddress) {
		this.multisigAddress = multisigAddress;
		this.cosignatoryAddress = cosignatoryAddress;
	}

	public PartialSignatureInformationRequest(final Deserializer deserializer) {
		this.multisigAddress = Address.readFromOptional(deserializer, "multisig", AddressEncoding.COMPRESSED);
		this.cosignatoryAddress = Address.readFromOptional(deserializer, "cosignatory", AddressEncoding.COMPRESSED);
	}

	/**
	 * Gets the multisig account id.
	 *
	 * @return The multisig account id.
	 */
	public Address getMultisigAddress() {
		return this.multisigAddress;
	}

	/**
	 * Gets the cosignatory account id.
	 *
	 * @return The cosignatory account id.
	 */
	public Address getCosignatoryAddress() {
		return this.cosignatoryAddress;
	}
}
