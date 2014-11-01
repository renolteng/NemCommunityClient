package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class AggregateNemStatusVisitorTest {

	@Test
	public void notifyStatusPropagatesInitialNotificationToAllChildren() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);

		// Assert:
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
	}

	@Test
	public void notifyStatusPropagatesAdditionalNotificationToAllChildrenWhenStatusChanges() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);

		// Assert:
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);
		Mockito.verify(context.childVisitor1, Mockito.times(2)).notifyStatus(Mockito.any(), Mockito.any());
		Mockito.verify(context.childVisitor2, Mockito.times(2)).notifyStatus(Mockito.any(), Mockito.any());
	}

	@Test
	public void notifyStatusDoesNotPropagateAdditionalNotificationToAllChildrenWhenStatusDoesNotChange() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);

		// Assert:
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
		Mockito.verify(context.childVisitor1, Mockito.times(1)).notifyStatus(Mockito.any(), Mockito.any());
		Mockito.verify(context.childVisitor2, Mockito.times(1)).notifyStatus(Mockito.any(), Mockito.any());
	}

	private static class TestContext {
		private final NodeStatusVisitor childVisitor1 = Mockito.mock(NodeStatusVisitor.class);
		private final NodeStatusVisitor childVisitor2 = Mockito.mock(NodeStatusVisitor.class);
		private final NodeStatusVisitor visitor = new AggregateNemStatusVisitor(Arrays.asList(this.childVisitor1, this.childVisitor2));
	}
}