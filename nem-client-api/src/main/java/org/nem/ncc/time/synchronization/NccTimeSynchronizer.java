package org.nem.ncc.time.synchronization;

import org.nem.core.model.primitive.TimeOffset;
import org.nem.core.time.*;
import org.nem.core.time.synchronization.*;
import org.nem.ncc.connector.*;
import org.nem.ncc.services.TimeSynchronizationServices;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * NCC time synchronization with its NIS.
 */
public class NccTimeSynchronizer implements TimeSynchronizer {
	final private static Logger LOGGER = Logger.getLogger(NccTimeSynchronizer.class.getName());

	private final TimeSynchronizationServices timeSynchronizationServices;
	private final TimeProvider timeProvider;
	private final PrimaryNisConnector nisConnector;

	/**
	 * Creates a new NCC time synchronizer.
	 *
	 * @param timeSynchronizationServices The time synchronization services.
	 * @param timeProvider The time provider.
	 * @param nisConnector The NIS connector.
	 */
	public NccTimeSynchronizer(
			final TimeSynchronizationServices timeSynchronizationServices,
			final TimeProvider timeProvider,
			final PrimaryNisConnector nisConnector) {
		this.timeSynchronizationServices = timeSynchronizationServices;
		this.timeProvider = timeProvider;
		this.nisConnector = nisConnector;
	}

	@Override
	public java.util.concurrent.CompletableFuture<java.lang.Void> synchronizeTime() {
		if (!this.nisConnector.isConnected()) {
			LOGGER.warning("Time synchronization not possible, NIS not available.");
			return CompletableFuture.completedFuture(null);
		}

		final NetworkTimeStamp sendTimeStamp = this.timeProvider.getNetworkTime();
		return this.nisConnector.forwardAsync(this.timeSynchronizationServices::getCommunicationTimeStampsAsync)
				.thenAccept(remoteTimeStamps -> {
					final NetworkTimeStamp receiveTimeStamp = this.timeProvider.getNetworkTime();
					final TimeSynchronizationSample sample = new TimeSynchronizationSample(
							null,
							new CommunicationTimeStamps(sendTimeStamp, receiveTimeStamp),
							remoteTimeStamps);
					final TimeOffset timeOffset = new TimeOffset(sample.getTimeOffsetToRemote());
					this.timeProvider.updateTimeOffset(timeOffset);
				});
	}
}
