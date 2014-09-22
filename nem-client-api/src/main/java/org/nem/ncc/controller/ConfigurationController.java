package org.nem.ncc.controller;

import org.nem.ncc.controller.viewmodels.ConfigurationViewModel;
import org.nem.ncc.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigurationController {
	private final Configuration configuration;

	/**
	 * Creates a new configuration controller.
	 *
	 * @param configuration The configuration.
	 */
	@Autowired(required = true)
	public ConfigurationController(final Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Gets the current configuration.
	 *
	 * @return The current configuration.
	 */
	@RequestMapping(value = "/configuration/get", method = RequestMethod.GET)
	public ConfigurationViewModel getConfiguration() {
		return new ConfigurationViewModel(
				this.configuration.getLanguage(),
				this.configuration.getRemoteServer(),
				this.configuration.getNisBootInfo());
	}

	/**
	 * Updates the current configuration with the given values.
	 *
	 * @param configurationViewModel The updated configuration.
	 */
	@RequestMapping(value = "/configuration/update", method = RequestMethod.POST)
	public void updateConfiguration(@RequestBody final ConfigurationViewModel configurationViewModel) {
		this.configuration.update(
				configurationViewModel.getLanguage(),
				configurationViewModel.getRemoteServer(),
				configurationViewModel.getNisBootInfo());
	}
}
