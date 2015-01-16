package org.nem.ncc.test.StorableEntity;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.SecureStorableEntityDescriptor;

// TODO 20150115 J-B: i'm confused by why you need these default implementations?
// > applies to most everything in this package

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
