package org.nem.monitor.launcher;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.core.node.NodeEndpoint;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class WebBrowserTest {

	@Test
	public void navigationOccursIfDesktopSupportsBrowsing() throws URISyntaxException, IOException {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.56");
		final Desktop desktop = Mockito.mock(Desktop.class);
		Mockito.when(desktop.isSupported(Desktop.Action.BROWSE)).thenReturn(true);
		final WebBrowser browser = new WebBrowser();

		// Act:
		browser.navigate(desktop, endpoint);

		// Assert:
		Mockito.verify(desktop, Mockito.times(1)).browse(endpoint.getBaseUrl().toURI());
	}

	@Test
	public void navigationDoesNotOccurIfDesktopIsNotPresent() {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.56");
		final WebBrowser browser = new WebBrowser();

		// Act:
		browser.navigate(null, endpoint);

		// Assert: (no exception)
	}

	@Test
	public void navigationDoesNotOccurIfDesktopDoesNotSupportBrowsing() throws IOException {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.56");
		final Desktop desktop = Mockito.mock(Desktop.class);
		Mockito.when(desktop.isSupported(Desktop.Action.BROWSE)).thenReturn(false);
		final WebBrowser browser = new WebBrowser();

		// Act:
		browser.navigate(desktop, endpoint);

		// Assert:
		Mockito.verify(desktop, Mockito.never()).browse(Mockito.any());
	}
}