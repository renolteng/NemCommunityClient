package org.nem.monitor.launcher;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Function;

public class WebStartLauncherTest {

	@Test
	public void launchDelegatesToProcessBuilderFactory() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.launcher.launch("http://bob.nem.ninja/webstart/nem-client.jnlp");

		// Assert:
		Mockito.verify(context.factory, Mockito.times(1)).apply("http://bob.nem.ninja/webstart/nem-client.jnlp");
	}

	@Test
	public void launchSetsUpLogFile() throws IOException {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.launcher.launch("http://bob.nem.ninja/webstart/nem-client.jnlp");

		// Assert:
		Mockito.verify(context.builder, Mockito.times(1))
				.setLogFile(Paths.get("my_nem_folder", "nem-client.log").toFile().getCanonicalFile());
	}

	@Test
	public void launchStartsProcess() throws IOException {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.launcher.launch("http://bob.nem.ninja/webstart/nem-client.jnlp");

		// Assert:
		Mockito.verify(context.builder, Mockito.times(1)).start();
	}

	private static class TestContext {
		private final Function<String, WebStartProcessBuilder> factory;
		private final WebStartProcessBuilder builder;
		private final WebStartLauncher launcher;

		@SuppressWarnings("unchecked")
		private TestContext() {
			this.factory = Mockito.mock(Function.class);
			this.builder = Mockito.mock(WebStartProcessBuilder.class);
			Mockito.when(this.factory.apply(Mockito.any())).thenReturn(this.builder);
			this.launcher = new WebStartLauncher("my_nem_folder", this.factory);
		}
	}
}