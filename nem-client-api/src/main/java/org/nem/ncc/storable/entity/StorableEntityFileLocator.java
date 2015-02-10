package org.nem.ncc.storable.entity;

import org.nem.core.function.QuadFunction;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.ncc.storable.entity.storage.StorableEntityDescriptor;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A file-based StorableEntityLocator implementation.
 */
public class StorableEntityFileLocator<
		TEntity extends StorableEntity & ObjectDeserializer<TEntity>,
		TEntityName extends StorableEntityName,
		TEntityFileExtension extends StorableEntityFileExtension,
		TEntityDescriptor extends StorableEntityDescriptor<TEntity, TEntityName, TEntityFileExtension>>
		implements StorableEntityLocator {
	private final File directory;
	private final FilenameFilter filter;
	private final ObjectDeserializer<TEntity> deserializer;
	private final Function<String, TEntityName> nameActivator;
	private final Function<String, TEntityFileExtension> fileExtensionActivator;
	private final QuadFunction<
				File,
				ObjectDeserializer<TEntity>,
				Function<String, TEntityName>,
				Function<String, TEntityFileExtension>,
				TEntityDescriptor> descriptorActivator;

	/**
	 * Creates a new storable entity file locator.
	 *
	 * @param directory The search directory.
	 * @param filter The filename filter.
	 */
	public StorableEntityFileLocator(
			final File directory,
			final FilenameFilter filter,
			final ObjectDeserializer<TEntity> deserializer,
			final Function<String, TEntityName> nameActivator,
			final Function<String, TEntityFileExtension> fileExtensionActivator,
			final QuadFunction<
					File,
					ObjectDeserializer<TEntity>,
					Function<String, TEntityName>,
					Function<String, TEntityFileExtension>,
					TEntityDescriptor> descriptorActivator) {
		this.directory = directory;
		this.filter = filter;
		this.deserializer = deserializer;
		this.nameActivator = nameActivator;
		this.fileExtensionActivator = fileExtensionActivator;
		this.descriptorActivator = descriptorActivator;
	}

	@Override
	public List<TEntityDescriptor> getAll() {
		final String[] files = this.directory.list(this.filter);
		return Arrays.asList(files).stream()
				.map(f -> this.descriptorActivator.apply(new File(f), this.deserializer, this.nameActivator, this.fileExtensionActivator))
				.collect(Collectors.toList());
	}
}
