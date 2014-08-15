package org.nem.ncc.cache;

import org.apache.commons.io.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.ncc.exceptions.NccException;
import org.nem.ncc.test.ExceptionAssert;

import java.io.*;

public class AccountsFileDescriptorTest {

	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_ACCOUNTS_FILE = new File(TEST_FILE_DIRECTORY, "test.cache");
	private static final byte[] TEST_ACCOUNTS_FILE_CONTENTS = new byte[] { 1, 2, 3 };

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		final boolean result = TEST_FILE_DIRECTORY.mkdir()
				&& TEST_ACCOUNTS_FILE.createNewFile();

		try (final FileOutputStream os = new FileOutputStream(TEST_ACCOUNTS_FILE)) {
			os.write(TEST_ACCOUNTS_FILE_CONTENTS);
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

	//region openRead

	@Test
	public void openReadCanOpenFileThatExists() throws IOException {
		// Arrange:
		final AccountsFileDescriptor descriptor = new AccountsFileDescriptor(TEST_ACCOUNTS_FILE);

		// Act:
		try (final InputStream is = descriptor.openRead()) {
			// Assert:
			Assert.assertThat(IOUtils.toByteArray(is), IsEqual.equalTo(TEST_ACCOUNTS_FILE_CONTENTS));
		}
	}

	@Test
	public void openReadCanOpenFileThatDoesNotExist() throws IOException {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "imaginary-read.wlt");
		final AccountsFileDescriptor descriptor = new AccountsFileDescriptor(file);

		// Act:
		try (final InputStream is = descriptor.openRead()) {
			// Assert:
			Assert.assertThat(IOUtils.toByteArray(is), IsEqual.equalTo(new byte[] { }));
		}
	}

	@Test
	public void openReadCannotOpenFileThatIsInvalid() {
		// Arrange:
		File file = new File("fo\0o.cache");
		file = Mockito.spy(file);
		Mockito.when(file.exists()).thenReturn(true);
		final AccountsFileDescriptor descriptor = new AccountsFileDescriptor(file);

		// Act:
		ExceptionAssert.assertThrowsNccException(
				v -> descriptor.openRead(),
				NccException.Code.ACCOUNT_CACHE_ERROR);
	}

	//endregion

	//region openWrite

	@Test
	public void openWriteCanOpenFileThatExists() throws IOException {
		// Arrange:
		final AccountsFileDescriptor descriptor = new AccountsFileDescriptor(TEST_ACCOUNTS_FILE);

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			// Assert:
			Assert.assertThat(os, IsNull.notNullValue());
		}
	}

	@Test
	public void openWriteCanOpenFileThatDoesNotExist() throws IOException {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "imaginary-write.wlt");
		final AccountsFileDescriptor descriptor = new AccountsFileDescriptor(file);

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			// Assert:
			Assert.assertThat(os, IsNull.notNullValue());
		}
	}

	@Test
	public void openWriteCannotOpenFileThatIsInvalid() {
		// Arrange:
		File file = new File("fo\0o.cache");
		file = Mockito.spy(file);
		Mockito.when(file.exists()).thenReturn(true);
		final AccountsFileDescriptor descriptor = new AccountsFileDescriptor(file);

		// Act:
		ExceptionAssert.assertThrowsNccException(
				v -> descriptor.openWrite(),
				NccException.Code.ACCOUNT_CACHE_ERROR);
	}

	//endregion
}