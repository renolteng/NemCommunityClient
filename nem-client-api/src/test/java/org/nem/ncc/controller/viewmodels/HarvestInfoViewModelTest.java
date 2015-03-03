package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.model.primitive.*;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.time.*;

public class HarvestInfoViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Arrange:
		final HarvestInfo model = new HarvestInfo(123L, new BlockHeight(84), new TimeInstant(12), Amount.fromNem(7));

		// Act:
		final HarvestInfoViewModel viewModel = new HarvestInfoViewModel(model);

		// Assert:
		Assert.assertThat(viewModel.getId(), IsEqual.equalTo(123L));
		Assert.assertThat(viewModel.getBlockHeight(), IsEqual.equalTo(new BlockHeight(84)));
		Assert.assertThat(viewModel.getTimeStamp(), IsEqual.equalTo(new TimeInstant(12)));
		Assert.assertThat(viewModel.getTotalFee(), IsEqual.equalTo(Amount.fromNem(7)));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final HarvestInfo model = new HarvestInfo(123L, new BlockHeight(84), new TimeInstant(12), Amount.fromNem(7));

		final HarvestInfoViewModel viewModel = new HarvestInfoViewModel(model);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(5));
		Assert.assertThat(jsonObject.get("id"), IsEqual.equalTo(123L));
		Assert.assertThat(jsonObject.get("message"), IsEqual.equalTo("Block #84"));
		Assert.assertThat(jsonObject.get("timeStamp"), IsEqual.equalTo(SystemTimeProvider.getEpochTimeMillis() + 12000));
		Assert.assertThat(jsonObject.get("fee"), IsEqual.equalTo(7000000L));
		Assert.assertThat(jsonObject.get("height"), IsEqual.equalTo(84L));
	}
}