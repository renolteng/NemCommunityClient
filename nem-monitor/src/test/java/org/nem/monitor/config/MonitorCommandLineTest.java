package org.nem.monitor.config;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class MonitorCommandLineTest {

	@Test
	public void parseWithNonParsableArgsReturnsDefaultConfig() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-ncccConfig", "some_ncc_url", "nisConfig", "some_nis_url" });

		// Assert:
		Assert.assertThat(config.getNccConfig(), IsEqual.equalTo("ncc-config.properties"));
		Assert.assertThat(config.getNisConfig(), IsEqual.equalTo("nis-config.properties"));
	}

	@Test
	public void parseWithParsableArgsReturnsParsedConfig() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-nccConfig", "some_ncc_url", "-nisConfig", "some_nis_url" });

		// Assert:
		Assert.assertThat(config.getNccConfig(), IsEqual.equalTo("some_ncc_url"));
		Assert.assertThat(config.getNisConfig(), IsEqual.equalTo("some_nis_url"));
	}

	@Test
	public void parseWithParsableArgsReturnsParsedConfigWhenOnlyNccJnlpUrlIsSpecified() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-nccConfig", "some_ncc_url" });

		// Assert:
		Assert.assertThat(config.getNccConfig(), IsEqual.equalTo("some_ncc_url"));
		Assert.assertThat(config.getNisConfig(), IsEqual.equalTo("nis-config.properties"));
	}

	@Test
	public void parseWithParsableArgsReturnsParsedConfigWhenOnlyNisJnlpUrlIsSpecified() {
		// Act:
		final MonitorCommandLine config = MonitorCommandLine.parse(new String[] { "-nisConfig", "some_nis_url" });

		// Assert:
		Assert.assertThat(config.getNccConfig(), IsEqual.equalTo("ncc-config.properties"));
		Assert.assertThat(config.getNisConfig(), IsEqual.equalTo("some_nis_url"));
	}
}