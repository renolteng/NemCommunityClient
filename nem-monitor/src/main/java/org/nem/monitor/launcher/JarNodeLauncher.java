package org.nem.monitor.launcher;

import org.nem.core.utils.ExceptionUtils;
import org.nem.monitor.node.NemNodeType;

import java.nio.file.*;
import java.util.*;

/**
 * A JAR based node launcher.
 */
public class JarNodeLauncher implements NodeLauncher {
	private final JavaProcessLauncher launcher;
	private final String nisJarPath;
	private final String nccJarPath;

	/**
	 * Creates a new node launcher.
	 *
	 * @param launcher The launcher for launching a java process.
	 * @param nisJarPath The NIS jar path.
	 * @param nccJarPath The NCC jar path.
	 */
	public JarNodeLauncher(
			final JavaProcessLauncher launcher,
			final String nisJarPath,
			final String nccJarPath) {
		this.launcher = launcher;
		this.nisJarPath = nisJarPath;
		this.nccJarPath = nccJarPath;
	}

	@Override
	public void launch(final NemNodeType type) {
		final String jarPathString;
		final List<String> arguments = new ArrayList<>();
		switch (type) {
			case NIS:
				arguments.add("-Xms512M");
				arguments.add("-Xmx1G");

				jarPathString = this.nisJarPath;
				break;

			case NCC:
			default:
				jarPathString = this.nccJarPath;
				break;
		}

		final Path jarPath = Paths.get(jarPathString);
		final Path directory = jarPath.getParent();
		arguments.addAll(Arrays.asList(
				"-cp",
				".:./*:../libs/*",
				"org.nem.core.deploy.CommonStarter"));
		ExceptionUtils.propagateVoid(() -> {
			this.launcher.launch(arguments, directory.toFile());
		});
	}
}