package org.nem.ncc.wallet;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.*;
import org.nem.ncc.test.Utils;

public class WalletNameTest extends StorableEntityNameTest {

	@Override
	protected WalletName createEntityName(final String name) {
		return new WalletName(name);
	}

	@Override
	protected WalletName createEntityName(final Deserializer deserializer) {
		return new WalletName(deserializer);
	}

	@Test
	public void canRoundtripName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		WalletName.writeTo(serializer, "name", this.createEntityName("foo"));
		final WalletName name = WalletName.readFrom(Utils.createDeserializer(serializer.getObject()), "name");

		// Assert:
		Assert.assertThat(name, IsEqual.equalTo(this.createEntityName("foo")));
	}

}