package org.nem.ncc.controller;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.serialization.SerializableList;
import org.nem.ncc.test.IsEquivalent;
import org.nem.ncc.wallet.*;
import org.nem.ncc.wallet.storage.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class WalletsControllerTest {

	@Test
	public void getAllWalletsDelegatesToLocator() {
		// Arrange:
		final WalletLocator locator = Mockito.mock(WalletLocator.class);
		Mockito.when(locator.getAllWallets()).thenReturn(createMockWalletDescriptors());
		final WalletsController controller = new WalletsController(locator);

		// Act:
		final SerializableList<WalletDescriptor> descriptors = controller.getAllWallets();

		// Assert:
		final List<WalletName> expectedNames = Arrays.asList("alpha", "zeta", "sigma").stream()
				.map(WalletName::new)
				.collect(Collectors.toList());
		Assert.assertThat(
				descriptors.asCollection().stream().map(WalletDescriptor::getWalletName).collect(Collectors.toList()),
				IsEquivalent.equivalentTo(expectedNames));
		Assert.assertThat(descriptors.getLabel(), IsEqual.equalTo("wallets"));
	}

	private static List<WalletDescriptor> createMockWalletDescriptors() {
		return Arrays.asList(
				new WalletFileDescriptor(new File("alpha.wlt")),
				new WalletFileDescriptor(new File("zeta.wlt")),
				new WalletFileDescriptor(new File("sigma.wlt")));
	}
}