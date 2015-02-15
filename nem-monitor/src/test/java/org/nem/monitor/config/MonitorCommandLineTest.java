package org.nem.monitor.config;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class MonitorCommandLineTest {

	@Test
	public void parseWithNonParsableArgsReturnsDefaultConfig() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-ncccJnlpUrl", "some_ncc_url", "-nisUri", "some_nis_url" });

		// Assert:
		Assert.assertThat(config.getNccUri(), IsEqual.equalTo("http://bob.nem.ninja/webstart/nem-client.jnlp"));
		Assert.assertThat(config.getNisUri(), IsEqual.equalTo("http://bob.nem.ninja/webstart/nem-server.jnlp"));
	}

	@Test
	public void parseWithParsableArgsReturnsParsedConfig() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-nccUri", "some_ncc_url", "-nisUri", "some_nis_url" });

		// Assert:
		Assert.assertThat(config.getNccUri(), IsEqual.equalTo("some_ncc_url"));
		Assert.assertThat(config.getNisUri(), IsEqual.equalTo("some_nis_url"));
	}

	@Test
	public void parseWithParsableArgsReturnsParsedConfigWhenOnlyNccJnlpUrlIsSpecified() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-nccUri", "some_ncc_url" });

		// Assert:
		Assert.assertThat(config.getNccUri(), IsEqual.equalTo("some_ncc_url"));
		Assert.assertThat(config.getNisUri(), IsEqual.equalTo("http://bob.nem.ninja/webstart/nem-server.jnlp"));
	}

	@Test
	public void parseWithParsableArgsReturnsParsedConfigWhenOnlyNisJnlpUrlIsSpecified() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-nisUri", "some_nis_url" });

		// Assert:
		Assert.assertThat(config.getNccUri(), IsEqual.equalTo("http://bob.nem.ninja/webstart/nem-client.jnlp"));
		Assert.assertThat(config.getNisUri(), IsEqual.equalTo("some_nis_url"));
	}
}