package org.nem.ncc.services;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.core.crypto.Hash;
import org.nem.core.model.*;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.*;
import org.nem.core.time.TimeInstant;
import org.nem.ncc.connector.PrimaryNisConnector;
import org.nem.ncc.model.NisApiId;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.stream.Collectors;

public class AccountServicesTest {

	@Test
	public void getAccountMetaDataPairDelegatesToConnector() {
		// Arrange:
		final TestContext context = new TestContext();
		final AccountMetaDataPair originalPair = new AccountMetaDataPair(
				Utils.createAccountInfoFromAddress(Address.fromEncoded("FOO")),
				new AccountMetaData(AccountStatus.UNLOCKED));

		Mockito.when(context.connector.get(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=FOO"))
				.thenReturn(serialize(originalPair));

		// Act:
		final AccountMetaDataPair pair = context.services.getAccountMetaDataPair(Address.fromEncoded("FOO"));

		// Assert:
		Mockito.verify(context.connector, Mockito.times(1)).get(NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=FOO");
		Assert.assertThat(pair.getAccount().getAddress(), IsEqual.equalTo(Address.fromEncoded("FOO")));
		Assert.assertThat(pair.getMetaData().getStatus(), IsEqual.equalTo(AccountStatus.UNLOCKED));
	}

	//region NEW account/transactions API

	private static void assertGetTransactionsWithHashDelegatesToConnector(
			final NisApiId nisApiId,
			final TransactionDirection direction,
			final Hash hash,
			final String expectedQueryString) {
		// Arrange:
		final TestContext context = new TestContext();
		final List<TransactionMetaDataPair> originalPairs = Arrays.asList(
				createTransferMetaDataPair(Amount.fromNem(124), 5),
				createTransferMetaDataPair(Amount.fromNem(572), 9),
				createTransferMetaDataPair(Amount.fromNem(323), 4));

		Mockito.when(context.connector.get(Matchers.eq(nisApiId), Matchers.anyString()))
				.thenReturn(serialize(new SerializableList<>(originalPairs)));

		// Act:
		final List<TransactionMetaDataPair> pairs = context.services.getTransactions(direction, Address.fromEncoded("FOO"), hash);

		// Assert:
		Mockito.verify(context.connector, Mockito.times(1)).get(nisApiId, expectedQueryString);
		Assert.assertThat(
				pairs.stream().map(p -> p.getTransaction().getFee()).collect(Collectors.toList()),
				IsEqual.equalTo(Arrays.asList(Amount.fromNem(124), Amount.fromNem(572), Amount.fromNem(323))));
		Assert.assertThat(
				pairs.stream().map(p -> p.getMetaData().getHeight()).collect(Collectors.toList()),
				IsEqual.equalTo(Arrays.asList(new BlockHeight(5), new BlockHeight(9), new BlockHeight(4))));
	}

	@Test
	public void transactionsAllDelegateToConnectorWithoutHash() {
		assertGetTransactionsWithHashDelegatesToConnector(
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
				TransactionDirection.ALL,
				null,
				"address=FOO"
		);
	}

	@Test
	public void transactionsAllDelegateToConnectorWithHash() {
		assertGetTransactionsWithHashDelegatesToConnector(
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
				TransactionDirection.ALL,
				Hash.fromHexString("1234"),
				"address=FOO&hash=1234"
		);
	}

	@Test
	public void transactionsIncomingDelegateToConnectorWithoutHash() {
		assertGetTransactionsWithHashDelegatesToConnector(
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
				TransactionDirection.INCOMING,
				null,
				"address=FOO"
		);
	}

	@Test
	public void transactionsIncomingDelegateToConnectorWithHash() {
		assertGetTransactionsWithHashDelegatesToConnector(
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
				TransactionDirection.INCOMING,
				Hash.fromHexString("1234"),
				"address=FOO&hash=1234"
		);
	}

	@Test
	public void transactionsOutgoingDelegateToConnectorWithoutHash() {
		assertGetTransactionsWithHashDelegatesToConnector(
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
				TransactionDirection.OUTGOING,
				null,
				"address=FOO"
		);
	}

	@Test
	public void transactionsOutgoingDelegateToConnectorWithHash() {
		assertGetTransactionsWithHashDelegatesToConnector(
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
				TransactionDirection.OUTGOING,
				Hash.fromHexString("1234"),
				"address=FOO&hash=1234"
		);
	}

	//endregion

	@Test
	public void getUnconfirmedTransactionsDelegatesToConnector() {
		// Arrange:
		final TestContext context = new TestContext();
		final List<Transaction> originalTransactions = Arrays.asList(
				createTransfer(Amount.fromNem(124)),
				createTransfer(Amount.fromNem(572)),
				createTransfer(Amount.fromNem(323)));

		Mockito.when(context.connector.get(NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=FOO"))
				.thenReturn(serialize(new SerializableList<>(originalTransactions)));

		// Act:
		final List<Transaction> transactions = context.services.getUnconfirmedTransactions(Address.fromEncoded("FOO"));

		// Assert:
		Mockito.verify(context.connector, Mockito.times(1)).get(NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=FOO");
		Assert.assertThat(
				transactions.stream().map(Transaction::getFee).collect(Collectors.toList()),
				IsEqual.equalTo(Arrays.asList(Amount.fromNem(124), Amount.fromNem(572), Amount.fromNem(323))));
	}

	private static Deserializer serialize(final SerializableEntity entity) {
		return new JsonDeserializer(
				JsonSerializer.serializeToJson(entity),
				new DeserializationContext(new MockAccountLookup()));
	}

	private static TransactionMetaDataPair createTransferMetaDataPair(final Amount fee, final int blockHeight) {
		return new TransactionMetaDataPair(
				createTransfer(fee),
				new TransactionMetaData(new BlockHeight(blockHeight)));
	}

	private static Transaction createTransfer(final Amount fee) {
		final Transaction transaction = new TransferTransaction(
				new TimeInstant(125),
				Utils.generateRandomAccount(),
				Utils.generateRandomAccount(),
				Amount.fromNem(75),
				null);
		transaction.setFee(fee);
		transaction.sign();
		return transaction;
	}

	private static class TestContext {
		private final PrimaryNisConnector connector = Mockito.mock(PrimaryNisConnector.class);

		private final AccountServices services = new AccountServices(this.connector);
	}
}