package org.nem.console.utils;

import org.apache.commons.cli.Options;
import org.hamcrest.core.IsEqual;
import org.junit.*;

public class OptionsUtilsTest {

	@Test
	public void addWriteOptionsAddsDefaultWriteOptions() {
		// Arrange:
		final Options options = new Options();

		// Act:
		OptionsUtils.addWriteOptions(options);

		// Assert:
		Assert.assertThat(options.getOptions().size(), IsEqual.equalTo(3));
		Assert.assertThat(options.hasOption("output"), IsEqual.equalTo(true));
		Assert.assertThat(options.hasOption("pass"), IsEqual.equalTo(true));
		Assert.assertThat(options.hasOption("numHashes"), IsEqual.equalTo(true));
	}

	@Test
	public void addReadOptionsAddsDefaultWriteOptions() {
		// Arrange:
		final Options options = new Options();

		// Act:
		OptionsUtils.addReadOptions(options);

		// Assert:
		Assert.assertThat(options.getOptions().size(), IsEqual.equalTo(3));
		Assert.assertThat(options.hasOption("input"), IsEqual.equalTo(true));
		Assert.assertThat(options.hasOption("pass"), IsEqual.equalTo(true));
		Assert.assertThat(options.hasOption("numHashes"), IsEqual.equalTo(true));
	}
}