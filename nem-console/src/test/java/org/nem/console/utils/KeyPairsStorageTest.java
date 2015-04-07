package org.nem.console.utils;

import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.console.models.AliasedKeyPair;
import org.nem.console.test.Utils;
import org.nem.core.crypto.KeyPair;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.util.*;

public class KeyPairsStorageTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		if (!TEST_FILE_DIRECTORY.mkdir()) {
			throw new IllegalStateException("unable to create test files");
		}
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	//endregion

	@Test
	public void canRoundTripKeyPairs() {
		// Arrange:
		final String fileName = new File(TEST_FILE_DIRECTORY, "default.kp").toString();
		final Collection<AliasedKeyPair> originalKeyPairs = Arrays.asList(
				new AliasedKeyPair("ALPHA", new KeyPair()),
				new AliasedKeyPair("BETA", new KeyPair()),
				new AliasedKeyPair("GAMMA", new KeyPair()));

		// Act:
		KeyPairsStorage.save(originalKeyPairs, fileName, "pass", 12);
		final Collection<AliasedKeyPair> keyPairs = KeyPairsStorage.load(fileName, "pass", 12);

		// Assert:
		assertKeyPairsAreEqual(keyPairs, originalKeyPairs);
	}

	@Test
	public void canRoundTripKeyPairsUsingCommandLineOverloads() {
		// Arrange:
		final String fileName = new File(TEST_FILE_DIRECTORY, "commandline.kp").toString();
		final Collection<AliasedKeyPair> originalKeyPairs = Arrays.asList(
				new AliasedKeyPair("ALPHA", new KeyPair()),
				new AliasedKeyPair("BETA", new KeyPair()),
				new AliasedKeyPair("GAMMA", new KeyPair()));
		final CommandLine saveOptions = Utils.createCommandLine(
				String.format("--output=%s", fileName),
				"--pass=pwd",
				"--numHashes=12");
		final CommandLine loadOptions = Utils.createCommandLine(
				String.format("--input=%s", fileName),
				"--pass=pwd",
				"--numHashes=12");

		// Act:
		KeyPairsStorage.save(originalKeyPairs, saveOptions);
		final Collection<AliasedKeyPair> keyPairs = KeyPairsStorage.load(loadOptions);

		// Assert:
		assertKeyPairsAreEqual(keyPairs, originalKeyPairs);
	}

	private static void assertKeyPairsAreEqual(final Collection<AliasedKeyPair> actual, final Collection<AliasedKeyPair> expected) {
		// Assert:
		assertKeyPairsAreEqual(new ArrayList<>(actual), new ArrayList<>(expected));
	}

	private static void assertKeyPairsAreEqual(final List<AliasedKeyPair> actual, final List<AliasedKeyPair> expected) {
		// Assert:
		final List<AliasedKeyPair> actualList = new ArrayList<>(actual);
		final List<AliasedKeyPair> expectedList = new ArrayList<>(expected);
		Assert.assertThat(actualList.size(), IsEqual.equalTo(expectedList.size()));

		for (int i = 0; i < actualList.size(); ++i) {
			final AliasedKeyPair actualPair = actualList.get(i);
			final AliasedKeyPair expectedPair = expectedList.get(i);
			Assert.assertThat(actualPair.alias(), IsEqual.equalTo(expectedPair.alias()));
			Assert.assertThat(actualPair.keyPair().getPrivateKey(), IsEqual.equalTo(expectedPair.keyPair().getPrivateKey()));
		}
	}
}