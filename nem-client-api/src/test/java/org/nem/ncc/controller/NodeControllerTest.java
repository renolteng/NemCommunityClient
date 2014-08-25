package org.nem.ncc.controller;

import net.minidev.json.*;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.core.connect.HttpPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.crypto.*;
import org.nem.core.model.Account;
import org.nem.ncc.connector.PrimaryNisConnector;
import org.nem.ncc.controller.requests.BootNodeRequest;
import org.nem.ncc.services.WalletServices;
import org.nem.ncc.wallet.*;

public class NodeControllerTest {

	@Test
	public void bootNodeDelegatesToNisConnector() {
		// Arrange:
		final TestContext context = new TestContext();
		final Wallet wallet = Mockito.mock(Wallet.class);
		final KeyPair keyPair = new KeyPair();
		final Account account = new Account(new KeyPair(keyPair.getPublicKey()));
		Mockito.when(context.walletServices.get(new WalletName("wal-blah"))).thenReturn(wallet);
		Mockito.when(wallet.getAccountPrivateKey(account.getAddress())).thenReturn(keyPair.getPrivateKey());

		// Act:
		context.controller.bootNode(new BootNodeRequest(account.getAddress(), new WalletName("wal-blah"), "node-bar"));

		// Assert:
		Mockito.verify(context.walletServices, Mockito.times(1)).get(new WalletName("wal-blah"));
		Mockito.verify(wallet, Mockito.times(1)).getAccountPrivateKey(account.getAddress());
		Mockito.verify(context.connector, Mockito.times(1)).voidPost(Mockito.eq(NisApiId.NIS_REST_NODE_BOOT), Mockito.any());
	}

	@Test
	public void bootNodeBuildsAppropriateRequest() {
		// Arrange:
		final TestContext context = new TestContext();
		final Wallet wallet = Mockito.mock(Wallet.class);
		final KeyPair keyPair = new KeyPair();
		final Account account = new Account(new KeyPair(keyPair.getPublicKey()));
		Mockito.when(context.walletServices.get(new WalletName("wal-blah"))).thenReturn(wallet);
		Mockito.when(wallet.getAccountPrivateKey(account.getAddress())).thenReturn(keyPair.getPrivateKey());

		// Act:
		context.controller.bootNode(new BootNodeRequest(account.getAddress(), new WalletName("wal-blah"), "node-bar"));

		final ArgumentCaptor<HttpPostRequest> requestCaptor = ArgumentCaptor.forClass(HttpPostRequest.class);
		Mockito.verify(context.connector, Mockito.times(1)).voidPost(Mockito.eq(NisApiId.NIS_REST_NODE_BOOT), requestCaptor.capture());
		final JSONObject jsonRequest = (JSONObject)JSONValue.parse(requestCaptor.getValue().getPayload());

		// Assert:
		Assert.assertThat(jsonRequest.size(), IsEqual.equalTo(3));
		assertBootRequestIdentity((JSONObject)jsonRequest.get("identity"), keyPair.getPrivateKey());
		assertBootRequestEndpoint((JSONObject)jsonRequest.get("endpoint"));
		assertBootRequestMetaData((JSONObject)jsonRequest.get("metaData"));
	}

	private static void assertBootRequestIdentity(final JSONObject jsonIdentity, final PrivateKey privateKey) {
		Assert.assertThat(jsonIdentity.size(), IsEqual.equalTo(2));
		Assert.assertThat(jsonIdentity.get("private-key"), IsEqual.equalTo(privateKey.toString()));
		Assert.assertThat(jsonIdentity.get("name"), IsEqual.equalTo("node-bar"));
	}

	private static void assertBootRequestEndpoint(final JSONObject jsonEndpoint) {
		Assert.assertThat(jsonEndpoint.size(), IsEqual.equalTo(3));
		Assert.assertThat(jsonEndpoint.get("protocol"), IsEqual.equalTo("http"));
		Assert.assertThat(jsonEndpoint.get("host"), IsEqual.equalTo("localhost"));
		Assert.assertThat(jsonEndpoint.get("port"), IsEqual.equalTo(7890));
	}

	private static void assertBootRequestMetaData(final JSONObject jsonMetaData) {
		Assert.assertThat(jsonMetaData.size(), IsEqual.equalTo(1));
		Assert.assertThat(jsonMetaData.get("application"), IsEqual.equalTo("NIS"));
	}

	@Test
	public void checkNodeStatusDelegatesToNisConnector() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.controller.checkNodeStatus();

		// Assert:
		Mockito.verify(context.connector, Mockito.times(1)).get(NisApiId.NIS_REST_NODE_INFO, null);
	}

	private static class TestContext {
		private final WalletServices walletServices = Mockito.mock(WalletServices.class);
		private final PrimaryNisConnector connector = Mockito.mock(PrimaryNisConnector.class);
		private final NodeController controller = new NodeController(this.walletServices, this.connector);
	}
}