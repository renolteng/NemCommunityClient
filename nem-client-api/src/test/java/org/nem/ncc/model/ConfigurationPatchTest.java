package org.nem.ncc.model;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.node.NodeEndpoint;

public class ConfigurationPatchTest {

	@Test
	public void canCreatePatch() {
		// Act:
		final ConfigurationPatch patch = new ConfigurationPatch();

		// Assert:
		Assert.assertThat(patch.getLanguage(), IsNull.nullValue());
		Assert.assertThat(patch.getNisEndpoint(), IsNull.nullValue());
		Assert.assertThat(patch.getNisBootInfo(), IsNull.nullValue());
	}

	@Test
	public void canSetPatchProperties() {
		// Act:
		final ConfigurationPatch patch = new ConfigurationPatch();
		patch.setLanguage("en-CA");
		patch.setNisEndpoint(NodeEndpoint.fromHost("127.0.0.1"));
		patch.setNisBootInfo(new NisBootInfo(12, "A", "C"));

		// Assert:
		Assert.assertThat(patch.getLanguage(), IsEqual.equalTo("en-CA"));
		Assert.assertThat(patch.getNisEndpoint(), IsEqual.equalTo(NodeEndpoint.fromHost("127.0.0.1")));
		Assert.assertThat(patch.getNisBootInfo().getBootStrategy(), IsEqual.equalTo(12));
	}
}