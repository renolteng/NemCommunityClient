package org.nem.console.utils;

import org.nem.console.models.AliasedKeyPair;
import org.nem.core.crypto.*;
import org.nem.core.model.Address;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiAddressGenerator {
	private final KeyGenerator generator = CryptoEngines.defaultEngine().createKeyGenerator();
	private final Map<String, KeyPair> generatedKeys = new HashMap<>();
	private final AtomicInteger iterations = new AtomicInteger(0);
	private final Object lock = new Object();

	public MultiAddressGenerator(final String[] prefixes) {
		for (final String prefix : prefixes) {
			this.generatedKeys.put(prefix, null);
		}
	}

	public void generate() {
		this.iterations.incrementAndGet();

		final KeyPair keyPair = this.generator.generateKeyPair();
		final String address = Address.fromPublicKey(keyPair.getPublicKey()).getEncoded();
		for (final String prefix : this.generatedKeys.keySet()) {
			if (address.startsWith(prefix)) {
				synchronized (this.lock) {
					this.generatedKeys.put(prefix, keyPair);
				}
			}
		}
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
		final List<AliasedKeyPair> keyPairs = new ArrayList<>();
		for (final Map.Entry<String, KeyPair> entry : this.generatedKeys.entrySet()) {
			keyPairs.add(new AliasedKeyPair(entry.getKey(), entry.getValue()));
		}

		return keyPairs;
	}
}
