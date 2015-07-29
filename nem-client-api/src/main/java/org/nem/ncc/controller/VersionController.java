package org.nem.ncc.controller;

import org.nem.ncc.controller.viewmodels.VersionInformationViewModel;
import org.nem.ncc.services.VersionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	final VersionServices versionServices;

	@Autowired(required = true)
	public VersionController(final VersionServices versionServices) {
		this.versionServices = versionServices;
	}

	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public VersionInformationViewModel getVersion() {
		return versionServices.getVersions();
	}
}
