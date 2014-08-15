package org.nem.client.download;

import org.junit.Test;

import java.net.*;

public class TestSplashScreen {

	@Test
	public void test() throws InterruptedException, MalformedURLException {
		// Arrange
		final SplashProgressBar bar = new SplashProgressBar();
		final URL url = new URL("http://nem.pucchiwerk.eu");
		for (int i = 0; i < 100; i++) {
			bar.progress(url, "", (long)100, (long)i, i);
			Thread.sleep(250);
		}
	}
}
