package org.nem.ncc.addressbook;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.StorableEntityNameTest;
import org.nem.ncc.test.Utils;

public class AddressBookNameTest extends StorableEntityNameTest {

	@Override
	protected AddressBookName createEntityName(final String name) {
		return new AddressBookName(name);
	}

	@Override
	protected AddressBookName createEntityName(final Deserializer deserializer) {
		return new AddressBookName(deserializer);
	}

	@Override
	protected String getEntityNameLabel() {
		return "addressBook";
	}

	@Test
	public void canRoundtripName() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		AddressBookName.writeTo(serializer, this.getEntityNameLabel(), this.createEntityName("foo"));
		final AddressBookName name = AddressBookName.readFrom(Utils.createDeserializer(serializer.getObject()), this.getEntityNameLabel());

		// Assert:
		Assert.assertThat(name, IsEqual.equalTo(this.createEntityName("foo")));
	}
}
