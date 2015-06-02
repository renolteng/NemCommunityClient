package org.nem.ncc.controller.requests;

import org.nem.core.model.*;
import org.nem.core.serialization.*;

import java.util.List;

/**
 * A request to get information about a partially created multisig modification.
 * TODO 20150131 J-G: fix empty comments
 * TODO 20150131 J-G: a few basic tests
 */
public class PartialModificationInformationRequest {
	private final Address multisigAddress;
	private final List<Address> cosignatoriesAddresses;
	private final MultisigMinCosignatoriesModification minCosignatoriesModification;

	public PartialModificationInformationRequest(
			final Address multisigAddress,
			final List<Address> cosignatoriesAddresses,
			final MultisigMinCosignatoriesModification minCosignatoriesModification) {
		this.multisigAddress = multisigAddress;
		this.cosignatoriesAddresses = cosignatoriesAddresses;
		this.minCosignatoriesModification = minCosignatoriesModification;
	}

	/**
	 * Deserializes a partial transfer information request.
	 *
	 * @param deserializer The deserializer.
	 */
	public PartialModificationInformationRequest(final Deserializer deserializer) {
		this.multisigAddress = Address.readFrom(deserializer, "multisig", AddressEncoding.COMPRESSED);
		this.cosignatoriesAddresses = deserializer.readObjectArray("cosignatories", obj -> Address.readFrom(obj, "address", AddressEncoding.COMPRESSED));
		this.minCosignatoriesModification = deserializer.readObject("minCosignatories", MultisigMinCosignatoriesModification::new);
	}

	/**
	 * Gets the multsig address.
	 *
	 * @return The multisig address.
	 */
	public Address getMultisigAddress() {
		return this.multisigAddress;
	}

	/**
	 * Gets the list of cosignatory address.
	 *
	 * @return The list of cosignatory address.
	 */
	public List<Address> getCosignatoriesAddresses() {
		return this.cosignatoriesAddresses;
	}

	/**
	 * Gets the min cosignatory modification.
	 *
	 * @return The min cosignatory modification.
	 */
	public MultisigMinCosignatoriesModification getMinCosignatoriesModification() {
		return this.minCosignatoriesModification;
	}
}
