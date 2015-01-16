package org.nem.ncc.storable.entity;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.nem.ncc.storable.entity.storage.StorableEntityDescriptor;
import org.nem.ncc.test.IsEquivalent;
import org.nem.ncc.test.StorableEntity.*;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class StorableEntityFileLocatorTest {
	protected static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	protected static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	protected static final File TEST_FILE_SUB_DIRECTORY = new File(TEST_FILE_DIRECTORY, "sub");
	protected static final File TEST_FILE_MC_DIRECTORY = new File(TEST_FILE_DIRECTORY, "mixed_case");
	protected static final File TEST_FILE_NN_DIRECTORY = new File(TEST_FILE_DIRECTORY, "no_name");
	protected static final File TEST_FILE_ME_DIRECTORY = new File(TEST_FILE_DIRECTORY, "multi_ext");
	protected static final String DEFAULT_EXTENSION = getDefaultFileExtension();

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		createTestFiles(DEFAULT_EXTENSION);
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	protected static void createTestFiles(final String fileExtension) throws IOException {
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

		for (final String fileName : new String[] {
				String.format("alpha%s", fileExtension),
				String.format("sigma%s", fileExtension),
				String.format("beta%sx", fileExtension),
				String.format("zeta%s", fileExtension) }) {
			result = result && new File(TEST_FILE_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] {
				String.format("sub%s", fileExtension),
				String.format("xyz%sx", fileExtension),
				String.format("sigma%s.tmp", fileExtension),
				String.format("sigma%s", fileExtension) }) {
			result = result && new File(TEST_FILE_SUB_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] {
				String.format("upper%s", fileExtension.toUpperCase()),
				String.format("lower%s", fileExtension.toLowerCase()),
				String.format("mixed%s", toMixedCase(fileExtension.toUpperCase())) }) {
			result = result && new File(TEST_FILE_MC_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] {
				String.format("name%s", fileExtension),
				String.format("%s", fileExtension),
				String.format("n%s", fileExtension) }) {
			result = result && new File(TEST_FILE_NN_DIRECTORY, fileName).createNewFile();
		}

		for (final String fileName : new String[] {
				String.format("tw.tmp%s", fileExtension),
				String.format("wt%s.tmp", fileExtension) }) {
			result = result && new File(TEST_FILE_ME_DIRECTORY, fileName).createNewFile();
		}

		if (!result) {
			throw new RuntimeException("unable to initialize test suite");
		}
	}

	//endregion

	@Test
	public void filesWithMatchingExtensionInSearchDirectoryAreDetected() {
		// Assert:
		this.assertLocatedStorableEntityNames(TEST_FILE_SUB_DIRECTORY, new String[] { "sub", "sigma" });
	}

	@Test
	public void searchDirectoryIsNotSearchedRecursively() {
		// Assert: no wallet names from any subdirectory are surfaced
		this.assertLocatedStorableEntityNames(TEST_FILE_DIRECTORY, new String[] { "alpha", "sigma", "zeta" });
	}

	@Test
	public void filesWithMatchingExtensionOfAnyCasingInSearchDirectoryAreDetected() {
		// Assert:
		this.assertLocatedStorableEntityNames(TEST_FILE_MC_DIRECTORY, new String[] { "upper", "lower", "mixed" });
	}

	@Test
	public void namelessStorableEntityFilesAreNotDetected() {
		// Assert:
		this.assertLocatedStorableEntityNames(TEST_FILE_NN_DIRECTORY, new String[] { "name", "n" });
	}

	@Test
	public void walletFilesWithMultipleExtensionsAreDetected() {
		// Assert:
		this.assertLocatedStorableEntityNames(TEST_FILE_ME_DIRECTORY, new String[] { "tw.tmp" });
	}

	private void assertLocatedStorableEntityNames(final File directory, final String[] expectedNames) {
		// Arrange:
		final StorableEntityFileLocator locator = this.createFileLocator(directory);

		// Act:
		final List<StorableEntityDescriptor> descriptors = locator.getAll();

		// Assert:
		Assert.assertThat(
				descriptors.stream().map(d -> d.getName().toString()).collect(Collectors.toList()),
				IsEquivalent.equivalentTo(expectedNames));
	}

	private static String toMixedCase(final String fileExtension) {
		return String.format(
				"%s%s%s",
				fileExtension.substring(0, 2),
				fileExtension.substring(2, 3).toLowerCase(),
				fileExtension.substring(3));
	}

	protected static String getDefaultFileExtension() {
		return StorableEntityFileExtension.getDefaultFileExtension().toString();
	}

	protected StorableEntityFileLocator createFileLocator(final File file) {
		return new StorableEntityFileLocator<>(
				file,
				(dir, name) -> StorableEntityFileExtension.isValidAndHasDefaultExtension(name),
				DefaultStorableEntity::new,
				StorableEntityName::new,
				StorableEntityFileExtension::new,
				DefaultStorableEntityFileDescriptor::new);
	}
}
