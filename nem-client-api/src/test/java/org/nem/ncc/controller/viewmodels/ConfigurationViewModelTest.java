package org.nem.ncc.controller.viewmodels;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.nem.core.serialization.SerializationException;
import org.nem.ncc.model.NisBootInfo;
import org.nem.ncc.test.*;

import java.util.*;

public class ConfigurationViewModelTest {

	@Test
	public void viewModelCanBeCreated() {
		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel("de-DE", new NisBootInfo(7, "a", "a", "a"));

		// Assert:
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCanBeRoundTripped() {
		// Arrange:
		final ConfigurationViewModel originalViewModel = new ConfigurationViewModel("de-DE", new NisBootInfo(7, "a", "a", "a"));

		// Act:
		final ConfigurationViewModel viewModel = new ConfigurationViewModel(Utils.roundtripSerializableEntity(originalViewModel, null));

		// Assert:
		Assert.assertThat(viewModel.getLanguage(), IsEqual.equalTo("de-DE"));
		Assert.assertThat(viewModel.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(7));
	}

	@Test
	public void viewModelCannotBeDeserializedWithMissingParameters() {
		// Arrange:
		final List<ConfigurationViewModel> illegalViewModels = Arrays.asList(
				new ConfigurationViewModel(null, new NisBootInfo(7, "a", "a", "a")),
				new ConfigurationViewModel("de-DE", null));

		// Assert:
		for (final ConfigurationViewModel viewModel : illegalViewModels) {
			ExceptionAssert.assertThrows(
					v -> new ConfigurationViewModel(Utils.roundtripSerializableEntity(viewModel, null)),
					SerializationException.class);
		}
	}
}