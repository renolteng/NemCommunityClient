package org.nem.ncc.controller;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.ncc.controller.viewmodels.ConfigurationViewModel;
import org.nem.ncc.model.*;

public class ConfigurationControllerTest {

	@Test
	public void getConfigurationDelegatesToConfiguration() {
		// Arrange:
		final NodeEndpoint remoteServer = NodeEndpoint.fromHost("10.10.10.12");
		final NisBootInfo bootInfo = new NisBootInfo(7, "1", "2");
		final Configuration config = new Configuration("de-DE", remoteServer, bootInfo, "sp");
		final ConfigurationController controller = new ConfigurationController(config);

		// Act:
		final ConfigurationViewModel configViewModel = controller.getConfiguration();

		// Assert:
		Assert.assertThat(configViewModel.getPatch().getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(configViewModel.getPatch().getNisEndpoint(), IsEqual.equalTo(remoteServer));
		Assert.assertThat(configViewModel.getPatch().getNisBootInfo(), IsEqual.equalTo(bootInfo));
	}

	@Test
	public void updateConfigurationDelegatesToConfiguration() {
		// Arrange:
		final NisBootInfo changedBootInfo = new NisBootInfo(7, "1", "2");
		final NodeEndpoint remoteServer = NodeEndpoint.fromHost("10.10.10.12");
		final NodeEndpoint changedRemoteServer = NodeEndpoint.fromHost("10.10.10.15");
		final Configuration config = new Configuration("de-DE", remoteServer, new NisBootInfo(8, "1", "2"), "sp");
		final ConfigurationPatch patch = new ConfigurationPatch();
		patch.setLanguage("en-CA");
		patch.setNisEndpoint(changedRemoteServer);
		patch.setNisBootInfo(changedBootInfo);
		final ConfigurationViewModel configViewModel = new ConfigurationViewModel(patch);
		final ConfigurationController controller = new ConfigurationController(config);

		// Act:
		controller.updateConfiguration(configViewModel);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("en-CA"));
		Assert.assertThat(config.getNisEndpoint(), IsEqual.equalTo(changedRemoteServer));
		Assert.assertThat(config.getNisBootInfo(), IsEqual.equalTo(changedBootInfo));
	}
}