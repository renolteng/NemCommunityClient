package org.nem.ncc.test.StorableEntity;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.SecureStorableEntityDescriptor;

public class DefaultSecureStorableEntityDescriptor
	extends SecureStorableEntityDescriptor<
			DefaultStorableEntity,
		StorableEntityName,
		StorableEntityFileExtension,
			StorableEntityPassword,
			DefaultStorableEntityFileDescriptor> {

	public DefaultSecureStorableEntityDescriptor(
			final DefaultStorableEntityFileDescriptor descriptor,
			final StorableEntityPassword password) {
		super(descriptor, password);
	}
}
