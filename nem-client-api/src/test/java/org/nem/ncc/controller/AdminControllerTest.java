package org.nem.ncc.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.core.deploy.CommonStarter;
import org.nem.ncc.cache.*;

import java.util.ArrayList;

public class AdminControllerTest {
	@Test
	public void shutdownDelegatesToRepositoryAndNccMain() throws InterruptedException {
		// Arrange:
		final AccountsFileRepository repository = Mockito.mock(AccountsFileRepository.class);
		final NccAccountCache cache = Mockito.mock(NccAccountCache.class);
		final CommonStarter commonStarter = Mockito.mock(CommonStarter.class);
		Mockito.when(cache.getAccounts()).thenReturn(new ArrayList<>());

		// Act:
		final AdminController controller = new AdminController(repository, cache, commonStarter);
		controller.shutdown();
		Thread.sleep(500);

		// Assert:
		Mockito.verify(commonStarter, Mockito.times(1)).stopServer();
		Mockito.verify(repository, Mockito.times(1)).save(cache.getAccounts());
	}
}
