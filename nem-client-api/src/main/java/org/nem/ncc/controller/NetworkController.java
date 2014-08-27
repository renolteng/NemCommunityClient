package org.nem.ncc.controller;

import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.*;
import org.nem.ncc.model.graph.*;
import org.nem.ncc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to the REST resource "network".
 */
@RestController
public class NetworkController {
	private final NetworkServices networkServices;
	private final NodeServices nodeServices;
	private final GraphViewModelFactory graphViewModelFactory;

	/**
	 * Creates a new network controller.
	 *
	 * @param networkServices The network services.
	 * @param nodeServices The node services.
	 */
	@Autowired(required = true)
	public NetworkController(
			final NetworkServices networkServices,
			final NodeServices nodeServices,
			final GraphViewModelFactory graphViewModelFactory) {
		this.networkServices = networkServices;
		this.nodeServices = nodeServices;
		this.graphViewModelFactory = graphViewModelFactory;
	}

	/**
	 * Requests a view model for the peers to which the specified peers are connected.
	 *
	 * @param deserializer The requested node endpoints.
	 * @return The network graph.
	 */
	@RequestMapping(value = "/network", method = RequestMethod.POST)
	public GraphViewModel network(@RequestBody final Deserializer deserializer) {
		final SerializableList<NodeEndpoint> endpoints = new SerializableList<>(deserializer, NodeEndpoint::new);
		return this.graphViewModelFactory.createViewModel(this.networkServices, this.nodeServices, endpoints.asCollection());
	}

	/**
	 * Requests a view model for the peers to which the local NIS is connected.
	 *
	 * @return The network graph.
	 */
	@RequestMapping(value = "/network/local", method = RequestMethod.GET)
	public GraphViewModel networkLocal() {
		return this.graphViewModelFactory.createViewModel(this.networkServices, this.nodeServices);
	}
}