package org.nem.ncc.model;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.NetworkInfo;
import org.nem.ncc.test.Utils;

public class KeyPairViewTest {

	@Test
	public void canCreateKeyPairView() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final byte version = NetworkInfo.getMainNetworkInfo().getVersion();

		// Assert:
		new KeyPairView(keyPair, version);
	}

	@Test
	public void keyPairViewCanBeRoundTripped() {
		// Arrange:
		final KeyPair keyPair = new KeyPair();
		final byte version = NetworkInfo.getMainNetworkInfo().getVersion();
		final KeyPairView originalKeyPairView = new KeyPairView(keyPair, version);

		// Act:
		final KeyPairView keyPairView = new KeyPairView(Utils.roundtripSerializableEntity(originalKeyPairView, null));

		// Assert:
		Assert.assertThat(keyPairView.getKeyPair().getPrivateKey(), IsEqual.equalTo(originalKeyPairView.getKeyPair().getPrivateKey()));
		Assert.assertThat(keyPairView.getKeyPair().getPublicKey(), IsEqual.equalTo(originalKeyPairView.getKeyPair().getPublicKey()));
		Assert.assertThat(keyPairView.getNetworkVersion(), IsEqual.equalTo(NetworkInfo.getMainNetworkInfo().getVersion()));
	}
}
