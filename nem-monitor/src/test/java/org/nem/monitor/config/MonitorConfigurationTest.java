package org.nem.monitor.config;

import org.hamcrest.core.IsEqual;
import org.junit.*;

import java.util.Properties;

public class MonitorConfigurationTest {

	@Test
	public void canReadConfigurationWithCustomFolderNotContainingHomeFolder() {
		// Arrange:
		final Properties properties = new Properties();
		properties.put("nem.folder", "blah");

		// Act:
		final MonitorConfiguration config = new MonitorConfiguration(properties);

		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo("blah"));
	}

	@Test
	public void canReadConfigurationWithCustomFolderContainingHomeFolder() {
		// Arrange:
		final Properties properties = new Properties();
		properties.put("nem.folder", "%h/blah");

		// Act:
		final MonitorConfiguration config = new MonitorConfiguration(properties);

		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo(System.getProperty("user.home") + "/blah"));
	}

	@Test
	public void canReadConfigurationWithoutCustomFolder() {
		// Arrange:
		final Properties properties = new Properties();

		// Act:
		final MonitorConfiguration config = new MonitorConfiguration(properties);

		Assert.assertThat(config.getNemFolder(), IsEqual.equalTo(System.getProperty("user.home")));
	}
}