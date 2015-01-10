package org.nem.ncc.storable.entity;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.ncc.test.ExceptionAssert;

public class StorableEntityFileExtensionTest {

	@Test
	public void parameterlessConstructorUsesDefaultFileExtension() {
		// Act:
		final StorableEntityFileExtension fileExtension = this.createEntityFileExtension();

		// Assert:
		Assert.assertThat(fileExtension, IsEqual.equalTo(this.getDefaultFileExtension()));
	}

	@Test
	public void fileExtensionCanBeCreatedAroundNonWhitespaceStringStartingWithADotAndHavingCorrectLength() {
		// Act:
		final StorableEntityFileExtension fileExtension = this.createEntityFileExtension(".bar");

		// Assert:
		Assert.assertThat(fileExtension.toString(), IsEqual.equalTo(".bar"));
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension((String)null), StorableEntityStorageException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension(""), StorableEntityStorageException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension("  \t\t "), StorableEntityStorageException.class);
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundStringNotStartingWithADot() {
		// Assert:
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension("bar"), StorableEntityStorageException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension("*bar"), StorableEntityStorageException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension(",bar"), StorableEntityStorageException.class);
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundStringWithWrongLength() {
		// Assert:
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension(".fooBar"), StorableEntityStorageException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension(".baar"), StorableEntityStorageException.class);
		ExceptionAssert.assertThrows(v -> this.createEntityFileExtension(".anotherBar"), StorableEntityStorageException.class);
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityFileExtension fileExtension = this.createEntityFileExtension(".bar");

		// Assert:
		Assert.assertThat(this.createEntityFileExtension(".bar"), IsEqual.equalTo(fileExtension));
		Assert.assertThat(this.createEntityFileExtension(".bab"), IsNot.not(IsEqual.equalTo(fileExtension)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(fileExtension)));
		Assert.assertThat(".bar", IsNot.not(IsEqual.equalTo((Object)fileExtension)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final StorableEntityFileExtension fileExtension = this.createEntityFileExtension(".bar");
		final int hashCode = fileExtension.hashCode();

		// Assert:
		Assert.assertThat(this.createEntityFileExtension(".bar").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(this.createEntityFileExtension(".bab").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawFileExtension() {
		// Arrange:
		final StorableEntityFileExtension name = this.createEntityFileExtension(".bar");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo(".bar"));
	}

	protected StorableEntityFileExtension getDefaultFileExtension() {
		return StorableEntityFileExtension.getDefaultFileExtension();
	}

	protected StorableEntityFileExtension createEntityFileExtension() {
		return new StorableEntityFileExtension();
	}

	protected StorableEntityFileExtension createEntityFileExtension(final String fileExtension) {
		return new StorableEntityFileExtension(fileExtension);
	}
}