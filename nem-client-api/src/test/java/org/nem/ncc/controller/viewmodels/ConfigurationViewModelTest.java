package org.nem.ncc.controller.viewmodels;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.SerializationException;
import org.nem.ncc.model.NisBootInfo;
import org.nem.ncc.test.*;

import java.util.*;

public class ConfigurationViewModelTest {
	private static final NodeEndpoint ENDPOINT = NodeEndpoint.fromHost("10.10.10.12");
	private static final NisBootInfo BOOT_INFO = new NisBootInfo(7, "1", "2");

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel("de-DE", ENDPOINT, BOOT_INFO);

		// Assert:
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(viewModel.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCanBeRoundTripped() {
		// Arrange:
		final ConfigurationViewModel originalViewModel = new ConfigurationViewModel("de-DE", ENDPOINT, BOOT_INFO);

		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel(Utils.roundtripSerializableEntity(originalViewModel, null));

		// Assert:
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(viewModel.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final List<ConfigurationViewModel> illegalViewModels = Arrays.asList(
				new ConfigurationViewModel(null, ENDPOINT, BOOT_INFO),
				new ConfigurationViewModel("de-DE", null, BOOT_INFO),
				new ConfigurationViewModel("de-DE", ENDPOINT, null));

		// Assert:
		for (final ConfigurationViewModel viewModel : illegalViewModels) {
			ExceptionAssert.assertThrows(
					v -> new ConfigurationViewModel(Utils.roundtripSerializableEntity(viewModel, null)),
					SerializationException.class);
		}
	}
}