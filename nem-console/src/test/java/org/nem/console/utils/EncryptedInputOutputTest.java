package org.nem.console.utils;

import org.apache.commons.io.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.console.test.*;
import org.nem.core.utils.ExceptionUtils;

import java.io.*;
import java.nio.file.Files;

public class EncryptedInputOutputTest {
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

	//region constructor parameter checks

	@Test
	public void cannotCreateIoWithEmptyPassword() {
		// Act:
		ExceptionAssert.assertThrows(v -> new EncryptedInputOutput(null, 10), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new EncryptedInputOutput("", 10), IllegalArgumentException.class);
	}

	@Test
	public void cannotCreateIoWithLessThanOneHash() {
		// Act:
		ExceptionAssert.assertThrows(v -> new EncryptedInputOutput("abc", 0), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new EncryptedInputOutput("abc", -1), IllegalArgumentException.class);
	}

	//endregion

	//region can write encrypted data

	@Test
	public void canWriteDataWithSingleHash() {
		// Assert:
		assertCanWriteEncryptedData(1);
	}

	@Test
	public void canWriteDataWithMultipleHashes() {
		// Assert:
		assertCanWriteEncryptedData(123);
	}

	private static void assertCanWriteEncryptedData(final int numHashes) {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "assertCanWriteEncryptedData_" + numHashes);
		final byte[] data = Utils.generateRandomBytes();
		final EncryptedInputOutput io = new EncryptedInputOutput("pass", numHashes);

		// Act:
		io.writeTo(file.toString(), data);

		// Assert:
		final byte[] contents = ExceptionUtils.propagate(() -> Files.readAllBytes(file.toPath()));
		Assert.assertThat(contents, IsNot.not(IsEqual.equalTo(data)));
	}

	//endregion

	//region can round trip encrypted data

	@Test
	public void canRoundTripDataWithSingleHash() {
		// Assert:
		assertCanRoundTripEncryptedData(1);
	}

	@Test
	public void canRoundTripDataWithMultipleHashes() {
		assertCanRoundTripEncryptedData(123);
	}

	private static void assertCanRoundTripEncryptedData(final int numHashes) {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "assertCanRoundTripEncryptedData" + numHashes);
		final byte[] data = Utils.generateRandomBytes();
		final EncryptedInputOutput ioWriter = new EncryptedInputOutput("pass", numHashes);
		ioWriter.writeTo(file.toString(), data);

		// Act:
		final EncryptedInputOutput ioReader = new EncryptedInputOutput("pass", numHashes);
		final byte[] contents = ioReader.readFrom(file.toString());

		// Assert:
		Assert.assertThat(contents, IsEqual.equalTo(data));
	}

	//endregion

	//region cannot decrypt with wrong parameters

	@Test
	public void cannotDecryptWithWrongPassword() {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "cannotDecryptWithWrongPassword");
		final byte[] data = Utils.generateRandomBytes();
		final EncryptedInputOutput ioWriter = new EncryptedInputOutput("pass", 123);
		ioWriter.writeTo(file.toString(), data);

		// Act:
		final EncryptedInputOutput ioReader = new EncryptedInputOutput("pass2", 123);
		ExceptionAssert.assertThrows(
				v -> ioReader.readFrom(file.toString()),
				StorageException.class);
	}

	@Test
	public void cannotDecryptWithWrongNumHashes() {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "cannotDecryptWithWrongNumHashes");
		final byte[] data = Utils.generateRandomBytes();
		final EncryptedInputOutput ioWriter = new EncryptedInputOutput("pass", 123);
		ioWriter.writeTo(file.toString(), data);

		// Act:
		final EncryptedInputOutput ioReader = new EncryptedInputOutput("pass", 124);
		ExceptionAssert.assertThrows(
				v -> ioReader.readFrom(file.toString()),
				StorageException.class);
	}

	//endregion

	//region file existence checks

	@Test
	public void cannotWriteToFileThatExists() {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "cannotWriteToFileThatExists");
		final byte[] data = Utils.generateRandomBytes();
		final EncryptedInputOutput io = new EncryptedInputOutput("pass", 123);
		io.writeTo(file.toString(), data);

		// Act:
		ExceptionAssert.assertThrows(
				v -> io.writeTo(file.toString(), data),
				IllegalArgumentException.class);
	}

	@Test
	public void cannotReadFromFileThatDoesNotExist() {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "cannotReadFromFileThatDoesNotExist");
		final EncryptedInputOutput io = new EncryptedInputOutput("pass", 124);

		// Act:
		ExceptionAssert.assertThrows(
				v -> io.readFrom(file.toString()),
				IllegalArgumentException.class);
	}

	//endregion
}