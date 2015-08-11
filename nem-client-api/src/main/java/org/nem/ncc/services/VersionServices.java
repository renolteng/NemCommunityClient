package org.nem.ncc.services;

import org.nem.core.time.TimeInstant;
import org.nem.core.time.TimeProvider;
import org.nem.ncc.connector.VersionProvider;
import org.nem.ncc.controller.viewmodels.VersionInformationViewModel;

// TODO 20150810 J-G: add comments + small test?

public class VersionServices {
	final VersionProvider versionProvider;
	final TimeProvider timeProvider;
	final int REFRESH_IN_SECOND = 5*60;
	TimeInstant refreshTime;
	VersionInformationViewModel versionInformationViewModel;

	public VersionServices(final VersionProvider versionProvider, final TimeProvider timeProvider)
	{
		this.versionProvider = versionProvider;
		this.timeProvider = timeProvider;

		this.refreshTime = TimeInstant.ZERO;
	}

	public VersionInformationViewModel getVersions() {
		final TimeInstant currentTime = this.timeProvider.getCurrentTime();
		if (currentTime.subtract(refreshTime) > this.REFRESH_IN_SECOND) {
			versionInformationViewModel = new VersionInformationViewModel(
					this.versionProvider.getLocalVersion(),
					this.versionProvider.getLatestVersion()
			);
		}
		return versionInformationViewModel;
	}
}
