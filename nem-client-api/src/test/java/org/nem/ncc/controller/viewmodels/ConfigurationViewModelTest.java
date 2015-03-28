package org.nem.ncc.controller.viewmodels;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.SerializationException;
import org.nem.ncc.model.*;
import org.nem.ncc.test.*;

import java.util.*;

public class ConfigurationViewModelTest {
	private static final NodeEndpoint ENDPOINT = NodeEndpoint.fromHost("10.10.10.12");
	private static final NisBootInfo BOOT_INFO = new NisBootInfo(7, "1", "2");

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final ConfigurationViewModel viewModel = createViewModel("de-DE", ENDPOINT, BOOT_INFO);

		// Assert:
		Assert.assertThat(viewModel.getPatch().getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getPatch().getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(viewModel.getPatch().getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCanBeRoundTripped() {
		// Arrange:
		final ConfigurationViewModel originalViewModel = createViewModel("de-DE", ENDPOINT, BOOT_INFO);

		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel(Utils.roundtripSerializableEntity(originalViewModel, null));

		// Assert:
		Assert.assertThat(viewModel.getPatch().getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getPatch().getNisEndpoint(), IsEqual.equalTo(ENDPOINT));
		Assert.assertThat(viewModel.getPatch().getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final List<ConfigurationViewModel> illegalViewModels = Arrays.asList(
				createViewModel(null, ENDPOINT, BOOT_INFO),
				createViewModel("de-DE", null, BOOT_INFO),
				createViewModel("de-DE", ENDPOINT, null));

		// Assert:
		for (final ConfigurationViewModel viewModel : illegalViewModels) {
			ExceptionAssert.assertThrows(
					v -> new ConfigurationViewModel(Utils.roundtripSerializableEntity(viewModel, null)),
					SerializationException.class);
		}
	}

	private static ConfigurationViewModel createViewModel(final String language, final NodeEndpoint nisEndpoint, final NisBootInfo nisBootInfo) {
		final ConfigurationPatch patch = new ConfigurationPatch();
		patch.setLanguage(language);
		patch.setNisEndpoint(nisEndpoint);
		patch.setNisBootInfo(nisBootInfo);
		return new ConfigurationViewModel(patch);
	}
}