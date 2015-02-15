package org.nem.monitor.launcher;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.monitor.node.NemNodeType;

public class WebStartNodeLauncherTest {

	@Test
	public void canLaunchNisNode() {
		// Arrange:
		final WebStartLauncher webStartLauncher = Mockito.mock(WebStartLauncher.class);
		final NodeLauncher launcher = new WebStartNodeLauncher(
				webStartLauncher,
				"nis-jnlp-url",
				"ncc-jnlp-url");

		// Act:
		launcher.launch(NemNodeType.NIS);

		// Assert:
		Mockito.verify(webStartLauncher, Mockito.only()).launch("nis-jnlp-url");
	}

	@Test
	public void canLaunchNccNode() {
		// Arrange:
		final WebStartLauncher webStartLauncher = Mockito.mock(WebStartLauncher.class);
		final NodeLauncher launcher = new WebStartNodeLauncher(
				webStartLauncher,
				"nis-jnlp-url",
				"ncc-jnlp-url");

		// Act:
		launcher.launch(NemNodeType.NCC);

		// Assert:
		Mockito.verify(webStartLauncher, Mockito.only()).launch("ncc-jnlp-url");
	}
}