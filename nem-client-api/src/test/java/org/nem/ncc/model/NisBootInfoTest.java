package org.nem.ncc.model;

import net.minidev.json.JSONObject;
import org.hamcrest.core.*;
import org.junit.*;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.ncc.test.Utils;

public class NisBootInfoTest {

	@Test
	public void bootInfoCanBeCreated() {
		// Act:
		final NisBootInfo bootInfo = this.createBootInfoFromJson(7, "rs", "aid", "nn");

		// Assert:
		Assert.assertThat(bootInfo.getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(bootInfo.getRemoteServer(), IsEqual.equalTo("rs"));
		Assert.assertThat(bootInfo.getAccountId(), IsEqual.equalTo("aid"));
		Assert.assertThat(bootInfo.getNodeName(), IsEqual.equalTo("nn"));
	}

	@Test
	public void localBootInfoCanBeCreated() {
		// Act:
		final NisBootInfo bootInfo = NisBootInfo.createLocal();

		// Assert:
		Assert.assertThat(bootInfo.getBootStrategy(), IsEqual.equalTo(NisBootInfo.BOOT_STRATEGY_BOOT));
		Assert.assertThat(bootInfo.getRemoteServer(), IsNull.nullValue());
		Assert.assertThat(bootInfo.getAccountId(), IsNull.nullValue());
		Assert.assertThat(bootInfo.getNodeName(), IsNull.nullValue());
	}

	@Test
	public void bootInfoCanBeRoundTripped() {
		// Arrange:
		final NisBootInfo originalBootInfo = new NisBootInfo(7, "rs", "aid", "nn");

		// Act:
		final NisBootInfo bootInfo = new NisBootInfo(Utils.roundtripSerializableEntity(originalBootInfo, null));

		// Assert:
		Assert.assertThat(bootInfo.getBootStrategy(), IsEqual.equalTo(7));
		Assert.assertThat(bootInfo.getRemoteServer(), IsEqual.equalTo("rs"));
		Assert.assertThat(bootInfo.getAccountId(), IsEqual.equalTo("aid"));
		Assert.assertThat(bootInfo.getNodeName(), IsEqual.equalTo("nn"));
	}

	@Test
	public void localBootInfoCanBeRoundTripped() {
		// Arrange:
		final NisBootInfo originalBootInfo = NisBootInfo.createLocal();

		// Act:
		final NisBootInfo bootInfo = new NisBootInfo(Utils.roundtripSerializableEntity(originalBootInfo, null));

		// Assert:
		Assert.assertThat(bootInfo.getBootStrategy(), IsEqual.equalTo(NisBootInfo.BOOT_STRATEGY_BOOT));
		Assert.assertThat(bootInfo.getRemoteServer(), IsNull.nullValue());
		Assert.assertThat(bootInfo.getAccountId(), IsNull.nullValue());
		Assert.assertThat(bootInfo.getNodeName(), IsNull.nullValue());
	}

	@Test
	public void bootInfoCanBeDeserializedFromEmptyJsonObject() {
		// Act:
		final NisBootInfo bootInfo = this.createBootInfoFromJson(null, null, null, null);

		// Assert:
		Assert.assertThat(bootInfo.getBootStrategy(), IsEqual.equalTo(NisBootInfo.BOOT_STRATEGY_NO_BOOT));
		Assert.assertThat(bootInfo.getRemoteServer(), IsNull.nullValue());
		Assert.assertThat(bootInfo.getAccountId(), IsNull.nullValue());
		Assert.assertThat(bootInfo.getNodeName(), IsNull.nullValue());
	}

	@Test
	public void isNisLocalReturnsFalseWhenRemoteServerIsNotWhitespace() {
		// Assert:
		Assert.assertThat(new NisBootInfo(1, "rs", "aid", "nn").isNisLocal(), IsEqual.equalTo(false));
	}

	@Test
	public void isNisLocalReturnsTrueWhenRemoteServerIsWhitespace() {
		// Assert:
		for (final String remoteServer : new String[] { null, "", "  \t\t " }) {
			Assert.assertThat(new NisBootInfo(1, remoteServer, "aid", "nn").isNisLocal(), IsEqual.equalTo(true));
		}
	}

	private NisBootInfo createBootInfoFromJson(
			final Integer bootStrategy,
			final String remoteServer,
			final String accountId,
			final String nodeName) {
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("bootNis", bootStrategy);
		jsonObject.put("remoteServer", remoteServer);
		jsonObject.put("account", accountId);
		jsonObject.put("nodeName", nodeName);
		return new NisBootInfo(new JsonDeserializer(jsonObject, null));
	}
}
