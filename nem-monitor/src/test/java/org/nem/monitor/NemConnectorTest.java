package org.nem.monitor;

import org.apache.commons.io.FileUtils;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.NemStatus;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.utils.LockFile;
import org.nem.monitor.node.NemNodePolicy;

import java.io.*;
import java.util.concurrent.CompletableFuture;

public class NemConnectorTest {
	private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
	private static final File TEST_FILE_DIRECTORY = new File(WORKING_DIRECTORY, "test_files");
	private static final File TEST_LOCK_FILE = new File(TEST_FILE_DIRECTORY, "test.lock");

	//region BeforeClass / AfterClass

	@BeforeClass
	public static void createTestFiles() throws IOException {
		final boolean result = TEST_FILE_DIRECTORY.mkdir() && TEST_LOCK_FILE.createNewFile();

		if (!result) {
			throw new RuntimeException("unable to initialize test suite");
		}
	}

	@AfterClass
	public static void removeTestFiles() throws IOException {
		FileUtils.deleteDirectory(TEST_FILE_DIRECTORY);
	}

	//endregion

	@Test
	public void isRunningReturnsRunningWhenNoExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.completedFuture(null));

		// Act:
		final NemStatus status = context.connector.getStatus().join();

		// Assert:
		Assert.assertThat(status, IsEqual.equalTo(NemStatus.RUNNING));
		context.assertStatusCalledOnce();
	}

	@Test
	public void isRunningReturnsRunningWhenNemNodeExpectedExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.supplyAsync(() -> { throw new NemNodeExpectedException(); }));

		// Act:
		final NemStatus status = context.connector.getStatus().join();

		// Assert:
		Assert.assertThat(status, IsEqual.equalTo(NemStatus.RUNNING));
		context.assertStatusCalledOnce();
	}

	@Test
	public void isRunningReturnsStoppedWhenOtherExceptionIsThrownAndLockFileIsNotLocked() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.supplyAsync(() -> { throw new RuntimeException(); }));

		// Act:
		final NemStatus status = context.connector.getStatus().join();

		// Assert:
		Assert.assertThat(status, IsEqual.equalTo(NemStatus.STOPPED));
		context.assertStatusCalledOnce();
	}

	@Test
	public void isRunningReturnsBootingWhenOtherExceptionIsThrownAndLockFileIsLocked() throws IOException {
		// Arrange:
		try (final Closeable ignored = LockFile.tryAcquireLock(TEST_LOCK_FILE)) {
			final TestContext context = new TestContext();
			Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(CompletableFuture.supplyAsync(() -> { throw new RuntimeException(); }));

			// Act:
			final NemStatus status = context.connector.getStatus().join();

			// Assert:
			Assert.assertThat(status, IsEqual.equalTo(NemStatus.STARTING));
			context.assertStatusCalledOnce();
		}
	}

	@Test
	public void shutdownDelegatesToAsyncConnector() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.completedFuture(null));

		// Act:
		context.connector.shutdown().join();

		// Assert:
		context.assertShutdownCalledOnce();
	}

	private static class TestContext {
		private final NemNodePolicy policy;
		private final DefaultAsyncNemConnector<String> asyncConnector;
		private final NemConnector connector;

		@SuppressWarnings("unchecked")
		private TestContext() {
			this.policy = Mockito.mock(NemNodePolicy.class);
			Mockito.when(this.policy.getEndpoint()).thenReturn(NodeEndpoint.fromHost("10.0.0.18"));
			Mockito.when(this.policy.getLockFile()).thenReturn(TEST_LOCK_FILE);
			Mockito.when(this.policy.mapToUrlPath(Mockito.any()))
					.then(invocationOnMock -> String.format("{%s}", invocationOnMock.getArguments()[0]));

			this.asyncConnector = Mockito.mock(DefaultAsyncNemConnector.class);
			this.connector = new NemConnector(this.policy, this.asyncConnector);
		}

		private void assertStatusCalledOnce() {
			Mockito.verify(this.asyncConnector, Mockito.times(1)).getAsync(NodeEndpoint.fromHost("10.0.0.18"), "{/status}", null);
		}

		private void assertShutdownCalledOnce() {
			Mockito.verify(this.asyncConnector, Mockito.times(1)).getAsync(NodeEndpoint.fromHost("10.0.0.18"), "{/shutdown}", null);
		}
	}
}