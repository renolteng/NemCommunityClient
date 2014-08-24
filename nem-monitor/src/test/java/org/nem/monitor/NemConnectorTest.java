package org.nem.monitor;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.connect.client.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.monitor.node.*;

import java.util.concurrent.CompletableFuture;

public class NemConnectorTest {

	@Test
	public void isRunningReturnsTrueWhenNoExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.completedFuture(null));

		// Act:
		final NemNodeStatus status = context.connector.getStatus().join();

		// Assert:
		Assert.assertThat(status, IsEqual.equalTo(NemNodeStatus.RUNNING));
		context.assertHeartbeatCalledOnce();
	}

	@Test
	public void isRunningReturnsTrueWhenNemNodeExpectedExceptionIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.supplyAsync(() -> { throw new NemNodeExpectedException(); }));

		// Act:
		final NemNodeStatus status = context.connector.getStatus().join();

		// Assert:
		Assert.assertThat(status, IsEqual.equalTo(NemNodeStatus.RUNNING));
		context.assertHeartbeatCalledOnce();
	}

	@Test
	public void isRunningReturnsFalseWhenOtherIsThrown() {
		// Arrange:
		final TestContext context = new TestContext();
		Mockito.when(context.asyncConnector.getAsync(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(CompletableFuture.supplyAsync(() -> { throw new RuntimeException(); }));

		// Act:
		final NemNodeStatus status = context.connector.getStatus().join();

		// Assert:
		Assert.assertThat(status, IsEqual.equalTo(NemNodeStatus.STOPPED));
		context.assertHeartbeatCalledOnce();
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
			this.policy = new NemNodePolicy() {
				@Override
				public NodeEndpoint getEndpoint() {
					return NodeEndpoint.fromHost("10.0.0.18");
				}

				@Override
				public NemNodeType getNodeType() {
					return null;
				}

				@Override
				public String mapToUrlPath(final NisApiId apiId) {
					return String.format("{%s}", apiId);
				}

				@Override
				public boolean hasBrowserGui() {
					return false;
				}
			};

			this.asyncConnector = Mockito.mock(DefaultAsyncNemConnector.class);
			this.connector = new NemConnector(this.policy, this.asyncConnector);
		}

		private void assertHeartbeatCalledOnce() {
			Mockito.verify(this.asyncConnector, Mockito.times(1)).getAsync(NodeEndpoint.fromHost("10.0.0.18"), "{/heartbeat}", null);
		}

		private void assertShutdownCalledOnce() {
			Mockito.verify(this.asyncConnector, Mockito.times(1)).getAsync(NodeEndpoint.fromHost("10.0.0.18"), "{/shutdown}", null);
		}
	}
}