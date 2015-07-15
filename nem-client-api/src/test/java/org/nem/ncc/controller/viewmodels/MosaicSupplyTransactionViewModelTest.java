package org.nem.ncc.controller.viewmodels;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.crypto.Hash;
import org.nem.core.model.*;
import org.nem.core.model.mosaic.*;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeInstant;
import org.nem.ncc.test.MockTransaction;
import org.nem.ncc.test.Utils;

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

	private void assertCreateViewModelAroundUnconfirmedMosaicSupply(final SmartTileSupplyType createSmartTiles, final Quantity quantity) {
		final Context context = new Context(MOSAIC_NAME, MOSAIC_NS, createSmartTiles, quantity);

		// Act:
		final MosaicSupplyTransactionViewModel viewModel = context.map(Address.fromEncoded("foo"));

		// Assert:
		context.assertViewModel(viewModel);
	}
	//endregion

	private static MosaicSupplyTransactionViewModel map(final Transaction transaction, final Address address) {
		return (MosaicSupplyTransactionViewModel)TransactionToViewModelMapper.map(transaction, address);
	}

	private static class Context
	{
		final Account sender = Utils.generateRandomAccount();
		final Transaction transaction;
		private final String mosaicName;
		private final SmartTileSupplyType smartTileSupplyType;
		private final Quantity quantity;
		private final String namespaceName;
		Hash transactionHash;

		Context(final String mosaicName, final NamespaceId parent, final SmartTileSupplyType smartTileSupplyType, final Quantity quantity)
		{
			this.namespaceName = parent.toString();
			this.mosaicName = mosaicName;
			this.smartTileSupplyType = smartTileSupplyType;
			this.quantity = quantity;
			this.transaction = new SmartTileSupplyChangeTransaction(
					new TimeInstant(125),
					sender,
					new MosaicId(parent, mosaicName),
					smartTileSupplyType,
					quantity
			);
			transaction.setFee(Amount.fromNem(23));
			this.recalculateHash();
		}

		public void recalculateHash() {
			this.transactionHash = HashUtils.calculateHash(transaction);
		}

		public MosaicSupplyTransactionViewModel map(final Address address) {
			return (MosaicSupplyTransactionViewModel)TransactionToViewModelMapper.map(this.transaction, address);
		}

		public void assertViewModel(final MosaicSupplyTransactionViewModel viewModel) {
			Assert.assertThat(viewModel.getHash(), IsEqual.equalTo(this.transactionHash));
			Assert.assertThat(viewModel.getSigner(), IsEqual.equalTo(sender.getAddress()));
			Assert.assertThat(viewModel.getTimeStamp(), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 125 * 1000));
			Assert.assertThat(viewModel.getFee(), IsEqual.equalTo(Amount.fromNem(23)));
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
					new TransactionMetaDataPair(this.transaction, createMetaData(9, 33L)),
					address,
					blockHeight);
		}
	}
}