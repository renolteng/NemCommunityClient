package org.nem.ncc.storable.entity.storage;

import org.apache.commons.io.FileUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.test.ExceptionAssert;
import org.nem.ncc.test.StorableEntity.*;

import java.io.*;

public class StorableEntityFileDescriptorFactoryTest {
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

	// TODO 20150101 BR: Same as createNewSucceedsIfFileDoesNotExist. How can I test another way?
	@Test
	public void createNewSucceedsWithNullPasswordInPair() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test-create"),
				null);

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.createNew(pair, FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getName(), IsEqual.equalTo(new StorableEntityName("test-create")));
		Assert.assertThat(descriptor.getFileExtension(), IsEqual.equalTo(DefaultStorableEntity.FILE_EXTENSION));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(StorableEntityFileDescriptor.class));
	}

	@Test
	public void createNewFailsIfFileAlreadyExists() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.createNew(pair, FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_ALREADY_EXISTS);
	}

	@Test
	public void createNewSucceedsIfFileDoesNotExist() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test-create"),
				null);

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.createNew(pair, FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getName(), IsEqual.equalTo(new StorableEntityName("test-create")));
		Assert.assertThat(descriptor.getFileExtension(), IsEqual.equalTo(DefaultStorableEntity.FILE_EXTENSION));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(StorableEntityFileDescriptor.class));
	}

	@Test
	public void openExistingFailsIfFileDoesNotExist() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test-open"),
				null);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.openExisting(pair, FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST);
	}

	@Test
	public void openExistingSucceedsIfFileAlreadyExists() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = this.createFactory(TEST_FILE_DIRECTORY);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair<>(
				new StorableEntityName("test"),
				null);

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.openExisting(pair, FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getName(), IsEqual.equalTo(new StorableEntityName("test")));
		Assert.assertThat(descriptor.getFileExtension(), IsEqual.equalTo(DefaultStorableEntity.FILE_EXTENSION));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(StorableEntityFileDescriptor.class));
	}

	protected StorableEntityFileDescriptorFactory createFactory(final File file) {
		return new StorableEntityFileDescriptorFactory<
				DefaultStorableEntity,
				StorableEntityName,
				StorableEntityPassword,
				StorableEntityFileExtension,
				DefaultStorableEntityNamePasswordPair,
				DefaultStorableEntityFileDescriptor>(
				file,
				DefaultStorableEntity::new,
				StorableEntityName::new,
				StorableEntityFileExtension::new,
				DefaultStorableEntityFileDescriptor::new);
	}
}
