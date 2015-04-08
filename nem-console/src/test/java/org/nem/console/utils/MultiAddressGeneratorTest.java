package org.nem.console.utils;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.console.models.AliasedKeyPair;
import org.nem.console.test.*;

import java.util.*;
import java.util.stream.Collectors;

public class MultiAddressGeneratorTest {

	//region invalid construction

	@Test
	public void cannotGenerateAddressWithInvalidPrefix() {
		// Assert:
		assertInvalidPrefixes("TA", "TJ", "TC"); // non-matching version
		assertInvalidPrefixes("TA", "Tb", "TC"); // non-base32 char
		assertInvalidPrefixes("TA", "TB ", "TC"); // space char
	}

	@Test
	public void cannotGenerateAddressesWithDuplicatePrefixes() {
		// Arrange:
		assertInvalidPrefixes("TA", "TB", "TA");
	}

	private static void assertInvalidPrefixes(final String... prefixes) {
		ExceptionAssert.assertThrows(
				v -> new MultiAddressGenerator(new TestObserver(), prefixes),
				IllegalArgumentException.class);
	}

	//endregion

	//region valid construction

	@Test
	public void canCreateSingleAddressGenerator() {
		// Act:
		final MultiAddressGenerator generator = new MultiAddressGenerator(new TestObserver(), new String[] { "TA" });

		// Assert:
		Assert.assertThat(generator.keyPairs().isEmpty(), IsEqual.equalTo(true));
		Assert.assertThat(generator.numIterations(), IsEqual.equalTo(0));
		Assert.assertThat(generator.numPrefixes(), IsEqual.equalTo(1));
		Assert.assertThat(generator.numPrefixesFound(), IsEqual.equalTo(0));
	}

	@Test
	public void canCreateMultiAddressGenerator() {
		// Arrange:
		final MultiAddressGenerator generator = new MultiAddressGenerator(new TestObserver(), new String[] { "TA", "TB", "TC" });

		// Assert:
		Assert.assertThat(generator.keyPairs().isEmpty(), IsEqual.equalTo(true));
		Assert.assertThat(generator.numIterations(), IsEqual.equalTo(0));
		Assert.assertThat(generator.numPrefixes(), IsEqual.equalTo(3));
		Assert.assertThat(generator.numPrefixesFound(), IsEqual.equalTo(0));
	}

	//endregion

	//region generate

	@Test
	public void observerIsCalledForEachIteration() {
		// Arrange:
		final TestObserver observer = new TestObserver();
		final MultiAddressGenerator generator = new MultiAddressGenerator(observer, new String[] { "TA" });

		// Act:
		generator.generate();
		generator.generate();
		generator.generate();

		// Assert:
		Assert.assertThat(observer.iterations, IsEqual.equalTo(Arrays.asList(1, 2, 3)));
		Assert.assertThat(generator.numIterations(), IsEqual.equalTo(3));
	}

	@Test
	public void canGenerateSingleAddress() {
		// Arrange:
		final TestObserver observer = new TestObserver();
		final MultiAddressGenerator generator = new MultiAddressGenerator(observer, new String[] { "TA" });

		// Act:
		findAllPrefixes(generator);

		// Assert:
		Assert.assertThat(getPrefixes(generator.keyPairs()), IsEquivalent.equivalentTo(Collections.singletonList("TA")));
		Assert.assertThat(observer.aliases, IsEquivalent.equivalentTo(Collections.singletonList("TA")));

		Assert.assertThat(generator.numIterations() > 0, IsEqual.equalTo(true));
		Assert.assertThat(generator.numPrefixes(), IsEqual.equalTo(1));
		Assert.assertThat(generator.numPrefixesFound(), IsEqual.equalTo(1));
	}

	@Test
	public void canGenerateMultipleAddresses() {
		// Arrange:
		final TestObserver observer = new TestObserver();
		final MultiAddressGenerator generator = new MultiAddressGenerator(observer, new String[] { "TA", "TB", "TC" });

		// Act:
		findAllPrefixes(generator);

		// Assert: note that the observer can be called multiple times per alias
		Assert.assertThat(getPrefixes(generator.keyPairs()), IsEquivalent.equivalentTo(Arrays.asList("TA", "TB", "TC")));
		Assert.assertThat(new HashSet<>(observer.aliases), IsEquivalent.equivalentTo(Arrays.asList("TA", "TB", "TC")));

		Assert.assertThat(generator.numIterations() > 0, IsEqual.equalTo(true));
		Assert.assertThat(generator.numPrefixes(), IsEqual.equalTo(3));
		Assert.assertThat(generator.numPrefixesFound(), IsEqual.equalTo(3));
	}

	//endregion

	private static void findAllPrefixes(final MultiAddressGenerator generator) {
		while (generator.numPrefixes() != generator.numPrefixesFound()) {
			generator.generate();
		}
	}

	private static Collection<String> getPrefixes(final Collection<AliasedKeyPair> keyPairs) {
		return keyPairs.stream()
				.map(kp -> kp.address().getEncoded().substring(0, 2))
				.collect(Collectors.toList());
	}

	private static class TestObserver implements MultiAddressGeneratorObserver {
		private final List<Integer> iterations = new ArrayList<>();
		private final List<String> aliases = new ArrayList<>();

		@Override
		public void notifyIteration(final MultiAddressGenerator source, final int iteration) {
			this.iterations.add(iteration);
		}

		@Override
		public void notifyAddressFound(final MultiAddressGenerator source, final String alias) {
			this.aliases.add(alias);
		}
	}
}