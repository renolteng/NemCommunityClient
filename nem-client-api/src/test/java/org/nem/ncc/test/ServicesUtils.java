package org.nem.ncc.test;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.mockito.Mockito;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.connector.*;
import org.nem.ncc.model.NisApiId;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Static class containing functions to facilitate testing services.
 */
public class ServicesUtils {

	/**
	 * Asserts that the specified action delegates to the connector when the connector succeeds.
	 *
	 * @param action The action.
	 * @param connector The connector.
	 * @param apiId The api id.
	 * @param entity The entity.
	 */
	public static void assertSuccessfulDelegationToConnector(
			final Function<NodeEndpoint, SerializableEntity> action,
			final AsyncNisConnector connector,
			final NisApiId apiId,
			final SerializableEntity entity) {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		final CompletableFuture<Deserializer> future = Utils.createDeserializerFuture(entity);
		Mockito.when(connector.getAsync(endpoint, apiId, null)).thenReturn(future);

		// Act:
		final SerializableEntity returnedEntity = action.apply(endpoint);

		// Assert:
		Assert.assertThat(returnedEntity, IsEqual.equalTo(entity));
		Mockito.verify(connector, Mockito.times(1)).getAsync(endpoint, apiId, null);
	}

	/**
	 * Asserts that the specified action delegates to the connector when the connector fails.
	 *
	 * @param action The action.
	 * @param connector The connector.
	 * @param apiId The api id.
	 */
	public static void assertFailureDelegationToConnector(
			final Function<NodeEndpoint, SerializableEntity> action,
			final AsyncNisConnector connector,
			final NisApiId apiId) {
		// Arrange:
		final NodeEndpoint endpoint = NodeEndpoint.fromHost("10.0.0.4");
		Mockito.when(connector.getAsync(endpoint, apiId, null))
				.thenReturn(Utils.createExceptionalFuture(new FatalPeerException("badness")));

		// Act:
		ExceptionAssert.assertThrowsCompletionException(
				v -> action.apply(endpoint),
				FatalPeerException.class);

		// Assert:
		Mockito.verify(connector, Mockito.times(1)).getAsync(endpoint, apiId, null);
	}

	/**
	 * Sets up forwarding so that connector.forward executes the function parameter.
	 *
	 * @param connector The connector.
	 * @param endpoint The endpoint to which requests should be forwarded.
	 */
	@SuppressWarnings("unchecked")
	public static void setupForwarding(final PrimaryNisConnector connector, final NodeEndpoint endpoint) {
		// Arrange:
		Mockito.when(connector.forward(Mockito.any())).then(invocationOnMock -> {
			final Function funcArgument = (Function)invocationOnMock.getArguments()[0];
			final CompletableFuture<?> future = (CompletableFuture<?>)funcArgument.apply(endpoint);
			return future.get();
		});
	}
}
