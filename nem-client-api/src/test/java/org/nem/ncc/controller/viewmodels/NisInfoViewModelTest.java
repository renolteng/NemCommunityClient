package org.nem.ncc.controller.viewmodels;

import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.node.NisNodeInfo;
import org.nem.core.time.*;
import org.nem.ncc.model.NisNodeMetaData;
import org.nem.ncc.test.*;

public class NisInfoViewModelTest {

	// region constructor

	@Test
	public void viewModelCanBeCreated() {
		// Arrange
		final NisNodeInfo nodeInfo = new NisNodeInfo(GraphUtils.createNode(), createAppMetaData());
		final NisNodeMetaData nodeMetaData = new NisNodeMetaData(9, new BlockHeight(10), new BlockHeight(5));

		// Act
		final NisInfoViewModel viewModel = new NisInfoViewModel(nodeInfo, nodeMetaData);

		// Assert
		Assert.assertThat(viewModel.getNodeInfo(), IsSame.sameInstance(nodeInfo));
		Assert.assertThat(viewModel.getNodeMetaData(), IsSame.sameInstance(nodeMetaData));
	}

	// endregion

	// region serialization

	@Test
	public void viewModelCanBeRoundTripped() {
		// Arrange
		final NisNodeInfo nodeInfo = new NisNodeInfo(GraphUtils.createNode(), createAppMetaData());
		final NisNodeMetaData nodeMetaData = new NisNodeMetaData(9, new BlockHeight(10), new BlockHeight(5));
		final NisInfoViewModel originalViewModel = new NisInfoViewModel(nodeInfo, nodeMetaData);

		// Act
		final NisInfoViewModel viewModel = new NisInfoViewModel(Utils.roundtripSerializableEntity(originalViewModel, null));

		// Assert:
		Assert.assertThat(viewModel.getNodeInfo().getNode(), IsEqual.equalTo(nodeInfo.getNode()));
		Assert.assertThat(viewModel.getNodeInfo().getAppMetaData().getVersion(), IsEqual.equalTo(nodeInfo.getAppMetaData().getVersion()));
		Assert.assertThat(viewModel.getNodeMetaData().getActivePeers(), IsEqual.equalTo(9));
	}

	// endregion

	private static ApplicationMetaData createAppMetaData() {
		final TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
		Mockito.when(timeProvider.getCurrentTime()).thenReturn(new TimeInstant(17));
		return new ApplicationMetaData("NIS", "1.0.0", null, timeProvider);
	}
}
