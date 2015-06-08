package org.nem.ncc.controller.requests;

import net.minidev.json.*;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.*;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;
import org.nem.ncc.wallet.*;

import java.util.*;

public class MultisigModificationRequestTest {

	//region constructor

	@Test
	public void requestCanBeCreated() {
		// Arrange:
		final Address sender = Utils.generateRandomAddress();
		final List<Address> cosignatoryAddresses = Arrays.asList(Utils.generateRandomAddress(), Utils.generateRandomAddress());

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(
				new WalletName("wlt"),
				new WalletPassword("pwd"),
				sender,
				cosignatoryAddresses,
				new MultisigMinCosignatoriesModification(5),
				12,
				Amount.fromNem(123));

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("wlt")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("pwd")));
		Assert.assertThat(request.getSenderAddress(),IsEqual.equalTo(sender));
		Assert.assertThat(request.getCosignatoriesAddresses(), IsEquivalent.equivalentTo(cosignatoryAddresses));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(),IsEqual.equalTo(Amount.fromNem(123)));
	}

	//endregion

	//region serialization

	@Test
	public void requestCanBeDeserializedWithAllParameters() {
		// Arrange:
		final Deserializer deserializer = createDeserializer("w", "p", "a", "c", 5, 12, 123L);

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(deserializer);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getSenderAddress(),IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getCosignatoriesAddresses(), IsEquivalent.equivalentTo(Collections.singletonList(Address.fromEncoded("c"))));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(),IsEqual.equalTo(Amount.fromMicroNem(123)));
	}

	@Test
	public void requestCanBeDeserializedWithEmptyCosignatoryAddressList() {
		// Arrange:
		final Deserializer deserializer = createDeserializer("w", "p", "a", "", 5, 12, 123L);

		// Act:
		final MultisigModificationRequest request = new MultisigModificationRequest(deserializer);

		// Assert:
		Assert.assertThat(request.getWalletName(),IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getPassword(),IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getSenderAddress(),IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getCosignatoriesAddresses(), IsEquivalent.equivalentTo(Collections.emptyList()));
		Assert.assertThat(request.getMinCosignatoriesModification().getRelativeChange(), IsEqual.equalTo(5));
		Assert.assertThat(request.getHoursDue(),IsEqual.equalTo(12));
		Assert.assertThat(request.getFee(), IsEqual.equalTo(Amount.fromMicroNem(123)));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingRequiredParameters() {
		// Arrange:
		final List<Deserializer> deserializers = Arrays.asList(
				createDeserializer(null, "p", "a", "c", 5, 12, 123L),
				createDeserializer("w", null, "a", "c", 5, 12, 123L),
				createDeserializer("w", "p", null, "c", 5, 12, 123L),
				createDeserializer("w", "p", "a", null, 5, 12, 123L),
				createDeserializer("w", "p", "a", "c", null, 12, 123L),
				createDeserializer("w", "p", "a", "c", 5, null, 123L),
				createDeserializer("w", "p", "a", "c", 5, 12, null)
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
			final String cosignatory,
			final Integer relativeChange,
			final Integer hoursDue,
			final Long fee) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", walletName);
		jsonObject.put("password", walletPassword);
		jsonObject.put("account", sender);
		final JSONArray cosignatoryArray = null != cosignatory ? new JSONArray() : null;
		if (null != cosignatory && !cosignatory.isEmpty()) {
			final JSONObject address = new JSONObject();
			address.put("address", cosignatory);
			cosignatoryArray.add(address);
		}

		jsonObject.put("cosignatories", cosignatoryArray);
		final JSONObject minCosignatories = new JSONObject();
		minCosignatories.put("relativeChange", relativeChange);
		jsonObject.put("minCosignatories", minCosignatories);
		jsonObject.put("hoursDue", hoursDue);
		jsonObject.put("fee", fee);
		return new JsonDeserializer(jsonObject, null);
	}
}
