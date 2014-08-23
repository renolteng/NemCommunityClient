package org.nem.monitor;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.monitor.node.*;

import java.util.Arrays;

public class AggregateNodeStatusVisitorTest {

	@Test
	public void notifyStatusPropagatesInitialNotificationToAllChildren() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);

		// Assert:
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
	}

	@Test
	public void notifyStatusPropagatesAdditionalNotificationToAllChildrenWhenStatusChanges() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);

		// Assert:
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);
		Mockito.verify(context.childVisitor1, Mockito.times(2)).notifyStatus(Mockito.any(), Mockito.any());
		Mockito.verify(context.childVisitor2, Mockito.times(2)).notifyStatus(Mockito.any(), Mockito.any());
	}

	@Test
	public void notifyStatusDoesNotPropagateAdditionalNotificationToAllChildrenWhenStatusDoesNotChange() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);

		// Assert:
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(Mockito.any(), Mockito.any());
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(Mockito.any(), Mockito.any());
	}

	private static class TestContext {
		private final NodeStatusVisitor childVisitor1 = Mockito.mock(NodeStatusVisitor.class);
		private final NodeStatusVisitor childVisitor2 = Mockito.mock(NodeStatusVisitor.class);
		private final NodeStatusVisitor visitor = new AggregateNodeStatusVisitor(Arrays.asList(this.childVisitor1, this.childVisitor2));
	}
}