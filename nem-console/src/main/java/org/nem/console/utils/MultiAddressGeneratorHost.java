package org.nem.console.utils;

import org.nem.console.models.AliasedKeyPair;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Helper class for generating multiple vanity addresses that uses all cores.
 */
public class MultiAddressGeneratorHost {

	/**
	 * Generates a collection of key pairs such that each address in the returned list
	 * has a prefix in prefixes.
	 *
	 * @param prefixes The desired address prefixes.
	 * @return The key pairs.
	 */
	public static List<AliasedKeyPair> generate(final String[] prefixes) {
		final MultiAddressGenerator generator = createGenerator(prefixes);

		final int numThreads = Runtime.getRuntime().availableProcessors();
		final CompletableFuture[] futures = new CompletableFuture[numThreads];

		System.out.println(String.format("Generating addresses on %d threads", numThreads));
		for (int i = 0; i < numThreads; ++i) {
			futures[i] = CompletableFuture.runAsync(() -> {
				while (generator.numPrefixes() > generator.numPrefixesFound()) {
					generator.generate();
				}
			});
		}

		CompletableFuture.allOf(futures).join();
		final List<AliasedKeyPair> keyPairs = generator.keyPairs();
		for (final AliasedKeyPair keyPair : keyPairs) {
			System.out.println(String.format("'%s' -> '%s'", keyPair.alias(), keyPair.address()));
		}

		return keyPairs;
	}

	private static MultiAddressGenerator createGenerator(final String[] prefixes) {
		return new MultiAddressGenerator(new Observer(), prefixes);
	}

	private static class Observer implements MultiAddressGeneratorObserver {
		private long startTime = System.currentTimeMillis();

		@Override
		public void notifyIteration(final MultiAddressGenerator source, final int iteration) {
			if (0 == iteration % 100000) {
				final long endTime = System.currentTimeMillis();

				final String statusMessage = String.format(
						"iteration %d (%d/%d) (%s ms)",
						source.numIterations(),
						source.numPrefixesFound(),
						source.numPrefixes(),
						endTime - this.startTime);
				System.out.println(statusMessage);

				this.startTime = endTime;
			}
		}

		@Override
		public void notifyAddressFound(final MultiAddressGenerator source, final String alias) {
			final String statusMessage = String.format(
					"alias '%s' found at %d",
					alias,
					source.numIterations());
			System.out.println(statusMessage);
		}
	}
}
