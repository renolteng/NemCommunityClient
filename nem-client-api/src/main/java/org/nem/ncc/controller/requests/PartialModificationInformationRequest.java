package org.nem.ncc.controller.requests;

import org.nem.core.model.Address;
import org.nem.core.model.MultisigModification;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.AddressEncoding;
import org.nem.core.serialization.Deserializer;

import java.util.Collection;
import java.util.List;

/**
 * A request to get information about a partially created multisig modification.
 * TODO 20150131 J-G: fix empty comments
 * TODO 20150131 J-G: a few basic tests
 */
public class PartialModificationInformationRequest {
	private final Address multisigAddress;
	private final List<Address> cosignatoriesAddresses;

	public PartialModificationInformationRequest(final Address multisigAddress, final List<Address> cosignatoriesAddresses) {
		this.multisigAddress = multisigAddress;
		this.cosignatoriesAddresses = cosignatoriesAddresses;
	}

	/**
	 * Deserializes a partial transfer information request.
	 *
	 * @param deserializer The deserializer.
	 */
	public PartialModificationInformationRequest(final Deserializer deserializer) {
		this.multisigAddress = Address.readFrom(deserializer, "multisig", AddressEncoding.COMPRESSED);
		this.cosignatoriesAddresses = deserializer.readObjectArray("cosignatories", obj -> Address.readFrom(obj, "address", AddressEncoding.COMPRESSED));
	}

	public Address getMultisigAddress() {
		return multisigAddress;
	}

	public List<Address> getCosignatoriesAddresses() {
		return cosignatoriesAddresses;
	}
}
