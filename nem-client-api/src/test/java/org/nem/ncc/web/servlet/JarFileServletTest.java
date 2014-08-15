package org.nem.ncc.web.servlet;

import org.eclipse.jetty.http.HttpContent;
import org.eclipse.jetty.util.resource.Resource;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.*;
import java.io.IOException;

public class JarFileServletTest {

	@Test
	public void sendDataSetsCorrectHttpHeaders() throws IOException {
		// Arrange:
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		final Resource resource = Mockito.mock(Resource.class);
		final HttpContent content = Mockito.mock(HttpContent.class);
		Mockito.when(content.getResource()).thenReturn(resource);
		final JarFileServlet servlet = new JarFileServlet();

		// Act:
		servlet.sendData(request, response, false, resource, content, null);

		// Assert:
		Mockito.verify(response, Mockito.times(1)).setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		Mockito.verify(response, Mockito.times(1)).setHeader("Pragma", "no-cache");
		Mockito.verify(response, Mockito.times(1)).setDateHeader("Expires", 0);
	}
}