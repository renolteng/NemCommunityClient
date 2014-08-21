package org.nem.deploy;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.deploy.appconfig.NccAppConfig;
import org.nem.ncc.connector.NisController;
import org.nem.ncc.web.servlet.*;

public class NccConfigurationPolicyTest {
	//region get class

	@Test
	public void canGetNisAppConfigClass() {
		// Arrange:
		final NccConfigurationPolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getAppConfigClass(), IsEqual.equalTo(NccAppConfig.class));
	}

	@Test
	public void canGetNisWebAppInitializerClass() {
		// Arrange:
		final NccConfigurationPolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getWebAppInitializerClass(), IsEqual.equalTo(NccWebAppInitializer.class));
	}

	@Test
	public void canGetJarFileServletClass() {
		// Arrange:
		final NccConfigurationPolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getJarFileServletClass(), IsEqual.equalTo(JarFileServlet.class));
	}

	@Test
	public void canGetDefaultServletClass() {
		// Arrange:
		final NccConfigurationPolicy policy = createPolicy();

		// Assert:
		Assert.assertThat(policy.getDefaultServletClass(), IsEqual.equalTo(NccDefaultServlet.class));
	}

	@Test
	public void openWebBrowserDelegatesToWebStartProxy() {
		// Arrange:
		final WebStartProxy proxy = Mockito.mock(WebStartProxy.class);
		Mockito.when(proxy.openWebBrowser("someUrl")).thenReturn(true);

		final NccConfigurationPolicy policy = new NccConfigurationPolicy(proxy, Mockito.mock(NisController.class));

		// Act:
		policy.openWebBrowser("someUrl");

		// Assert:
		Mockito.verify(proxy, Mockito.times(1)).openWebBrowser("someUrl");
	}

	@Test
	public void handleWebStartDelegatesToNisController() {
		// Arrange:
		final NisController nisController = Mockito.mock(NisController.class);
		final NccConfigurationPolicy policy = new NccConfigurationPolicy(Mockito.mock(WebStartProxy.class), nisController);

		// Act:
		policy.handleWebStart(new String[] { "-isWebStart", "1", "-nisJnlpUrl", "someUrl" });

		// Assert:
		Mockito.verify(nisController, Mockito.times(1)).startNisViaWebStart("someUrl");
	}

	@Test
	public void handleWebStartDoesNotDelegateToNisControllerWhenIsWebStartIsZero() {
		// Arrange:
		final NisController nisController = Mockito.mock(NisController.class);
		final NccConfigurationPolicy policy = new NccConfigurationPolicy(Mockito.mock(WebStartProxy.class), nisController);

		// Act:
		policy.handleWebStart(new String[] { "-isWebStart", "0", "-nisJnlpUrl", "someUrl" });

		// Assert:
		Mockito.verify(nisController, Mockito.times(0)).startNisViaWebStart(Mockito.any());
	}

	//endregion

	private static NccConfigurationPolicy createPolicy() {
		return new NccConfigurationPolicy(
				Mockito.mock(WebStartProxy.class),
				Mockito.mock(NisController.class));
	}
}
