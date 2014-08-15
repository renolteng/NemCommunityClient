package org.nem.ncc.controller;

import org.hamcrest.core.IsSame;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.model.graph.*;
import org.nem.ncc.services.*;
import org.nem.ncc.test.Utils;

import java.util.*;

public class NetworkControllerTest {
	//region basic tests

	@Test
	public void networkDelegatesToGraphViewModelFactory() {
		// Arrange:
		final BasicTestContext context = new BasicTestContext();
		final Collection<NodeEndpoint> endpoints = Arrays.asList(
				NodeEndpoint.fromHost("10.0.0.1"),
				NodeEndpoint.fromHost("10.0.0.2"));

		final GraphViewModel original = new GraphViewModel(null, null);
		Mockito.when(context.graphViewModelFactory.createViewModel(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(original);

		// Act:
		final NetworkController networkController = new NetworkController(
				context.networkServices,
				context.nodeServices,
				context.graphViewModelFactory);
		final GraphViewModel viewModel = networkController.network(endpointsToDeserializer(endpoints));

		// Assert:
		Assert.assertThat(viewModel, IsSame.sameInstance(original));
		Mockito.verify(context.graphViewModelFactory, Mockito.times(1)).createViewModel(
				context.networkServices,
				context.nodeServices,
				endpoints);
	}

	private static Deserializer endpointsToDeserializer(final Collection<NodeEndpoint> endpoints) {
		return Utils.createDeserializer(JsonSerializer.serializeToJson(new SerializableList<>(endpoints)));
	}

	private static class BasicTestContext {
		private final NetworkServices networkServices = Mockito.mock(NetworkServices.class);
		private final NodeServices nodeServices = Mockito.mock(NodeServices.class);
		private final GraphViewModelFactory graphViewModelFactory = Mockito.mock(GraphViewModelFactory.class);
	}

	//endregion
}