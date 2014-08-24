package org.nem.monitor.visitors;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.monitor.*;
import org.nem.monitor.node.NemNodeType;

public class NodeManagerTest {

	@Test
	public void shutdownDelegatesToConnector() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.manager.shutdown();

		// Assert:
		Mockito.verify(context.connector, Mockito.times(1)).shutdown();
	}

	@Test
	public void launchDelegatesToLauncher() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.manager.launch();

		// Assert:
		Mockito.verify(context.launcher, Mockito.times(1)).launch("foo.jnlp");
	}

	private static class TestContext {
		private final NemConnector connector = Mockito.mock(NemConnector.class);
		private final WebStartLauncher launcher = Mockito.mock(WebStartLauncher.class);
		private final NodeManager manager = new NodeManager(NemNodeType.NCC, this.connector, this.launcher, "foo.jnlp");
	}
}