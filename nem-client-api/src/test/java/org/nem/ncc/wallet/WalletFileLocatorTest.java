package org.nem.ncc.wallet;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.nem.ncc.test.IsEquivalent;
import org.nem.ncc.wallet.storage.WalletDescriptor;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class WalletFileLocatorTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_FILE_SUB_DIRECTORY = new File(TEST_FILE_DIRECTORY, "sub");
	private static final File TEST_FILE_MC_DIRECTORY = new File(TEST_FILE_DIRECTORY, "mixed_case");
	private static final File TEST_FILE_NN_DIRECTORY = new File(TEST_FILE_DIRECTORY, "no_name");
	private static final File TEST_FILE_ME_DIRECTORY = new File(TEST_FILE_DIRECTORY, "multi_ext");

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		final File[] directories = new File[] {
				TEST_FILE_DIRECTORY,
				TEST_FILE_SUB_DIRECTORY,
				TEST_FILE_MC_DIRECTORY,
				TEST_FILE_NN_DIRECTORY,
				TEST_FILE_ME_DIRECTORY
		};

		boolean result = true;
		for (final File directory : directories) {
			result = result && directory.mkdir();
		}

		for (final String fileName : new String[] { "alpha.wlt", "sigma.wlt", "beta.wltx", "zeta.wlt" }) {
			result = result && new File(TEST_FILE_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] { "sub.wlt", "xyz.wltx", "sigma.wlt.tmp", "sigma.wlt" }) {
			result = result && new File(TEST_FILE_SUB_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] { "upper.WLT", "lower.wlt", "mixed.WlT" }) {
			result = result && new File(TEST_FILE_MC_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] { "name.wlt", ".wlt", "n.wlt" }) {
			result = result && new File(TEST_FILE_NN_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] { "tw.tmp.wlt", "wt.wlt.tmp" }) {
			result = result && new File(TEST_FILE_ME_DIRECTORY, fileName).createNewFile();
		}

		if (!result) {
			throw new RuntimeException("unable to initialize test suite");
		}
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	//endregion

	@Test
	public void filesWithMatchingExtensionInSearchDirectoryAreDetected() {
		// Assert:
		assertLocatedWalletNames(TEST_FILE_SUB_DIRECTORY, new String[] { "sub", "sigma" });
	}

	@Test
	public void searchDirectoryIsNotSearchedRecursively() {
		// Assert: no wallet names from any subdirectory are surfaced
		assertLocatedWalletNames(TEST_FILE_DIRECTORY, new String[] { "alpha", "sigma", "zeta" });
	}

	@Test
	public void filesWithMatchingExtensionOfAnyCasingInSearchDirectoryAreDetected() {
		// Assert:
		assertLocatedWalletNames(TEST_FILE_MC_DIRECTORY, new String[] { "upper", "lower", "mixed" });
	}

	@Test
	public void namelessWalletFilesAreNotDetected() {
		// Assert:
		assertLocatedWalletNames(TEST_FILE_NN_DIRECTORY, new String[] { "name", "n" });
	}

	@Test
	public void walletFilesWithMultipleExtensionsAreDetected() {
		// Assert:
		assertLocatedWalletNames(TEST_FILE_ME_DIRECTORY, new String[] { "tw.tmp" });
	}

	private static void assertLocatedWalletNames(final File directory, final String[] expectedNames) {
		// Arrange:
		final WalletFileLocator locator = new WalletFileLocator(directory);

		// Act:
		final List<WalletDescriptor> descriptors = locator.getAllWallets();

		// Assert:
		Assert.assertThat(
				descriptors.stream().map(d -> d.getWalletName().toString()).collect(Collectors.toList()),
				IsEquivalent.equivalentTo(expectedNames));
	}
}