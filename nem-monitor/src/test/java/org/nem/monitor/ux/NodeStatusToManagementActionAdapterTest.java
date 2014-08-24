package org.nem.monitor.ux;

import junit.framework.TestCase;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.monitor.node.*;

import java.awt.event.ActionEvent;

public class NodeStatusToManagementActionAdapterTest {

	@Test
	public void constructorInitializesListenerToNullAction() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(0)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
	}

	@Test
	public void stateChangeToRunningUpdatesAction() {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.RUNNING);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(0)).launch();
		Mockito.verify(context.manager, Mockito.times(1)).shutdown();
	}

	@Test
	public void stateChangeToStoppedUpdatesAction() {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(1)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
	}

	@Test
	public void stateChangeToUnknownUpdatesAction() {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.STOPPED);
		context.visitor.notifyStatus(NemNodeType.NCC, NemNodeStatus.UNKNOWN);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(0)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
	}

	@Test
	public void stateChangeForOtherNodeDoesNotUpdateAction() {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NIS, NemNodeStatus.STOPPED);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(0)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
	}

	private static class TestContext {
		private final NodeManager manager = Mockito.mock(NodeManager.class);
		private final NodeStatusToManagementActionAdapter visitor = new NodeStatusToManagementActionAdapter(NemNodeType.NCC, this.manager);
	}
}