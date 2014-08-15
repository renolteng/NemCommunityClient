package org.nem.ncc.wallet;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.serialization.*;
import org.nem.core.utils.*;
import org.nem.ncc.wallet.storage.*;

import java.math.BigInteger;

/**
 * Repository that loads V1 wallet files.
 */
public class LegacyWalletRepository implements WalletRepository {
	private final static ObjectDeserializer<WalletAccount> DESERIALIZER = deserializer ->
			new WalletAccount(new PrivateKey(new BigInteger(1, Base64Encoder.getBytes(deserializer.readString("key")))));

	@Override
	public void save(final WalletDescriptor descriptor, final Wallet wallet) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Wallet load(final WalletDescriptor descriptor) {
		return ExceptionUtils.propagate(() -> {
			final JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
			final Object object = parser.parse(descriptor.openRead());
			if (!(object instanceof JSONObject)) {
				throw new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_READ);
			}

			return loadWalletFromJson((JSONObject)object);
		}, ex -> new WalletStorageException(WalletStorageException.Code.WALLET_COULD_NOT_BE_READ, ex));
	}

	private static Wallet loadWalletFromJson(final JSONObject jsonObject) {
		final JsonDeserializer deserializer = new JsonDeserializer(jsonObject, null);
		return new MemoryWallet(
				WalletName.readFrom(deserializer, "name"),
				deserializer.readObject("primaryAccount", LegacyWalletRepository.DESERIALIZER),
				deserializer.readObjectArray("additionalAccounts", LegacyWalletRepository.DESERIALIZER));
	}
}