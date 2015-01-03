package org.nem.ncc.storage;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.*;

public class SecureStorableEntityDescriptorFactoryTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	private final StorableEntityFileExtension FILE_EXTENSION = new StorableEntityFileExtension(".bar");

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
/*
	@Test
	public void createNewFailsIfPasswordIsNull() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test-create"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.createNew(pair, FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL);
	}

	@Test
	public void openExistingFailsIfPasswordIsNull() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.openExisting(pair, FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL);
	}

	@SuppressWarnings("unchecked")
	private SecureStorableEntityDescriptorFactory createFactory(final File file) {
		return new SecureStorableEntityDescriptorFactory(
				file,
				DefaultStorableEntity::new,
				StorableEntityName::new,
				StorableEntityFileExtension::new,
				StorableEntityFileDescriptor::new,
				SecureStorableEntityDescriptor::new,
				StorableEntityFileDescriptorFactory::new);
	}
	*/
}
