package org.nem.console.utils;

import org.junit.*;
import org.nem.console.models.AliasedKeyPair;
import org.nem.console.test.IsEquivalent;

import java.util.Collection;
import java.util.stream.Collectors;

public class MultiAddressGeneratorHostTest {

	@Test
	public void generateGeneratesAddressesMatchingEachPrefix() {
		// Act:
		final String[] desiredPrefixes = new String[] { "TA", "TB", "TC", "TD" };
		final Collection<AliasedKeyPair> keyPairs = MultiAddressGeneratorHost.generate(desiredPrefixes);
		final Collection<String> actualPrefixes = keyPairs.stream()
				.map(kp -> kp.address().getEncoded().substring(0, 2))
				.collect(Collectors.toList());

		// Assert:
		Assert.assertThat(actualPrefixes, IsEquivalent.equivalentTo(desiredPrefixes));
	}
}