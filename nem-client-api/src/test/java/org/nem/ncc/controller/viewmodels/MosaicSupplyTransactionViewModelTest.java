package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.Hash;
import org.nem.core.model.*;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.ncc.*;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.time.*;
import org.nem.ncc.test.*;

public class MosaicSupplyTransactionViewModelTest {
	private final static String MOSAIC_NAME = "paddle";
	private final static NamespaceId MOSAIC_NS = new NamespaceId("nem.games.pong");

	//region construction
	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateViewModelAroundNonMosaicCreation() {
		// Arrange:
		final Transaction transaction = new MockTransaction();

		// Act:
		map(transaction, Address.fromEncoded("foo"));
	}

	@Test
	public void canCreateViewModelAroundUnconfirmedMosaicSupply() {
		assertCreateViewModelAroundUnconfirmedMosaicSupply(SmartTileSupplyType.CreateSmartTiles, Quantity.fromValue(100));
		assertCreateViewModelAroundUnconfirmedMosaicSupply(SmartTileSupplyType.CreateSmartTiles, Quantity.fromValue(1000));
		assertCreateViewModelAroundUnconfirmedMosaicSupply(SmartTileSupplyType.DeleteSmartTiles, Quantity.fromValue(100));
		assertCreateViewModelAroundUnconfirmedMosaicSupply(SmartTileSupplyType.DeleteSmartTiles, Quantity.fromValue(1000));
	}

	private static void assertCreateViewModelAroundUnconfirmedMosaicSupply(final SmartTileSupplyType createSmartTiles, final Quantity quantity) {
		final Context context = new Context(MOSAIC_NAME, MOSAIC_NS, createSmartTiles, quantity);

		// Act:
		final MosaicSupplyTransactionViewModel viewModel = context.map(Address.fromEncoded("foo"));

		// Assert:
		context.assertViewModel(viewModel);
	}

	@Test
	public void canCreateViewModelAroundConfirmedMosaicSupply() {
		assertCreateViewModelAroundConfirmedMosaicSupply(SmartTileSupplyType.CreateSmartTiles, Quantity.fromValue(100));
		assertCreateViewModelAroundConfirmedMosaicSupply(SmartTileSupplyType.CreateSmartTiles, Quantity.fromValue(1000));
		assertCreateViewModelAroundConfirmedMosaicSupply(SmartTileSupplyType.DeleteSmartTiles, Quantity.fromValue(100));
		assertCreateViewModelAroundConfirmedMosaicSupply(SmartTileSupplyType.DeleteSmartTiles, Quantity.fromValue(1000));
	}

	private static void assertCreateViewModelAroundConfirmedMosaicSupply(final SmartTileSupplyType createSmartTiles, final Quantity quantity) {
		final Context context = new Context(MOSAIC_NAME, MOSAIC_NS, createSmartTiles, quantity);

		// Act:
		final MosaicSupplyTransactionViewModel viewModel = context.map(
				Address.fromEncoded("foo"),
				new BlockHeight(654)
		);

		// Assert:
		context.assertViewModel(viewModel);

		Assert.assertThat(viewModel.getId(), IsEqual.equalTo(44L));
		Assert.assertThat(viewModel.isConfirmed(), IsEqual.equalTo(true));
		Assert.assertThat(viewModel.getConfirmations(), IsEqual.equalTo(654L - 10L + 1));
		Assert.assertThat(viewModel.getBlockHeight(), IsEqual.equalTo(10L));
	}
	//endregion

	//region serialization
	@Test
	public void assertSerializeViewModelAroundUnconfirmedTransfer() {
		assertSerializeViewModelAroundUnconfirmedTransfer(SmartTileSupplyType.CreateSmartTiles, "CreateSmartTiles");
		assertSerializeViewModelAroundUnconfirmedTransfer(SmartTileSupplyType.DeleteSmartTiles, "DeleteSmartTiles");
	}

