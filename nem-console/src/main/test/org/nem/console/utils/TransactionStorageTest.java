package org.nem.console.utils;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class TransactionStorageTest {
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
	public void canWriteTransaction() {
		Assert.fail("need to write a test");
	}

	@Test
	public void canWriteTransactionUsingCommandLineOverload() {
		Assert.fail("need to write a test");
	}
}