package org.nem.ncc.storage;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.ncc.test.ExceptionAssert;

public class StorableEntityFileExtensionTest {

	@Test
	public void fileExtensionCanBeCreatedAroundNonWhitespaceStringStartingWithADotAndHavingCorrectLength() {
		// Act:
		final StorableEntityFileExtension fileExtension = new StorableEntityFileExtension(".foo");

		// Assert:
		Assert.assertThat(fileExtension.toString(), IsEqual.equalTo(".foo"));
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
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension("foo"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension("*foo"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(",foo"), IllegalArgumentException.class);
	}

	@Test
	public void fileExtensionCannotBeCreatedAroundStringWithWrongLength() {
		// Assert:
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(".fooBar"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(".fooo"), IllegalArgumentException.class);
		ExceptionAssert.assertThrows(v -> new StorableEntityFileExtension(".anotherFoo"), IllegalArgumentException.class);
	}
}