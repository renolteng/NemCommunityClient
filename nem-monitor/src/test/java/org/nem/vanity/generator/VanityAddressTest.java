package org.nem.vanity.generator;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class VanityAddressTest {

	@Test
	public void constructor() {
		// Arrange:
		KeyPair keyPair = new KeyPair();
		Address address = Address.fromPublicKey(keyPair.getPublicKey());
		// Act:
		VanityAddress vAddress = new VanityAddress(address.getEncoded(), keyPair.getPrivateKey(), "", (byte)0x01);

		// Assert:
		Assert.assertThat(vAddress.getPrivateKey(), IsEqual.equalTo(keyPair.getPrivateKey().toString()));
	}
}
