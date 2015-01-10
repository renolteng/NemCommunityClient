package org.nem.ncc.controller;

import net.minidev.json.JSONObject;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.model.Address;
import org.nem.ncc.addressbook.*;
import org.nem.ncc.controller.requests.AddressBookNamePasswordBag;
import org.nem.ncc.controller.viewmodels.AddressBookViewModel;
import org.nem.ncc.services.*;
import org.nem.ncc.test.Utils;

public class AddressBookAccountLabelControllerTest {

	@Test
	public void addAccountLabelDelegatesToServices() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = this.createJsonObject(address, "", "sister");
		final AccountLabel accountLabel = new AccountLabel(address, "", "sister");
		final TestContext context = new TestContext(jsonObject);

		// Act:
		final AccountLabel result = context.controller.addAccountLabel(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(accountLabel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.addressBook, Mockito.times(1)).addLabel(Mockito.eq(accountLabel));
	}

	@Test
	public void removeAccountLabelDelegatesToServices() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = this.createJsonObject(address, null, null);
		final TestContext context = new TestContext(jsonObject);
		final AddressBookViewModel addressBookViewModel = Mockito.mock(AddressBookViewModel.class);
		Mockito.when(context.addressBookMapper.toViewModel(context.addressBook)).thenReturn(addressBookViewModel);

		// Act:
		final AddressBookViewModel result = context.controller.removeAccountLabel(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(addressBookViewModel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.addressBook, Mockito.times(1)).removeLabel(address);
		Mockito.verify(context.addressBookMapper, Mockito.times(1)).toViewModel(context.addressBook);
	}

	@Test
	public void changeAccountLabelDelegatesToServices() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = this.createJsonObject(address, "", "brother");
		final AccountLabel accountLabel = new AccountLabel(address, "", "brother");
		final TestContext context = new TestContext(jsonObject);
		context.mockAddressBook();

		// Act:
		final AccountLabel result = context.controller.changeAccountLabel(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(accountLabel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.addressBook, Mockito.times(1)).setLabel(Mockito.eq(address), Mockito.eq(""), Mockito.eq("brother"));
	}

	@Test
	public void findAccountLabelDelegatesToServices() {
		// Arrange:
		final Address address = Utils.generateRandomAddress();
		final JSONObject jsonObject = this.createJsonObject(address, "", "brother");
		final AccountLabel accountLabel = new AccountLabel(address, "", "brother");
		final TestContext context = new TestContext(jsonObject);
		context.mockAddressBook();

		// Act:
		final AccountLabel result = context.controller.findAccountLabel(context.bag);

		// Assert:
		Assert.assertThat(result, IsEqual.equalTo(accountLabel));
		Mockito.verify(context.addressBookServices, Mockito.times(1)).open(context.bag);
		Mockito.verify(context.addressBook, Mockito.times(1)).getLabel(Mockito.eq(address));
	}

	private JSONObject createJsonObject(
			final Address address,
			final String publicLabel,
			final String privateLabel) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("addressBook", "n");
		jsonObject.put("password", "p");
		jsonObject.put("address", address.getEncoded());
		if (null != publicLabel) {
			jsonObject.put("publicLabel", publicLabel);
		}
		if (null != privateLabel) {
			jsonObject.put("privateLabel", privateLabel);
		}
		return jsonObject;
	}

	private static class TestContext {
		private final AddressBookServices addressBookServices = Mockito.mock(AddressBookServices.class);
		private final AddressBookMapper addressBookMapper = Mockito.mock(AddressBookMapper.class);
		private final AddressBookAccountLabelController controller
				= new AddressBookAccountLabelController(this.addressBookServices, this.addressBookMapper);

		private final AddressBook addressBook = Mockito.mock(AddressBook.class);
		private final AddressBookNamePasswordBag bag;

		private TestContext(final JSONObject jsonObject) {
			this.bag = new AddressBookNamePasswordBag(Utils.createDeserializer(jsonObject));
			Mockito.when(this.addressBookServices.open(this.bag)).thenReturn(this.addressBook);
		}

		private void mockAddressBook() {
			Mockito.when(this.addressBook.getLabel(this.bag.getAddress()))
					.thenReturn(new AccountLabel(this.bag.getAddress(), "", this.bag.getPrivateLabel()));
		}
	}
}
