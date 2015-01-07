package org.nem.ncc.controller;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.AddressBookNamePasswordBag;
import org.nem.ncc.controller.viewmodels.*;
import org.nem.ncc.services.*;
import org.nem.ncc.test.Utils;

public class AddressBookControllerTest {

	//region create / open / info / close

	@Test
	public void createDelegatesToAddressBookServicesAndMapper() {
		// Arrange:
		final AddressBookNamePasswordPair request = new AddressBookNamePasswordPair(new AddressBookName("adb"), new AddressBookPassword("pwd"));
		final AddressBook addressBook = Mockito.mock(AddressBook.class);
		final AddressBookViewModel addressBookViewModel = Mockito.mock(AddressBookViewModel.class);
		final TestContext context = new TestContext();
		Mockito.when(context.addressBookServices.create(request)).thenReturn(addressBook);
		Mockito.when(context.addressBookMapper.toViewModel(addressBook)).thenReturn(addressBookViewModel);

		// Act:
		final AddressBookViewModel result = context.controller.create(request);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(addressBookViewModel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).create(request);
		Mockito.verify(context.addressBookMapper, Mockito.times(1)).toViewModel(addressBook);
	}

	@Test
	public void openDelegatesToAddressBookServicesAndMapper() {
		// Arrange:
		final AddressBookNamePasswordPair request = new AddressBookNamePasswordPair(new AddressBookName("adb"), new AddressBookPassword("pwd"));
		final AddressBook addressBook = Mockito.mock(AddressBook.class);
		final AddressBookViewModel addressBookViewModel = Mockito.mock(AddressBookViewModel.class);
		final TestContext context = new TestContext();
		Mockito.when(context.addressBookServices.open(request)).thenReturn(addressBook);
		Mockito.when(context.addressBookMapper.toViewModel(addressBook)).thenReturn(addressBookViewModel);

		// Act:
		final AddressBookViewModel result = context.controller.open(request);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(addressBookViewModel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(request);
		Mockito.verify(context.addressBookMapper, Mockito.times(1)).toViewModel(addressBook);
	}

	@Test
	public void infoDelegatesToAddressBookServicesAndMapper() {
		// Arrange:
		final AddressBookName request = new AddressBookName("adb");
		final AddressBook addressBook = Mockito.mock(AddressBook.class);
		final AddressBookViewModel addressBookViewModel = Mockito.mock(AddressBookViewModel.class);
		final TestContext context = new TestContext();
		Mockito.when(context.addressBookServices.get(request)).thenReturn(addressBook);
		Mockito.when(context.addressBookMapper.toViewModel(addressBook)).thenReturn(addressBookViewModel);

		// Act:
		final AddressBookViewModel result = context.controller.info(request);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(addressBookViewModel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).get(request);
		Mockito.verify(context.addressBookMapper, Mockito.times(1)).toViewModel(addressBook);
	}

	@Test
	public void closeDelegatesToAddressBookServices() {
		// Arrange:
		final AddressBookName request = new AddressBookName("adb");
		final TestContext context = new TestContext();

		// Act:
		context.controller.close(request);

		// Assert:
		Mockito.verify(context.addressBookServices, Mockito.times(1)).close(request);
	}

	//endregion

	//region changePassword / changeAddressBookName

	@Test
	public void changePasswordDelegatesToAddressBookServices() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("addressBook", "ab1");
		jsonObject.put("password", "p1");
		jsonObject.put("newPassword", "p2");
		final AddressBookNamePasswordBag bag = new AddressBookNamePasswordBag(Utils.createDeserializer(jsonObject));
		final TestContext context = new TestContext();

		// Act:
		context.controller.changePassword(bag);

		// Assert:
		Mockito.verify(context.addressBookServices, Mockito.times(1))
				.move(new AddressBookNamePasswordPair("ab1", "p1"), new AddressBookNamePasswordPair("ab1", "p2"));
	}

	@Test
	public void changeNameDelegatesToAddressBookServices() {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("addressBook", "ab1");
		jsonObject.put("password", "p1");
		jsonObject.put("newName", "ab2");
		final AddressBookNamePasswordBag bag = new AddressBookNamePasswordBag(Utils.createDeserializer(jsonObject));
		final TestContext context = new TestContext();

		// Act:
		context.controller.changeName(bag);

		// Assert:
		Mockito.verify(context.addressBookServices, Mockito.times(1))
				.move(new AddressBookNamePasswordPair("ab1", "p1"), new AddressBookNamePasswordPair("ab2", "p1"));
	}

	//endregion

	private static class TestContext {
		private final AddressBookServices addressBookServices = Mockito.mock(AddressBookServices.class);
		private final AddressBookMapper addressBookMapper = Mockito.mock(AddressBookMapper.class);
		private final AddressBookController controller = new AddressBookController(this.addressBookServices, this.addressBookMapper);
	}
}
