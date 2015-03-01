package org.nem.ncc.storable.entity.storage;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.test.ExceptionAssert;

import java.io.*;

public abstract class SecureStorableEntityDescriptorFactoryTest<
		TEntityName extends StorableEntityName,
		TEntityPassword extends StorableEntityPassword,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityNamePasswordPair extends StorableEntityNamePasswordPair<TEntityName, TEntityPassword, ?>,
		TEntityDescriptorFactory extends StorableEntityDescriptorFactory<TEntityNamePasswordPair, TEntityFileExtension, ?>> {
	protected static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	protected static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	protected static final File TEST_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	protected static final String FILE_EXTENSION = ".bar";

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
		final TEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair(this.createEntityName("test-create"), null);

		// Act:
		ExceptionAssert.assertThrowsStorageException(
				v -> descriptorFactory.createNew(pair, this.createFileExtension(FILE_EXTENSION)),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL.value()));
	}

	@Test
	public void openExistingFailsIfPasswordIsNull() {
		// Arrange:
		final TEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair(this.createEntityName("test"), null);

		// Act:
		ExceptionAssert.assertThrowsStorageException(
				v -> descriptorFactory.openExisting(pair, this.createFileExtension(FILE_EXTENSION)),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_PASSWORD_CANNOT_BE_NULL.value()));
	}

	protected abstract TEntityName createEntityName(final String name);

	protected abstract TEntityNamePasswordPair createEntityNamePasswordPair(final TEntityName name, final TEntityPassword password);

	protected abstract TEntityFileExtension createFileExtension(final String extension);

	protected abstract TEntityDescriptorFactory createFactory(final File file);

	protected abstract Class<? extends StorableEntityStorageException> getExceptionClass();

	protected abstract Integer getExceptionValue(final Integer originalValue);
}
