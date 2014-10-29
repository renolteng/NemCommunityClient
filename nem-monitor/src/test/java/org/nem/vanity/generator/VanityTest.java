package org.nem.vanity.generator;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class VanityTest {

	@Test
	public void normalizeVanityTextWithNormalCharacaters() {
		// Arrange:
		String testText = "ABCDE";

		// Act:
		String result = Vanity.normalizeVanityText(testText);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(testText));
	}

	@Test
	public void normalizeVanityTextWithNormalLowerCaseCharacaters() {
		// Arrange:
		String testText = "aBcDe";

		// Act:
		String result = Vanity.normalizeVanityText(testText);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(testText.toUpperCase()));
	}

	@Test
	public void normalizeVanityTextWithSpecialCharacaters() {
		// Arrange:
		String testText = "aBcDe_.;. @€P0987ßöäü~";

		// Act:
		String result = Vanity.normalizeVanityText(testText);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo("ABCDEP0987"));
	}
}
