package org.nem.console.utils;

import org.nem.console.models.AliasedKeyPair;
import org.nem.core.crypto.KeyPair;

import java.util.List;
import java.util.concurrent.*;

public class MultiAddressGeneratorHost {

	public static List<AliasedKeyPair> generate(final String[] prefixes) {
		final MultiAddressGenerator generator = new MultiAddressGenerator(prefixes);

		final int numThreads = Thread.activeCount();
		final CompletableFuture[] futures = new CompletableFuture[numThreads];

		for (int i = 0; i < numThreads; ++i) {
			futures[i] = CompletableFuture.runAsync(() -> {
				while (generator.numPrefixes() > generator.numPrefixesFound()) {
					if (0 == generator.numIterations() % 10000) {
						final String statusMessage = String.format(
								"iteration %d (%d/%d)",
								generator.numIterations(),
								generator.numPrefixesFound(),
								generator.numPrefixes());
						System.out.println(statusMessage);
					}

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
}
