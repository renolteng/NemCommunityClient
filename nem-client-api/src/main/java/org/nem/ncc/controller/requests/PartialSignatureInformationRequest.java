package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.serialization.*;

/**
 * TODO 20150131 J-G: fix empty comments
 * TODO 20150131 J-G: a few basic tests
 * TODO 20150605 BR -> J: done
 *
 * Helper class to pass partial signature transaction data to NCC.
 */
public class PartialSignatureInformationRequest {
	private final Address multisigAddress;
	private final Address cosignatoryAddress;

	/**
	 * Creates a partial signature information request.
	 *
	 * @param multisigAddress The multisig address.
	 * @param cosignatoryAddress The cosignatory address.
	 */
	public PartialSignatureInformationRequest(final Address multisigAddress, final Address cosignatoryAddress) {
		this.multisigAddress = multisigAddress;
		this.cosignatoryAddress = cosignatoryAddress;
	}

	/**
	 * Desrializes partial signature information request.
	 *
	 * @param deserializer The deserializer.
	 */
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
