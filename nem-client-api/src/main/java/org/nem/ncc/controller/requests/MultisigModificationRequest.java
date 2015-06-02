package org.nem.ncc.controller.requests;

import org.nem.core.model.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;
import org.nem.ncc.wallet.*;

import java.util.List;

/**
 * A request containing all information necessary to create a multisig aggregate modification transaction.
 * TODO 20150131 J-G: probably makes sense to have a base class for these
 * TODO 20150131 J-G: fix comments referring to transfer [send request]
 * TODO 20150131 J-G: a few basic tests
 */
public class MultisigModificationRequest {
	private final WalletName walletName;
	private final WalletPassword password;
	private final Address senderAddress;
	private final List<Address> cosignatoriesAddresses;
	private final MultisigMinCosignatoriesModification minCosignatoriesModification;
	private final int hoursDue;
	private final Amount fee;

	/**
	 * Creates a new multisig modification request.
	 */
	public MultisigModificationRequest(
			final WalletName walletName,
			final WalletPassword password,
			final Address senderAddress,
			final List<Address> cosignatoriesAddresses,
			final MultisigMinCosignatoriesModification minCosignatoriesModification,
			final int hoursDue,
			final Amount fee) {
		this.walletName = walletName;
		this.password = password;
		this.senderAddress = senderAddress;
		this.cosignatoriesAddresses = cosignatoriesAddresses;
		this.minCosignatoriesModification = minCosignatoriesModification;
		this.hoursDue = hoursDue;
		this.fee = fee;
	}

	/**
	 * Deserializes a multisig modification request.
	 *
	 * @param deserializer The deserializer.
	 */
	public MultisigModificationRequest(final Deserializer deserializer) {
		this.walletName = WalletName.readFrom(deserializer, "wallet");
		this.password = WalletPassword.readFrom(deserializer, "password");
		this.senderAddress = Address.readFrom(deserializer, "account");
		this.cosignatoriesAddresses = deserializer.readObjectArray("cosignatories", obj -> Address.readFrom(obj, "address", AddressEncoding.COMPRESSED));
		this.minCosignatoriesModification = deserializer.readObject("minCosignatories", MultisigMinCosignatoriesModification::new);
		this.hoursDue = deserializer.readInt("hoursDue");
		this.fee = Amount.readFrom(deserializer, "fee");
	}

	/**
	 * Gets the wallet name.
	 *
	 * @return The wallet name.
	 */
	public WalletName getWalletName() {
		return this.walletName;
	}

	/**
	 * Gets the sender account id.
	 *
	 * @return The sender account id.
	 */
	public Address getSenderAddress() {
		return this.senderAddress;
	}

	/**
	 * Gets list of cosignatories.
	 *
	 * @return The list of cosignatories.
	 */
	public List<Address> getCosignatoriesAddresses() {
		return this.cosignatoriesAddresses;
	}

	/**
	 * Gets the min cosignatories modification.
	 *
	 * @return The min cosignatories modification.
	 */
	public MultisigMinCosignatoriesModification getMinCosignatoriesModification() {
		return this.minCosignatoriesModification;
	}

	/**
	 * Gets the hours due.
	 *
	 * @return The hours due.
	 */
	public int getHoursDue() {
		return this.hoursDue;
	}

	/**
	 * Gets the password.
	 *
	 * @return The password.
	 */
	public WalletPassword getPassword() {
		return this.password;
	}

	/**
	 * Gets the fee.
	 *
	 * @return The fee.
	 */
	public Amount getFee() {
		return this.fee;
	}
}
