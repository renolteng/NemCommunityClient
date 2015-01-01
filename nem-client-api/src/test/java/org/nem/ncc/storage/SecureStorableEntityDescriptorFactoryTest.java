package org.nem.ncc.storage;

import org.apache.commons.io.FileUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.ncc.test.*;

import java.io.*;
import java.util.function.BiFunction;

public class SecureStorableEntityDescriptorFactoryTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	private static final StorableEntityFileExtension TEST_FILE_EXTENSION = new StorableEntityFileExtension(".bar");
	private final BiFunction<StorableEntityName, StorableEntityFileExtension, StorableEntity> entityActivator =	StorableEntityUtils::createStorableEntity;

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
	public void createNewFailsIfFileAlreadyExists() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = new SecureStorableEntityDescriptorFactory<>(TEST_FILE_DIRECTORY, entityActivator);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair(
				new StorableEntityName("test"),
				new StorableEntityPassword("p"));

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.createNew(pair, TEST_FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_ALREADY_EXISTS);
	}

	@Test
	public void createNewSucceedsIfFileDoesNotExist() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = new SecureStorableEntityDescriptorFactory<>(TEST_FILE_DIRECTORY, entityActivator);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair(
				new StorableEntityName("test-create"),
				new StorableEntityPassword("p"));

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.createNew(pair, TEST_FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getEntity().getName(), IsEqual.equalTo(new StorableEntityName("test-create")));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(SecureStorableEntityDescriptor.class));
	}

	@Test
	public void openExistingFailsIfFileDoesNotExist() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = new SecureStorableEntityDescriptorFactory<>(TEST_FILE_DIRECTORY, entityActivator);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair(
				new StorableEntityName("test-open"),
				new StorableEntityPassword("p"));

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptorFactory.openExisting(pair, TEST_FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST);
	}

	@Test
	public void openExistingSucceedsIfFileAlreadyExists() {
		// Arrange:
		final StorableEntityDescriptorFactory descriptorFactory = new SecureStorableEntityDescriptorFactory<>(TEST_FILE_DIRECTORY, entityActivator);
		final StorableEntityNamePasswordPair pair = new StorableEntityNamePasswordPair(
				new StorableEntityName("test"),
				new StorableEntityPassword("p"));

		// Act:
		final StorableEntityDescriptor descriptor = descriptorFactory.openExisting(pair, TEST_FILE_EXTENSION);

		// Assert:
		Assert.assertThat(descriptor.getEntity().getName(), IsEqual.equalTo(new StorableEntityName("test")));
		Assert.assertThat(descriptor, IsInstanceOf.instanceOf(SecureStorableEntityDescriptor.class));
	}
}
