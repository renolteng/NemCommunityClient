package org.nem.ncc.storage;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.ncc.test.ExceptionAssert;

public class StorableEntityFileExtensionTest {

	@Test
	public void fileExtensionCanBeCreatedAroundNonWhitespaceStringStartingWithADotAndHavingCorrectLength() {
		// Act:
		final StorableEntityFileExtension fileExtension = new StorableEntityFileExtension(".bar");

		// Assert:
		Assert.assertThat(fileExtension.toString(), IsEqual.equalTo(".bar"));
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundWhitespaceString() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension((String)null), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(""), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension("  \t\t "), IllegalArgumentException.class);
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundStringNotStartingWithADot() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension("bar"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension("*bar"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(",bar"), IllegalArgumentException.class);
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundStringWithWrongLength() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(".fooBar"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(".baar"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(".anotherBar"), IllegalArgumentException.class);
	}

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final StorableEntityFileExtension fileExtension = new StorableEntityFileExtension(".bar");

		// Assert:
		Assert.assertThat(new StorableEntityFileExtension(".bar"), IsEqual.equalTo(fileExtension));
		Assert.assertThat(new StorableEntityFileExtension(".bab"), IsNot.not(IsEqual.equalTo(fileExtension)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(fileExtension)));
		Assert.assertThat(".bar", IsNot.not(IsEqual.equalTo((Object)fileExtension)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final StorableEntityFileExtension fileExtension = new StorableEntityFileExtension(".bar");
		final int hashCode = fileExtension.hashCode();

		// Assert:
		Assert.assertThat(new StorableEntityFileExtension(".bar").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new StorableEntityFileExtension(".bab").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	@Test
	public void toStringReturnsRawName() {
		// Arrange:
		final StorableEntityFileExtension name = new StorableEntityFileExtension(".bar");

		// Assert:
		Assert.assertThat(name.toString(), IsEqual.equalTo(".bar"));
	}
}