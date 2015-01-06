package org.nem.ncc.addressbook;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.storable.entity.StorableEntityPasswordTest;
import org.nem.ncc.test.Utils;

public class AddressBookPasswordTest extends StorableEntityPasswordTest {

	@Override
	protected AddressBookPassword createEntityPassword(final String name) {
		return new AddressBookPassword(name);
	}

	@Test
	public void canRoundtripRequiredPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		AddressBookPassword.writeTo(serializer, "abp", new AddressBookPassword("foo"));
		final AddressBookPassword password = AddressBookPassword.readFrom(Utils.createDeserializer(serializer.getObject()), "abp");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(new AddressBookPassword("foo")));
	}

	@Test
	public void canRoundtripOptionalPassword() {
		// Arrange:
		final JsonSerializer serializer = new JsonSerializer();
		AddressBookPassword.writeTo(serializer, "abp", new AddressBookPassword("foo"));
		final AddressBookPassword password = AddressBookPassword.readFromOptional(
				Utils.createDeserializer(serializer.getObject()), "abp");

		// Assert:
		Assert.assertThat(password, IsEqual.equalTo(new AddressBookPassword("foo")));
	}

	@Test
	public void canDeserializeNullOptionalPassword() {
		// Arrange:
		final AddressBookPassword password = AddressBookPassword.readFromOptional(Utils.createDeserializer(new JSONObject()), "abp");

		// Assert:
		Assert.assertThat(password, IsNull.nullValue());
	}

	@Test(expected = MissingRequiredPropertyException.class)
	public void cannotDeserializeNullRequiredPassword() {
		// Act:
		AddressBookPassword.readFrom(Utils.createDeserializer(new JSONObject()), "abp");
	}

	//endregion
}
