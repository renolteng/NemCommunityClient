package org.nem.ncc.controller.requests;

import net.minidev.json.*;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;
import org.nem.ncc.controller.viewmodels.TransactionViewModel;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.*;

import java.util.*;

public class MultisigModificationRequestTest {

	//region constructor

	@Test
		 public void requestCanBeCreated() {
		// Arrange:
		final Address initiator = Utils.generateRandomAddress();
		final List<Address> cosignatoryAddAddresses = Arrays.asList(Utils.generateRandomAddress(), Utils.generateRandomAddress());
		final List<Address> cosignatoryDelAddresses = Arrays.asList(Utils.generateRandomAddress(), Utils.generateRandomAddress());

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(
				new WalletName("wlt"),
				TransactionViewModel.Type.Multisig_Modification.getValue(),
				new WalletPassword("pwd"),
				initiator,
				null,
				cosignatoryAddAddresses,
				cosignatoryDelAddresses,
				new MultisigMinCosignatoriesModification(5),
				12,
				Amount.fromNem(123),
				Amount.ZERO);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("wlt")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("pwd")));
		Assert.assertThat(request.getType(), IsEqual.equalTo(TransactionViewModel.Type.Multisig_Modification.getValue()));
		Assert.assertThat(request.getMultisigAccount(),IsEqual.equalTo(initiator));
		Assert.assertThat(request.getAddedCosignatories(), IsEquivalent.equivalentTo(cosignatoryAddAddresses));
		Assert.assertThat(request.getRemovedCosignatories(), IsEquivalent.equivalentTo(cosignatoryDelAddresses));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(),IsEqual.equalTo(Amount.fromNem(123)));
	}

	@Test
	public void multisigRequestCanBeCreated() {
		// Arrange:
		final Address initiator = Utils.generateRandomAddress();
		final Address issuer = Utils.generateRandomAddress();
		final List<Address> cosignatoryAddAddresses = Arrays.asList(Utils.generateRandomAddress(), Utils.generateRandomAddress());
		final List<Address> cosignatoryDelAddresses = Arrays.asList(Utils.generateRandomAddress(), Utils.generateRandomAddress());

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(
				new WalletName("wlt"),
				TransactionViewModel.Type.Multisig_Multisig_Modification.getValue(),
				new WalletPassword("pwd"),
				initiator,
				issuer,
				cosignatoryAddAddresses,
				cosignatoryDelAddresses,
				new MultisigMinCosignatoriesModification(5),
				12,
				Amount.fromNem(123),
				Amount.fromNem(321));

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("wlt")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("pwd")));
		Assert.assertThat(request.getType(), IsEqual.equalTo(TransactionViewModel.Type.Multisig_Multisig_Modification.getValue()));
		Assert.assertThat(request.getMultisigAccount(),IsEqual.equalTo(initiator));
		Assert.assertThat(request.getIssuerAddress(),IsEqual.equalTo(issuer));
		Assert.assertThat(request.getAddedCosignatories(), IsEquivalent.equivalentTo(cosignatoryAddAddresses));
		Assert.assertThat(request.getRemovedCosignatories(), IsEquivalent.equivalentTo(cosignatoryDelAddresses));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(),IsEqual.equalTo(Amount.fromNem(123)));
		Assert.assertThat(request.getMultisigFee(),IsEqual.equalTo(Amount.fromNem(321)));
	}
	//endregion

	//region serialization

	@Test
	public void requestCanBeDeserializedWithAllParameters() {
		// Arrange:
		int type = TransactionViewModel.Type.Multisig_Modification.getValue();
		final Deserializer deserializer = createDeserializer("w", "p", "a", "c", "d", 5, 12, 123L, 10L, type);

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(deserializer);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getType(), IsEqual.equalTo(type));
		Assert.assertThat(request.getMultisigAccount(),IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getAddedCosignatories(), IsEquivalent.equivalentTo(Collections.singletonList(Address.fromEncoded("c"))));
		Assert.assertThat(request.getRemovedCosignatories(), IsEquivalent.equivalentTo(Collections.singletonList(Address.fromEncoded("d"))));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(),IsEqual.equalTo(Amount.fromMicroNem(123)));
	}

	@Test
	public void requestCanBeDeserializedWithEmptyCosignatoryAddressList() {
		// Arrange:
		int type = TransactionViewModel.Type.Multisig_Modification.getValue();
		final Deserializer deserializer = createDeserializer("w", "p", "a", "", "", 5, 12, 123L, 10L, type);

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(deserializer);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getType(), IsEqual.equalTo(type));
		Assert.assertThat(request.getMultisigAccount(),IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getAddedCosignatories(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getRemovedCosignatories(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(), IsEqual.equalTo(Amount.fromMicroNem(123)));
		Assert.assertThat(request.getMultisigFee(), IsEqual.equalTo(Amount.fromMicroNem(10)));
	}

	@Test
	public void requestCanBeDeserializedWithZeroMultisigFee() {
		// Arrange:
		int type = TransactionViewModel.Type.Multisig_Modification.getValue();
		final Deserializer deserializer = createDeserializer("w", "p", "a", "", "", 5, 12, 123L, 0L, type);

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(deserializer);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getType(), IsEqual.equalTo(type));
		Assert.assertThat(request.getMultisigAccount(),IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getAddedCosignatories(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getRemovedCosignatories(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(), IsEqual.equalTo(Amount.fromMicroNem(123)));
		Assert.assertThat(request.getMultisigFee(), IsEqual.equalTo(Amount.fromMicroNem(0)));
	}

	@Test
	public void requestCanBeDeserializedWithMultisigMultisigType() {
		// Arrange:
		int type = TransactionViewModel.Type.Multisig_Multisig_Modification.getValue();
		final Deserializer deserializer = createDeserializer("w", "p", "a", "", "", 5, 12, 123L, 10L, type);

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(deserializer);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getType(), IsEqual.equalTo(type));
		Assert.assertThat(request.getMultisigAccount(),IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getAddedCosignatories(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getRemovedCosignatories(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(), IsEqual.equalTo(Amount.fromMicroNem(123)));
		Assert.assertThat(request.getMultisigFee(), IsEqual.equalTo(Amount.fromMicroNem(10L)));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingRequiredParameters() {
		// Arrange:
		int type = TransactionViewModel.Type.Multisig_Modification.getValue();
		final List<Deserializer> deserializers = Arrays.asList(
				createDeserializer(null, "p", "a", "c", "d", 5, 12, 123L, 10L, type),
				createDeserializer("w", null, "a", "c", "d", 5, 12, 123L, 10L, type),
				createDeserializer("w", "p", null, "c", "d", 5, 12, 123L, 10L, type),
				createDeserializer("w", "p", "a", null, "d", 5, 12, 123L, 10L, type),
				createDeserializer("w", "p", "a", "c", null, 5, 12, 123L, 10L, type),
				createDeserializer("w", "p", "a", "c", "d", null, 12, 123L, 10L, type),
				createDeserializer("w", "p", "a", "c", "d", 5, null, 123L, 10L, type),
				createDeserializer("w", "p", "a", "c", "d", 5, 12, null, 10L, type),
				createDeserializer("w", "p", "a", "c", "d", 5, 12, 123L, null, type),
				createDeserializer("w", "p", "a", "c", "d", 5, 12, 123L, 10L, null)
		);

		// Assert:
		for (final Deserializer deserializer : deserializers) {
			ExceptionAssert.assertThrows(v -> new MultisigModificationRequest(deserializer), MissingRequiredPropertyException.class);
		}
	}

	//endregion

	private static Deserializer createDeserializer(
			final String walletName,
			final String walletPassword,
			final String sender,
			final String addCosignatory,
			final String delCosignatory,
			final Integer relativeChange,
			final Integer hoursDue,
			final Long fee,
			final Long multisigFee,
			final Integer type) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", walletName);
		jsonObject.put("password", walletPassword);
		jsonObject.put("account", sender);

		final JSONArray cosignatoryAddArray = null != addCosignatory ? new JSONArray() : null;
		if (null != addCosignatory && !addCosignatory.isEmpty()) {
			final JSONObject address = new JSONObject();
			address.put("address", addCosignatory);
			cosignatoryAddArray.add(address);
		}
		jsonObject.put("addedCosignatories", cosignatoryAddArray);

		final JSONArray cosignatoryDelArray = null != delCosignatory ? new JSONArray() : null;
		if (null != delCosignatory && !delCosignatory.isEmpty()) {
			final JSONObject address = new JSONObject();
			address.put("address", delCosignatory);
			cosignatoryDelArray.add(address);
		}
		jsonObject.put("removedCosignatories", cosignatoryDelArray);

		final JSONObject minCosignatories = new JSONObject();
		minCosignatories.put("relativeChange", relativeChange);
		jsonObject.put("minCosignatories", minCosignatories);
		jsonObject.put("hoursDue", hoursDue);
		jsonObject.put("fee", fee);
		jsonObject.put("multisigFee", multisigFee);
		jsonObject.put("type", type);
		return new JsonDeserializer(jsonObject, null);
	}
}
