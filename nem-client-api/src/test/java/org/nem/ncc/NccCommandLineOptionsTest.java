package org.nem.ncc;

import org.hamcrest.core.IsEqual;
import org.junit.*;

import java.nio.file.Paths;

public class NccCommandLineOptionsTest {
	private final String DEFAULT_JNLP_PATH = "http://bob.nem.ninja/webstart/nem-server.jnlp";
	private final String DEFAULT_NEM_FOLDER = Paths.get(System.getProperty("user.home"), "ncc").toString();

	@Test
	public void optionsExposeCorrectDefaults() {
		// Act:
		final NccCommandLineOptions options = NccCommandLineOptions.parse(new String[] { });

		// Assert:
		Assert.assertThat(options.getNisJnlpUrl(), IsEqual.equalTo(this.DEFAULT_JNLP_PATH));
		Assert.assertThat(options.getNemFolder(), IsEqual.equalTo(this.DEFAULT_NEM_FOLDER));
		Assert.assertThat(options.isNisWebStart(), IsEqual.equalTo(true));
	}

	@Test
	public void defaultOptionsAreUsedOnParseError() {
		// Act:
		final NccCommandLineOptions options = NccCommandLineOptions.parse(new String[] { "-nis_jnlp" });

		// Assert:
		Assert.assertThat(options.getNisJnlpUrl(), IsEqual.equalTo(this.DEFAULT_JNLP_PATH));
		Assert.assertThat(options.getNemFolder(), IsEqual.equalTo(this.DEFAULT_NEM_FOLDER));
		Assert.assertThat(options.isNisWebStart(), IsEqual.equalTo(true));
	}

	@Test
	public void nisJnlpUrlOptionCanBeOverridden() {
		// Act:
		final NccCommandLineOptions options = NccCommandLineOptions.parse(new String[] { "-nis_jnlp", "jnlp-url" });

		// Assert:
		Assert.assertThat(options.getNisJnlpUrl(), IsEqual.equalTo("jnlp-url"));
		Assert.assertThat(options.getNemFolder(), IsEqual.equalTo(this.DEFAULT_NEM_FOLDER));
		Assert.assertThat(options.isNisWebStart(), IsEqual.equalTo(true));
	}

	@Test
	public void nemFolderOptionCanBeOverridden() {
		// Act:
		final NccCommandLineOptions options = NccCommandLineOptions.parse(new String[] { "-nem_folder", "storage-path" });

		// Assert:
		Assert.assertThat(options.getNisJnlpUrl(), IsEqual.equalTo(this.DEFAULT_JNLP_PATH));
		Assert.assertThat(options.getNemFolder(), IsEqual.equalTo("storage-path"));
		Assert.assertThat(options.isNisWebStart(), IsEqual.equalTo(true));
	}

	@Test
	public void nisWebStartOptionCanBeOverridden() {
		// Act:
		final NccCommandLineOptions options = NccCommandLineOptions.parse(new String[] { "-nis_ws", "0" });

		// Assert:
		Assert.assertThat(options.getNisJnlpUrl(), IsEqual.equalTo(this.DEFAULT_JNLP_PATH));
		Assert.assertThat(options.getNemFolder(), IsEqual.equalTo(this.DEFAULT_NEM_FOLDER));
		Assert.assertThat(options.isNisWebStart(), IsEqual.equalTo(false));
	}
}