	private static void assertSerializeViewModelAroundUnconfirmedTransfer(final SmartTileSupplyType smartTileSupplyType, final String expectedSmartTileType) {
		// Arrange:
		final Context context = new Context(MOSAIC_NAME, MOSAIC_NS, smartTileSupplyType, new Quantity(100));
		context.transaction.setDeadline(new TimeInstant(333));
		context.recalculateHash();

		// Act:
		final MosaicSupplyTransactionViewModel viewModel = context.map(context.sender.getAddress());
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(14));
		Assert.assertThat(jsonObject.get("type"), IsEqual.equalTo(TransactionViewModel.Type.Mosaic_Supply.getValue()));
		Assert.assertThat(jsonObject.get("hash"), IsEqual.equalTo(context.transactionHash.toString()));
		Assert.assertThat(jsonObject.get("sender"), IsEqual.equalTo(context.sender.getAddress().toString()));
		Assert.assertThat(jsonObject.get("timeStamp"), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 125 * 1000));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(34000000L));

		Assert.assertThat(jsonObject.get("mosaicName"), IsEqual.equalTo(MOSAIC_NAME));
		Assert.assertThat(jsonObject.get("namespaceName"), IsEqual.equalTo(MOSAIC_NS.toString()));
		Assert.assertThat(jsonObject.get("supplyType"), IsEqual.equalTo(expectedSmartTileType));
		Assert.assertThat(jsonObject.get("supplyQuantity"), IsEqual.equalTo(100L));

		Assert.assertThat(jsonObject.get("id"), IsEqual.equalTo(context.transactionHash.getShortId()));
		Assert.assertThat(jsonObject.get("confirmed"), IsEqual.equalTo(0));
		Assert.assertThat(jsonObject.get("confirmations"), IsEqual.equalTo(0L));
		Assert.assertThat(jsonObject.get("blockHeight"), IsEqual.equalTo(0L));
		Assert.assertThat(jsonObject.get("deadline"), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 333 * 1000));
	}

	@Test
	public void assertSerializeViewModelAroundConfirmedTransfer() {
		assertSerializeViewModelAroundConfirmedTransfer(SmartTileSupplyType.CreateSmartTiles, "CreateSmartTiles");
		assertSerializeViewModelAroundConfirmedTransfer(SmartTileSupplyType.DeleteSmartTiles, "DeleteSmartTiles");
	}

	private static void assertSerializeViewModelAroundConfirmedTransfer(final SmartTileSupplyType smartTileSupplyType, final String expectedSmartTileType) {
		// Arrange:
		final Context context = new Context(MOSAIC_NAME, MOSAIC_NS, smartTileSupplyType, new Quantity(100));
		context.transaction.setDeadline(new TimeInstant(333));
		context.recalculateHash();

		// Act:
		final MosaicSupplyTransactionViewModel viewModel = context.map(
				context.sender.getAddress(),
				new BlockHeight(654)
		);
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(14));
		Assert.assertThat(jsonObject.get("type"), IsEqual.equalTo(TransactionViewModel.Type.Mosaic_Supply.getValue()));
		Assert.assertThat(jsonObject.get("hash"), IsEqual.equalTo(context.transactionHash.toString()));
		Assert.assertThat(jsonObject.get("sender"), IsEqual.equalTo(context.sender.getAddress().toString()));
		Assert.assertThat(jsonObject.get("timeStamp"), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 125 * 1000));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(34000000L));

		Assert.assertThat(jsonObject.get("mosaicName"), IsEqual.equalTo(MOSAIC_NAME));
		Assert.assertThat(jsonObject.get("namespaceName"), IsEqual.equalTo(MOSAIC_NS.toString()));
		Assert.assertThat(jsonObject.get("supplyType"), IsEqual.equalTo(expectedSmartTileType));
		Assert.assertThat(jsonObject.get("supplyQuantity"), IsEqual.equalTo(100L));

		Assert.assertThat(jsonObject.get("id"), IsEqual.equalTo(44L));
		Assert.assertThat(jsonObject.get("confirmed"), IsEqual.equalTo(1));
		Assert.assertThat(jsonObject.get("confirmations"), IsEqual.equalTo(654L - 10L + 1));
		Assert.assertThat(jsonObject.get("blockHeight"), IsEqual.equalTo(10L));
		Assert.assertThat(jsonObject.get("deadline"), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 333 * 1000));
	}
	//endregion

	private static MosaicSupplyTransactionViewModel map(final Transaction transaction, final Address address) {
		return (MosaicSupplyTransactionViewModel)TransactionToViewModelMapper.map(transaction, address);
	}

	private static class Context {
		final Account sender = Utils.generateRandomAccount();
		final Transaction transaction;
		private final String mosaicName;
		private final SmartTileSupplyType smartTileSupplyType;
		private final Quantity quantity;
		private final String namespaceName;
		Hash transactionHash;

		Context(final String mosaicName, final NamespaceId parent, final SmartTileSupplyType smartTileSupplyType, final Quantity quantity) {
			this.namespaceName = parent.toString();
			this.mosaicName = mosaicName;
			this.smartTileSupplyType = smartTileSupplyType;
			this.quantity = quantity;
			this.transaction = new SmartTileSupplyChangeTransaction(
					new TimeInstant(125),
					this.sender,
					new MosaicId(parent, mosaicName),
					smartTileSupplyType,
					quantity
			);
			this.transaction.setFee(Amount.fromNem(34));
			this.recalculateHash();
		}

		public void recalculateHash() {
			this.transactionHash = HashUtils.calculateHash(this.transaction);
		}

		public MosaicSupplyTransactionViewModel map(final Address address) {
			return (MosaicSupplyTransactionViewModel)TransactionToViewModelMapper.map(this.transaction, address);
		}

		public void assertViewModel(final MosaicSupplyTransactionViewModel viewModel) {
			Assert.assertThat(viewModel.getHash(), IsEqual.equalTo(this.transactionHash));
			Assert.assertThat(viewModel.getSigner(), IsEqual.equalTo(this.sender.getAddress()));
			Assert.assertThat(viewModel.getTimeStamp(), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 125 * 1000));
			Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromNem(34)));
			Assert.assertThat(viewModel.getNamespaceName(), IsEqual.equalTo(this.namespaceName));
			Assert.assertThat(viewModel.getMosaicName(), IsEqual.equalTo(this.mosaicName));
			Assert.assertThat(viewModel.getSupplyType(), IsEqual.equalTo(this.smartTileSupplyType));
			Assert.assertThat(viewModel.getSupplyQuantity(), IsEqual.equalTo(this.quantity));
		}

		private static TransactionMetaData createMetaData(final int height, final long id) {
			return new TransactionMetaData(new BlockHeight(height), id, Hash.ZERO);
		}

		public MosaicSupplyTransactionViewModel map(final Address address, final BlockHeight blockHeight) {
			return (MosaicSupplyTransactionViewModel)TransactionToViewModelMapper.map(
					new TransactionMetaDataPair(this.transaction, createMetaData(10, 44L)),
					address,
					blockHeight);
		}
	}
}