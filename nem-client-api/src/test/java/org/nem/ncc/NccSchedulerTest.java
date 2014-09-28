package org.nem.ncc;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.time.TimeProvider;
import org.nem.ncc.time.synchronization.NccTimeSynchronizer;

public class NccSchedulerTest {

	@Test
	public void initiallyNoTasksAreStarted() {
		// Act:
		try (final NccScheduler scheduler = new NccScheduler(Mockito.mock(TimeProvider.class))) {
			// Assert:
			Assert.assertThat(scheduler.getVisitors().size(), IsEqual.equalTo(0));
		}
	}

	@Test
	public void addTimeSynchronizationTaskAddsTimeSynchronizationTask() {
		// Arrange:
		final NccScheduler scheduler = new NccScheduler(Mockito.mock(TimeProvider.class));

		// Act:
		scheduler.addTimeSynchronizationTask(Mockito.mock(NccTimeSynchronizer.class));

		// Assert:
		Assert.assertThat(scheduler.getVisitors().size(), IsEqual.equalTo(1));
		Assert.assertThat(scheduler.getVisitors().get(0).getTimerName(), IsEqual.equalTo("TIME SYNCHRONIZATION"));
	}
}
