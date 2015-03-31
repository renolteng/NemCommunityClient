package org.nem.console.utils;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.nem.console.test.ExceptionAssert;

import java.io.*;

public class FileMustTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File REAL_FILE = new File(TEST_FILE_DIRECTORY, "real");
	private static final File FAKE_FILE = new File(TEST_FILE_DIRECTORY, "fake");

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		if (!TEST_FILE_DIRECTORY.mkdir() || !REAL_FILE.createNewFile()) {
			throw new IllegalStateException("unable to create test files");
		}
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	//endregion

	@Test
	public void existThrowsIfFileDoesNotExist() {
		// Act:
		ExceptionAssert.assertThrows(v -> FileMust.exist(FAKE_FILE.toString()), IllegalArgumentException.class);
	}

	@Test
	public void existDoesNotThrowIfFileExists() {
		// Act:
		FileMust.exist(REAL_FILE.toString());
	}

	@Test
	public void notExistThrowsIfExists() {
		// Act:
		ExceptionAssert.assertThrows(v -> FileMust.notExist(REAL_FILE.toString()), IllegalArgumentException.class);
	}

	@Test
	public void notExistDoesNotThrowIfFileDoesNotExist() {
		// Act:
		FileMust.notExist(FAKE_FILE.toString());
	}
}