package org.nem.monitor.launcher;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.monitor.node.NemNodeType;

public class WebStartNodeLauncherTest {

	@Test
	public void canLaunchNisNode() {
		// Assert:
		assertCanLaunchNode(NemNodeType.NIS, "nis-jnlp-url");
	}

	@Test
	public void canLaunchNccNode() {
		// Assert:
		assertCanLaunchNode(NemNodeType.NCC, "ncc-jnlp-url");
	}

	private static void assertCanLaunchNode(
			final NemNodeType nodeType,
			final String expectedJnlpUrl) {
		// Arrange:
		final WebStartLauncher webStartLauncher = Mockito.mock(WebStartLauncher.class);
		final NodeLauncher launcher = new WebStartNodeLauncher(
				webStartLauncher,
				"nis-jnlp-url",
				"ncc-jnlp-url");

		// Act:
		launcher.launch(nodeType);

		// Assert:
		Mockito.verify(webStartLauncher, Mockito.only()).launch(expectedJnlpUrl);
	}
}