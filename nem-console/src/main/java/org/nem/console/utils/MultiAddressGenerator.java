package org.nem.console.utils;

import org.nem.console.models.AliasedKeyPair;
import org.nem.core.crypto.*;
import org.nem.core.model.*;
import org.nem.core.utils.Base32Encoder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MultiAddressGenerator {
	private final MultiAddressGeneratorObserver observer;
	private final KeyGenerator generator = CryptoEngines.defaultEngine().createKeyGenerator();
	private final Map<String, KeyPair> generatedKeys = new HashMap<>();
	private final AtomicInteger iterations = new AtomicInteger(0);
	private final Object lock = new Object();

	public MultiAddressGenerator(final MultiAddressGeneratorObserver observer, final String[] prefixes) {
		this.observer = observer;
		for (final String prefix : prefixes) {
			if (!isValidAddressPrefix(prefix)) {
				throw new IllegalArgumentException(String.format("address prefix '%s' is invalid", prefix));
			}

			if (this.generatedKeys.containsKey(prefix)) {
				throw new IllegalArgumentException(String.format("address prefix '%s' was not unique", prefix));
			}

			this.generatedKeys.put(prefix, null);
		}
	}

	private static boolean isValidAddressPrefix(final String prefix) {
		final byte[] encodedBytes;

		try {
			encodedBytes = Base32Encoder.getBytes(prefix);
		} catch (final IllegalArgumentException e) {
			return false;
		}

		return NetworkInfos.getDefault().getVersion() == encodedBytes[0];
	}

	public void generate() {
		this.observer.notifyIteration(this, this.iterations.incrementAndGet());

		final KeyPair keyPair = this.generator.generateKeyPair();
		final String address = Address.fromPublicKey(keyPair.getPublicKey()).getEncoded();
		this.generatedKeys.keySet().stream()
				.filter(address::startsWith)
				.forEach(prefix -> {
					synchronized (this.lock) {
						this.generatedKeys.put(prefix, keyPair);
						this.observer.notifyAddressFound(this, prefix);
					}
				});
	}

	public int numIterations() {
		return this.iterations.intValue();
	}

	public int numPrefixes() {
		return this.generatedKeys.size();
	}

	public int numPrefixesFound() {
		int numFound = 0;
		for (final KeyPair keyPair : this.generatedKeys.values()) {
			numFound += null == keyPair ? 0 : 1;
		}

		return numFound;
	}

	public List<AliasedKeyPair> keyPairs() {
		return this.generatedKeys.entrySet().stream()
				.map(entry -> new AliasedKeyPair(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}
}
