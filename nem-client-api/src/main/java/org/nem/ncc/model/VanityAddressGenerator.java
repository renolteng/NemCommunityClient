package org.nem.ncc.model;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import java.util.function.*;

/**
 * Helper class for generating vanity addresses.
 */
public class VanityAddressGenerator {
	private final Supplier<KeyPair> generateKeyPair;
	private final Function<KeyPair, Address> getAddressFromKeyPair;

	/**
	 * Creates a vanity generator.
	 *
	 * @param generateKeyPair The function that should be called to generate a new key pair.
	 * @param getAddressFromKeyPair The function that should be called to map a key pair to an address.
	 */
	public VanityAddressGenerator(
			final Supplier<KeyPair> generateKeyPair,
			final Function<KeyPair, Address> getAddressFromKeyPair) {
		this.generateKeyPair = generateKeyPair;
		this.getAddressFromKeyPair = getAddressFromKeyPair;
	}

	/**
	 * Generates a vanity key pair.
	 *
	 * @param pattern The desired vanity pattern.
	 * @param maxAttempts The maximum number of generation attempts.
	 * @return The best key pair.
	 */
	public KeyPair generate(final String pattern, final int maxAttempts) {
		final VanityAddressSelector selector = new VanityAddressSelector(pattern);
		for (int i = 0; i < maxAttempts; ++i) {
			final KeyPair keyPair = this.generateKeyPair.get();
			final Address address = this.getAddressFromKeyPair.apply(keyPair);
			selector.addCandidate(keyPair, address);

			if (selector.hasCompleteMatch()) {
				break;
			}
		}

		return selector.getBestKeyPair();
	}

	private static class VanityAddressSelector {
		private final String pattern;
		private KeyPair bestKeyPair;
		private int bestMatchIndex = Integer.MAX_VALUE;
		private int bestMatchLength;

		public VanityAddressSelector(final String pattern) {
			this.pattern = pattern;
		}

		public boolean hasCompleteMatch() {
			return this.pattern.length() == this.bestMatchLength;
		}

		public KeyPair getBestKeyPair() {
			return this.bestKeyPair;
		}

		public void addCandidate(final KeyPair keyPair, final Address address) {
			int matchIndex = -1;
			int matchLength = 0;

			final String encodedAddress = address.toString();
			for (int i = this.pattern.length(); i >= this.bestMatchLength; --i) {
				final String subPattern = this.pattern.substring(0, i);
				matchIndex = encodedAddress.indexOf(subPattern);
				if (matchIndex >= 0) {
					matchLength = subPattern.length();
					break;
				}
			}

			if (matchLength < this.bestMatchLength || (matchLength == this.bestMatchLength && matchIndex >= this.bestMatchIndex)) {
				return;
			}

			this.bestKeyPair = keyPair;
			this.bestMatchIndex = matchIndex;
			this.bestMatchLength = matchLength;
		}
	}
}
