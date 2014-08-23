package org.nem.monitor.ux;

import java.util.Objects;

/**
 * Simple class that describes an icon.
 */
public class IconDescriptor {
	private final String imageFileName;
	private final String description;

	/**
	 * Creates a new descriptor.
	 *
	 * @param imageFileName The image file name.
	 * @param description The description.
	 */
	public IconDescriptor(final String imageFileName, final String description) {
		this.imageFileName = imageFileName;
		this.description = description;
	}

	/**
	 * Gets the name of the icon's image.
	 *
	 * @return The name of the icon's image.
	 */
	public String getImageFileName() {
		return this.imageFileName;
	}

	/**
	 * Gets the icon's description.
	 *
	 * @return The icon's description.
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.imageFileName, this.description);
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof IconDescriptor)) {
			return false;
		}

		final IconDescriptor rhs = (IconDescriptor)obj;
		return Objects.equals(this.imageFileName, rhs.imageFileName) &&
				Objects.equals(this.description, rhs.description);
	}
}
