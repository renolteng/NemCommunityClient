package org.nem.ncc.controller.requests;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.serialization.*;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.test.*;

import java.util.*;
import java.util.function.*;

public class AddressBookNamePasswordBagTest {

	//region serialization

	@Test
	public void bagCanBeDeserializedWithAllRequiredParameters() {
		// Act:
		final AddressBookNamePasswordBag bag = createBagFromJson("name", "password");

		// Assert:
		Assert.assertThat(bag.getName(), IsEqual.equalTo(new AddressBookName("name")));
		Assert.assertThat(bag.getPassword(), IsEqual.equalTo(new AddressBookPassword("password")));
	}

	@Test
	public void bagCannotBeDeserializedWithMissingRequiredParameters() {
		// Arrange:
		final List<Consumer<Void>> actions = Arrays.asList(
				v -> createBagFromJson(null, "pass"),
				v -> createBagFromJson("ab", null));

		// Assert:
		for (final Consumer<Void> action : actions) {
			ExceptionAssert.assertThrows(
					v -> action.accept(null),
					SerializationException.class);
		}
	}

	private static JSONObject createJson(final String name, final String password) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("addressBook", name);
		jsonObject.put("password", password);
		return jsonObject;
	}

	private static AddressBookNamePasswordBag createBagFromJson(final String name, final String password) {
		return new AddressBookNamePasswordBag(Utils.createDeserializer(createJson(name, password)));
	}

	//endregion

	//region getNewName

	@Test
	public void getNewNameFailsIfNameIsNotSpecified() {
		// Act:
		final AddressBookNamePasswordBag bag = createBagFromJson("name", "password");

		// Assert:
		assertThrowsMissingPropertyException(bag::getNewName, "new_name");
	}

	@Test
	public void getNewNameReturnsNameIfSpecified() {
		// Arrange:
		final JSONObject jsonObject = createJson("name", "password");
		jsonObject.put("new_name", "blah");

		// Act:
		final AddressBookNamePasswordBag bag = new AddressBookNamePasswordBag(Utils.createDeserializer(jsonObject));

		// Assert:
		Assert.assertThat(bag.getNewName(), IsEqual.equalTo(new AddressBookName("blah")));
	}

	//endregion

	//region getNewPassword

	@Test
	public void getNewPasswordFailsIfPasswordIsNotSpecified() {
		// Act:
		final AddressBookNamePasswordBag bag = createBagFromJson("name", "password");

		// Assert:
		assertThrowsMissingPropertyException(bag::getNewPassword, "new_password");
	}

	@Test
	public void getNewPasswordReturnsPasswordIfSpecified() {
		// Arrange:
		final JSONObject jsonObject = createJson("name", "password");
		jsonObject.put("new_password", "pwd1");

		// Act:
		final AddressBookNamePasswordBag bag = new AddressBookNamePasswordBag(Utils.createDeserializer(jsonObject));

		// Assert:
		Assert.assertThat(bag.getNewPassword(), IsEqual.equalTo(new AddressBookPassword("pwd1")));
	}

	//endregion

	private static void assertThrowsMissingPropertyException(final Supplier<Object> consumer, final String propertyName) {
		ExceptionAssert.assertThrows(
				v -> consumer.get(),
				MissingRequiredPropertyException.class,
				ex -> Assert.assertThat(ex.getPropertyName(), IsEqual.equalTo(propertyName)));
	}
}
