package org.nem.monitor.visitors;

import org.hamcrest.core.*;
import org.junit.*;

public class NodeStatusDescriptionTest {

	@Test
	public void constructorCreatesDescriptorAroundParameters() {
		// Act:
		final NodeStatusDescription description = new NodeStatusDescription("status", "action");

		// Assert:
		Assert.assertThat(description.getStatusMessage(), IsEqual.equalTo("status"));
		Assert.assertThat(description.getActionMessage(), IsEqual.equalTo("action"));
	}

	//region equals / hashCode

	@Test
	public void equalsOnlyReturnsTrueForEquivalentObjects() {
		// Arrange:
		final NodeStatusDescription description = new NodeStatusDescription("status1", "action1");

		// Assert:
		Assert.assertThat(new NodeStatusDescription("status1", "action1"), IsEqual.equalTo(description));
		Assert.assertThat(new NodeStatusDescription("status1", "action2"), IsNot.not(IsEqual.equalTo(description)));
		Assert.assertThat(new NodeStatusDescription("status2", "action1"), IsNot.not(IsEqual.equalTo(description)));
		Assert.assertThat(null, IsNot.not(IsEqual.equalTo(description)));
		Assert.assertThat("action1", IsNot.not(IsEqual.equalTo((Object)description)));
	}

	@Test
	public void hashCodesAreEqualForEquivalentObjects() {
		// Arrange:
		final NodeStatusDescription description = new NodeStatusDescription("status1", "action1");
		final int hashCode = description.hashCode();

		// Assert:
		Assert.assertThat(new NodeStatusDescription("status1", "action1").hashCode(), IsEqual.equalTo(hashCode));
		Assert.assertThat(new NodeStatusDescription("status1", "action2").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
		Assert.assertThat(new NodeStatusDescription("status2", "action1").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
	}

	//endregion
}