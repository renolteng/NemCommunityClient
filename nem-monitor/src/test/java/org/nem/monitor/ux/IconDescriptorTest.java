package org.nem.monitor.ux;

import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.model.Address;

public class IconDescriptorTest {

	@Test
	public void constructorCreatesDescriptorAroundParameters() {
		// Act:
		final IconDescriptor descriptor = new IconDescriptor("source", "description");

		// Assert:
		Assert.assertThat(descriptor.getImageFileName(), IsEqual.equalTo("source"));
		Assert.assertThat(descriptor.getDescription(), IsEqual.equalTo("description"));
	}

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final IconDescriptor descriptor = new IconDescriptor("source1", "description1");

		// Assert:
		Assert.assertThat(new IconDescriptor("source1", "description1"), IsEqual.equalTo(descriptor));
		Assert.assertThat(new IconDescriptor("source2", "description1"), IsNot.not(IsEqual.equalTo(descriptor)));
		Assert.assertThat(new IconDescriptor("source1", "description2"), IsNot.not(IsEqual.equalTo(descriptor)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(descriptor)));
		Assert.assertThat("source1", IsNot.not(IsEqual.equalTo((Object)descriptor)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final IconDescriptor descriptor = new IconDescriptor("source1", "description1");
		final int hashCode = descriptor.hashCode();

		// Assert:
		Assert.assertThat(new IconDescriptor("source1", "description1").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new IconDescriptor("source2", "description1").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(new IconDescriptor("source1", "description2").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	//endregion
}