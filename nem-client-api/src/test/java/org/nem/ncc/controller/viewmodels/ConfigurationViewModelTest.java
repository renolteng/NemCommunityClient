package org.nem.ncc.controller.viewmodels;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.SerializationException;
import org.nem.ncc.model.NisBootInfo;
import org.nem.ncc.test.*;

import java.util.*;

public class ConfigurationViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "a", "a"));

		// Assert:
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getRemoteServer().getBaseUrl().getHost(), IsEqual.equalTo("10.10.10.12"));
		Assert.assertThat(viewModel.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCanBeRoundTripped() {
		// Arrange:
		final ConfigurationViewModel originalViewModel = new ConfigurationViewModel("de-DE", NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "a", "a"));

		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel(Utils.roundtripSerializableEntity(originalViewModel, null));

		// Assert:
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getRemoteServer().getBaseUrl().getHost(), IsEqual.equalTo("10.10.10.12"));
		Assert.assertThat(viewModel.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final List<ConfigurationViewModel> illegalViewModels = Arrays.asList(
				new ConfigurationViewModel(null, NodeEndpoint.fromHost("10.10.10.12"), new NisBootInfo(7, "a", "a")),
				new ConfigurationViewModel("de-DE", NodeEndpoint.fromHost("10.10.10.12"), null));

		// Assert:
		for (final ConfigurationViewModel viewModel : illegalViewModels) {
			ExceptionAssert.assertThrows(
					v -> new ConfigurationViewModel(Utils.roundtripSerializableEntity(viewModel, null)),
					SerializationException.class);
		}
	}
}