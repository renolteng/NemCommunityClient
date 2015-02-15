package org.nem.monitor.launcher;

import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.mockito.*;
import org.nem.monitor.node.NemNodeType;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JarNodeLauncherTest {

	@Test
	public void canLaunchNisNode() throws IOException{
		// Assert:
		final List<String> expectedArguments = Arrays.asList(
			"-Xms512M",
			"-Xmx1G",
			"-cp",
			".:./*:../libs/*",
			"org.nem.core.deploy.CommonStarter");
		assertCanLaunchNode(
				NemNodeType.NIS,
				expectedArguments,
				"nis-jar-path");	}

	@Test
	public void canLaunchNccNode() throws IOException{
		// Assert:
		final List<String> expectedArguments = Arrays.asList(
				"-cp",
				".:./*:../libs/*",
				"org.nem.core.deploy.CommonStarter");
		assertCanLaunchNode(
				NemNodeType.NCC,
				expectedArguments,
				"ncc-jar-path");
	}

	@SuppressWarnings("unchecked")
	private static void assertCanLaunchNode(
			final NemNodeType nodeType,
			final List<String> expectedArguments,
			final String expectedWorkingDirectory) throws IOException {
		// Arrange:
		final JavaProcessLauncher processLauncher = Mockito.mock(JavaProcessLauncher.class);
		final NodeLauncher launcher = new JarNodeLauncher(
				processLauncher,
				Paths.get("nis-jar-path", "nis.jar").toString(),
				Paths.get("ncc-jar-path", "nis.jar").toString());

		// Act:
		launcher.launch(nodeType);

		// Assert:
		final ArgumentCaptor<List<String>> argumentsCaptor = ArgumentCaptor.forClass((Class)List.class);
		final ArgumentCaptor<File> workingDirectoryCaptor = ArgumentCaptor.forClass(File.class);
		Mockito.verify(processLauncher, Mockito.only())
				.launch(argumentsCaptor.capture(), workingDirectoryCaptor.capture());

		// Assert:
		Assert.assertThat(argumentsCaptor.getValue(), IsEqual.equalTo(expectedArguments));
		Assert.assertThat(workingDirectoryCaptor.getValue().toString(), IsEqual.equalTo(expectedWorkingDirectory));
	}
}