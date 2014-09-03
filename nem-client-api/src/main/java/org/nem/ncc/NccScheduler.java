package org.nem.ncc;

import org.nem.core.async.*;
import org.nem.core.time.TimeProvider;
import org.nem.ncc.time.synchronization.NccTimeSynchronizer;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Scheduler that keeps track of all scheduled, recurring timers.
 */
public class NccScheduler implements AutoCloseable {
	private static final int ONE_SECOND = 1000;
	private static final int ONE_MINUTE = 60 * ONE_SECOND;

	private static final int TIME_SYNC_INITIAL_DELAY = 10 * ONE_SECOND;
	private static final int TIME_SYNC_INTERVAL = ONE_MINUTE;

	private final TimeProvider timeProvider;
	private final List<NemAsyncTimerVisitor> timerVisitors = new ArrayList<>();
	private final List<AsyncTimer> timers = new ArrayList<>();
	private final Executor executor = Executors.newCachedThreadPool();

	/**
	 * Creates a new scheduler.
	 *
	 * @param timeProvider The time provider.
	 */
	public NccScheduler(final TimeProvider timeProvider) {
		this.timeProvider = timeProvider;
	}

	/**
	 * Gets all timer visitors.
	 *
	 * @return All timer visitors.
	 */
	public List<NemAsyncTimerVisitor> getVisitors() {
		return this.timerVisitors;
	}

	/**
	 * Adds the NCC time synchronization task.
	 */
	public void addTimeSynchronizationTask(final NccTimeSynchronizer nccTimeSynchronizer) {
		final AsyncTimerVisitor timerVisitor = this.createNamedVisitor("TIME SYNCHRONIZATION");
		this.timers.add(new AsyncTimer(
				this.runnableToFutureSupplier(() -> nccTimeSynchronizer.synchronizeTime()),
				TIME_SYNC_INITIAL_DELAY,
				new UniformDelayStrategy(TIME_SYNC_INTERVAL),
				timerVisitor));
	}

	@Override
	public void close() {
		this.timers.forEach(AsyncTimer::close);
	}

	private Supplier<CompletableFuture<?>> runnableToFutureSupplier(final Runnable runnable) {
		return () -> CompletableFuture.runAsync(runnable, this.executor);
	}

	private NemAsyncTimerVisitor createNamedVisitor(final String name) {
		final NemAsyncTimerVisitor timerVisitor = new NemAsyncTimerVisitor(name, this.timeProvider);
		this.timerVisitors.add(timerVisitor);
		return timerVisitor;
	}
}
