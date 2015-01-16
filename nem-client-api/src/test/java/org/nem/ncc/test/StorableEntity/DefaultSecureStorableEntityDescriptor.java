package org.nem.ncc.test.StorableEntity;

import org.nem.ncc.storable.entity.*;
import org.nem.ncc.storable.entity.storage.SecureStorableEntityDescriptor;

// TODO 20150115 J-B: i'm confused by why you need these default implementations?
// > applies to most everything in this package
// TODO 20150116 BR -> J: since the test classes are not abstract I need it (didn't get it to work otherwise)
// > if I make the storable entity classes all abstract then i will make the test classes abstract too, so we can drop this default implementation.

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
