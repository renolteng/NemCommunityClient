package org.nem.monitor.visitors;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.core.model.NemStatus;
import org.nem.monitor.node.NemNodeType;

import java.awt.event.ActionEvent;

public class NodeStatusToManagementActionAdapterTest {

	@Test
	public void noActionsAreInitiallyRegistered() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		context.assertNoActionPerformed();
	}

	//region no action states

	@Test
	public void noActionsAreRegisteredWithUnknownStatus() {
		// Assert:
		assertNoActionsAreRegisteredWithStatus(NemStatus.UNKNOWN);
	}

	@Test
	public void noActionsAreRegisteredWithStartingStatus() {
		// Assert:
		assertNoActionsAreRegisteredWithStatus(NemStatus.STARTING);
	}

	private static void assertNoActionsAreRegisteredWithStatus(final NemStatus status) {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NCC, status);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		context.assertNoActionPerformed();
	}

	//endregion

	//region shutdown action states

	@Test
	public void shutdownActionIsRegisteredWithRunningStatus() {
		// Assert:
		assertShutdownActionIsRegisteredWithStatus(NemStatus.RUNNING);
	}

	@Test
	public void shutdownActionIsRegisteredWithBootedStatus() {
		// Assert:
		assertShutdownActionIsRegisteredWithStatus(NemStatus.BOOTED);
	}

	@Test
	public void shutdownActionIsRegisteredWithSynchronizedStatus() {
		// Assert:
		assertShutdownActionIsRegisteredWithStatus(NemStatus.SYNCHRONIZED);
	}

	private static void assertShutdownActionIsRegisteredWithStatus(final NemStatus status) {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NCC, status);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(0)).launch();
		Mockito.verify(context.manager, Mockito.times(1)).shutdown();
		Mockito.verify(context.manager, Mockito.times(0)).launchBrowser();
	}

	//endregion

	//region launch action states

	@Test
	public void launchActionIsRegisteredWithStoppedStatus() {
		// Assert:
		assertLaunchActionIsRegisteredWithStatus(NemStatus.STOPPED);
	}

	private static void assertLaunchActionIsRegisteredWithStatus(final NemStatus status) {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NCC, status);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		Mockito.verify(context.manager, Mockito.times(1)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
		Mockito.verify(context.manager, Mockito.times(0)).launchBrowser();
	}

	//endregion

	@Test
	public void stateChangeForOtherNodeDoesNotUpdateAction() {
		// Arrange:
		final TestContext context = new TestContext();
		context.visitor.notifyStatus(NemNodeType.NIS, NemStatus.STOPPED);

		// Act:
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));

		// Assert:
		context.assertNoActionPerformed();
	}

	@Test
	public void firstTransitionToRunningAfterExplicitLaunchLaunchesBrowser() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);

		// Assert:
		Mockito.verify(context.manager, Mockito.times(1)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
		Mockito.verify(context.manager, Mockito.times(1)).launchBrowser();
	}

	@Test
	public void subsequentTransitionToRunningAfterExplicitLaunchDoesNotRelaunchBrowser() {
		// Arrange:
		final TestContext context = new TestContext();

		// Act:
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);
		context.visitor.actionPerformed(Mockito.mock(ActionEvent.class));
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.STOPPED);
		context.visitor.notifyStatus(NemNodeType.NCC, NemStatus.RUNNING);

		// Assert:
		Mockito.verify(context.manager, Mockito.times(1)).launch();
		Mockito.verify(context.manager, Mockito.times(0)).shutdown();
		Mockito.verify(context.manager, Mockito.times(1)).launchBrowser();
	}

	private static class TestContext {
		private final NodeManager manager = Mockito.mock(NodeManager.class);
		private final NodeStatusToManagementActionAdapter visitor = new NodeStatusToManagementActionAdapter(NemNodeType.NCC, this.manager);

		private void assertNoActionPerformed() {
			Mockito.verify(this.manager, Mockito.times(0)).launch();
			Mockito.verify(this.manager, Mockito.times(0)).shutdown();
			Mockito.verify(this.manager, Mockito.times(0)).launchBrowser();
		}
	}
}