package org.nem.monitor;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.core.node.NodeEndpoint;

import javax.jnlp.BasicService;

public class WebBrowserTest {

	@Test
	public void navigationOccursIfServiceSupportsNavigation() {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.56");
		final BasicService service = Mockito.mock(BasicService.class);
		Mockito.when(service.isWebBrowserSupported()).thenReturn(true);
		final WebBrowser browser = new WebBrowser();

		// Act:
		browser.navigate(service, endpoint);

		// Assert:
		Mockito.verify(service, Mockito.times(1)).showDocument(endpoint.getBaseUrl());
	}

	@Test
	public void navigationDoesNotOccurIfServiceDoesNotSupportNavigation() {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.56");
		final BasicService service = Mockito.mock(BasicService.class);
		Mockito.when(service.isWebBrowserSupported()).thenReturn(false);
		final WebBrowser browser = new WebBrowser();

		// Act:
		browser.navigate(service, endpoint);

		// Assert:
		Mockito.verify(service, Mockito.times(0)).showDocument(Mockito.any());
	}
}