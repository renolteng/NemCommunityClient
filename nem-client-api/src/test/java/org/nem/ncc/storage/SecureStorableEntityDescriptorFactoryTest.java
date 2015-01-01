package org.nem.ncc.storage;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.nem.ncc.test.*;

import java.io.*;
import java.util.function.Function;

public class SecureStorableEntityDescriptorFactoryTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	private final Function<StorableEntityName, StorableEntity> entityActivator = StorableEntityUtils::createStorableEntity;

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		final boolean result = TEST_FILE_DIRECTORY.mkdir() && TEST_FILE.createNewFile();

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
	public void createNewFailsIfPasswordIsNull() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = new SecureStorableEntityDescriptorFactory<>(TEST_FILE_DIRECTORY, entityActivator);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair(
				new StorableEntityName("test-create"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.createNew(pair),
				StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL);
	}

	@Test
	public void openExistingFailsIfPasswordIsNull() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = new SecureStorableEntityDescriptorFactory<>(TEST_FILE_DIRECTORY, entityActivator);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair(
				new StorableEntityName("test"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.openExisting(pair),
				StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL);
	}
}
