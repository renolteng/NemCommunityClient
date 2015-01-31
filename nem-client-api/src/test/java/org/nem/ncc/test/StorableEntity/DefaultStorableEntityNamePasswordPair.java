package org.nem.ncc.test.StorableEntity;

import org.nem.ncc.storable.entity.*;

public class DefaultStorableEntityNamePasswordPair extends StorableEntityNamePasswordPair<StorableEntityName, StorableEntityPassword, DefaultStorableEntityNamePasswordPair> {
	public DefaultStorableEntityNamePasswordPair(
			final StorableEntityName name,
			final StorableEntityPassword password) {
		super(name, password, DefaultStorableEntityNamePasswordPair.class);
	}
}
