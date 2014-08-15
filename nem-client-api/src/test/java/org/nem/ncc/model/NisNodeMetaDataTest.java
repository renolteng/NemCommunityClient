package org.nem.ncc.model;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.ncc.test.Utils;

public class NisNodeMetaDataTest {

	@Test
	public void nisNodeMetaDataCanBeCreated() {
		// Act:
		final NisNodeMetaData nodeMetaData = new NisNodeMetaData(7, new BlockHeight(54), new BlockHeight(45));

		// Assert:
		Assert.assertThat(nodeMetaData.getActivePeers(), IsEqual.equalTo(7));
		Assert.assertThat(nodeMetaData.getMaxBlockHeight(), IsEqual.equalTo(new BlockHeight(54)));
		Assert.assertThat(nodeMetaData.getNodeBlockHeight(), IsEqual.equalTo(new BlockHeight(45)));
	}

	@Test
	public void nisNodeMetaDataCanBeRoundTripped() {
		// Arrange:
		final NisNodeMetaData originalNodeMetaData = new NisNodeMetaData(7, new BlockHeight(54), new BlockHeight(45));

		// Act:
		final NisNodeMetaData nodeMetaData = new NisNodeMetaData(Utils.roundtripSerializableEntity(originalNodeMetaData, null));

		// Assert:
		Assert.assertThat(nodeMetaData.getActivePeers(), IsEqual.equalTo(7));
		Assert.assertThat(nodeMetaData.getMaxBlockHeight(), IsEqual.equalTo(new BlockHeight(54)));
		Assert.assertThat(nodeMetaData.getNodeBlockHeight(), IsEqual.equalTo(new BlockHeight(45)));
	}
}
