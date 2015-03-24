package org.nem.console.models;

import org.nem.core.model.Account;

import java.util.List;

public class AliasedKeyPairs {
	private final List<AliasedKeyPair> keyPairs;

	public AliasedKeyPairs(final List<AliasedKeyPair> keyPairs) {
		this.keyPairs = keyPairs;
	}

	public Account findByAlias(final String alias) {
		for (final AliasedKeyPair keyPair : this.keyPairs) {
			if (keyPair.alias().equals(alias)) {
				return new Account(keyPair.keyPair());
			}
		}

		throw new IllegalArgumentException(String.format("no know key pair for alias '%s'", alias));
	}
}
