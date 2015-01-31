package org.nem.ncc.wallet;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.StorableEntityNameTest;
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

	@Override
	protected String getEntityNameLabel() {
		return "wallet";
	}

	@Test
	public void canRoundtripName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		WalletName.writeTo(serializer, this.getEntityNameLabel(), this.createEntityName("foo"));
		final WalletName name = WalletName.readFrom(Utils.createDeserializer(serializer.getObject()), this.getEntityNameLabel());

		// Assert:
		Assert.assertThat(name, IsEqual.equalTo(this.createEntityName("foo")));
	}
}