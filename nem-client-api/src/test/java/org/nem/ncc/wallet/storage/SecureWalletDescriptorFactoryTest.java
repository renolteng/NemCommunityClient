package org.nem.ncc.wallet.storage;

import org.apache.commons.io.FileUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.ncc.storage.StorableEntityStorageException;
import org.nem.ncc.test.ExceptionAssert;
import org.nem.ncc.wallet.*;

import java.io.*;

public class SecureWalletDescriptorFactoryTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_WALLET = new File(TEST_FILE_DIRECTORY, "test.wlt");
	private static final WalletFileExtension FILE_EXTENSION = new WalletFileExtension();

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		final boolean result = TEST_FILE_DIRECTORY.mkdir() && TEST_WALLET.createNewFile();

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
	public void createNewFailsIfFileAlreadyExists() {
		// Arrange:
		final WalletDescriptorFactory descriptorFactory = new SecureWalletDescriptorFactory(TEST_FILE_DIRECTORY);
		final WalletNamePasswordPair pair = new WalletNamePasswordPair(
				new WalletName("test"),
				new WalletPassword("p"));

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.createNew(pair, FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_ALREADY_EXISTS);
	}

	@Test
	public void createNewSucceedsIfFileDoesNotExist() {
		// Arrange:
		final WalletDescriptorFactory descriptorFactory = new SecureWalletDescriptorFactory(TEST_FILE_DIRECTORY);
		final WalletNamePasswordPair pair = new WalletNamePasswordPair(
				new WalletName("test-create"),
				new WalletPassword("p"));

		// Act:
		final WalletDescriptor descriptor = descriptorFactory.createNew(pair, FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getWalletName(), IsEqual.equalTo(new WalletName("test-create")));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(SecureWalletDescriptor.class));
	}

	@Test
	public void openExistingFailsIfFileDoesNotExist() {
		// Arrange:
		final WalletDescriptorFactory descriptorFactory = new SecureWalletDescriptorFactory(TEST_FILE_DIRECTORY);
		final WalletNamePasswordPair pair = new WalletNamePasswordPair(
				new WalletName("test-open"),
				new WalletPassword("p"));

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.openExisting(pair, FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST);
	}

	@Test
	public void openExistingSucceedsIfFileAlreadyExists() {
		// Arrange:
		final WalletDescriptorFactory descriptorFactory = new SecureWalletDescriptorFactory(TEST_FILE_DIRECTORY);
		final WalletNamePasswordPair pair = new WalletNamePasswordPair(
				new WalletName("test"),
				new WalletPassword("p"));

		// Act:
		final WalletDescriptor descriptor = descriptorFactory.openExisting(pair, FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getWalletName(), IsEqual.equalTo(new WalletName("test")));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(SecureWalletDescriptor.class));
	}
}