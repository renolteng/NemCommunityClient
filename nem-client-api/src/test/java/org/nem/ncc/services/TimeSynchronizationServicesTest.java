package org.nem.ncc.services;

import org.junit.Test;
import org.mockito.Mockito;
import org.nem.core.connect.client.*;
import org.nem.core.model.primitive.NetworkTimeStamp;
import org.nem.core.time.synchronization.CommunicationTimeStamps;
import org.nem.ncc.test.ServicesUtils;

public class TimeSynchronizationServicesTest {

	// region getCommunicationTimeStamps

	@Test
	public void getCommunicationTimeStampsAsyncReturnsCommunicationTimeStampsOnSuccess() {
		// Arrange:
		final TestContext context = new TestContext();

		// Assert:
		ServicesUtils.assertSuccessfulDelegationToConnector(
				endpoint -> context.services.getCommunicationTimeStampsAsync(endpoint).join(),
				context.connector,
				NisApiId.NIS_REST_TIME_SYNC_NETWORK_TIME,
				new CommunicationTimeStamps(new NetworkTimeStamp(10), new NetworkTimeStamp(20)));
	}

	@Test
	public void getCommunicationTimeStampsAsyncThrowsExceptionOnError() {
		// Assert:
		final TestContext context = new TestContext();
		ServicesUtils.assertFailureDelegationToConnector(
				endpoint -> context.services.getCommunicationTimeStampsAsync(endpoint).join(),
				context.connector,
				NisApiId.NIS_REST_TIME_SYNC_NETWORK_TIME);
	}

	//endregion

	private static class TestContext {
		final AsyncNisConnector connector = Mockito.mock(AsyncNisConnector.class);
		final TimeSynchronizationServices services = new TimeSynchronizationServices(this.connector);
	}
}
