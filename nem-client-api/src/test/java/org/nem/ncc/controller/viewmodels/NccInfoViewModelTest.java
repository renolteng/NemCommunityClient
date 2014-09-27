package org.nem.ncc.controller.viewmodels;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.mockito.Mockito;
import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.time.*;
import org.nem.ncc.model.*;

public class NccInfoViewModelTest {
	private final static NodeEndpoint ENDPOINT = NodeEndpoint.fromHost("10.10.10.12");
	private final static NisBootInfo BOOT_INFO = new NisBootInfo(0, "aid", "nn");

	@Test
	public void viewModelCanBeCreated() {
		// Arrange:
		final Configuration configuration = new Configuration("de-DE", ENDPOINT, BOOT_INFO, "nem");
		final ApplicationMetaData metaData = new ApplicationMetaData("app", "ver", null, Mockito.mock(TimeProvider.class));

		// Act:
		final NccInfoViewModel viewModel = new NccInfoViewModel(metaData, configuration);

		// Assert:
		Assert.assertThat(viewModel.getMetaData(), IsSame.sameInstance(metaData));
		Assert.assertThat(viewModel.getRemoteServer(), IsEqual.equalTo("http://10.10.10.12:7890/"));
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
	}

	@Test
	public void viewModelCanBeSerialized() {
		// Arrange:
		final TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
		Mockito.when(timeProvider.getCurrentTime()).thenReturn(new TimeInstant(88));
		final Configuration configuration = new Configuration("de-DE", ENDPOINT, BOOT_INFO, "nem");
		final ApplicationMetaData metaData = new ApplicationMetaData("app", "ver", null, timeProvider);

		// Act:
		final NccInfoViewModel viewModel = new NccInfoViewModel(metaData, configuration);
		final JSONObject jsonObject = JsonSerializer.serializeToJson(viewModel);

		// Assert:
		Assert.assertThat(jsonObject.size(), IsEqual.equalTo(3));
		Assert.assertThat(((JSONObject)jsonObject.get("metaData")).get("version"), IsEqual.equalTo("ver"));
		Assert.assertThat(viewModel.getRemoteServer(), IsEqual.equalTo("http://10.10.10.12:7890/"));
		Assert.assertThat(jsonObject.get("language"), IsEqual.equalTo("de-DE"));
	}
}