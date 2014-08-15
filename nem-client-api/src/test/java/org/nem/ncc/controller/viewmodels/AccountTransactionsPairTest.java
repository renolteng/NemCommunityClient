package org.nem.ncc.controller.viewmodels;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.AccountStatus;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.test.Utils;

import java.util.*;

public class AccountTransactionsPairTest {

	@Test
	public void canCreateMetaDataPair() {
		// Arrange:
		final AccountViewModel account = new AccountViewModel(Utils.generateRandomAccountInfo(), AccountStatus.LOCKED, null);
		final List<TransferViewModel> transactions = new ArrayList<>();
		final AccountTransactionsPair pair = new AccountTransactionsPair(account, transactions);

		// Assert:
		Assert.assertThat(pair.getAccount(), IsSame.sameInstance(account));
		Assert.assertThat(pair.getTransactions(), IsSame.sameInstance(transactions));
	}

	@Test
	public void canSerializeMetaDataPair() {
		// Arrange:
		final AccountViewModel account = new AccountViewModel(Utils.generateRandomAccountInfo(), AccountStatus.LOCKED, null);
		final List<TransferViewModel> transactions = new ArrayList<>();
		final AccountTransactionsPair pair = new AccountTransactionsPair(account, transactions);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(pair);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(8));
		Assert.assertThat(jsonObject.get("address"), IsEqual.equalTo(account.getAddress().getEncoded()));
		Assert.assertThat(((JSONArray)jsonObject.get("transactions")).size(), IsEqual.equalTo(0));
	}
}