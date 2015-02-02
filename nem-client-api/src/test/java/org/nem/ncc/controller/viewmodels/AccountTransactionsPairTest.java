package org.nem.ncc.controller.viewmodels;

import net.minidev.json.*;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.JsonSerializer;
import org.nem.ncc.test.Utils;

import java.util.*;

public class AccountTransactionsPairTest {

	@Test
	public void canCreateMetaDataPair() {
		// Arrange:
		final AccountViewModel account = createAccountViewModel();
		final List<TransactionViewModel> transactions = new ArrayList<>();
		final AccountTransactionsPair pair = new AccountTransactionsPair(account, transactions);

		// Assert:
		Assert.assertThat(pair.getAccount(), IsSame.sameInstance(account));
		Assert.assertThat(pair.getTransactions(), IsSame.sameInstance(transactions));
	}

	@Test
	public void canSerializeMetaDataPair() {
		// Arrange:
		final AccountViewModel account = createAccountViewModel();
		final List<TransactionViewModel> transactions = new ArrayList<>();
		final AccountTransactionsPair pair = new AccountTransactionsPair(account, transactions);

		// Act:
		final JSONObject jsonObject = JsonSerializer.serializeToJson(pair);

		// Assert:
		// TODO 20150131 J-G: what was removed (from 9 -> 8)?
		// TODO 20150202 BR -> J: The label was removed from AccountViewModel.
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(8));
		Assert.assertThat(jsonObject.get("address"), IsEqual.equalTo(account.getAddress().getEncoded()));
		Assert.assertThat(((JSONArray)jsonObject.get("transactions")).size(), IsEqual.equalTo(0));
	}

	private static AccountViewModel createAccountViewModel() {
		return Utils.createAccountViewModelFromAddress(Utils.generateRandomAddress());
	}
}