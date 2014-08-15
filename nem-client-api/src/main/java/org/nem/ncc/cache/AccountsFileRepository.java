package org.nem.ncc.cache;

import net.minidev.json.*;
import org.apache.commons.io.IOUtils;
import org.nem.core.model.ncc.AccountInfo;
import org.nem.core.serialization.*;
import org.nem.core.utils.ExceptionUtils;
import org.nem.ncc.exceptions.NccException;

import java.io.*;
import java.util.*;

/**
 * A JSON-file based accounts repository.
 */
public class AccountsFileRepository implements AccountsRepository {
	private final AccountsFileDescriptor descriptor;

	/**
	 * Creates a new accounts file repository.
	 *
	 * @param descriptor The descriptor.
	 */
	public AccountsFileRepository(final AccountsFileDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public void save(final Collection<AccountInfo> accounts) {
		ExceptionUtils.propagateVoid(() -> {
			try (final OutputStream os = this.descriptor.openWrite()) {
				os.write(JsonSerializer.serializeToBytes(new SerializableList<>(accounts)));
			}
		}, ex -> new NccException(NccException.Code.ACCOUNT_CACHE_ERROR));
	}

	@Override
	public Collection<AccountInfo> load() {
		return ExceptionUtils.propagate(() -> {
			Collection<AccountInfo> accounts = new ArrayList<>();
			try (final InputStream is = this.descriptor.openRead()) {
				final byte[] contents = IOUtils.toByteArray(is);
				if (0 != contents.length) {
					final JSONObject jsonObject = (JSONObject)JSONValue.parse(contents);
					// fix ::new
					accounts = new SerializableList<>(new JsonDeserializer(jsonObject, null), AccountInfo::new).asCollection();
				}
			}

			return accounts;
		}, ex -> new NccException(NccException.Code.ACCOUNT_CACHE_ERROR));
	}
}
