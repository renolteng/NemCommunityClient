package org.nem.ncc.storage;

import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.*;
import org.nem.ncc.test.*;

import java.io.*;
import java.nio.file.Paths;

public class StorableEntityFileDescriptorTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_STORABLE_ENTITY_FILE = new File(TEST_FILE_DIRECTORY, "test.bar");
	private static final File TEST_STORABLE_ENTITY_FILE_BAD_EXT = new File(TEST_FILE_DIRECTORY, "test.foobar");
	private static final File TEST_STORABLE_ENTITY_FILE_MISMATCH_NAME = new File(TEST_FILE_DIRECTORY, "foo.bar");
	private static final File TEST_STORABLE_ENTITY_FILE_MISMATCH_FILE_EXTENSION = new File(TEST_FILE_DIRECTORY, "test.foo");
	private final StorableEntity ENTITY = this.getEntity("test", ".bar");

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		final boolean result = TEST_FILE_DIRECTORY.mkdir()
				&& TEST_STORABLE_ENTITY_FILE.createNewFile()
				&& TEST_STORABLE_ENTITY_FILE_BAD_EXT.createNewFile();

		if (!result) {
			throw new RuntimeException("unable to initialize test suite");
		}
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	//endregion

	//region construction

	@Test
	public void descriptorCannotBeCreatedAroundDirectory() {
		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new StorableEntityFileDescriptor<StorableEntity>(ENTITY, TEST_FILE_DIRECTORY),
				StorableEntityStorageException.Code.STORABLE_ENTITY_CANNOT_BE_DIRECTORY);
	}

	@Test
	public void descriptorCannotBeCreatedAroundStorableEntityWithInvalidExtension() {
		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new StorableEntityFileDescriptor<StorableEntity>(ENTITY, TEST_STORABLE_ENTITY_FILE_BAD_EXT),
				StorableEntityStorageException.Code.STORABLE_ENTITY_HAS_INVALID_EXTENSION);
	}

	@Test
	public void descriptorCannotBeCreatedAroundStorableEntityWithMismatchingName() {
		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new StorableEntityFileDescriptor<StorableEntity>(ENTITY, TEST_STORABLE_ENTITY_FILE_MISMATCH_NAME),
				StorableEntityStorageException.Code.STORABLE_ENTITY_FILE_NAME_DOES_NOT_MATCH_ENTITY_NAME);
	}

	@Test
	public void descriptorCannotBeCreatedAroundStorableEntityWithMismatchingFileExtension() {
		// Assert:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> new StorableEntityFileDescriptor<StorableEntity>(ENTITY, TEST_STORABLE_ENTITY_FILE_MISMATCH_FILE_EXTENSION),
				StorableEntityStorageException.Code.STORABLE_ENTITY_FILE_EXTENSION_DOES_NOT_MATCH_ENTITY_FILE_EXTENSION);
	}

	@Test
	public void descriptorCanBeCreatedAroundStorableEntityWithValidExtension() {
		// Arrange:
		final File file = new File(Paths.get(TEST_FILE_DIRECTORY.toString(), "blah").toString(), "foo.bar");
		final StorableEntity entity = getEntity("foo", ".bar");

		// Act:
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Assert:
		Assert.assertThat(descriptor.getEntity().getName(), IsEqual.equalTo(new StorableEntityName("foo")));
		Assert.assertThat(descriptor.getEntity().getFileExtension(), IsEqual.equalTo(new StorableEntityFileExtension(".bar")));
		Assert.assertThat(
				descriptor.getStorableEntityLocation(),
				IsEqual.equalTo(Paths.get(TEST_FILE_DIRECTORY.toString(), "blah", "foo.bar").toString()));
	}

	@Test
	public void descriptorCanBeCreatedAroundStorableEntityWithMixedCaseExtension() {
		// Arrange:
		final File file = new File(Paths.get(TEST_FILE_DIRECTORY.toString(), "BlAh").toString(), "FoO.BaR");
		final StorableEntity entity = getEntity("FoO", ".BaR");

		// Act:
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Assert:
		Assert.assertThat(descriptor.getEntity().getName(), IsEqual.equalTo(new StorableEntityName("FoO")));
		Assert.assertThat(descriptor.getEntity().getFileExtension(), IsEqual.equalTo(new StorableEntityFileExtension(".BaR")));
		Assert.assertThat(
				descriptor.getStorableEntityLocation(),
				IsEqual.equalTo(Paths.get(TEST_FILE_DIRECTORY.toString(), "BlAh", "FoO.BaR").toString()));
	}

	@Test
	public void descriptorCanBeCreatedAroundUrlEncodedStorableEntityWithValidExtension() {
		// Arrange:
		final File file = new File(Paths.get(TEST_FILE_DIRECTORY.toString(), "blah").toString(), "%C3%B6%C3%A4%C3%BC%40.bar");
		final StorableEntity entity = getEntity("öäü@", ".bar");

		// Act:
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Assert:
		Assert.assertThat(descriptor.getEntity().getName(), IsEqual.equalTo(new StorableEntityName("öäü@")));
		Assert.assertThat(descriptor.getEntity().getFileExtension(), IsEqual.equalTo(new StorableEntityFileExtension(".bar")));
		Assert.assertThat(
				descriptor.getStorableEntityLocation(),
				IsEqual.equalTo(Paths.get(TEST_FILE_DIRECTORY.toString(), "blah", "%C3%B6%C3%A4%C3%BC%40.bar").toString()));
	}

	//endregion

	//region openRead

	@Test
	public void openReadCanOpenFileThatExists() throws IOException {
		// Arrange:
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(ENTITY, TEST_STORABLE_ENTITY_FILE);

		// Act:
		try (final InputStream is = descriptor.openRead()) {
			// Assert:
			Assert.assertThat(is, IsNull.notNullValue());
		}
	}

	@Test
	public void openReadCannotOpenFileThatDoesNotExist() {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "imaginary-read.bar");
		final StorableEntity entity = getEntity("imaginary-read", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptor.openRead(),
				StorableEntityStorageException.Code.STORABLE_ENTITY_DOES_NOT_EXIST);
	}

	@Test
	public void openReadCannotOpenFileThatIsInvalid() {
		// Arrange:
		final File file = Mockito.mock(File.class);
		Mockito.when(file.getName()).thenReturn("fo\0o.bar");
		Mockito.when(file.getAbsolutePath()).thenReturn("fo\0o.bar");
		Mockito.when(file.exists()).thenReturn(true);
		final StorableEntity entity = getEntity("fo\0o", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptor.openRead(),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_READ);
	}

	//endregion

	//region openWrite

	@Test
	public void openWriteCanOpenFileThatExists() throws IOException {
		// Arrange:
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(ENTITY, TEST_STORABLE_ENTITY_FILE);

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			// Assert:
			Assert.assertThat(os, IsNull.notNullValue());
		}
	}

	@Test
	public void openWriteCanOpenFileThatDoesNotExist() throws IOException {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "imaginary-write.bar");
		final StorableEntity entity = getEntity("imaginary-write", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Act:
		try (final OutputStream os = descriptor.openWrite()) {
			// Assert:
			Assert.assertThat(os, IsNull.notNullValue());
		}
	}

	@Test
	public void openWriteCannotOpenFileThatIsInvalid() {
		// Arrange:
		final File file = Mockito.mock(File.class);
		Mockito.when(file.getName()).thenReturn("fo\0o.bar");
		Mockito.when(file.getAbsolutePath()).thenReturn("fo\0o.bar");
		final StorableEntity entity = getEntity("fo\0o", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptor.openWrite(),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_SAVED);
	}

	//endregion

	//region delete

	@Test
	public void deleteDeletesUnderlyingStorableEntityFile() throws IOException {
		// Arrange:
		final File file = new File(TEST_FILE_DIRECTORY, "to-be-deleted.bar");
		final StorableEntity entity = getEntity("to-be-deleted", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);
		Assert.assertThat(file.createNewFile(), IsEqual.equalTo(true));

		// Act:
		descriptor.delete();

		// Assert:
		Assert.assertThat(file.exists(), IsEqual.equalTo(false));
	}

	@Test
	public void deleteRaisesExceptionIfFileCannotBeDeleted() {
		// Arrange:
		final File file = Mockito.mock(File.class);
		Mockito.when(file.getName()).thenReturn("foo.bar");
		Mockito.when(file.getAbsolutePath()).thenReturn("foo.bar");
		final StorableEntity entity = getEntity("foo", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		Mockito.when(file.delete()).thenReturn(false);

		// Act:
		ExceptionAssert.assertThrowsStorableEntityStorageException(
				v -> descriptor.delete(),
				StorableEntityStorageException.Code.STORABLE_ENTITY_COULD_NOT_BE_DELETED);
	}

	//endregion

	//region serialization

	@Test
	public void descriptorCanBeSerialized() {
		// Arrange:
		final File file = new File(Paths.get(TEST_FILE_DIRECTORY.toString(), "blah").toString(), "foo.bar");
		final StorableEntity entity = getEntity("foo", ".bar");
		final StorableEntityFileDescriptor descriptor = new StorableEntityFileDescriptor<>(entity, file);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(descriptor);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonObject.get("defaultStorableEntity"), IsEqual.equalTo("foo"));
		Assert.assertThat(
				jsonObject.get("location"),
				IsEqual.equalTo(Paths.get(TEST_FILE_DIRECTORY.toString(), "blah", "foo.bar").toString()));
	}

	//endregion

	private StorableEntity getEntity(final String name, final String fileExtension) {
		return StorableEntityUtils.createStorableEntity(name, fileExtension);
	}

}
