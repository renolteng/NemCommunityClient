package org.nem.deploy;

import org.hamcrest.core.IsEqual;
import org.junit.*;

import java.util.Properties;
public class NccConfigurationTest {

	//region nis.webStart

	@Test
	public void canReadConfigurationWithWebStart() {
		// Arrange:
		final Properties properties = this.getCommonProperties();
		properties.setProperty("ncc.isWebStart", "true");

		// Act:
		final NccConfiguration config = new NccConfiguration(properties);

		// Assert:
		Assert.assertThat(config.isWebStart(), IsEqual.equalTo(true));
	}

	@Test
	public void canReadConfigurationWithoutWebStart() {
		// Arrange:
		final Properties properties = this.getCommonProperties();

		// Act:
		final NccConfiguration config = new NccConfiguration(properties);

		// Assert:
		Assert.assertThat(config.isWebStart(), IsEqual.equalTo(false));
	}

	//endregion

	//region ncc.nisJnlpUrl

	@Test
	public void canReadConfigurationWithNisJnlpUrl() {
		// Arrange:
		final Properties properties = this.getCommonProperties();
		properties.setProperty("ncc.nisJnlpUrl", "url");

		// Act:
		final NccConfiguration config = new NccConfiguration(properties);

		// Assert:
		Assert.assertThat(config.getNisJnlpUrl(), IsEqual.equalTo("url"));
	}

	@Test
	public void canReadConfigurationWithoutNisJnlpUrl() {
		// Arrange:
		final Properties properties = this.getCommonProperties();

		// Act:
		final NccConfiguration config = new NccConfiguration(properties);

		// Assert:
		Assert.assertThat(config.getNisJnlpUrl(), IsEqual.equalTo("http://bob.nem.ninja/webstart/nem-server.jnlp"));
	}

	//endregion

	//region loadConfig

	@Test
	public void loadConfigWithNonParsableArgsReturnsDefaultConfig() {
		// Act:
		NccConfiguration config = NccConfiguration.loadConfig(new String[] { "-issWebStart", "1", "-nisJnlpUrl", "someUrl" });

		// Assert:
		Assert.assertThat(config.isWebStart(), IsEqual.equalTo(false));
		Assert.assertThat(config.getNisJnlpUrl(), IsEqual.equalTo("http://bob.nem.ninja/webstart/nem-server.jnlp"));
	}

	@Test
	public void loadConfigWithParsableArgsReturnsParsedConfig() {
		// Act:
		NccConfiguration config = NccConfiguration.loadConfig(new String[] { "-isWebStart", "1", "-nisJnlpUrl", "someUrl" });

		// Assert:
		Assert.assertThat(config.isWebStart(), IsEqual.equalTo(true));
		Assert.assertThat(config.getNisJnlpUrl(), IsEqual.equalTo("someUrl"));
	}

	//endregion

	private Properties getCommonProperties() {
		final Properties properties = new Properties();
		properties.setProperty("nem.shortServerName", "Ncc");
		properties.setProperty("nem.folder", "folder");
		properties.setProperty("nem.maxThreads", "1");
		properties.setProperty("nem.protocol", "ftp");
		properties.setProperty("nem.host", "10.0.0.1");
		properties.setProperty("nem.httpPort", "100");
		properties.setProperty("nem.httpsPort", "101");
		properties.setProperty("nem.webContext", "/web");
		properties.setProperty("nem.apiContext", "/api");
		properties.setProperty("nem.homePath", "/home");
		properties.setProperty("nem.shutdownPath", "/shutdown");
		properties.setProperty("nem.useDosFilter", "true");
		return properties;
	}
}
