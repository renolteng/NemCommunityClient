package org.nem.console.utils;

import org.apache.commons.io.FileUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.console.test.*;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.nio.file.Files;

public class PlainTextInputOutputTest {
	protected static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	protected static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");

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

	//region can write plain data

	@Test
	public void canWriteData() {
		// Assert:
		final File file = new File(TEST_FILE_DIRECTORY, "canWriteData");
		final byte[] data = Utils.generateRandomBytes();
		final PlainTextInputOutput io = new PlainTextInputOutput();

		// Act:
		io.writeTo(file.toString(), data);

		// Assert:
		final byte[] contents = ExceptionUtils.propagate(() -> Files.readAllBytes(file.toPath()));
		Assert.assertThat(contents, IsEqual.equalTo(data));
	}

	//endregion

	//region file existence checks

	@Test
	public void cannotWriteToFileThatExists() {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "cannotWriteToFileThatExists");
		final byte[] data = Utils.generateRandomBytes();
		final PlainTextInputOutput io = new PlainTextInputOutput();
		io.writeTo(file.toString(), data);

		// Act:
		ExceptionAssert.assertThrows(
				v -> io.writeTo(file.toString(), data),
				IllegalArgumentException.class);
	}

	//endregion
}