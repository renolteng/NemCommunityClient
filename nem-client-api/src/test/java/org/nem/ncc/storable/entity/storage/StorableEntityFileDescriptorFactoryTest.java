package org.nem.ncc.storable.entity.storage;

import org.apache.commons.io.FileUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.test.ExceptionAssert;

import java.io.*;

public abstract class StorableEntityFileDescriptorFactoryTest<
		TEntityName extends StorableEntityName,
		TEntityPassword extends StorableEntityPassword,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityNamePasswordPair extends StorableEntityNamePasswordPair<TEntityName, TEntityPassword, TEntityNamePasswordPair>,
		TEntityFileDescriptorFactory extends StorableEntityFileDescriptorFactory<?, TEntityName, TEntityPassword, TEntityFileExtension, TEntityNamePasswordPair, ?>> {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	private static final String FILE_EXTENSION = ".bar";

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

	// TODO 20150101 BR: Same as createNewSucceedsIfFileDoesNotExist. How can I test another way?
	@Test
	public void createNewSucceedsWithNullPasswordInPair() {
		// Arrange:
		final TEntityFileDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair("test-create");
		final TEntityFileExtension extension = this.createFileExtension(FILE_EXTENSION);

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.createNew(pair, extension);

		// Assert:
		Assert.assertThat(descriptor.getName(), IsEqual.equalTo(this.createEntityName("test-create")));
		Assert.assertThat(descriptor.getFileExtension(), IsEqual.equalTo(extension));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(StorableEntityFileDescriptor.class));
	}

	@Test
	public void createNewFailsIfFileAlreadyExists() {
		// Arrange:
		final TEntityFileDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair("test");
		final TEntityFileExtension extension = this.createFileExtension(FILE_EXTENSION);

		// Act:
		ExceptionAssert.assertThrowsStorageException(
				v -> descriptorFactory.createNew(pair, extension),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_ALREADY_EXISTS.value()));
	}

	@Test
	public void createNewSucceedsIfFileDoesNotExist() {
		// Arrange:
		final TEntityFileDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair("test-create");
		final TEntityFileExtension extension = this.createFileExtension(FILE_EXTENSION);

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.createNew(pair, extension);

		// Assert:
		Assert.assertThat(descriptor.getName(), IsEqual.equalTo(this.createEntityName("test-create")));
		Assert.assertThat(descriptor.getFileExtension(), IsEqual.equalTo(extension));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(StorableEntityFileDescriptor.class));
	}

	@Test
	public void openExistingFailsIfFileDoesNotExist() {
		// Arrange:
		final TEntityFileDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair("test-open");
		final TEntityFileExtension extension = this.createFileExtension(FILE_EXTENSION);

		// Act:
		ExceptionAssert.assertThrowsStorageException(
				v -> descriptorFactory.openExisting(pair, extension),
				this.getExceptionClass(),
				this.getExceptionValue(StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST.value()));
	}

	@Test
	public void openExistingSucceedsIfFileAlreadyExists() {
		// Arrange:
		final TEntityFileDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final TEntityNamePasswordPair pair = this.createEntityNamePasswordPair("test");
		final TEntityFileExtension extension = this.createFileExtension(FILE_EXTENSION);

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.openExisting(pair, extension);

		// Assert:
		Assert.assertThat(descriptor.getName(), IsEqual.equalTo(this.createEntityName("test")));
		Assert.assertThat(descriptor.getFileExtension(), IsEqual.equalTo(extension));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(StorableEntityFileDescriptor.class));
	}

	protected abstract TEntityNamePasswordPair createEntityNamePasswordPair(final String name);

	protected abstract TEntityFileExtension createFileExtension(final String extension);

	protected abstract TEntityFileDescriptorFactory createFactory(final File file);

	protected abstract Class<? extends StorableEntityStorageException> getExceptionClass();

	protected abstract Integer getExceptionValue(final Integer originalValue);

	private TEntityName createEntityName(final String name) {
		return this.createEntityNamePasswordPair(name).getName();
	}
}
