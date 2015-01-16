package org.nem.ncc.storable.entity.storage;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.test.ExceptionAssert;
import org.nem.ncc.test.StorableEntity.*;

import java.io.*;

public class SecureStorableEntityDescriptorFactoryTest {
	protected static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	protected static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	protected static final File TEST_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	protected static final StorableEntityFileExtension FILE_EXTENSION = new StorableEntityFileExtension(".bar");

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
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test-create"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorageException(
				v -> descriptorFactory.createNew(pair, FILE_EXTENSION),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL.value()));
	}

	@Test
	public void openExistingFailsIfPasswordIsNull() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorageException(
				v -> descriptorFactory.openExisting(pair, FILE_EXTENSION),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL.value()));
	}

	protected SecureStorableEntityDescriptorFactory createFactory(final File file) {
		return new SecureStorableEntityDescriptorFactory<>(
				file,
				DefaultStorableEntity::new,
				StorableEntityName::new,
				StorableEntityFileExtension::new,
				DefaultStorableEntityFileDescriptor::new,
				DefaultSecureStorableEntityDescriptor::new,
				DefaultStorableEntityFileDescriptorFactory::new);
	}

	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return StorableEntityStorageException.class;
	}

	protected Integer getExceptionValue(final Integer originalValue) {
		return originalValue;
	}
}
