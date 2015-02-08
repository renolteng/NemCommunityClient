package org.nem.ncc;

/**
 * The central NCC class.
 */
public class NccMain {
	private final NccConfiguration configuration;
	private final NccScheduler scheduler;

	/**
	 * Creates a main NCC instance.
	 *
	 * @param configuration The configuration.
	 * @param scheduler The scheduler.
	 */
	public NccMain(
			final NccConfiguration configuration,
			final NccScheduler scheduler) {
		this.configuration = configuration;
		this.scheduler = scheduler;
	}
}
