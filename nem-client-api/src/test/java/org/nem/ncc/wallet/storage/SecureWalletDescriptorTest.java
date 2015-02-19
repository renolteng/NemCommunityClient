package org.nem.ncc.wallet.storage;

import org.mockito.Mockito;
import org.nem.ncc.storable.entity.storage.*;
import org.nem.ncc.wallet.WalletPassword;

public class SecureWalletDescriptorTest extends SecureStorableEntityDescriptorTest<
		WalletDescriptor,
		SecureWalletDescriptor> {

	@Override
	protected WalletDescriptor createDescriptor() {
		return Mockito.mock(WalletDescriptor.class);
	}

	@Override
	protected SecureWalletDescriptor createSecureDescriptor(final WalletDescriptor descriptor, final String password) {
		return new SecureWalletDescriptor(descriptor, new WalletPassword(password));
	}

	@Override
	protected Class<? extends StorableEntityStorageException> getExceptionClass() {
		return WalletStorageException.class;
	}

	@Override
	protected Integer getExceptionValue(final Integer originalValue) {
		return originalValue;
	}
}