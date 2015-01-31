package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.*;
import org.nem.ncc.controller.viewmodels.TransactionViewModel;
import org.nem.ncc.test.ExceptionAssert;
import org.nem.ncc.wallet.*;

import java.util.*;
import java.util.function.Consumer;

public class TransferSendRequestTest {

	// TODO 20150131 J-G: should check all new fields in create and deserialize tests
	@Test
	public void requestCanBeCreated() {
		// Act:
		final TransferSendRequest request = new TransferSendRequest(
				new WalletName("w"),
				Address.fromEncoded("m"),
				Address.fromEncoded("a"),
				Address.fromEncoded("r"),
				Amount.fromMicroNem(7),
				"m",
				true,
				5,
				new WalletPassword("p"),
				Amount.fromMicroNem(2),
				Amount.ZERO,
				TransactionViewModel.Type.Transfer.getValue());

		// Assert:
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getMultisigAddress(), IsEqual.equalTo(Address.fromEncoded("m")));
		Assert.assertThat(request.getSenderAddress(), IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getRecipientAddress(), IsEqual.equalTo(Address.fromEncoded("r")));
		Assert.assertThat(request.getAmount(), IsEqual.equalTo(Amount.fromMicroNem(7L)));
		Assert.assertThat(request.getMessage(), IsEqual.equalTo("m"));
		Assert.assertThat(request.shouldEncrypt(), IsEqual.equalTo(true));
		Assert.assertThat(request.getHoursDue(), IsEqual.equalTo(5));
		Assert.assertThat(request.getPassword(), IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getFee(), IsEqual.equalTo(Amount.fromMicroNem(2L)));
	}

	@Test
	public void requestCanBeDeserializedWithAllParameters() {
		// Act:
		final TransferSendRequest request = this.createRequestFromJson("w", "a", "r", 7L, "m", 3, 5, "p", 2L);

		// Assert:
		Assert.assertThat(request.getWalletName(), IsEqual.equalTo(new WalletName("w")));
		Assert.assertThat(request.getSenderAddress(), IsEqual.equalTo(Address.fromEncoded("a")));
		Assert.assertThat(request.getRecipientAddress(), IsEqual.equalTo(Address.fromEncoded("r")));
		Assert.assertThat(request.getAmount(), IsEqual.equalTo(Amount.fromMicroNem(7L)));
		Assert.assertThat(request.getMessage(), IsEqual.equalTo("m"));
		Assert.assertThat(request.shouldEncrypt(), IsEqual.equalTo(true));
		Assert.assertThat(request.getHoursDue(), IsEqual.equalTo(5));
		Assert.assertThat(request.getPassword(), IsEqual.equalTo(new WalletPassword("p")));
		Assert.assertThat(request.getFee(), IsEqual.equalTo(Amount.fromMicroNem(2L)));
	}

	@Test
	public void requestCanBeDeserializedWithoutMessage() {
		// Act:
		final TransferSendRequest request = this.createRequestFromJson("w", "a", "r", 7L, null, 0, 5, "p", 2L);

		// Assert:
		Assert.assertThat(request.getMessage(), IsNull.nullValue());
		Assert.assertThat(request.shouldEncrypt(), IsEqual.equalTo(false));
	}

	@Test
	public void requestCannotBeDeserializedWithMissingRequiredParameters() {
		// Arrange:
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> this.createRequestFromJson(null, "a", "r", 7L, "m", 3, 5, "p", 2L),
				v -> this.createRequestFromJson("w", null, "r", 7L, "m", 3, 5, "p", 2L),
				v -> this.createRequestFromJson("w", "a", null, 7L, "m", 3, 5, "p", 2L),
				v -> this.createRequestFromJson("w", "a", "r", null, "m", 3, 5, "p", 2L),
				v -> this.createRequestFromJson("w", "a", "r", 7L, "m", null, 5, "p", 2L),
				v -> this.createRequestFromJson("w", "a", "r", 7L, "m", 3, null, "p", 2L),
				v -> this.createRequestFromJson("w", "a", "r", 7L, "m", 3, 5, null, 2L),
				v -> this.createRequestFromJson("w", "a", "r", 7L, "m", 3, 5, "p", null));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(v -> action.accept(null), SerializationException.class);
		}
	}

	private TransferSendRequest createRequestFromJson(
			final String walletName,
			final String accountId,
			final String recipientId,
			final Long amount,
			final String message,
			final Integer shouldEncrypt,
			final Integer hoursDue,
			final String password,
			final Long fee) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("wallet", walletName);
		jsonObject.put("account", accountId);
		jsonObject.put("recipient", recipientId);
		jsonObject.put("amount", amount);
		jsonObject.put("message", message);
		jsonObject.put("encrypt", shouldEncrypt);
		jsonObject.put("hoursDue", hoursDue);
		jsonObject.put("password", password);
		jsonObject.put("fee", fee);
		jsonObject.put("multisigFee", fee);
		jsonObject.put("type", TransactionViewModel.Type.Transfer.getValue());
		return new TransferSendRequest(new JsonDeserializer(jsonObject, null));
	}
}