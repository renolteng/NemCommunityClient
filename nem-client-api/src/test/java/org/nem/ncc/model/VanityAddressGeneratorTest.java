package org.nem.ncc.model;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import java.util.*;
import java.util.stream.Collectors;

public class VanityAddressGeneratorTest {

	@Test
	public void generatorReturnsFirstKeyPairWhenThereAreNoMatches() {
		// Arrange:
		final List<String> encodedAddresses = Arrays.asList("BALO_0", "ALBO_0");
		final TestContext context = new TestContext(encodedAddresses);

		// Act:
		final String bestAddress = context.generate("ZZZZ", 2);

		// Assert:
		Assert.assertThat(bestAddress, IsEqual.equalTo("BALO_0"));
	}

	@Test
	public void generatorReturnsLongestMatchingString() {
		// Arrange:
		final List<String> encodedAddresses = Arrays.asList("ABOB_1", "ALOB_2", "ALPO_3", "ZZZZ_0");
		final TestContext context = new TestContext(encodedAddresses);

		// Act:
		final String bestAddress = context.generate("ALPHA", 4);

		// Assert:
		Assert.assertThat(bestAddress, IsEqual.equalTo("ALPO_3"));
	}

	@Test
	public void generatorOnlyLooksForSubstringsFromStartOfPattern() {
		// Arrange:
		final List<String> encodedAddresses = Arrays.asList("ALOB_2", "PHAA_3");
		final TestContext context = new TestContext(encodedAddresses);

		// Act:
		final String bestAddress = context.generate("ALPHA", 2);

		// Assert:
		Assert.assertThat(bestAddress, IsEqual.equalTo("ALOB_2"));
	}

	@Test
	public void generatorPrefersEarlierMatchesToLaterMatches() {
		// Arrange:
		final List<String> encodedAddresses = Arrays.asList("BALO_2", "ALBO_2", "BOAL_2");
		final TestContext context = new TestContext(encodedAddresses);

		// Act:
		final String bestAddress = context.generate("ALPHA", 3);

		// Assert:
		Assert.assertThat(bestAddress, IsEqual.equalTo("ALBO_2"));
	}

	@Test
	public void generatorShortCircuitsWhenFullMatchIsMade() {
		// Arrange:
		final List<String> encodedAddresses = Arrays.asList("BALO_2", "ALPHA_5");
		final TestContext context = new TestContext(encodedAddresses);

		// Act: (if short-circuiting does not occur, there will be an out of bounds exception)
		final String bestAddress = context.generate("ALPHA", 1000);

		// Assert:
		Assert.assertThat(bestAddress, IsEqual.equalTo("ALPHA_5"));
	}

	private static class TestContext {
		private final VanityAddressGenerator generator;
		private final Map<KeyPair, Address> keyPairAddressMap;

		public TestContext(final List<String> encodedAddresses) {
			final List<KeyPair> keys = encodedAddresses.stream()
					.map(encodedAddress -> KeyPair.random())
					.collect(Collectors.toList());

			this.keyPairAddressMap = new HashMap<>();
			for (int i = 0; i < encodedAddresses.size(); ++i) {
				this.keyPairAddressMap.put(keys.get(i), Address.fromEncoded(encodedAddresses.get(i)));
			}

			final int i[] = new int[] { 0 };
			this.generator = new VanityAddressGenerator(
					() -> keys.get(i[0]++),
					this.keyPairAddressMap::get);
		}

		private String generate(final String pattern, final int maxAttempts) {
			final KeyPair bestKeyPair = this.generator.generate(pattern, maxAttempts);
			return this.keyPairAddressMap.get(bestKeyPair).toString();
		}
	}
}