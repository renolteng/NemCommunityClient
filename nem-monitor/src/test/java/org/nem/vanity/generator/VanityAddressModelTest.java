package org.nem.vanity.generator;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import org.hamcrest.core.IsEqual;
import org.junit.*;

public class VanityAddressModelTest {

	@Test
	public void constructor() {
		// Arrange:

		// Act:
		VanityAddressModel model = new VanityAddressModel();

		// Assert:
		Assert.assertThat(model.getSize(), IsEqual.equalTo(0));
	}

	@Test
	public void addNewAddressIsAddedToList() {
		// Arrange:
		VanityAddressModel model = new VanityAddressModel();
		KeyPair keyPair = new KeyPair();
		String address = Address.fromPublicKey(keyPair.getPublicKey()).getEncoded();

		// Act:
		model.addressFound(address, keyPair.getPrivateKey());

		// Assert:
		Assert.assertThat(model.getSize(), IsEqual.equalTo(1));
		Assert.assertThat(model.getElementAt(0), IsEqual.equalTo(address));
	}

	@Test
	public void checkClipboardFormat() {
		// Arrange:
		VanityAddressModel model = new VanityAddressModel();
		KeyPair keyPair1 = new KeyPair();
		String address1 = Address.fromPublicKey(keyPair1.getPublicKey()).getEncoded();
		VanityAddress vAddress1 = new VanityAddress(address1, keyPair1.getPrivateKey());
		KeyPair keyPair2 = new KeyPair();
		String address2 = Address.fromPublicKey(keyPair2.getPublicKey()).getEncoded();
		VanityAddress vAddress2 = new VanityAddress(address2, keyPair2.getPrivateKey());

		// Act:
		model.addressFound(address1, keyPair1.getPrivateKey());
		model.addressFound(address2, keyPair2.getPrivateKey());

		// Assert:
		Assert.assertThat(model.getSize(), IsEqual.equalTo(2));
		Assert.assertThat(model.getElementAt(0).getClipboardFormat(), IsEqual.equalTo(vAddress1.getClipboardFormat()));
		Assert.assertThat(model.getElementAt(1).getClipboardFormat(), IsEqual.equalTo(vAddress2.getClipboardFormat()));
	}
}
