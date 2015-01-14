package org.nem.ncc.wallet.storage;

import org.mockito.Mockito;
import org.nem.ncc.storable.entity.storage.StorableEntityStorageException;
import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.wallet.WalletPassword;

public class SecureWalletDescriptorTest extends SecureStorableEntityDescriptorTest {

	@Override
	protected WalletDescriptor createDescriptor() {
		return Mockito.mock(WalletDescriptor.class);
	}

	@Override
	protected void createSecureDescriptor(final TestContext context) {
		final WalletDescriptor descriptor = this.createDescriptor();
		context.setDescriptor(descriptor);
		context.setSecureDescriptor(new SecureWalletDescriptor(descriptor, new WalletPassword(context.getPassword())));
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}
}