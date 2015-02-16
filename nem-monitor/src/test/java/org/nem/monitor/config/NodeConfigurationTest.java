package org.nem.monitor.config;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class NodeConfigurationTest {

	@Test
	public void canCreateConfig() {
		// Act:
		final NodeConfiguration config = new NodeConfiguration(
				"local-uri",
				"-XDebug",
				"remote.jnlp",
				true,
				false);

		// Assert:
		Assert.assertThat(config.getUri(), IsEqual.equalTo("local-uri"));
		Assert.assertThat(config.getVmOptions(), IsEqual.equalTo("-XDebug"));
		Assert.assertThat(config.getJnlpUrl(), IsEqual.equalTo("remote.jnlp"));
		Assert.assertThat(config.isMonitored(), IsEqual.equalTo(true));
		Assert.assertThat(config.shouldAutoBoot(), IsEqual.equalTo(false));
	}
}