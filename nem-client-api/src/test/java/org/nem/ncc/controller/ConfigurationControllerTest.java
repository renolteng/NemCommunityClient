package org.nem.ncc.controller;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.ncc.controller.viewmodels.ConfigurationViewModel;
import org.nem.ncc.model.*;

public class ConfigurationControllerTest {

	@Test
	public void getConfigurationDelegatesToConfiguration() {
		// Arrange:
		final NisBootInfo bootInfo = new NisBootInfo(7, "1", "2", "3");
		final Configuration config = new Configuration("de-DE", bootInfo, "sp");
		final ConfigurationController controller = new ConfigurationController(config);

		// Act:
		final ConfigurationViewModel configViewModel = controller.getConfiguration();

		// Assert:
		Assert.assertThat(configViewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(configViewModel.getNisBootInfo(), IsEqual.equalTo(bootInfo));
	}

	@Test
	public void updateConfigurationDelegatesToConfiguration() {
		// Arrange:
		final NisBootInfo changedBootInfo = new NisBootInfo(7, "1", "2", "3");
		final Configuration config = new Configuration("de-DE", new NisBootInfo(7, "1", "2", "3"), "sp");
		final ConfigurationViewModel configViewModel = new ConfigurationViewModel("en-CA", changedBootInfo);
		final ConfigurationController controller = new ConfigurationController(config);

		// Act:
		controller.updateConfiguration(configViewModel);

		// Assert:
		Assert.assertThat(config.getLanguage(), IsEqual.equalTo("en-CA"));
		Assert.assertThat(config.getNisBootInfo(), IsEqual.equalTo(changedBootInfo));
	}
}